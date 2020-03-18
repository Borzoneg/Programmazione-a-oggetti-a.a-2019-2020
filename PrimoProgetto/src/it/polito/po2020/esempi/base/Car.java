package it.polito.po2020.esempi.base;

public class Car {
	// Attributi
	private String colore;
	private String marca;
	private boolean acceso;

	// Costruttore
	public Car() {
		colore = "Rosso";
		marca = "Ferrari";
		marca = new String("Ferrari");
		acceso = false;
	}
	
	public Car(Car origine) {
		this.colore = origine.colore;
		// EQUIVALE A
		colore = origine.colore;
		
		this.marca = origine.marca;
		this.acceso = origine.acceso;
	}
	
	// Metodi
	/**
	 * Metodo che permette di impostare il colore dell'auto.
	 * Il metodo accetta solo colori validi.
	 * 
	 * @param newCol il nuovo colore dell'auto.
	 */
	public void paint(String newCol) {
		if(newCol.equals("Bianco") ||
				newCol.equals("Nero") ||
				newCol.equals("Rosso") ) {
			colore = newCol;
			// equivale a
			this.colore = newCol;
		}else {
			System.out.println("Colore errato!");
		}
	}
	
	public String getColore() {  // GETTER
		return colore;
	}
	
	public void setMarca(String newMarca) {  // SETTER
		this.marca = newMarca;
	}
						
	public boolean uguali(Car altraAuto) {
		return //this.colore == altraAuto.colore
				this.colore.equals(altraAuto.colore)
				&& this.marca == altraAuto.marca
				&& this.acceso == altraAuto.acceso;
	}
}
