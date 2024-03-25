package org.free.data.test;
import static org.junit.Assert.*; 
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;
import org.junit.*;
import org.junit.runner.RunWith;
import java.util.*;

public class RangeTest {
	
	private Range testRange1; // Created this range to test other functionalities of the test range.
	
	
	//------------------- Range ---------------------

	// Verifies that the Range constructor successfully creates a valid range
	@Test 
	public void testRangeConstructorWithValidRange(){
	  Range range = new Range(-10, 10);
	  assertEquals("The lower bound is as expected", -10, range.getLowerBound(), .000000001d);
	  assertEquals("The upper bound is as expected", 10, range.getUpperBound(), .000000001d);
	}

	// Verifies that the Range constructor raises IllegalArgumentException for an invalid range
	@Test
	public void testRangeConstructorWithInvalidRange(){
	    try {
	        Range range = new Range(10, -10);
	        fail("Expected IllegalArgumentException to be thrown");
	    } catch (IllegalArgumentException e) {
	        assertEquals("Range(double, double): require lower (10.0) <= upper (-10.0).",
	                     e.getMessage());
	    }
	}
		

	// ------------- getLength() Tests ---------------------

	/**
	 * This test aims to evaluate the range's length when it is equal.
	 */
	
	@Test
	public void testGetLengthZero() {
		Range tempRange = new Range(0, 0); // creating a new Range object
		double length = tempRange.getLength(); // calling method to calculate the length of the range
		// assertion that expected value matches the actual value (0.0)
		assertEquals("The length should be 0.0 and it is " + length, 0.0, length, .000000001d);
		
	}
	
	/**
	 * This test is designed to examine the length of an very large positive range.
	 */
	@Test
	public void testGetLengthLargePositive() {
		Range tempRange = new Range(2, 300000000); // create a new Range object
		double length = tempRange.getLength(); // calling method to calculate the length of the range
		// assertion that expected value matches the actual value
		assertEquals("The length should be 299,999,998 and it is " + length, 299999998.0, length, .000000001d);
		
	}
	
	/**
	 * This test is designed to examine the length of an very large negative range.
	 */
	@Test
	public void testGetLengthLargeNegative() {
		Range tempRange = new Range(-300000000, -2); // create a new Range object
		double length = tempRange.getLength(); // calling method to calculate the length of the range
		// assertion that expected value matches the actual value
		assertEquals("The length should be 299,999,998 and it is " + length, 299999998.0, length, .000000001d);
		
	}
	
    
    /**
     *  This test is for checking if an exception will be thrown if an invalid range is given
     */

	Range badRange;
    @Test(expected= Exception.class)
	    public void getLength_invalid() {
    	try {
    		badRange = new Range(1,-1);
    	}catch(Exception e) {
    		badRange.getLength();
    	}
    }
	
	/**
	 * This test is intended to assess the length of a range consisting of two decimal numbers (a double).
	 */
	@Test
	public void testGetLengthRangeIsDecimal() {
		Range tempRange = new Range(-200.73, 200.50); // create a new Range object
		double length = tempRange.getLength(); // calling method to calculate the length of the range
		// assertion that expected value matches the actual value
		assertEquals("The length should be 401.23 and it is " + length, 401.23, length, .000000001d);
		
	}
	
	/**
	 * This test is intended to test the length of a range composed of non decimals
	 */
	@Test
	public void testGetLengthRangeIsNotDecimal() {
		Range tempRange = new Range(-35, 35); // create a new Range object
		double length = tempRange.getLength(); // calling method to calculate the length of the range
		// assertion that expected value matches the actual value
		assertEquals("The length should be 70 and it is " + length, 70, length, .000000001d);
		
	}
	
	
	// ------------- shift(Range base, double delta) Tests ---------------------
	
	/**
	 * Tests the behavior when the first parameter (base range) is null for the upper bound during the shift operation.
	 * This test expects an IllegalArgumentException to be thrown as InvalidParameterException is not available in the libraries.
	 */

	
	@Test(expected = IllegalArgumentException.class)
	public void shiftNullUpperBound() {
		Range testShiftedRange = Range.shift(null, 2.0);
		assertEquals("The upper bound value should be invalid", testShiftedRange.getUpperBound());
	}
	
