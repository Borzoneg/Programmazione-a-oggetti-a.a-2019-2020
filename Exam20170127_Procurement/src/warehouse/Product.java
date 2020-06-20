package warehouse;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String code;
    private String description;
    private int quantity = 0;
    private List<Supplier> suppliers = new ArrayList<>(); 
    private List<Order> orders = new ArrayList<>();

    public Product(String code, String description) {
	this.code = code;
	this.description = description;
    }

    public String getCode(){		
	return code;
    }

    public String getDescription(){
	return description;
    }

    public void setQuantity(int quantity){
	this.quantity = quantity;
    }

    public void decreaseQuantity(){
	quantity--;
    }

    public int getQuantity(){
	return quantity;
    }

    public List<Supplier> suppliers(){
	suppliers.sort((s1, s2) -> s1.getNome().compareTo(s2.getNome()));
	return suppliers;
    }

    public List<Order> pendingOrders(){
	List<Order> result = new ArrayList<>();
	for(Order o : orders) {
	    if(! o.delivered())
		result.add(o);
	}
	result.sort((o1, o2) -> o2.getQuantity()- o1.getQuantity());
	return result;
    }

    public void addSupplier(Supplier supplier) {
	suppliers.add(supplier);
    }
    
    public boolean suppliedBy(Supplier supplier) {
	if(suppliers.contains(supplier))
	    return true;
	return false;
    }

    public void newOrder(Order o) {
	orders.add(o);
    }
}
