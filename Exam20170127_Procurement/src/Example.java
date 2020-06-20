import warehouse.*;

public class Example {

    public static void main(String[] args) throws InvalidSupplier, MultipleDelivery{
        Warehouse m = new Warehouse();
        
        Product banane = m.newProduct("BNN","Banane");
        banane.setQuantity(33);
        Product kiwi = m.newProduct("KWI","Kiwi");
        kiwi.setQuantity(44);
        kiwi.decreaseQuantity();
        
        Supplier chiquita = m.newSupplier("CQT", "Chiquita");
        Supplier delmonte = m.newSupplier("DMT", "Del Monte");
        
        
        chiquita.newSupply(banane);
        chiquita.newSupply(kiwi);
        
        delmonte.newSupply(banane);
        
        
        Order ord1 = m.issueOrder(banane,67,chiquita);
        Order ord2 = m.issueOrder(banane,100,delmonte);
        Order ord3 = m.issueOrder(kiwi ,67,chiquita);
        Order ord4 = m.issueOrder(banane, 100,delmonte);
        Order ord5 = m.issueOrder(banane, 67,chiquita);
        Order ord6 = m.issueOrder(banane, 100,delmonte);
        ord1.setDelivered();
        ord2.setDelivered();
        ord3.setDelivered();
        ord4.setDelivered();
        System.out.println(m.orderNBySupplier().toString());

        
    }
}
