package transactions;

public class Order {
    
    private String id;
    private String placeName;
    private String productId;
    private Transaction transaction;
    
    public Order(String id, String placeName, String productId) {
	this.id = id;
	this.placeName = placeName;
	this.productId = productId;
    }

    public String getId() {
	return id;
    }
    
    public String getProductId() {
   	return productId;
       }
    
    public String getPlaceName() {
	return placeName;
    }

    public void setTransaction(Transaction t) {
	transaction = t;
    }
}
