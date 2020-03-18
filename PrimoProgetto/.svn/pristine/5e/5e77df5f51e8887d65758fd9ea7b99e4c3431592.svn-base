import it.polito.po2020.esempi.base.Car;
import it.polito.po2020.esempi.base.Truck;
// EQUIVALENTI A
import it.polito.po2020.esempi.base.*;

//import it.polito.po2020.esempi.base.sotto.Car;
//import java.lang.*;  // IMPLICITO

public class Programma {
	int attributo;
	
	public Programma() {
		attributo = 42;
	}

	public static void main(String[] args) {
		
		
		it.polito.po2020.esempi.base.sotto.Car XX = new it.polito.po2020.esempi.base.sotto.Car();
		
		Car c; // riferimento
		
		int i; // variabile primitiva
		
		//i++;
		
		//c.paint("white");
		
		c = new Car();
		
		Car d = new Car();
		
		//Car d = c;
		
//		boolean stessoOggetto = ( c == d );
//		
//		System.out.println("stessoOggetto");
//		System.out.println(stessoOggetto);
//		
//		boolean uguali = c.uguali(d);
//		
//		System.out.println("Gli oggetti sono uguali? " + uguali + ".");
//
//		Programma p = new Programma();
//		p=null;
//		System.gc();
//		
//		
		// Visibilità e incapsulamento
		
		//c.colore = "Pippo";
		c.paint("Nero");
		
		System.out.println("Colore dell'auto: " + c.getColore());
		
		coloraDiBianco(c);
		
		System.out.println("Colore dell'auto: " + c.getColore());
		
		// Visibilità di package
		
		Truck t;
	}
	
	public static void coloraDiBianco(Car a) {
		a.paint("Bianco");
	}
	
	public void finalize() {
		System.out.println("Deallocato oggetto Programma");
	}

}