	/**
	 *  Tests the behavior when a range is shifted by a positive value. Upper bound of the range is only checked.
	 */
	@Test
	public void shiftByPositiveDoubleUpperBound() {
		testRange1 = new Range(2, 6);
		Range testShiftedRange = Range.shift(testRange1, 121.7);
		assertEquals("The shifted value should be ", 127.7, testShiftedRange.getUpperBound(), .000000001d);
	}

	/**
	 * Tests the behavior of what happens when a range is shifted by a
	 * positive amount. We are only checking the lower bound of the range.
	 */
	@Test
	public void shiftByPositiveDoubleLowerBound() {
		testRange1 = new Range(2, 6);
		Range testShiftedRange = Range.shift(testRange1, 2.5);
		assertEquals("The shifted value should be ", 4.5, testShiftedRange.getLowerBound(), .000000001d);
	}

	/**
	 * Tests the behavior of what happens when a range is shifted by a
	 * negative amount. We are only checking the upper bound of the range.
	 */
	@Test
	public void shiftByNegativeDoubleUpperBound() {
		testRange1 = new Range(2, 6);
		Range testShiftedRange = Range.shift(testRange1, -2.2);
		assertEquals("The shifted value should be ", 3.8, testShiftedRange.getUpperBound(), .000000001d);
	}

	/**
	 *Tests the behavior of what happens when a range is shifted by a
	 * negative amount. We are only checking the lower bound of the range.
	 */
	@Test
	public void shiftByNegativeDoubleLowerBound() {
		testRange1 = new Range(2, 6);
		Range testShiftedRange = Range.shift(testRange1, -1.2);
		assertEquals("The shifted value should be ", 0.8, testShiftedRange.getLowerBound(), .000000001d);
	}

	
    // Test for shift method with zero crossing allowed
    @Test
    public void testShiftWithZeroCrossing() {
        Range base = new Range(1.0, 5.0);
        double delta = 2.0;
        boolean allowZeroCrossing = true;
        
        Range result = Range.shift(base, delta, allowZeroCrossing);
        
        double lowerBound = result.getLowerBound();
        double upperBound = result.getUpperBound();
        
        // Check if the lower bound shifted correctly
        assertEquals("The combined lower bound should be 3.0 and it is " + lowerBound,
                     3.0, lowerBound, 0.000000001d);
        
        // Check if the upper bound shifted correctly
        assertEquals("The combined upper bound should be 7.0 and it is " + upperBound,
                     7.0, upperBound, 0.000000001d);
    }
    
	@Test
	public void shiftBoundry_MutationTest() {
		Range range1 = new Range(0, 0);
		Range range2  = new Range(0, 0);
		Range range3  = new Range(10,20);

		Range resultRange1 = Range.shift(range1, 5);
		Range expectedRange1 = new Range(5, 5);
		Range resultRange2 = Range.shift(range2, -5);
		Range expectedRange2 = new Range(-5, -5);
		Range resultRange3 = Range.shift(range3, 5);
		Range expectedRange3 = new Range(15, 25);

		assertEquals(resultRange1, expectedRange1);
		assertEquals(resultRange2, expectedRange2);
		assertEquals(resultRange3, expectedRange3);

	}
	
	
	// ------------- combine(Range range1, Range range2) Tests ---------------------

	/**
	 * Tests the behavior of what happens when the first parameter entered
	 * is null. The second value, the other range, should be returned.
	 */
	@Test
	public void combineFirstParameterNullUpperBound() {
		Range tempRange = Range.combine(null, new Range(3, 9)); 
		double upperBound = tempRange.getUpperBound(); 
		assertEquals("The combined upper bound should be 9.0 and it is " + upperBound, 9.0, upperBound, .000000001d);
	}

	/**
	 * Tests the behavior of what happens when the first parameter entered
	 * is null. The second value (the other range) should be returned.
	 */
	@Test
	public void combineFirstParameterNullLowerBound() {
		Range tempRange = Range.combine(null, new Range(3, 9));
		double lowerBound = tempRange.getLowerBound(); 
		assertEquals("The combined lower bound should be 3.0 and it is " + lowerBound, 3.0, lowerBound, .000000001d);
	}

	/**
	 * Tests the behavior of what happens when the second parameter entered
	 * is null. The first value (the other range) should be returned.
	 */
	@Test
	public void combineSecondParameterNullUpperBound() {
		Range tempRange = Range.combine(new Range(-8, -2), null);
		double upperBound = tempRange.getUpperBound(); 
		assertEquals("The combined upper bound should be -2.0 and it is " + upperBound, -2.0, upperBound, .000000001d);
	}

