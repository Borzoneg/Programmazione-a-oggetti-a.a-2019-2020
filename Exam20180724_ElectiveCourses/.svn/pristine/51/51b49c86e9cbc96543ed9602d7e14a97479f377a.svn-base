package it.polito.oop.elective;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private String name;
    private int posti;
    private Long preferenze[]  = {new Long(0), new Long(0), new Long(0)};
    
    public Course(String name, int posti) {
	super();
	this.name = name;
	this.posti = posti;
    }
    
    public String getName() {
	return name;
    }
    
    public void sceltoCome(int classificato) {
	preferenze[classificato] ++;
    }
    
    public List<Long> getPreferenze(){
	List<Long> result = new ArrayList<>();
	for(Long l: preferenze)
	    result.add(l);
	return result;
    }
}
