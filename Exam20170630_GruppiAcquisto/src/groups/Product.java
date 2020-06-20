package groups;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String name;
    private List<Supplier> suppliers = new ArrayList<>();
    
    public Product(String name) {
	this.name = name;
    }
    
    
    public void addSupplier(Supplier supplier) {
	suppliers.add(supplier);
    }

    public String getName(){
	return name;
    }
}
