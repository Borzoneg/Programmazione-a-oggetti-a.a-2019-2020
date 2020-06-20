package transactions;

public class Transaction {
    private Order order;
    private Carrier carrier;
    private Offer offer;
    private String id;
    private int score = 0;
    
    public Transaction(Order order, Carrier carrier, Offer offer, String id) {
	this.order = order;
	this.carrier = carrier;
	this.offer = offer;
	this.id = id;
    }

    public Offer getOffer() {
	return offer;
    }
    
    public Order getOrder() {
        return order;
    }

    public void evaluate(int score) {
	this.score = score;
    }    
    
    public int getScore() {
	return score;
    }
}
