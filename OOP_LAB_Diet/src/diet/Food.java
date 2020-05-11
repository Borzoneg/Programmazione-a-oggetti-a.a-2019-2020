package diet;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;


/**
 * Facade class for the diet management.
 * It allows defining and retrieving raw materials and products.
 *
 */
public class Food {

    Map<String, NutritionalElement> rawMaterialsMap = new TreeMap<>();
    Map<String, NutritionalElement> productsMap = new TreeMap<>();
    Map<String, NutritionalElement> recipeMap = new TreeMap<>();
    /**
     * Define a new raw material.
     * 
     * The nutritional values are specified for a conventional 100g amount
     * @param name 		unique name of the raw material
     * @param calories		calories per 100g
     * @param proteins		proteins per 100g
     * @param carbs		carbs per 100g
     * @param fat 		fats per 100g
     */
    public void defineRawMaterial(String name, double calories, double proteins, double carbs, double fat){
	rawMaterialsMap.put(name, new RawMaterial(name, calories, proteins, carbs, fat));
    }

    /**
     * Retrieves the collection of all defined raw materials
     * 
     * @return collection of raw materials though the {@link NutritionalElement} interface
     */
    public Collection<NutritionalElement> rawMaterials(){
	return rawMaterialsMap.values();
    }

    /**
     * Retrieves a specific raw material, given its name
     * 
     * @param name  name of the raw material
     * 
     * @return  a raw material though the {@link NutritionalElement} interface
     */
    public NutritionalElement getRawMaterial(String name){
	if(rawMaterialsMap.containsKey(name))
	    return rawMaterialsMap.get(name);
	return null;
    }

    /**
     * Define a new packaged product.
     * The nutritional values are specified for a unit of the product
     * 
     * @param name 		unique name of the product
     * @param calories	calories for a product unit
     * @param proteins	proteins for a product unit
     * @param carbs		carbs for a product unit
     * @param fat 		fats for a product unit
     */
    public void defineProduct(String name, double calories, double proteins, double carbs, double fat){
	productsMap.put(name, new Product(name, calories, proteins, carbs, fat));
    }

    /**
     * Retrieves the collection of all defined products
     * 
     * @return collection of products though the {@link NutritionalElement} interface
     */
    public Collection<NutritionalElement> products(){
	return productsMap.values();
    }

    /**
     * Retrieves a specific product, given its name
     * @param name  name of the product
     * @return  a product though the {@link NutritionalElement} interface
     */
    public NutritionalElement getProduct(String name){
	if(productsMap.containsKey(name))
	    return productsMap.get(name);
	return null;
    }

    /**
     * Creates a new recipe stored in this Food container.
     *  
     * @param name name of the recipe
     * 
     * @return the newly created Recipe object
     */
    public Recipe createRecipe(String name) {
	Recipe recipe = new Recipe(name, rawMaterialsMap);
	recipeMap.put(name, recipe);
	return recipe;
    }

    /**
     * Retrieves the collection of all defined recipes
     * 
     * @return collection of recipes though the {@link NutritionalElement} interface
     */
    public Collection<NutritionalElement> recipes(){
	return recipeMap.values();
    }

    /**
     * Retrieves a specific recipe, given its name
     * 
     * @param name  name of the recipe
     * 
     * @return  a recipe though the {@link NutritionalElement} interface
     */
    public NutritionalElement getRecipe(String name){	
	if(recipeMap.containsKey(name)) {
	    return recipeMap.get(name);
	}
	return null;
    }

    /**
     * Creates a new menu
     * 
     * @param name name of the menu
     * 
     * @return the newly created menu
     */
    public Menu createMenu(String name) {
	return new Menu(name, this);
    }

}
