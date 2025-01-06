public class Card {
    private String cardNumber;
    private double balance;
    private String pin;

    public Card(String cardNumber, double balance, String pin) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        if (pin.length() != 4) {
            throw new IllegalArgumentException("Pin must have 4 digits");
        }
        this.pin = pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "card number: " + cardNumber + ", balance: " + balance + ", pin: " + pin;
    }
}
