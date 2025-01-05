import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<Product> products;
    private double totalPrice;

    public Receipt() {
        this.products = new ArrayList<>();
        this.totalPrice = 0;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addProduct(Product product) {
        this.products.add(product);
        this.totalPrice += product.getPrice();
        System.out.println(product + " added to receipt...");
    }

    public void removeProduct(Product product) {
        if (this.products.contains(product)) {
            this.products.remove(product);
            this.totalPrice -= product.getPrice();
            System.out.println("Product: " + product + " removed from receipt...");
        } else {
            System.out.println("Product: " + product + " Not found in the receipt...");
        }
    }

    public void clearCart() {
        this.products.clear();
        this.totalPrice = 0;
        System.out.println("You cleared your cart...");
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Receipt products:\n");
        for (Product product : products) {
            sb.append(product).append("\n");
        }
        sb.append("Total price: ").append(totalPrice).append("\n");
        return sb.toString();
    }
}
