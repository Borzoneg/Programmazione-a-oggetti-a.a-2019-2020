package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.List;

public class Hall {

    private int id;
    private List<String> facilities = new ArrayList<>();
    private List<Party> partiesSeated = new ArrayList<>();

    public Hall(int id) {
	this.id = id;
    }

    public int getId() {
	return id;
    }

    public void addFacility(String facility) throws MilliwaysException {
	if(facilities.contains(facility))
	    throw new MilliwaysException();
	facilities.add(facility);
    }

    public List<String> getFacilities() {
	facilities.sort((s1, s2) -> s1.compareTo(s2));
	return facilities;
    }

    public int getNumFacilities(){
	return facilities.size();
    }

    public boolean isSuitable(Party party) {
	for(String r : party.getRequirements()) {
	    if(! facilities.contains(r))
		return false;
	}
	return true;
    }

    public void seat(Party party) {
	partiesSeated.add(party);
	party.updateRaces();
    }

}