	/**
	 *Tests the behavior of what happens when the second parameter entered
	 * is null. The first value (the other range) should be returned.
	 */
	@Test
	public void combineSecondParameterNullLowerBound() {
		Range tempRange = Range.combine(new Range(-8, -2), null);
		double lowerBound = tempRange.getLowerBound();
		assertEquals("The combined lower bound should be -8.0 and it is " + lowerBound, -8.0, lowerBound, .000000001d);
	}

	/**
	 * Tests the behavior of what happens when both parameters are null.
	 * The return value should be null and thus throw a null pointer exception
	 */
	@Test(expected = NullPointerException.class)
	public void combineBothParametersNullUpperBound() {
		Range tempRange = Range.combine(null, null); 
		double upperBound = tempRange.getUpperBound(); 
		assertNull("The combined upper bound should be null", upperBound);
	}

	/**
	 * This test will be used to test what happens when both parameters are null.
	 * The return value should be null and thus throw a null pointer exception
	 */
	@Test(expected = NullPointerException.class)
	public void combineBothParametersNullLowerBound() {
		Range tempRange = Range.combine(null, null); 
		double lowerBound = tempRange.getLowerBound(); 
		assertNull("The combined lower bound should be null", lowerBound);
	}

	/**
	 * This test will be used to test what happens when no parameters are null. A
	 * new range should be created from the combination of the two input ranges. 
	 * Upper Bound will be checked.
	 */
	@Test
	public void combineParametersPostiveNegativeUpperBound() {
		Range tempRange = Range.combine(new Range(1, 10), new Range(-8, -2));
		double upperBound = tempRange.getUpperBound();
		assertEquals("The combined upper bound should be 10.0 and it is " + upperBound, 10.0, upperBound, .000000001d);
	}

	/**
	 * Tests the behavior of what happens when no parameters are null. A
	 * new range should be created from the combination of the two input ranges. 
	 * Lower Bound will be checked.
	 */
	@Test
	public void combineParametersPostiveNegativeLowerBound() {
		Range tempRange = Range.combine(new Range(1, 10), new Range(-8, -2)); 
		double lowerBound = tempRange.getLowerBound(); 
		assertEquals("The combined lower bound should be -8.0 and it is " + lowerBound, -8.0, lowerBound, .000000001d);
	}
	
	/**
	 * Tests the behavior of what happens when we are close to zero. A
	 * new range should be created from the combination of the two input ranges.
	 */
	@Test
	public void combineParametersNearZeroBound() {
		Range tempRange = Range.combine(new Range(2,3), new Range(0, 1)); 
		double lowerBound = tempRange.getLowerBound();
		assertEquals("The combined upper bound should be 0.0 and it is " + lowerBound,0.0, lowerBound, .000000001d);
	}
	
	// Tests for the combine method in the Range class, covering various scenarios.

	// Verifies that the combine method returns the second range if the first range is null.
	@Test
	public void testCombineWithNullFirstRange() {
	    Range nullRange = null;
	    Range validRange = new Range(0, 2);
	    Range combinedRange = Range.combine(nullRange, validRange);

	    assertEquals("The lower bound should be", validRange.getLowerBound(), combinedRange.getLowerBound(), .000000001d);
	    assertEquals("The upper bound should be", validRange.getUpperBound(), combinedRange.getUpperBound(), .000000001d);
	}

    
    // ------------- getCentralValue() Tests ---------------------

	/**
	 * This method tests the behavior of getCentralValue() when the range
	 * spans across zero. The expected return value for the central value 
	 * in this case is 0.
	 */
	public void centralValueAtZero() {
	    Range tempRange = new Range(-1, 1);
	    assertEquals("The central value of -1 and 1 should be 0",
	        0, tempRange.getCentralValue(), .000000001d);
	}
	
	/**
     * This test verifies that the central value is correctly calculated
     * when the range is between positive bounds.
     */
    @Test
    public void centralValueBetweenPostiveBounds() {
        Range tempRange = new Range(1, 2);
        assertEquals("The central value of 1 and 2 should be 1.5",
            1.5, tempRange.getCentralValue(), .000000001d);
    }

