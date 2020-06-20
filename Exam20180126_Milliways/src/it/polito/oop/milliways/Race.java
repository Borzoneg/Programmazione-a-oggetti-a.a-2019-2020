package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.List;

public class Race {
    
    private String name;
    private List<String> requirements = new ArrayList<>();
    private int n = 0;
    
    public Race(String name) {
	this.name = name;
    }

    public void addRequirement(String requirement) throws MilliwaysException {
	if(requirements.contains(requirement))
	    throw new MilliwaysException();
	requirements.add(requirement);
    }

    public List<String> getRequirements() {
	requirements.sort((s1, s2) -> s1.compareTo(s2));
	return requirements;
    }

    public String getName() {
	return name;
    }
    
    public void addN(int n) {
	this.n += n;
    }
    
    public int getN() {
	return n;
    }
}
