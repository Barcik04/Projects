public class Checkout {
    private Receipt receipt;

    public Checkout() {
        this.receipt = new Receipt();
    }

    public void addProductToReceipt(Product product) {
        receipt.addProduct(product);
    }

    public void removeProductFromReceipt(Product product) {
        receipt.removeProduct(product);
    }

    public void checkout(double payment) {
        System.out.println("Your total payment is " + receipt.getTotalPrice());
        double change = payment - receipt.getTotalPrice();
        if (change < 0) {
            throw new IllegalArgumentException("Insufficient payment amount... Total price is: " + receipt.getTotalPrice() + ". Your funds: " + payment);
        }
        System.out.println("Payment successful! change: " + String.format("%.2f", change));
        printReceipt();
        receipt.clearCart();
    }

    public void printReceipt() {
        System.out.println("\n" + receipt);
    }
}
