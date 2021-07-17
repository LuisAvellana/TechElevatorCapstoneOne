package com.techelevator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class CafeTest {

    Cafe cafe;
    @Before
    public void setup(){
        cafe = new Cafe();
    }

    @After

    public void cleanup(){

        //do code that cleans up or resets here
    }


    @Test

    public void add_balance_false_when_test_fails(){
        //Arrange

        //Act
        cafe.addToAccountBalance(100);


        Assert.assertEquals(101, cafe.getCurrentAccountBalance(), 0.00);

    }

    @Test

    public void checking_current_balance_false_when_test_fails(){
        //Arrange

        //Act

        cafe.addToAccountBalance(cafe.getCurrentAccountBalance());
       Assert.assertEquals(1.0, cafe.getCurrentAccountBalance(), 0.0);

    }

}
