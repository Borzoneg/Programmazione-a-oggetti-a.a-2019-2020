package diet;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents the main class in the
 * take-away system.
 * 
 * It allows adding restaurant, users, and creating orders.
 *
 */
public class Takeaway {

    ArrayList<Restaurant> restaurantList = new ArrayList<>();
    ArrayList<User> usersList = new ArrayList<>();
    ArrayList<Order> ordersList = new ArrayList<>();
    
    /**
     * Adds a new restaurant to the take-away system
     * 
     * @param r the restaurant to be added
     */
    public void addRestaurant(Restaurant r) {
	restaurantList.add(r);
    }

    /**
     * Returns the collections of restaurants
     * 
     * @return collection of added restaurants
     */
    public Collection<String> restaurants() {	
	ArrayList<String> restaurantNamesList = new ArrayList<String>();
	for(Restaurant r : restaurantList)
	    restaurantNamesList.add(r.getName());
	return restaurantNamesList;
    }

    /**
     * Define a new user
     * 
     * @param firstName first name of the user
     * @param lastName  last name of the user
     * @param email     email
     * @param phoneNumber telephone number
     * @return
     */
    public User registerUser(String firstName, String lastName, String email, String phoneNumber) {
	User user = new User(firstName, lastName, email, phoneNumber);
	usersList.add(user);
	return user;
    }

    /**
     * Gets the collection of registered users
     * 
     * @return the collection of users
     */
    public Collection<User> users(){
	return usersList;
    }

    /**
     * Create a new order by a user to a given restaurant.
     * 
     * The order is initially empty and is characterized
     * by a desired delivery time. 
     * 
     * @param user				user object
     * @param restaurantName	restaurant name
     * @param h					delivery time hour
     * @param m					delivery time minutes
     * @return
     */
    public Order createOrder(User user, String restaurantName, int h, int m) {
	Restaurant restaurant = null;
	for(Restaurant r : restaurantList) {
	    if(r.getName().equals(restaurantName))
		restaurant = r;
	}
	if(restaurant == null)
	    return null;
	
	Order order = new Order(user, restaurant, h, m);
	return order;
    }

    /**
     * Retrieves the collection of restaurant that are open
     * at the given time.
     * 
     * @param time time to check open
     * 
     * @return collection of restaurants
     */
    public Collection<Restaurant> openedRestaurants(String time){
	return null;
    }



}
