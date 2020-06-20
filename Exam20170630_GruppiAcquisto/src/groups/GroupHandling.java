package groups;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class GroupHandling {
    //R1	
    private Map<String, Supplier> suppliers = new TreeMap<>();
    private Map<String, Product> products = new TreeMap<>();
    private Map<String, Group> groups = new TreeMap<>();
    private Map<String, Client> clients = new TreeMap<>();

    public void addProduct (String productTypeName, String... supplierNames) throws GroupHandlingException {
	if(products.containsKey(productTypeName))
	    throw new GroupHandlingException(productTypeName + " già definito");
	Product p = new Product(productTypeName);
	products.put(productTypeName, p);
	Supplier s;
	for(String suppName : supplierNames) {
	    if(! suppliers.containsKey(suppName)) {
		s = new Supplier(suppName);
		suppliers.put(suppName, s);
	    }
	    else
		s = suppliers.get(suppName);
	    p.addSupplier(s);
	    s.addProduct(p);
	}
    }

    public List<String> getProductTypes (String supplierName) {
	return suppliers.get(supplierName).getProducts();
    }

    //R2	
    public void addGroup (String name, String productName, String... customerNames)  throws GroupHandlingException {
	if(groups.containsKey(name))
	    throw new GroupHandlingException(name + " già definito");
	if(! products.containsKey(productName))
	    throw new GroupHandlingException(productName + " non definito");
	Group g = new Group(name, products.get(productName));
	for(String clientName : customerNames) {
	    Client c;
	    if(clients.containsKey(clientName))
		c = clients.get(clientName);
	    else {
		c = new Client(clientName);
		clients.put(clientName, c);
	    }
	    g.addClient(c);	
	    c.addGroup(g);
	}
	groups.put(name, g);
    }

    public List<String> getGroups (String customerName) {
	return clients.get(customerName).getGroups();
    }

    //R3
    public void setSuppliers (String groupName, String... supplierNames) throws GroupHandlingException {
	Group g = groups.get(groupName);
	for(String supplierName : supplierNames) {
	    if(! suppliers.containsKey(supplierName))
		throw new GroupHandlingException(supplierName + " non definito");
	    Supplier s = suppliers.get(supplierName);
	    if(! s.getProducts().contains(g.getProduct().getName()))
		throw new GroupHandlingException(supplierName + " non tratta " + g.getProduct().getName());
	    g.addSupplier(s);
	}
    }

    public void addBid (String groupName, String supplierName, int price) throws GroupHandlingException {
	if(! groups.get(groupName).suppliers().contains(suppliers.get(supplierName)))
	    throw new GroupHandlingException(supplierName + " non è fornitore di "  + groupName);

	suppliers.get(supplierName).addBid(price, groups.get(groupName), groups.get(groupName).getProduct());
	groups.get(groupName).addBid(price, suppliers.get(supplierName), groups.get(groupName).getProduct());

    }

    public String getBids (String groupName) {
	StringBuffer buf = new StringBuffer();
	Group g  = groups.get(groupName);
	List<Bid> tmp = g.bids();
	tmp.stream().forEach(b -> buf.append(b.toString() + ","));
	String str = buf.toString().substring(0, buf.toString().length()-1);
	return str;
    }


    //R4	
    public void vote (String groupName, String customerName, String supplierName) throws GroupHandlingException {
	if(! groups.get(groupName).contains(customerName))
	    throw new GroupHandlingException(customerName +" non fa parte " + groupName);
	Group g = groups.get(groupName);
	List<Bid> bidsOfGroup = g.bids();
	List<Supplier> suppliersWithBid = new ArrayList<>();
	bidsOfGroup.stream().forEach(b -> suppliersWithBid.add(b.getSupplier()));
	Supplier s = suppliers.get(supplierName);
	if(!suppliersWithBid.contains(s))
	    throw new GroupHandlingException(s.getName() + " non ha fatto un'offerta per il gruppo " + g.getName());

	for(Bid bid : bidsOfGroup) {
	    if(bid.getSupplier().getName().equals(supplierName)) {
		Bid b = bid;
		b.vote();
	    }
	}

    }

    public String  getVotes (String groupName) {
	Group g = groups.get(groupName);
	StringBuffer buf = new StringBuffer(); 
	List<Bid> bids = g.bids();
	bids.sort((b1, b2) -> b1.getSupplier().getName().compareTo(b2.getSupplier().getName()));
	for(Bid b : bids) {
	    if(b.getVote() > 0)
		buf.append(String.format("%s:%d,", b.getSupplier().getName(), b.getVote()));
	}
	String result = buf.toString();
	return result.substring(0, result.length()-1);
    }

    public String getWinningBid (String groupName) {
	Group g = groups.get(groupName);
	if(g.bids().size() == 0)
	    return null;
	Bid winner  = g.bids().get(0);

	for(Bid b : g.bids()) {
	    if(b.getVote() > winner.getVote() || (b.getVote() == winner.getVote() && b.getPrice() < winner.getPrice()))
		winner = b;
	}
	return String.format("%s:%d", winner.getSupplier().getName(), winner.getVote());
    }

    //R5
    public SortedMap<String, Integer> maxPricePerProductType() { 
	SortedMap<String, Integer> result = new TreeMap<>();
	List<Bid> bids = new ArrayList<>();
	groups.values().stream().forEach(g -> g.bids().stream().forEach(b -> bids.add(b)));
	bids.stream().forEach(b-> result.put(b.getProduct().getName(), result.containsKey(b.getProduct().getName()) ? Integer.max(b.getPrice(), result.get(b.getProduct().getName())) : b.getPrice()));
	return result;   
    }

    public SortedMap<Integer, List<String>> suppliersPerNumberOfBids() {
	SortedMap<Integer, List<String>> result = new TreeMap<>((i1, i2) -> i2.compareTo(i1));
	SortedMap<String, Integer> tmp = new TreeMap<>();
	List<Bid> bids = new ArrayList<>();
	groups.values().stream().forEach(g -> g.bids().stream().forEach(b -> bids.add(b)));
	bids.stream().forEach(b -> tmp.put(b.getSupplier().getName(), tmp.containsKey(b.getSupplier().getName()) ? tmp.get(b.getSupplier().getName()) +1 : 1));
	tmp.keySet().stream().forEach(k -> {
	    List<String> tmp1;
	    if(result.containsKey(tmp.get(k))) {
		tmp1 = result.get(tmp.get(k));
		tmp1.add(k);
	    }
	    else {
		tmp1  = new ArrayList<>();
		tmp1.add(k);
		result.put(tmp.get(k), tmp1);		
	    }
	    result.put(tmp.get(k), tmp1);
	});

	return result;
    }

    public SortedMap<String, Long> numberOfCustomersPerProductType() {
	SortedMap<String, Long> result = new TreeMap<>();
	groups.values().stream().forEach(g -> result.put(g.getProduct().getName(), result.containsKey(g.getProduct().getName()) ? result.get(g.getProduct().getName()) + g.getNClients() : g.getNClients()));
	return result;
    }

}