    /**
     * This test verifies that the central value is correctly calculated
     * when the range is between negative bounds.
     */
    @Test
    public void centralValueBetweenNegativeBounds() {
        Range tempRange = new Range(-2, -1);
        assertEquals("The central value of -2 and -1 should be -1.5",
            -1.5, tempRange.getCentralValue(), .000000001d);
    }

    /**
     * This test verifies the central value when the range spans a large numeric range.
     */
    @Test
    public void centralValuewithLargeBounds() {
        Range tempRange = new Range(-1000000000, 2000000000);
        assertEquals("The central value of -1000000000 and 2000000000 should be 500000000",
            500000000, tempRange.getCentralValue(), .000000001d);
    }
   
    @Test
    public void containIncrementedValue_MutationTest() {
        double lower = 0;
        double upper = 10;
        double value = 5; 
        double mutatedValue = value + 1;
        
        Range range = new Range(lower, upper);
        
        boolean containsMutatedValue = range.contains(mutatedValue);
        
        assertTrue(containsMutatedValue);
    }
    
    @Test
    public void containIncrementals_MutationTest() {
        double originalLower = 0.0;
        double originalUpper = 10.0;
        
        double incrementedValue = originalUpper + 1.0; 
        double decrementedValue = originalLower - 1.0; 
        double boundaryValueLower = originalLower; 
        double boundaryValueUpper = originalUpper; 
        double withinRangeValue = (originalLower + originalUpper) / 2.0; 
        
        Range range = new Range(originalLower, originalUpper);
        
        boolean result1 = range.contains(incrementedValue); 
        boolean result2 = range.contains(decrementedValue); 
        boolean result3 = range.contains(boundaryValueLower); 
        boolean result4 = range.contains(boundaryValueUpper); 
        boolean result5 = range.contains(withinRangeValue); 
        
        assertFalse(result1);
        assertFalse(result2);
        assertTrue(result3);
        assertTrue(result4);
        assertTrue(result5);
    }

   
	

 @Test
 public void testCombineIgnoringNaNWithMinAndMax() {
     double notANumber1 = Math.sqrt(-100);
     double notANumber2 = Math.sqrt(-4);  
     Range validRange = new Range(0, 4);   
     Range combinedResult = Range.combineIgnoringNaN(validRange, new Range(notANumber1, notANumber2));
     assertEquals("The lower bound is", combinedResult.getLowerBound(), 0, .000000001d);
 }

    
    // ------------- getLowerBound() Tests ---------------------

    /**
     * Tests the behavior of getLowerBound() when the range spans from negative to 
     * positive number, -1 to 1. The expected lower bound value in this case is -1.
     */
    @Test
    public void lowerBoundShouldBeNegOne() {
        Range tempRange = new Range(-1, 1);
        double lowerBound = tempRange.getLowerBound();
        assertEquals("The lower bound value of -1 and 1 should be -1", -1, lowerBound, .000000001d);
    }

    /**
     * Tests the behavior of getLowerBound() when the range spans from small positives, 1 to 2.
     * The expected lower bound value in this case is 1.
     */
    @Test
    public void lowerBoundShouldBePostiveOne() {
        Range tempRange = new Range(1, 2);
        double lowerBound = tempRange.getLowerBound();
        assertEquals("The lower bound value of 1 and 2 should be 1", 1, lowerBound, .000000001d);
    }

    /**
     * Tests the behavior of getLowerBound() when the range spans large negatives from -1000000000 to 2000000000.
     * The expected lower bound value in this case is -1000000000.
     */
    @Test
    public void lowerBoundShouldBeBigNeg() {
        Range tempRange = new Range(-1000000000, 2000000000);
        double lowerBound = tempRange.getLowerBound();
        assertEquals("The lower bound value of -1000000000 and 2000000000 should be -1000000000",
                -1000000000, lowerBound, .000000001d);
    }

    /**
     * Tests the behavior of getLowerBound() when the range spans large positives from 1000000 to 2000000.
     * The expected lower bound value in this case is 1000000.
     */
    @Test
    public void lowerBoundShouldBeBigPositive() {
        Range tempRange = new Range(1000000, 2000000);
        double lowerBound = tempRange.getLowerBound();
        assertEquals("The lower bound value of 1000000 and 2000000 should be 1000000", 1000000, lowerBound, .000000001d);
    }

