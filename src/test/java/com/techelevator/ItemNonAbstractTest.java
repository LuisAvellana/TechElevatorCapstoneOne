package com.techelevator;

import org.junit.Before;

//Here in order to be able to test Abstract Item class we need first override methods and then call them to test class
//we are not allowed to junit test abstract methods so add this class in the same package as normal testing classes
public class ItemNonAbstractTest  extends Item{

        public ItemNonAbstractTest(String name, double price) {
        super(name, price);
    }

    public ItemNonAbstractTest() {
        super();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean isAvailableToPurchase() {
        return super.isAvailableToPurchase();
    }

    @Override
    public void removeItem() {
        super.removeItem();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public int getNumberOfItems() {
        return super.getNumberOfItems();
    }

    @Before
    public void setup(){


    }
}
