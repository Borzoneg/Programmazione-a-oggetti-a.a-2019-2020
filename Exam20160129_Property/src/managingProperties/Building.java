package managingProperties;

import java.util.Map;
import java.util.TreeMap;

public class Building {

    private String name;
    private int nApartements;
    private Owner owners[]; // owner at i-th position is the owner of the i-th apartement of the building
    private Map <String ,Integer> charges = new TreeMap<>();;

    public Building(String building, int n) {
	name = building;
	nApartements = n;
	owners = new Owner[n];
    }

    public Integer getNApartement() {
	return nApartements;
    }


    public Owner ownerOf(Integer n) {
	return owners[n];
    }


    public void setOwner(Owner owner, int nApartement) {
	owners[nApartement] = owner;
    }

    public String getName() {
	return name;
    }

    public void addCharge(int amount, String professional) {
	charges.put(professional, charges.containsKey(professional) ? charges.get(professional) + amount : amount);

    }

    public Map<String, Integer> getCharges() {
	return charges;
    }

}
