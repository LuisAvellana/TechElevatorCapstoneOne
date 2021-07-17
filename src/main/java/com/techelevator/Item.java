package com.techelevator;

import java.text.DecimalFormat;

public abstract class Item { //Item class is superclass of Beverage, Dessert, Entree, Appetizers

    private String name;
    private double price;
    private int numberOfItems = 50;

    public Item(String name, double price) { // This is constructor of item class
        this.name = name;
        this.price = price;
    }

    public Item() { // empty constructor to go with above constructor

    }

    public String toString() { // We originally intended to use to string for printing purposes. this does format our DISPLAY_CATERING_ITEMS to our desired format.
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        String str = String.format(" %1$-20s %2$-10s %3$s", name, "$" + decimalFormat.format(price), this.getNumberOfItems());
        return str;
    }

    public boolean isAvailableToPurchase() { // allows system to see if there is available inventory
        if(this.numberOfItems >= 1) {
            return true;
        }
        return false;
    }

    public void removeItem() { // subtracts from inventory
        numberOfItems -= 1;
    }

    public String getName() { //just a getter
        return name;
    }

    public double getPrice() { //just a getter
        return price;
    }

    public int getNumberOfItems() { //just a getter
        return numberOfItems;
    }

}