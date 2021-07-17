package com.techelevator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemMethodsTest {
    //Please have ItemNonAbstractTest present at the same package


    ItemNonAbstractTest itemNonAbstractTest;
    @Before
    public void setup(){
        itemNonAbstractTest = new ItemNonAbstractTest();
    }

    @After

    public void cleanup(){

        //do code that cleans up or resets here
    }

    @Test
    public void fail_when_number_of_items_not_50() {

        int actual = itemNonAbstractTest.getNumberOfItems();

        Assert.assertEquals(51, actual);
    }

    @Test
    public void fail_when_isAvailableToPurchase_false() {

        boolean actual = itemNonAbstractTest.isAvailableToPurchase();

        Assert.assertEquals(false, actual);
    }


    @Test
    public void fail_when_getName_is_not_null() {

        String actual = itemNonAbstractTest.getName();

        Assert.assertEquals("not null", actual);
    }

    @Test
    public void fail_when_getPrice_is_not_zero() {

        double actual = itemNonAbstractTest.getPrice();

        Assert.assertEquals("not null", actual);
    }
}
