import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Fumetto uno = new Fumetto("Spider-man", 6, 5, 0);
		uno.show();
		uno.setPrice(10);
		System.out.println(uno.getPrice());
		Scanner s = new Scanner("123");
		System.out.println(s.nextInt());
	}

}
