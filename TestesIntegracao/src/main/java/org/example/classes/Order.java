package org.example.classes;

public class Order {
    private int id;
    private double amount;
    private boolean paid;

    public Order(int id, double amount) {
        this.id = id;
        this.amount = amount;
        this.paid = false;
    }

    public int getId() { return id; }
    public double getAmount() { return amount; }
    public boolean isPaid() { return paid; }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
