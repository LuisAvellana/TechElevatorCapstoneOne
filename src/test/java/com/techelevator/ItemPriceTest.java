package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemPriceTest {

    private Dessert dessert;
    private Entree entree;
    private Beverage beverage;
    private Appetizers appetizers;

    @Before
    public void setup(){
        dessert = new Dessert("Cake", 1.80);
        entree = new Entree("BBQ Ribs", 11.65);
        beverage = new Beverage("Cola", 11.65);
        appetizers = new Appetizers("Meatballs", 3.00);
    }

    @Test

    public void false_when_dessert_price_return_false_test(){
        Assert.assertEquals(2.00, dessert.getPrice(), 0.00);
    }

    @Test

    public void false_when_entree_price_return_false_test(){
        Assert.assertEquals(2.00, entree.getPrice(), 0.00);
    }

    @Test

    public void false_when_beverage_price_return_false_test(){
        Assert.assertEquals(2.00, beverage.getPrice(), 0.00);
    }

    @Test
    public void false_when_appetizers_price_return_false_test(){
        Assert.assertEquals(2.00, appetizers.getPrice(), 0.00);
    }
}

