package abbigliamento;

public class Materiale {
    	private String name;
    	private double costo;
	
    	public Materiale(String name, double costo) {		
		this.name = name;
		this.costo = costo;
	}

	public String getNome(){
		return name;
	}

	public double getCosto(){
		return costo;
	}
}
