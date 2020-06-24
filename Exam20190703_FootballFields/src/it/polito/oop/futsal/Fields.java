package it.polito.oop.futsal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a infrastructure with a set of playgrounds, it allows teams
 * to book, use, and  leave fields.
 *
 */
public class Fields {

    private List<Field> fields = new ArrayList<>();
    private String closingTime;
    private String openingTime;
    private List<Associate> associates = new ArrayList<>();
    private List<Boolean> bookings = new ArrayList<>();

    public static class Features {
	public final boolean indoor; // otherwise outdoor
	public final boolean heating;
	public final boolean ac;
	public Features(boolean i, boolean h, boolean a) {
	    this.indoor=i; this.heating=h; this.ac = a;
	}
    }

    public void defineFields(Features... features) throws FutsalException {
	for(Features f : features) {
	    if((f.ac || f.heating) && !f.indoor)
		throw new FutsalException();
	    Field field = new Field(f.heating, f.ac, f.indoor, fields.size());
	    fields.add(field);
	}
    }

    public long countFields() {
	return fields.size();
    }

    public long countIndoor() {
	long result = (long)0;
	for(Field f : fields) {
	    if(f.isIndoor())
		result++;
	}
	return result;
    }

    public String getOpeningTime() {
	return openingTime;
    }

    public void setOpeningTime(String time) {
	openingTime = time;
    }

    public String getClosingTime() {
	return closingTime;
    }

    public void setClosingTime(String time) {
	closingTime = time;
    }

    public int newAssociate(String first, String last, String mobile) {
	Associate a = new Associate(first, last, mobile);
	associates.add(a);
	return associates.size();
    }

    public String getFirst(int associate) throws FutsalException {
	if(associate < 0 || associate > associates.size())
	    throw new FutsalException();
	return associates.get(associate-1).getName();
    }

    public String getLast(int associate) throws FutsalException {
	if(associate < 0 || associate > associates.size())
	    throw new FutsalException();
	return associates.get(associate-1).getSurname();
    }

    public String getPhone(int associate) throws FutsalException {
	if(associate < 0 || associate > associates.size())
	    throw new FutsalException();
	return associates.get(associate-1).getPhone();
    }

    public int countAssociates() {
	return associates.size();
    }

    public void bookField(int field, int associate, String time) throws FutsalException {
	if(field < 0 || field > fields.size())
	    throw new FutsalException();
	if(associate < 0 || associate > associates.size())
	    throw new FutsalException();
	if((Integer.valueOf(time.split(":")[0]) < Integer.valueOf(openingTime.split(":")[0])) || // caso: opening time = 12:30 time = 11:XX
		(Integer.valueOf(time.split(":")[0]) == Integer.valueOf(openingTime.split(":")[0]) && Integer.valueOf(time.split(":")[1]) < Integer.valueOf(openingTime.split(":")[1])) || // caso: opening time = 12:30 time = 12:20
		(Integer.valueOf(time.split(":")[0]) >= Integer.valueOf(closingTime.split(":")[0])) || // caso: closing time = 17:00 time = 17:xx; closing time = 17:00 time = 18:xx 
		(Integer.valueOf(time.split(":")[1]) != Integer.valueOf(closingTime.split(":")[1])) ||  // caso: closing time = 17:00 time = 12:30
		(Integer.valueOf(time.split(":")[1]) != Integer.valueOf(openingTime.split(":")[1]))) // caso minuti apertura e
	    throw new FutsalException();
	associates.get(associate-1).book();
	fields.get(field-1).book(time, associates.get(associate-1));
    }

    public boolean isBooked(int field, String time) {
	return fields.get(field-1).isBooked(time);
    }


    public int getOccupation(int field) {
	return fields.get(field-1).getOccupation();
    }


    public List<FieldOption> findOptions(String time, Features required){
	List<FieldOption> result = new ArrayList<>();
	fields.stream().forEach(f -> {
	    if((!f.isBooked(time))) {
		if((required.ac && f.isAc()) || (required.heating && f.isHeat()) || (required.indoor && f.isIndoor()) || (!required.ac) || (!required.indoor) || (!required.heating))
		result.add(f);
	    }
	    });
	result.sort((f1, f2) -> f1.getOccupation() == f2.getOccupation() ? f1.getField() - f2.getField() : f2.getOccupation() - f1.getOccupation());
	return result;
    }

    public long countServedAssociates() {
	long result = 0;
	for(Associate a :associates)
	    result = a.getBookings()>0 ? result+1: result;
	return result;
    }

    public Map<Integer,Long> fieldTurnover() {
	Map<Integer,Long> result = new HashMap<>();
	for(Field f : fields) {
	    result.put(f.getField(), (long) f.getOccupation());
	}
	return result;
    }

    public double occupation() {
	double nPrenotazioni = 0, blocchi = 0, hApertura = Integer.valueOf(openingTime.split(":")[0]), hChiusura = Integer.valueOf(closingTime.split(":")[0]);
	for(Field f : fields) 
	    nPrenotazioni += f.getOccupation();
	while(hApertura != hChiusura) {
	    blocchi ++;
	    hApertura++;
	}
	return nPrenotazioni/(blocchi*fields.size());
    }

}
