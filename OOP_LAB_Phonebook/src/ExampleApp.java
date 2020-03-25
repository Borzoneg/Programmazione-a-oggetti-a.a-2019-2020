import phonebook.*;

public class ExampleApp {
	
	public static void main(String[] args) {
		
		PhoneBook pb = new PhoneBook("Personal book");
		System.out.println( pb.getName() );
		
		pb.add("Alice", "Green", "555-1234");
		pb.add("Mary", "Smith", "555-6784");
		pb.add("Bob", "Moore", "555-9756");

		System.out.println( pb.toString() );

		System.out.println( pb.first() );

		System.out.println( pb.get(2) );

		String toBeFound = new String("Moo");
		System.out.println( pb.find(toBeFound) );	}
}
