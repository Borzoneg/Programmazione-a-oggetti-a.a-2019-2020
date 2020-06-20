package warehouse;

import java.util.ArrayList;
import java.util.List;

public class Supplier {

    private String code;
    private String nome;
    private List<Product> products = new ArrayList<>();

    public Supplier(String code, String name) {
	this.code = code;
	this.nome = name;
    }

    public String getCodice(){
	return code;
    }

    public String getNome(){
	return nome;
    }

    public void newSupply(Product product){
	products.add(product);
	product.addSupplier(this);
    }

    public List<Product> supplies(){
	products.sort((p1, p2) -> p1.getDescription().compareTo(p2.getDescription()));
	return products;
    }
}
