package it.polito.oop.futsal;

public class Associate {

    private String name;
    private String surname;
    private String phone;
    private int bookings = 0;

    public Associate(String name, String surname, String phone) {
	this.name = name;
	this.surname = surname;
	this.phone = phone;
    }

    public String getName() {
	return name;
    }

    public String getSurname() {
	return surname;
    }

    public String getPhone() {
	return phone;
    }

    public void book() {
	bookings++;
    }
    
    public int getBookings() {
	return bookings;
    }

}
