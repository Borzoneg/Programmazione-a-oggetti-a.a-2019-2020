package managingProperties;

import java.util.ArrayList;

public class Owner {

    private String name;
    private ArrayList<String> apartements = new ArrayList<>();
    private int charges = 0;
    
    
    public Owner(String name) {
	this.name = name;
    }
    
    
    public String getName() {
	return name;
    }    

    
    public int getCharges() {
	return charges;
    }


    public void addApartement(String s) {
	apartements.add(s);
    }

    
    public boolean possess(String apartment) {
	if(apartements.contains(apartment))
	    return true;
	return false;
    }

    
    public void addCharge(int amount) {
	charges += amount;
    }

}
