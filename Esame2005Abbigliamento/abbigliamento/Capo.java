package abbigliamento;

public class Capo {

    private Modello modello;
    private Materiale materiale;
    private Colore colore;

    public Capo(Modello modello, Materiale materiale, Colore colore) {
	this.modello = modello;
	this.materiale = materiale;
	this.colore= colore;
    }

    public double prezzo() {
	return modello.getCosto() + modello.getQuantita() * materiale.getCosto();
    }
    
    public String toString() {
	return String.format("%s %s di %s", modello.getNome(), colore.getNome(), materiale.getNome());
    }

}
