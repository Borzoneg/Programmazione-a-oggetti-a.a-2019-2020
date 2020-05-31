package prove;

import abbigliamento.*;

public class Test {
    
    public static void main(String[] args) {
	Colore rosso = new Colore("rosso");
	Colore verde = new Colore("verde");
	Colore giallo = new Colore("giallo");
	Materiale cotone = new Materiale("cotone", 5);
	Materiale lana = new Materiale("lana", 10);
	Materiale seta = new Materiale("seta", 15);
	Modello tshirt = new Modello("t-shirt", 5, 2);
	Modello felpa = new Modello("felpa", 8, 4);
	Modello polo = new Modello("polo", 6, 3);
	
	Capo capo = new Capo(tshirt, cotone, rosso);
	System.out.println(capo.toString());
    }

}
