package com.thanos;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
* Calculator Tester.
*
* @author <Authors name>
* @since <pre>Oct 11, 2018</pre>
* @version 1.0
*/
public class CalculatorTest {

    private  Calculator calculator;
@Before
public void before() throws Exception {
    calculator = new Calculator();
}

@After
public void after() throws Exception {
}

/**
*
* Method: add(int x, int y)
*
*/
@Test
public void testAdd() throws Exception {
//TODO: Test goes here...
    Assert.assertEquals(calculator.add(8,2),10);
}

/**
*
* Method: sub(int x, int y)
*
*/
@Test
public void testSub() throws Exception {
//TODO: Test goes here...
    Assert.assertEquals(calculator.sub(8,2),6);
}

/**
*
* Method: mul(int x, int y)
*
*/
@Test
public void testMul() throws Exception {
//TODO: Test goes here...
    Assert.assertEquals(calculator.mul(8,2),16);
}

/**
*
* Method: div(int x, int y)
*
*/
@Test
public void testDiv() throws Exception {
//TODO: Test goes here...
    Assert.assertEquals(calculator.div(8,2),4);
}

/**
*
* Method: div2(int x, int y)
*
*/
@Test
public void testDiv2() throws Exception {
//TODO: Test goes here... 
}

/**
*
* Method: loop(int x, int y)
*
*/
@Test
public void testLoop() throws Exception {
//TODO: Test goes here... 
}

/**
*
* Method: unCompleted(int x, int y)
*
*/
@Test
public void testUnCompleted() throws Exception {
//TODO: Test goes here... 
}

} 