    /**
     * Tests the behavior of getLowerBound() when the range spans decimal number from 1.5 to 3.
     * The expected lower bound value in this case is 1.5.
     */
    @Test
    public void lowerBoundShouldBeDecimal() {
        Range tempRange = new Range(1.5, 3);
        double lowerBound = tempRange.getLowerBound();
        assertEquals("The lower bound value of 1.5 and 3 should be 1.5", 1.5, lowerBound, .000000001d);
    }
    
    
    
    //-------------------- expand (Range range, double lowerMargin, double upperMargin)  --------------------------

    
    // Tests for the expand method in the Range class with different margin values.

    @Test
    public void testExpandWithDecimals() {
        Range inputRange = new Range(2, 6);
        Range expandedRange = Range.expand(inputRange, 0.25, 0.5);

        assertEquals("The lower margin range is", 1, expandedRange.getLowerBound(), .000000001d);
        assertEquals("The upper margin range is", 8, expandedRange.getUpperBound(), .000000001d);
    }


    @Test
    public void testExpandWithNegativeRange() {
        Range inputRange = new Range(-6, -2);
        Range expandedRange = Range.expand(inputRange, 0.44, 0.33);

        assertEquals("The upper margin range is", -0.68, expandedRange.getUpperBound(), .000000001d);
    }

    @Test
    public void testExpandWithNegativeMargins() {
        Range inputRange = new Range(-6, -2);
        Range expandedRange = Range.expand(inputRange, -0.29, -0.35);

        assertEquals("The upper margin range is", -3.4, expandedRange.getUpperBound(), .000000001d);
    }
    
    // Test for expand method with valid range and margins
    @Test
    public void testExpandValidRangeAndMargins() {
        Range range = new Range(1.0, 5.0);
        double lowerMargin = 0.1;
        double upperMargin = 0.2;

        Range expandedRange = Range.expand(range, lowerMargin, upperMargin);

        // Check if the lower bound of the expanded range is correct
        assertEquals("The lower bound of the expanded range should be 0.6",
                0.6, expandedRange.getLowerBound(), 0.000000001d);

        // Check if the upper bound of the expanded range is correct
        assertEquals("The upper bound of the expanded range should be 5.8",
                5.8, expandedRange.getUpperBound(), 0.000000001d);
    }
    
    // Test for expand method with valid range and zero margins
    @Test
    public void testExpandValidRangeZeroMargins() {
        Range range = new Range(1.0, 5.0);
        double lowerMargin = 0.0;
        double upperMargin = 0.0;

        Range expandedRange = Range.expand(range, lowerMargin, upperMargin);

        // Check if the lower bound of the expanded range is correct
        assertEquals("The lower bound of the expanded range should be 1.0",
                1.0, expandedRange.getLowerBound(), 0.000000001d);

        // Check if the upper bound of the expanded range is correct
        assertEquals("The upper bound of the expanded range should be 5.0",
                5.0, expandedRange.getUpperBound(), 0.000000001d);
        
    }
    
    
    
    //------------------------------ hashCode ---------------------------------------
    // Verifies that the hashCode function produces the expected hash code within a valid numerical range
    
    @Test
    public void hashCode_valid(){
      Range range = new Range(-10,10);
      int result = range.hashCode();
      assertEquals("The hashcode is", 7.077888E7, result, .000000001d);
    }
    
    // Verifies that the hashCode function handles NaN ranges and produces the expected hash code
    @Test
    public void hashCode_NaN(){
        Range range = new Range(Double.NaN,Double.NaN);
        int result = range.hashCode();
        assertEquals("The hashcode is", -1.572864E7, result, .000000001d);
      }
    


    //Tests for getLowerBound() method
    @Test
    public void lowerBoundShouldBeMinusOne() {
        Range exampleRange = new Range(-1, 1);
        assertEquals("Lower bound should be -1", -1, exampleRange.getLowerBound(), .000000001d);
    }
    
    //Another Test for getLowerBound() method
    @Test
    public void lowerBoundShouldBeOne() {
        Range exampleRange = new Range(1, 500);
        assertEquals("Lower bound should be 1", 1, exampleRange.getLowerBound(), .000000001d);
    }
    
    

    // Tests for getUpperBound() method
    @Test
    public void upperBoundShouldBeMinusOne() {
        Range exampleRange = new Range(-240, -1);
        assertEquals("Upper bound should be -1", -1, exampleRange.getUpperBound(), .000000001d);
    }

    @Test
    public void upperBoundShouldBeOne() {
        Range exampleRange = new Range(-100, 1);
        assertEquals("Upper bound should be 1", 1, exampleRange.getUpperBound(), .000000001d);
    }
   
