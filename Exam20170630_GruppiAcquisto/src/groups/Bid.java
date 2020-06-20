package groups;

public class Bid {
    
    private Supplier supplier;
    private Group group;
    private int price;
    private int vote = 0;
    private Product product;
    
    public Bid(Supplier supplier, Group group, int price, Product product) {
	this.supplier = supplier;
	this.group = group;
	this.price = price;
	this.product = product;
    }
    
    public String toString() {
	return String.format("%s:%d", supplier.getName(), price);
    }
    
    public Supplier getSupplier() {
        return supplier;
    }

    public Group getGroup() {
        return group;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }
    
    public void vote() {
	vote++;
    }

    public int getVote() {
	return vote;
    }

    public Product getProduct() {
	return product;
    }

}
