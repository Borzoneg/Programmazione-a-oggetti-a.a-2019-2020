package groups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Supplier {

    private Set<Product> products = new TreeSet<>((p1, p2) -> p1.getName().compareTo(p2.getName()));
    private String name;
    private Map<String, Bid> bids = new HashMap<>();
    
    public Supplier(String name) {
	this.name = name;
    }
    	
    
    public void addProduct(Product product) {
	products.add(product);
    }


    public List<String> getProducts() {
	List<String> result = new ArrayList<>();
	products.stream().forEach(p -> result.add(p.getName()));
	return result;
    }
 
    
    public Bid addBid(int price, Group group, Product product) {
	if(bids.containsKey(group.getName())) {
	    bids.get(group.getName()).setPrice(price);
	    return bids.get(group.getName());
	}
	Bid b  = new Bid(this, group, price, product);
	bids.put(group.getName(), b);
	return b;
    }


    public String getName() {
	return name;
    }
}
