package warehouse;

public class Order {

    private String code;
    private Product product;
    private Supplier supplier;
    private int quantity;
    private boolean delivered = false;

    public Order(Product product, int quantity, Supplier supplier, String code) {
	this.code = code;
	this.product = product;
	this.supplier = supplier;
	this.quantity = quantity;
    }

    public String getCode(){
	return code;
    }

    public int getQuantity() {
	return quantity;
    }

    public boolean delivered(){
	return delivered;
    }

    public void setDelivered() throws MultipleDelivery {
	if(delivered)
	    throw new MultipleDelivery();
	product.setQuantity(product.getQuantity() + quantity);
	delivered = true;
    }

    public String toString(){
	return String.format("Order %s for %d %s : %s from %s", code, quantity, product.getCode(), product.getDescription(), supplier.getNome());
    }

    public String getProdCode() {
	return product.getCode();    
    }
    
    public Supplier getSupplier() {
	return supplier;
    }
}
