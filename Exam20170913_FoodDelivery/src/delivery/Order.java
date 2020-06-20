package delivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import delivery.Delivery.OrderStatus;

public class Order {
     
    private static final int TRANSPORT_TIME = 15;
    private static final int DELAY = 5;
    private Map<Food, Integer> foods = new HashMap<>();
    private Customer customer;
    private Delivery.OrderStatus status = OrderStatus.NEW;
    private int time;
    
    public Order(Customer customer) {
	this.customer = customer;
    }

    public void addFood(Food food, int qty) {
	foods.put(food, foods.containsKey(food) ? foods.get(food) + qty : qty);
    }

    public int getQuantity(Food food) {
	return foods.get(food);
    }

    public List<Food> getFoods() {
	return foods.keySet().stream().collect(Collectors.toList());
    }
    
    public List<String> foodsListString() {
	List<String> result = new ArrayList<>();
	for(Food f : foods.keySet()) {
	    result.add(String.format("%s, %d", f.getDescription(), foods.get(f)));
	}
	return result;
    }
    
    public Delivery.OrderStatus getStatus() {
	return status;
    }

    public double getTotal() {
	double result = 0;
	for(Food f : foods.keySet()) {
	    result += f.getPrice() * foods.get(f);
	}
	return result;
    }

    public int confirm() {
	int max = 0, result = DELAY + TRANSPORT_TIME;
	for(Food f : foods.keySet()) {
	    if(f.getTime() > max)
		max = f.getTime();
	}
	status = OrderStatus.CONFIRMED;
	time = result + max;
	return time;
    }

    public int start() {
	time -= DELAY;
	status = OrderStatus.PREPARATION;
	return time;
    }

    public int deliver() {
	time -= TRANSPORT_TIME;
	status = OrderStatus.ON_DELIVERY;
	return time;
    }
    
    public void complete() {
	time = 0;
	status = OrderStatus.DELIVERED;
    }
}
