public class Main {
    public static void main(String[] args) {
        Product aplle = new Product("Aplle", 1.99);
        Product banana = new Product("Banana", 2.99);
        Product orange = new Product("Orange", 3.99);

        Card card1 = new Card("1234", 7.2, "1357");

        Checkout receipt = new Checkout();

        receipt.addProductToReceipt(aplle);
        receipt.addProductToReceipt(banana);
        receipt.addProductToReceipt(orange);

        receipt.removeProductFromReceipt(aplle);
        System.out.println("\n");
        receipt.printReceipt();

        System.out.println("\n");
        receipt.payWithCard(card1);
    }
}