    // Tests for constrain() method
    @Test
    public void constrainedInRange() {
    	Range exampleRange = new Range(-1, 1);
        assertEquals("Constrained value should be 0", 0, exampleRange.constrain(0), 0.0001d);
    }

    @Test
    public void constrainedUpperBound() {
    	Range exampleRange = new Range(-1, 1);
        assertEquals("Constrained value should be 1", 1, exampleRange.constrain(1), 0.0001d);
    }

    @Test
    public void constrainedLowerBound() {
    	Range exampleRange = new Range(-1, 1);
        assertEquals("Constrained value should be -1", -1, exampleRange.constrain(-1), 0.0001d);
    }

    @Test
    public void constrainedTowardLowerBound() {
    	Range exampleRange = new Range(-1, 1);
        assertEquals("Constrained value should be -1", -1, exampleRange.constrain(-51239.34), 0.0001d);
    }

    @Test
    public void constrainedTowardUpperBound() {
    	Range exampleRange = new Range(-1, 1);
        assertEquals("Constrained value should be 1", 1, exampleRange.constrain(51239.34), 0.0001d);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetUpperBound_InvalidRange() {
        Range range = new Range(5.0, 2.0);  // Set up a range with lower > upper
        range.getUpperBound(); // This should throw IllegalArgumentException
    }
    
    
    //----------------------------------------- combineIgnoringNaN ------------------------------------
    
    // Verifies that combineIgnoringNaN returns the second valid range if the first range is null
    @Test 
    public void combineIgnoringNaN_firstRangeNull(){
      Range nullRange = null;
      Range validRange = new Range(0, 5);
      Range result = Range.combineIgnoringNaN(nullRange, validRange);
      assertEquals("The lower bound is", validRange.getLowerBound(), result.getLowerBound(), .000000001d);
      assertEquals("The upper bound is", validRange.getUpperBound(), result.getUpperBound(), .000000001d);
    }
    
    // Verifies that combineIgnoringNaN returns null if the first range is null and the second range contains NaNs
    @Test 
    public void combineIgnoringNaN_firstRangeNull_secondRangeNaN(){
      Range nullRange = null;
      Range nanRange = new Range(Double.NaN, Double.NaN);
      Range result = Range.combineIgnoringNaN(nullRange, nanRange);
      assertNull(result);
    }
    
    // Verifies that combineIgnoringNaN returns the first valid range if the second range is null
    @Test
    public void combineIgnoringNaN_secondRangeNull(){
      Range validRange = new Range(0, 5);
      Range nullRange = null;
      Range result = Range.combineIgnoringNaN(validRange, nullRange);
      assertEquals("The lower bound is", validRange.getLowerBound(), result.getLowerBound(), .000000001d);
      assertEquals("The upper bound is", validRange.getUpperBound(), result.getUpperBound(), .000000001d);
    }
    
    // Verifies that combineIgnoringNaN returns the false if second range is NaN
    @Test
    public void combineValidRange_NanRange(){
      Range validRange = new Range(0, 5);
      Range nanRange = new Range(Double.NaN,Double.NaN);
      Range result = Range.combineIgnoringNaN(validRange, nanRange);
      assertFalse(result.isNaNRange());
    }
    
    // Verifies that combineIgnoringNaN returns null if the second range is null and the first range contains NaNs
    @Test 
    public void combineIgnoringNaN_firstRangeNaN_secondRangeNull(){
      Range nanRange = new Range(Double.NaN, Double.NaN);
      Range nullRange = null;
      Range result = Range.combineIgnoringNaN(nanRange, nullRange);
      assertNull(result);
    }

    // Verifies that combineIgnoringNaN returns null if both ranges are null
    @Test
    public void combineIgnoringNaN_bothNull(){
      Range nullRange = null;
      Range result = Range.combineIgnoringNaN(nullRange, nullRange);
      assertNull(result);
    }
    
    // Verifies that combineIgnoringNaN returns null if both ranges have NaNs
    @Test
    public void combineIgnoringNaN_bothNaN(){
      Range nanRange1 = new Range(Double.NaN, Double.NaN);
      Range nanRange2 = new Range(Double.NaN, Double.NaN);
      Range result = Range.combineIgnoringNaN(nanRange1, nanRange2);
      assertNull(result);
    }
    


  //-------------------------------- Scale -----------------------------------------

	 // Validates the correct behavior of the scale function with positive factors
	 @Test
	 public void testScaleWithPositiveFactor() {
	   Range originalRange = new Range(-10, 10);
	   Range scaledRange = Range.scale(originalRange, 1.1);
	   assertEquals("Scaled lower bound is as expected", -11, scaledRange.getLowerBound(), .000000001d);
	   assertEquals("Scaled upper bound is as expected", 11, scaledRange.getUpperBound(), .000000001d);
	 }
	
	 // Verifies that the scale function throws an IllegalArgumentException for a negative factor
	 @Test(expected = IllegalArgumentException.class)
	 public void testScaleWithNegativeFactor() {
	   Range originalRange = new Range(-10, 10);
	   Range scaledRange = Range.scale(originalRange, -1);
	 }

	 //-------------------------------------- ToString -----------------------------------------
	 
	 @Test
	 public void centralValueShouldBeZero() {
	     // Test the getCentralValue method for a range with values -1.0 and 1.0
	     Range testRange = new Range(-1.0, 1.0);
	     assertEquals("The central value of -1 and 1 should be 0", 0, testRange.getCentralValue(), .000000001d);
	 }
	
	 @Test
	 public void toStringShouldFormatBaseCaseCorrectly() {
	     // Test the toString method for a basic range with values -1.0 and 1.0
	     Range baseRange = new Range(-1.0, 1.0);
	     assertEquals("A String \"Range[lower,upper]\" where lower=lower range and upper=upper range.",
	             "Range[-1.0,1.0]", baseRange.toString());
	 }
	
	 @Test
	 public void toStringShouldFormatPositiveRangeCorrectly() {
	     // Test the toString method for a range with positive values 5.0 and 10.0
	     Range positiveRange = new Range(5.0, 10.0);
	     assertEquals("A String \"Range[lower,upper]\" where lower=lower range and upper=upper range.",
	             "Range[5.0,10.0]", positiveRange.toString());
	 }
	
	 @Test
	 public void toStringShouldFormatNegativeRangeCorrectly() {
	     // Test the toString method for a range with negative values -10.0 and -5.0
	     Range negativeRange = new Range(-10.0, -5.0);
	     assertEquals("A String \"Range[lower,upper]\" where lower=lower range and upper=upper range.",
	             "Range[-10.0,-5.0]", negativeRange.toString());
	 }
	
	 @Test
	 public void toStringShouldFormatMixedRangeCorrectly() {
	     // Test the toString method for a range with negative and positive values -10.0 and 10.0
	     Range mixedRange = new Range(-10.0, 10.0);
	     assertEquals("A String \"Range[lower,upper]\" where lower=lower range and upper=upper range.",
	             "Range[-10.0,10.0]", mixedRange.toString());
	 }
 
	 //-------------------------- Intersects --------------------------------------------

	 private Range exampleRange;
	 private double x1, y1;
	 private boolean expectedResult;

	 @Parameters
	 public static Collection<Object[]> parameters() {
     return Arrays.asList(new Object[][] {
             {-1, 1, true},
             {-5, -1, true},
             {-2, 0, true},
             {-1, 0, true},
             {-2, 2, true},
             {1, 2, true},
             {0, 2, true},
             {0, 1, true},
             {-0.5, 0.5, true},
             {2, 3, false},
             {-5, -2, false}
     	});
	 }

	 @Test
	 public void shouldIntersectRanges() {
	     exampleRange = new Range(-1, 1);
	     Assume.assumeTrue(expectedResult);
	     assertTrue("Ranges should intersect", exampleRange.intersects(x1, y1));
	 }
	
	
	 @Test
	 public void shouldIntersectRangesWithObject() {
	     exampleRange = new Range(-1, 1);
	     Range toCompare = new Range(-1, 1);
	     assertTrue("Ranges intersect", exampleRange.intersects(toCompare));
	 }
	 
	 @Test
	 public void Intersects_MissingBounds_MutationTest() {
	     Range exampleRange = new Range(-1, 1);
	     Range mutatedRange = new Range(0, 0);
	     
	     assertTrue(exampleRange.intersects(mutatedRange));
	 }
	 
	 @Test
	 public void Intersects_ModifiedIntersectionCheck_MutationTest() {
	     Range exampleRange = new Range(-1, 1);
	     Range mutatedRange = new Range(0, 2); 
	     
	     assertTrue(exampleRange.intersects(mutatedRange));
	 }
	 
		@Test
		public void intersectBoundry_MutationTest() {	
			Range range1 = new Range(0, 10);
			Range range2 = new Range(-20,-10);

			assertTrue(range1.intersects(5, 5));
			assertFalse(range1.intersects(10, 10));
			assertFalse(range1.intersects(100, 10));	
			assertTrue(range2.intersects(-15, -15));
			assertFalse(range2.intersects(-20,-20));
			assertFalse(range2.intersects(-10, -10));

		}
 
	//--------------------------- Equals ---------------------------------
	 
	 private Range testRange;
	 private double lowerBound, upperBound;
	 private boolean expectedResult1;
	
	
	 @Parameters 
	 public static Collection<Object[]> parameters1() {
	     return Arrays.asList(new Object[][] {
	         {-1, 1, true}, 
	         {-0.5, 0.5, false},
	         {0, 1, false}, 
	         {-50, 1, false}, 
	         {-1, 5, false}, 
	         {-1, 0, false}, 
	         {50, 100, false}, 
	         {-50, -5, false}
	     });
	 }
	
	 @Test
	 public void invalidRangePassed() {
	     testRange = new Range(-1, 1);
	     assertFalse("Ranges should not be equal", testRange.equals(new Object()));
	 }
	
	 @Test
	 public void rangesShouldBeEqual() {
	     testRange = new Range(-1, 1);
	     Assume.assumeTrue(expectedResult);
	     assertTrue("Ranges should be equal", testRange.equals(new Range(lowerBound, upperBound)));
	 }
	
	 @Test
	 public void rangesShouldNotBeEqual() {
	     testRange = new Range(-1, 1);
	     Assume.assumeTrue(!expectedResult);
	     assertFalse("Ranges should not be equal", testRange.equals(new Range(lowerBound, upperBound)));
	 }
	 
	 @Test
	 public void testExpandToInclude_NullRangeWithPositiveValue() {
	     Range result = Range.expandToInclude(null, 5.0);
	     assertEquals("Expanding null range with positive value", new Range(5.0, 5.0), result);
	 }
	
	 @Test
	 public void testExpandToInclude_NullRangeWithZero() {
	     Range result = Range.expandToInclude(null, 0.0);
	     assertEquals("Expanding null range with zero", new Range(0.0, 0.0), result);
	 }
	
	 @Test
	 public void testExpandToInclude_ExistingRangeWithSmallerNegativeValue() {
	     Range range = new Range(-2.0, 3.0);
	     Range result = Range.expandToInclude(range, -5.0);
	     assertEquals("Expanding existing range with smaller negative value", new Range(-5.0, 3.0), result);
	 }
	
	 @Test
	 public void testExpandToInclude_ExistingRangeWithLargerPositiveValue() {
	     Range range = new Range(2.0, 5.0);
	     Range result = Range.expandToInclude(range, 10.0);
	     assertEquals("Expanding existing range with larger positive value", new Range(2.0, 10.0), result);
	 }
	
	 @Test
	 public void testExpandToInclude_ExistingRangeWithValueWithinRange() {
	     Range range = new Range(-2.0, 2.0);
	     Range result = Range.expandToInclude(range, 0.5);
	     assertEquals("Expanding existing range with value within range", new Range(-2.0, 2.0), result);
	 }
	
	 @Test
	 public void testExpandToInclude_ExistingRangeWithValueEqualToLowerBound() {
	     Range range = new Range(-3.0, 1.0);
	     Range result = Range.expandToInclude(range, -3.0);
	     assertEquals("Expanding existing range with value equal to lower bound", new Range(-3.0, 1.0), result);
	 }
	
	 @Test
	 public void testExpandToInclude_ExistingRangeWithValueEqualToUpperBound() {
	     Range range = new Range(0.0, 5.0);
	     Range result = Range.expandToInclude(range, 5.0);
	     assertEquals("Expanding existing range with value equal to upper bound", new Range(0.0, 5.0), result);
	 }

	
	 @Test
	 public void testExpandToInclude_ExistingRangeWithNaNValue() {
	     Range range = new Range(-2.0, 2.0);
	     Range result = Range.expandToInclude(range, Double.NaN);
	     assertEquals("Expanding existing range with NaN value", new Range(-2.0, 2.0), result);
	 }

 
}