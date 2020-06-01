package libreria;

public class Libro {
private String titolo;
private String autore;
private int anno;
private double prezzo;
private int quantita;
private int soglia;
private Editore editore;

    public Libro(String titolo, String autore, int anno, double prezzo, Editore editore) {
	this.titolo = titolo;
	this.autore = autore;
	this.anno = anno;
	this.prezzo = prezzo;
	this.editore = editore;    
}

    public String getTitolo(){
        return titolo;
    }
    
    public String getAutore(){
        return autore;
    }
    
    public int getAnno(){
        return anno;
    }

    public double getPrezzo(){
        return prezzo;
    }
    
    public Editore getEditore(){
        return editore;
    }

    public void setQuantita(int q){     
	this.quantita = q;
    }
    
    public int getQuantita(){
        return quantita;	
    }

    public void registraVendita(int settimana, int mese){
    }
    

    public void setParametri(int soglia, int quantitaRiordino){   
    }
}
