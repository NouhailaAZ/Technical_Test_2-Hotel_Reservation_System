public class User {
    private int userId;
    private int balance;
    
    public User(int userId, int balance) {
        this.userId = userId;
        this.balance = balance;
    }
    
    // Getters et Setters
    public int getUserId() {
        return userId;
    }
    
    public int getBalance() {
        return balance;
    }
    
    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    public boolean canBook(int amount) {
        return balance >= amount;
    }
    
    public void deductBalance(int amount) throws IllegalArgumentException {
        if (!canBook(amount)) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance -= amount;
    }
    
    @Override
    public String toString() {
        return "User{ userId = " + userId + ", balance = " + balance + " }";
    }
}