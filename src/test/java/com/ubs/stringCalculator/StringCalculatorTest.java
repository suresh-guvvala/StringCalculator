package com.ubs.stringCalculator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Suresh
 *
 */
public class StringCalculatorTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testAddWithZeroOneOrTwoNumbers() {
		assertEquals(StringCalculator.add(""), 0);
		assertEquals(StringCalculator.add(" "), 0);
		assertEquals(StringCalculator.add(null), 0);
		assertEquals(StringCalculator.add("4"), 4);
		assertEquals(StringCalculator.add(" 6 "), 6);
		assertEquals(StringCalculator.add("4,6"), 10);
		assertEquals(StringCalculator.add(" 6, 5 "), 11);
		assertEquals(StringCalculator.add("6,1002"), 6);
	}

	@Test
	public void testAddWithMoreNumbers() {
		assertEquals(StringCalculator.add(" 6, 5,2 "), 13);
		assertEquals(StringCalculator.add("1,2,3,4"), 10);
		assertEquals(StringCalculator.add("1,2,3,6,5,4, 11, 101"), 133);
	}

	@Test
	public void testAddWithNewLineDelimiter() {
		assertEquals(StringCalculator.add("//;\n3"), 3);
		assertEquals(StringCalculator.add("//;|%\n8"), 8);

		assertEquals(StringCalculator.add("4\n6"), 10);
		assertEquals(StringCalculator.add(" 6, 5\n4 "), 15);
		assertEquals(StringCalculator.add("6\n1002\n2,3"), 11);
	}

	@Test
	public void testAddWithAnyDelimiter() {
		assertEquals(StringCalculator.add("//;\n"), 0);
		assertEquals(StringCalculator.add("//;|%\n"), 0);
		assertEquals(StringCalculator.add("//;\n3;4"), 7);
		assertEquals(StringCalculator.add("//;|%\n8%5"), 13);
		assertEquals(StringCalculator.add("//;\n3;4;5;1"), 13);
		assertEquals(StringCalculator.add("//;|%\n8%5;2%1;10"), 26);
		assertEquals(StringCalculator.add("//^|$\n2134^2$1234^1$7"), 10);
	}

	@Test
	public void testAddWithBiggerNumbers() {
		assertEquals(StringCalculator.add("10002"), 0);
		assertEquals(StringCalculator.add("1005,1"), 1);
		assertEquals(StringCalculator.add("1,1007,3,4,10009"), 8);
		assertEquals(StringCalculator.add("1\n2007,2,3005\n3"), 6);
		assertEquals(StringCalculator.add("//&|$\n2134&2$1234&1$7"), 10);
	}

	@Test
	public void testAddWithMultiCharDelimiters() {
		assertEquals(StringCalculator.add("//**\n1**5"), 6);
		assertEquals(StringCalculator.add("//**|$$$\n1**5$$$2"), 8);
		assertEquals(StringCalculator.add("//%%%|^^\n1^^2%%%3"), 6);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testAddwithInvalidArguments() {
	    StringCalculator.add("//:|;\n1;-10:2;3");
	}
}
