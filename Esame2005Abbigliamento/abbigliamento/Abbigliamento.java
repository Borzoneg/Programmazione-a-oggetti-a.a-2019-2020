package abbigliamento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Abbigliamento {
    public Map<String, Capo> capi  = new HashMap<>();
    public Map<String, Colore> colori  = new HashMap<>();
    public Map<String, Modello> modelli = new HashMap<>();
    public Map<String, Materiale> materiali = new HashMap<>();
    public ArrayList<Collezione> collezioni = new ArrayList<>();

    public void leggiFile(String fileName){
	try {
	    BufferedReader src = new BufferedReader(new FileReader(fileName)); 
	    src.lines().forEach(l -> {
		String fields[] = l.split(";");
		switch(fields[0].trim()) {
		case "MOD":
		    Modello modello = new Modello(fields[1], Double.valueOf(fields[2]), Double.valueOf(fields[3]));
		    modelli.put(modello.getNome(), modello);
		    break;
		case "COL":		  
		    Colore colore = new Colore(fields[1]);		
		    colori.put(colore.getNome(), colore);
		    break;
		case "MAT":
		    Materiale materiale = new Materiale(fields[1], Double.valueOf(fields[2]));
		    materiali.put(materiale.getNome(), materiale);
		    break;
		case "CAP":
		    Capo capo = new Capo(modelli.get(fields[2]), materiali.get(fields[3]), colori.get(fields[4]));
		    capi.put(capo.toString(), capo);
		    break;
		case "COZ":
		    Collezione collezione = new Collezione();
		    for(String field : fields) {
			collezione.add(capi.get(field));
		    }
		    collezioni.add(collezione);
		    break;
		}
	    });


	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }

    public Modello getModello(String name){
	return modelli.get(name);
    }

    public Colore getColore(String name){
	return colori.get(name);
    }

    public Materiale getMateriale(String name){
	return materiali.get(name);
    }

    public Capo getCapo(String name){
	return capi.get(name);
    }

    public Collezione getCollezione(String name){
	return null;
    }

}
