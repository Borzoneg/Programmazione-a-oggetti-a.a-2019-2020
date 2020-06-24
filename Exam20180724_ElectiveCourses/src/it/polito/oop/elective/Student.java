package it.polito.oop.elective;

import java.util.ArrayList;
import java.util.List;

public class Student {
    
    private String id;
    private double media;
    private List<Course> courses = new ArrayList<>();
    
    public Student(String id, double media) {
	super();
	this.id = id;
	this.media = media;
    }
    
    public void setMedia(double media) {
	this.media = media;
    }

    public double getAvg() {
	return media;
    }
    
    public void setChoices(List<Course> courses) {
	this.courses = courses;
    }
    
    
}
