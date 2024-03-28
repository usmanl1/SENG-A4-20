package org.free.data.DataUtilitiesTest;
import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import org.junit.Test;


public class DataUtilitiesTest {

    // Test for calculateColumnTotal method with two values
    @Test
    public void calculateColumnTotalForTwoValues() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(7.5, 0, 0);
        values.addValue(2.5, 1, 0);

        double result = DataUtilities.calculateColumnTotal(values, 0);

        assertEquals(10.0, result, .000000001d);
    }
    
    // Test for calculateColumnTotal method with valid input
    @Test
    public void testCalculateColumnTotal_ValidInput() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(1.0, 0, 0);
        values.addValue(2.0, 1, 0);
        values.addValue(3.0, 2, 0);

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(6.0, result, .000000001d);
    }

    // Test for calculateColumnTotal method with an empty table
    @Test
    public void testCalculateColumnTotal_EmptyTable() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(0.0, result, .000000001d);
    }
    
    // Test for calculateColumnTotal method with two values
    @Test
    public void testCalculateColumnTotal() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(2.0, 1, 0);
        data.addValue(3.0, 2, 0);
        double total = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(5.0, total, 0.000000001d);
    }
    
    // Test for calculateColumnTotal method with a single row
    @Test
    public void testCalculateColumnTotal_SingleRow() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(5.0, 0, 0);

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(5.0, result, .000000001d);
    }

    // Test for calculateColumnTotal method with values in different column
        @Test
        public void testCalculateColumnTotal_DiffCol() {
            DefaultKeyedValues2D values = new DefaultKeyedValues2D();
            values.addValue(1.0, 0, 0);
            values.addValue(2.0, 1, 0);
            values.addValue(3.0, 2, 1);
    
            double result = DataUtilities.calculateColumnTotal(values, 0);
            assertEquals(3.0, result, .000000001d);
        }

    // Test for calculateRowTotal method with valid input
    @Test
    public void testCalculateRowTotal_ValidInput() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(1.0, 0, 0);
        values.addValue(2.0, 0, 1);
        values.addValue(3.0, 0, 2);

        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(6.0, result, .000000001d);
    }
    
    // Test for calculateRowTotal method with an empty table
    @Test
    public void testCalculateRowTotal_EmptyTable() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();

        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(0.0, result, .000000001d);
    }
    
    // Test for calculateRowTotal method with negative values
    @Test
    public void testCalculateRowTotal_NegativeValues() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(1.0, 0, 0);
        values.addValue(-2.0, 0, 1);
        values.addValue(3.0, 0, 2);

        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(2.0, result, .000000001d);
    }

    // Test for calculateRowTotal method with a single column
    @Test
    public void testCalculateRowTotal_SingleColumn() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(5.0, 0, 0);

        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(5.0, result, .000000001d);
    }

    @Test
    public void testCreateNumberArray_ValidInput() {
        double[] data = new double[] {1.0, 2.0, 3.0, 4.0};
        Number[] result = DataUtilities.createNumberArray(data);
        assertNotNull(result);
        assertEquals(data.length, result.length);
        for (int i = 0; i < data.length; i++) {
            assertEquals(data[i], result[i].doubleValue(), .000000001d);
        }
    }
    
    // Test createNumberArray with negative values in input array
    @Test
    public void testCreateNumberArray_NegativeValues() {
        double[] data = new double[] {-1.0, -2.0, -3.0};
        Number[] result = DataUtilities.createNumberArray(data);
        assertNotNull("The result should not be null", result);
        for (int i = 0; i < data.length; i++) {
            assertEquals("Each element should match the input data", data[i], result[i].doubleValue(), .000000001d);
        }
    }

    // Test for createNumberArray method with an empty array
    @Test
    public void testCreateNumberArray_EmptyArray() {
        double[] data = new double[] {};
        Number[] result = DataUtilities.createNumberArray(data);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    // Test for createNumberArray method with a single element
    @Test
    public void testCreateNumberArray_SingleElement() {
        double[] data = new double[] {42.0};
        Number[] result = DataUtilities.createNumberArray(data);
        assertNotNull(result);
        assertEquals(1, result.length);
        assertEquals(42.0, result[0].doubleValue(), .000000001d);
    }
    
    // Test createNumberArray with all zeros in input array
    @Test
    public void testCreateNumberArray_AllZeros() {
        double[] data = new double[] {0.0, 0.0, 0.0};
        Number[] result = DataUtilities.createNumberArray(data);
        assertNotNull("The result should not be null", result);
        for (Number number : result) {
            assertEquals("Each element should be zero", 0.0, number.doubleValue(), .000000001d);
        }
    }

    // Test for createNumberArray2D method with valid input
    @Test
    public void testCreateNumberArray2D_ValidInput() {
        double[][] data = new double[][] {
            {1.0, 2.0},
            {3.0, 4.0}
        };
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNotNull(result);
        assertEquals(data.length, result.length);
        for (int i = 0; i < data.length; i++) {
            assertEquals(data[i].length, result[i].length);
            for (int j = 0; j < data[i].length; j++) {
                assertEquals(data[i][j], result[i][j].doubleValue(), .000000001d);
            }
        }
    }
    
    // Test createNumberArray2D with empty array
    @Test
    public void testCreateNumberArray2D_EmptyArray() {
        double[][] data = new double[][] {};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNotNull("The result should not be null", result);
        assertEquals("The result array should be empty", 0, result.length);
    }

    // Test createNumberArray2D with single element array
    @Test
    public void testCreateNumberArray2D_SingleElement() {
        double[][] data = new double[][] {{42.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNotNull("The result should not be null", result);
        assertEquals("The result array should contain one row", 1, result.length);
        assertEquals("The result row should contain one element", 1, result[0].length);
        assertEquals("The single element should match the input data",
                     42.0, result[0][0].doubleValue(), .000000001d);
    }

    // Test for getCumulativePercentages method with valid input
    @Test
    public void testGetCumulativePercentages_ValidInput() {
        DefaultKeyedValues values = new DefaultKeyedValues();
        values.addValue( (Comparable) 0, 5);
        values.addValue( (Comparable) 1, 9);
        values.addValue( (Comparable) 2, 2);

        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull(result);
        assertEquals(0.3125, result.getValue(0).doubleValue(), .000000001d);
        assertEquals(0.875, result.getValue(1).doubleValue(), .000000001d);
        assertEquals(1.0, result.getValue(2).doubleValue(), .000000001d);
    }

    // Test for getCumulativePercentages method with an empty dataset
    @Test
    public void testGetCumulativePercentages_Empty() {
        DefaultKeyedValues values = new DefaultKeyedValues();

        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull(result);
        assertEquals(0, result.getItemCount());
    }

    // Test for getCumulativePercentages method with negative values
    @Test
    public void testGetCumulativePercentages_NegativeValues() {
        DefaultKeyedValues values = new DefaultKeyedValues();
        values.addValue( (Comparable) 0, -1);
        values.addValue( (Comparable) 1, -2);
        values.addValue( (Comparable) 2, -3);

        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull(result);

        double sumNegative = -1 - 2 - 3;
        assertEquals(-1.0 / sumNegative, result.getValue(0).doubleValue(), .000000001d);
        assertEquals((-1.0 - 2.0) / sumNegative, result.getValue(1).doubleValue(), .000000001d);
        assertEquals(1.0, result.getValue(2).doubleValue(), .000000001d); // This should sum up to 100%
    }
    
    // Test for the equal method
    @Test
    public void testEqualArrays() {
        double[][] array1 = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        double[][] array2 = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};

        boolean result = DataUtilities.equal(array1, array2);

        assertTrue(result);
    }
    
    // Test for the equal method with array a being null
    @Test
    public void testEqualArraysANull() {
        double[][] array1 = null;
        double[][] array2 = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};

        boolean result = DataUtilities.equal(array2, array1);

        assertFalse(result);
    }

    // Test for the equal method with array b being null
    @Test
    public void testEqualArraysBNull() {
        double[][] array1 = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        double[][] array2 = null;

        boolean result = DataUtilities.equal(array2, array1);

        assertFalse(result);
    }

    // Test for the equal method with different dimensions
    @Test
    public void testEqualArraysDifferentDimensions() {
        double[][] array1 = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        double[][] array2 = {{1.0}};

        boolean result = DataUtilities.equal(array1, array2);

        assertFalse(result);
    }

    // Test for the equal method with different values
    @Test
    public void testEqualArraysDifferentValues() {
        double[][] array1 = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        double[][] array2 = {{1.0, 2.0, 3.0}, {4.0, 5.0, 7.0}};

        boolean result = DataUtilities.equal(array1, array2);

        assertFalse(result);
    }

    // Test for the equal method with NaN values
    @Test
    public void testEqualArraysWithNaN() {
        double[][] array1 = {{1.0, 2.0, Double.NaN}, {4.0, 5.0, 6.0}};
        double[][] array2 = {{1.0, 2.0, Double.NaN}, {4.0, 5.0, 6.0}};

        boolean result = DataUtilities.equal(array1, array2);

        assertTrue(result);
    }
    
    // Test for the equal method when 'a' is null
    @Test
    public void testEqualArrayANull() {
        double[][] array1 = null;
        double[][] array2 = null;

        boolean result = DataUtilities.equal(array1, array2);

        assertTrue(result);
    }
    
    // Test for cloning a non-null 2D array
    @Test
    public void testCloneNonNullArray() {
        double[][] source = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};

        double[][] clone = DataUtilities.clone(source);

        assertNotSame(source, clone); 
        assertArrayEquals(source, clone);  
    }

    // Test for cloning a 2D array with some null rows
    @Test
    public void testCloneArrayWithNullRows() {
        double[][] source = {{1.0, 2.0, 3.0}, null, {4.0, 5.0, 6.0}};

        double[][] clone = DataUtilities.clone(source);

        assertNotSame(source, clone);
        
        for (int i = 0; i < source.length; i++) {
            if (source[i] != null) {
                assertArrayEquals(source[i], clone[i], 0.0001); 
            } else {
                assertNull(clone[i]);  
            }
        }
    }
    

    // Test for calculating total with valid values and valid rows
    @Test
    public void testCalculateColumnTotalValidValuesAndRows() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(5.0, 0, 0);
        values.addValue(10.0, 1, 0);
        values.addValue(15.0, 2, 0);

        int[] validRows = {0, 1, 2};

        double result = DataUtilities.calculateColumnTotal(values, 0, validRows);

        assertEquals(30.0, result, 0.0001); 
    }

    // Test for calculating total with valid values and invalid rows
    @Test
    public void testCalculateColumnTotalValidValuesInvalidRows() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(5.0, 0, 0);
        values.addValue(10.0, 1, 0);
        values.addValue(15.0, 2, 0);

        int[] invalidRows = {3, 4, 5};

        double result = DataUtilities.calculateColumnTotal(values, 0, invalidRows);

        assertEquals(0.0, result, 0.0001);  
    }

    // Test for calculating total with non-null values
    @Test
    public void testCalculateColumnTotalWithNonNullValues() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(5.0, 0, 0);
        values.addValue(10.0, 1, 0);
        values.addValue(15.0, 2, 0);

        int[] validRows = {0, 1, 2};

        double result = DataUtilities.calculateColumnTotal(values, 0, validRows);

        assertEquals(30.0, result, 0.0001);
    }

    // Test for calculating total with some null values
    @Test
    public void testCalculateColumnTotalWithNullValues() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(5.0, 0, 0);
        values.addValue(null, 1, 0);
        values.addValue(15.0, 2, 0);

        int[] validRows = {0, 1, 2};

        double result = DataUtilities.calculateColumnTotal(values, 0, validRows);

        assertEquals(20.0, result, 0.0001);
    }
    
    // Test for calculating total with valid values and valid columns
    @Test
    public void testCalculateRowTotalValidValuesAndCols() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(5.0, 0, 0);
        values.addValue(10.0, 0, 1);
        values.addValue(15.0, 0, 2);

        int[] validCols = {0, 1, 2};

        double result = DataUtilities.calculateRowTotal(values, 0, validCols);

        assertEquals(30.0, result, 0.0001);
    }
    


    // Test for calculating total with valid values and invalid columns
    @Test
    public void testCalculateRowTotalValidValuesInvalidCols() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(5.0, 0, 0);
        values.addValue(10.0, 0, 1);
        values.addValue(15.0, 0, 2);

        int[] invalidCols = {3, 4, 5};

        double result = DataUtilities.calculateRowTotal(values, 0, invalidCols);

        assertEquals(0.0, result, 0.0001);
    }

    // Test for calculating total with null data
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotalNullData() {
        DefaultKeyedValues2D values = null;
        int[] validCols = {0, 1, 2};

        DataUtilities.calculateRowTotal(values, 0, validCols);
    }

    // Test for calculating total with non-null values
    @Test
    public void testCalculateRowTotalWithNonNullValues() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(5.0, 0, 0);
        values.addValue(10.0, 0, 1);
        values.addValue(15.0, 0, 2);

        int[] validCols = {0, 1, 2};

        double result = DataUtilities.calculateRowTotal(values, 0, validCols);

        assertEquals(30.0, result, 0.0001);
    }

    // Test for calculating total with some null values
    @Test
    public void testCalculateRowTotalWithNullValues() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(5.0, 0, 0);
        values.addValue(null, 0, 1);
        values.addValue(15.0, 0, 2);

        int[] validCols = {0, 1, 2};

        double result = DataUtilities.calculateRowTotal(values, 0, validCols);

        assertEquals(20.0, result, 0.0001);
    }

}