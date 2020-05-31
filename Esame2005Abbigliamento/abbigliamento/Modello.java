package abbigliamento;

public class Modello {
    private String name;
    private double costoFisso;
    private double quantitaTessuto;

    public Modello(String name, double costoFisso, double quantitaTessuto) {
	this.name = name;
	this.costoFisso = costoFisso;
	this.quantitaTessuto = quantitaTessuto;
    }

    public String getNome(){
	return name;
    }
    public double getCosto(){
	return costoFisso;
    }

    public double getQuantita() {
	return quantitaTessuto;
    }

}
