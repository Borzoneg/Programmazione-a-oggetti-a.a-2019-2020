package phonebook;

public class Contatto {
	private String name;
	private String surname;
	private String phone;
	
	Contatto(String name, String surname, String phone){
		this.name = name;
		this.surname = surname;
		this.phone= phone;
	}
	
	
	public String toString() {
		return name + " " + surname + " " + phone;
	}
	
	
	public String getName() {
		return name;
	}


	public boolean contiene(String needle) {
		return name.contains(needle) ||
				surname.contains(needle) ||
				phone.contains(needle);
	}
}
