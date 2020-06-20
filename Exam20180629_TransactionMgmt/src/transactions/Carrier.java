package transactions;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Carrier {

    private String name;
    private Set<Region> regions = new TreeSet<>((r1, r2) -> r1.getName().compareTo(r2.getName()));
    private Set<Transaction> transactions = new HashSet<>();
    
    public Carrier(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }
    
    public void addRegion(Region region) {
	regions.add(region);
    }
    
    public Set<Region> getRegions(){
	return regions;
    }
    
    public boolean serve(String place) {
	for(Region r : regions) {
	    for(String s : r.getPlaces()) {
		if(s.equals(place))
		    return true;
	    }
	}
	return false;
    }
    
    public void addTransaction(Transaction transaction) {
	transactions.add(transaction);
    }

    public Integer getNTransaction() {
	return transactions.size();
    }

    public Integer getTotalValueTransaction(Integer minimumValue) {
	int total = 0;
	for(Transaction t : transactions) {		
	    if(t.getScore() >= minimumValue)
		total += t.getScore();
	}
	return total;
    }
}
