package delivery;

import java.util.ArrayList;
import java.util.List;

import delivery.Delivery.OrderStatus;

public class Customer {
    
    private String name;
    private String address;
    private String phone;
    private String email;
    private List<Order> orders = new ArrayList<>();

    public Customer(String name, String address, String phone, String email) {
	this.name = name;
	this.address = address;
	this.phone = phone;
	this.email = email;
    }
    
    
    public String toString() {
	return name + ", " + address + ", " + phone + ", " + email;
    }


    public String getEmail() {
	return email;
    }
    
    public void addOrder(Order order) {
	orders.add(order);
    }
    
    public List<Order> getOrders(){
	return orders;
    }


    public double getTotal() {
	double total = 0;
	for(Order o : orders) {
	    if(o.getStatus() != OrderStatus.NEW)
		total += o.getTotal();
	}
        return total;
    }

}
