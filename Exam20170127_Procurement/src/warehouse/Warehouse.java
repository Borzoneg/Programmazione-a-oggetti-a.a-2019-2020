package warehouse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Warehouse {

    private Map<String, Product> products = new TreeMap<>();
    private Map<String, Supplier> suppliers = new TreeMap<>();
    private Map<String, Order> orders = new TreeMap<>();

    public Product newProduct(String code, String description){
	Product p  = new Product(code, description);
	products.put(code, p);
	return p;
    }

    public Collection<Product> products(){
	return products.values();
    }

    public Product findProduct(String code){
	return products.get(code);
    }

    public Supplier newSupplier(String code, String name){
	Supplier s = new Supplier(code, name);
	suppliers.put(code, s);
	return s;
    }

    public Supplier findSupplier(String code){
	return suppliers.get(code);
    }

    public Order issueOrder(Product prod, int quantity, Supplier supp) throws InvalidSupplier {
	if(!prod.suppliedBy(supp))
	    throw new InvalidSupplier();

	String code =  "ORD" + String.valueOf(orders.size() +1);
	Order o = new Order(prod, quantity, supp, code);
	orders.put(code, o);
	prod.newOrder(o);
	return o;
    }

    public Order findOrder(String code){
	return orders.get(code);
    }

    public List<Order> pendingOrders(){
	List<Order> result = orders.values().stream().filter(o -> ! o.delivered()).collect(Collectors.toList());
	result.sort((o1, o2) -> o1.getProdCode().compareTo(o2.getProdCode()));
	return result;
    }

    public Map<String,List<Order>> ordersByProduct(){
	Map<String,List<Order>> result = new HashMap<>();
	orders.
	values().
	stream().
	forEach(o -> {
	    if(result.containsKey(o.getProdCode())) {
		List<Order> tmp = result.get(o.getProdCode());
		tmp.add(o);
		result.put(o.getProdCode(), tmp);
	    }
	    else {
		List<Order> tmp = new ArrayList<>();
		tmp.add(o);
		result.put(o.getProdCode(), tmp);
	    }
	});      
	return result;
    }

    public Map<String,Long> orderNBySupplier(){
	Map<String,Long> result = new TreeMap<>();
	orders.
	values().
	stream().
	forEach(o -> {
	    if(o.delivered()) {
		if(result.containsKey(o.getSupplier().getNome()))
		    result.put(o.getSupplier().getNome(), result.get(o.getSupplier().getNome()) + 1);
		else
		    result.put(o.getSupplier().getNome(), (long) 1);
	    }
	});
	return result;
    }

    public List<String> countDeliveredByProduct(){
	Map<String, Integer> tmp = new HashMap<>();
	for(Order o : orders.values()){
	    if(o.delivered()) {
		if(tmp.containsKey(o.getProdCode()))
		    tmp.put(o.getProdCode(), tmp.get(o.getProdCode()) + 1);
		else
		    tmp.put(o.getProdCode(), 1);		
	    }
	}
	List<String> result = new ArrayList<>();
	for(String s : tmp.keySet())
	    result.add(String.format("%s - %d", s, tmp.get(s)));
	result.sort((s1, s2) -> Integer.valueOf(s2.split(" - ")[1]).compareTo(Integer.valueOf(s1.split(" - ")[1])));
	return result;
    }
}
