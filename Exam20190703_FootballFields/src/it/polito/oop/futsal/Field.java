package it.polito.oop.futsal;
import java.util.HashMap;
import java.util.Map;

public class Field implements FieldOption{

    private int id;
    private boolean heat;
    private boolean ac;
    private boolean indoor;
    private Map<String, Associate> bookings = new HashMap<>();
    
    
    public Field(boolean heat, boolean ac, boolean indoor, int id) {
	this.id = id;
	this.heat = heat;
	this.ac = ac;
	this.indoor = indoor;
    }
    
    public boolean isHeat() {
        return heat;
    }

    public boolean isAc() {
        return ac;
    }
    
    public boolean isIndoor() {
        return indoor;
    }

    public boolean isBooked(String time) {
	if(bookings.containsKey(time))
	    return true;
	return false;
    }
    
    public void book(String time, Associate associate) {
	bookings.put(time, associate);	
    }

    public int getOccupation() {
	return bookings.size();
    }

    @Override
    public int getField() {
	return id;
    }

    
}
