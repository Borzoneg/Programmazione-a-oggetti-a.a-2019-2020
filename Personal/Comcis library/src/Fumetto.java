public class Fumetto {
	private String series;
	private int issue;
	private float price;
	private int id;
	
	public Fumetto(String series, int issue, float price, int id){
		this.series = series;
		this.issue = issue;
		this.price = price;
		this.id = id;
	}
	
	
	public void show() {
		System.out.println("Name: " + series + "\nIssue: " + issue + "\nPrice: " + price);
	}
	
	
	
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series= series;
	}
	
	
	public int getIssue() {
		return issue;
	}
	public void setIssue(int issue) {
		this.issue = issue;
	}
	
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	public int getId() {
		return id;
	}
}
