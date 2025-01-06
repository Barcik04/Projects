import java.util.Scanner;

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

    public void payWithCash(double payment) {
        System.out.println("Your total payment is " + receipt.getTotalPrice());
        double change = payment - receipt.getTotalPrice();
        if (change < 0) {
            throw new IllegalArgumentException("Insufficient payment amount... Total price is: " + receipt.getTotalPrice() + ". Your funds: " + payment);
        }
        System.out.println("Payment successful! change: " + String.format("%.2f", change));
        printReceipt();
        receipt.clearCart();
    }

    public void payWithCard(Card card) {
        System.out.println("Your total payment is " + receipt.getTotalPrice() + " Please insert the card ");
        System.out.print("Please input the pin code: ");
        int attempts = 0;
        Scanner scanner = new Scanner(System.in);
        while (attempts < 3) {
            String enteredPin = scanner.nextLine();
            if (enteredPin.equals(card.getPin())) {
                break;
            }
            attempts++;
            System.out.println("Invalid pin... try again: ");
        }

        if (attempts == 3) {
            System.out.println("Too many attempts. Payment canceled...");
            return;
        }

        System.out.println("Proceeding...");
        if (card.getBalance() < receipt.getTotalPrice()) {
            System.out.println("Card declined");
            return;
        }
        simulateProcessing();
        System.out.println("Sucess!");
        receipt.clearCart();
    }

    public void printReceipt() {
        System.out.println("\n" + receipt);
    }

    private void simulateProcessing() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
