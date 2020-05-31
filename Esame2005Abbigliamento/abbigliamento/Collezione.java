package abbigliamento;

import java.util.ArrayList;
import java.util.Collection;

public class Collezione {

    private ArrayList<Capo> collection = new ArrayList<>();
    
    public void add(Capo capo) {
	collection.add(capo);
    }

    public Collection trova(Colore colore) {
	ArrayList<Capo> toReturn = new ArrayList<>();
	for(Capo c : collection) {
	    if(c.getColore().getNome().equals(colore.getNome()))
		toReturn.add(c);
	}
	return toReturn;
    }

    public Collection trova(Materiale materiale) {
	ArrayList<Capo> toReturn = new ArrayList<>();
	for(Capo c : collection) {
	    if(c.getMateriale().getNome().equals(materiale.getNome()))
		toReturn.add(c);
	}
	return toReturn;
    }

    public Collection trova(Modello modello) {
	ArrayList<Capo> toReturn = new ArrayList<>();
	for(Capo c : collection) {
	    if(c.getModello().getNome().equals(modello.getNome()))
		toReturn.add(c);
	}
	return toReturn;
    }

}
