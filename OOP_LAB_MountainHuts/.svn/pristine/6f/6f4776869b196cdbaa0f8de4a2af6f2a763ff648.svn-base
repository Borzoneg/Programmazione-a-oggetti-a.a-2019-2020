package mountainhuts;

import java.util.ArrayList;

public class Province {

    private String name;
    private ArrayList<Municipality> municipalities = new ArrayList<>();
    
    public Province(String name) {
	this.name = name;
    }
    
    public void addIfNew(Municipality municipality) {
	if(!municipalities.contains(municipality))
	    municipalities.add(municipality);
    }

}
