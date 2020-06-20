package groups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Group {

    private String name;
    private Product product;
    private List<Client> clients = new ArrayList<>();
    private List<Supplier> suppliers = new ArrayList<>();
    private Map<String, Bid> bids = new HashMap<>();

    public Group(String name, Product product) {
	this.name = name;
	this.product = product;
    }

    public void addClient(Client client) {
	clients.add(client);
    }


    public void addSupplier(Supplier supplier) {
	suppliers.add(supplier);
    }

    public String getName() {
	return name;
    }

    public Product getProduct() {
	return product;
    }

    public List<Supplier> suppliers() {
	return suppliers;
    }


    public Bid addBid(int price, Supplier supplier, Product product) {

	if(bids.containsKey(supplier.getName())) {
	    bids.get(supplier.getName()).setPrice(price);
	    return bids.get(supplier.getName());
	}
	Bid b  = new Bid(supplier, this, price, product);
	bids.put(supplier.getName(), b);
	return b;
    }

    public List<Bid> bids() {
	List<Bid> result = bids.values().stream().collect(Collectors.toList());
	result.sort((b1, b2) ->
	Integer.valueOf(b1.getPrice()).compareTo(Integer.valueOf(b2.getPrice())) == 0 ? 
		b1.getSupplier().getName().compareTo(b2.getSupplier().getName()) : Integer.valueOf(b1.getPrice()).compareTo(Integer.valueOf(b2.getPrice())));
	return result;
    }

    public boolean contains(String customerName) {
	for(Client c : clients) {
	    if(c.getName().equals(customerName))
		return true;
	}
	return false;
    }

    public int getNClients() {
	return clients.size();
    }

}
