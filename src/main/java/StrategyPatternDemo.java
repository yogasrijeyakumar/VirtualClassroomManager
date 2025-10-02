// StrategyPatternDemo.java
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) { System.out.println("Paid " + amount + " using Credit Card."); }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(int amount) { System.out.println("Paid " + amount + " using PayPal."); }
}

class ShoppingCart {
    private PaymentStrategy paymentMethod;
    public void setPaymentMethod(PaymentStrategy paymentMethod) { this.paymentMethod = paymentMethod; }
    public void checkout(int amount) { paymentMethod.pay(amount); }
}

public class StrategyPatternDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.setPaymentMethod(new CreditCardPayment());
        cart.checkout(500);

        cart.setPaymentMethod(new PayPalPayment());
        cart.checkout(300);
    }
}
