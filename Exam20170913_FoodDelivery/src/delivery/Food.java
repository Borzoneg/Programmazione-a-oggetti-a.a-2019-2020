package delivery;

public class Food {
    private String description;
    private double price;
    private String category;
    private int prepTime;

    public Food(String description, double price, String category, int prepTime) {
	super();
	this.description = description;
	this.price = price;
	this.category = category;
	this.prepTime = prepTime;
    }   
    
    public String toString() {
	return String.format("[%s] %s : %.2f", category, description, price);
    }

    public String getDescription() {
	return description;
    }

    public Double getPrice() {
	return price;
    }

    public int getTime() {
	return prepTime;
    }
}
