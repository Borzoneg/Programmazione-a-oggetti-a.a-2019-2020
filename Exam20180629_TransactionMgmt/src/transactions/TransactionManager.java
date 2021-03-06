package transactions;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionManager {

    private Map<String, Region> regions = new HashMap<>();
    private Map<String, Carrier> carriers = new HashMap<>();
    private Map<String, Region> places = new HashMap<>();
    private Map<String, Order> requests = new HashMap<>();
    private Map<String, Offer> offers = new HashMap<>();
    private Map<String, Transaction> transactions = new HashMap<>();
    private SortedMap<String, Long> nTPerProduct = new TreeMap<>();
    private Map<Region, Long> transactionPerRegion = new HashMap<>();
    

    //R1
    public List<String> addRegion(String regionName, String... placeNames) { 
	Region r = new Region(regionName);
	for(String s : placeNames) {
	    if(! places.containsKey(s)) {
		places.put(s, r);
		r.addPlace(s);
	    }
	}
	regions.put(regionName, r);
	return r.getPlaces().stream().collect(Collectors.toList());
    }

    public List<String> addCarrier(String carrierName, String... regionNames) { 
	Carrier c = new Carrier(carrierName);
	for(String s : regionNames) {
	    if(regions.containsKey(s)) {
		regions.get(s).addCarrier(c);
		c.addRegion(regions.get(s));
	    }
	}
	carriers.put(carrierName, c);
	return c.getRegions().stream().map(r -> r.getName()).collect(Collectors.toList());
    }

    public List<String> getCarriersForRegion(String regionName) { 
	return regions.get(regionName).getCarriers().stream().map(c -> c.getName()).collect(Collectors.toList());
    }

    //R2
    public void addRequest(String requestId, String placeName, String productId) throws TMException {
	if(requests.containsKey(requestId))
	    throw new TMException();
	if(! places.containsKey(placeName))
	    throw new TMException();
	Order o = new Order(requestId, placeName, productId);
	requests.put(requestId, o);
    }

    public void addOffer(String offerId, String placeName, String productId) throws TMException {
	if(offers.containsKey(offerId))
	    throw new TMException();
	if(! places.containsKey(placeName))
	    throw new TMException();
	Offer o = new Offer(offerId, placeName, productId);
	offers.put(offerId, o);
    }


    //R3
    public void addTransaction(String transactionId, String carrierName, String requestId, String offerId) throws TMException {
	for(Transaction t : transactions.values()) {
	    if(t.getOffer().getId().equals(offerId) || t.getOrder().getId().equals(requestId))
		throw new TMException();
	}
	if(! requests.get(requestId).getProductId().equals(offers.get(offerId).getProductId()))
	    throw new TMException();
	if(! carriers.get(carrierName).getRegions().contains(places.get(offers.get(offerId).getPlaceName()))||
	   ! carriers.get(carrierName).getRegions().contains(places.get(requests.get(requestId).getPlaceName())))
	    throw new TMException();
	Transaction t = new Transaction(requests.get(requestId), carriers.get(carrierName), offers.get(offerId), transactionId);
	transactions.put(transactionId, t);
	requests.get(requestId).setTransaction(t);
	carriers.get(carrierName).addTransaction(t);
	offers.get(offerId).setTransaction(t);
	nTPerProduct.put(requests.get(requestId).getProductId(), nTPerProduct.containsKey(requests.get(requestId).getProductId()) ?  nTPerProduct.get(requests.get(requestId).getProductId()) + 1 : 1);
	transactionPerRegion.put(places.get(requests.get(requestId).getPlaceName()), transactionPerRegion.containsKey(places.get(requests.get(requestId).getPlaceName())) ?
											transactionPerRegion.get(places.get(requests.get(requestId).getPlaceName())) + 1 : 1);
    }

    public boolean evaluateTransaction(String transactionId, int score) {
	transactions.get(transactionId).evaluate(score);
	if(score <= 0 || score > 10)
	    return false;
	return true;
    }

    //R4
    public SortedMap<Long, List<String>> deliveryRegionsPerNT() {
	SortedMap<Long, List<String>> result = new TreeMap<>((i1, i2) -> i2.compareTo(i1));
	List<String> tmp;
	for(Region r : transactionPerRegion.keySet()) {
	    if(! result.containsKey(transactionPerRegion.get(r))) 
		tmp = new ArrayList<>();
	    else
		tmp = result.get(transactionPerRegion.get(r));
	    
	    tmp.add(r.getName());
	    tmp.sort((s1, s2) -> s1.compareTo(s2));
	    result.put(transactionPerRegion.get(r), tmp);
	}
	return result;
    }

    public SortedMap<String, Integer> scorePerCarrier(int minimumScore) {
	SortedMap<String, Integer> result = new TreeMap<>();
	carriers.values().stream().forEach(c -> {if(c.getTotalValueTransaction(minimumScore) > 0)result.put(c.getName(), c.getTotalValueTransaction(minimumScore));});
	return result;
    }

    public SortedMap<String, Long> nTPerProduct() {
	return nTPerProduct;
    }


}

