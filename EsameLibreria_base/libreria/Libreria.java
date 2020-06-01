package libreria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Libreria {

    Map<String, Editore> editori = new TreeMap<>();
    ArrayList<Libro> libri = new ArrayList<>();
    
    public Editore creaEditore(String nome, int tempoConsegna, String email){
	Editore editore = new Editore(nome, tempoConsegna, email);
	editori.put(nome, editore);
	return editore;
    }

    public Editore getEditore(String nome){
	 if(editori.containsKey(nome))
	            return editori.get(nome);
	return null;
    }

    public Collection getEditori(){
        return (Collection) editori;
    }

    public Libro creaLibro(String titolo, String autore, int anno, double prezzo, String nomeEditore) throws EditoreInesistente {
	 if(!editori.containsKey(nomeEditore))
            throw new EditoreInesistente(nomeEditore);
	 Libro libro = new Libro(titolo, autore, anno, prezzo, editori.get(nomeEditore));
	 libri.add(libro);
	return libro;
    }
    
    public Libro getLibro(String autore, String titolo){
	for(Libro l : libri)
	    if(l.getAutore().equals(autore) || l.getTitolo().equals(titolo))
		return l;
	return null;
    }
    
    public Collection getClassificaSettimana(final int settimana){
        return null;
    }
    
    public Collection getClassificaMese(final int mese){
        return null;
    }
    
    public Collection getOrdini(){
        return null;
    }
    
    public void ordineRicevuto(int numOrdine){
    }
    
    public void leggi(String file){
    }
}
