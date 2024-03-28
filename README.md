**SENG 637 - Dependability and Reliability of Software Systems**

**Lab. Report \#4 – Mutation Testing and Web app testing**

| Group \#:      | 20  |
| -------------- | --- |
| Student Names: |     |
| Usman          |     |
| Gopal          |     |
| Jubayer        |     |
| Robby          |     |
| Ehsan          |     |

# Introduction
In this lab assignment, the primary focus was on mutation testing and utilizing the Selenium tool to gain a better understanding of these concepts introduced in the lectures. Our group opted to employ the PIT testing tool for mutation testing to assess mutation coverage within Eclipse. In the second part of the lab, we utilized the Selenium IDE extension on our individual browsers. Mutation testing was integrated to enhance error detection in the original source code, with test cases designed to identify vulnerabilities in the code that may have been overlooked during initial testing. The lab also emphasized GUI testing, providing an introduction to automating test cases, particularly focusing on user interface interactions where users can record and execute their scripts.

# Analysis of 10 Mutants of the Range class 

## 1 - getLength()

**Mutation** 
Replaced double subtraction with addition -> KILLED

**Analysis**  
We replaced "return this.upper - this.lower" subraction operator with addition. This killed our tests for this class since all the expected values changed due to the change of the operator.

## 2 - getCentralValue()

**Mutation** 
Replaced double addition with division -> Killed

**Analysis**  
In the line "return this.lower / 2.0 + this.upper / 2.0;" we replaced with double addition with another division. This killed our tests because the expected values changed due to the change of the operators used in the return statement.

## 3 - contains(double value)

**Mutation** 
less than to greater than -> Killed

**Analysis**  
In the line "return (value >= this.lower && value <= this.upper);", we replaced the less-than operator ("<=") with the greater-than operator (">="). This alteration occurred within the contains(double value) method, which returns a boolean indicating whether the provided double value falls within the specified range. By changing the evaluation operators, we modified the logic determining whether a value is within the range, leading to a change in the expected boolean output of the method. Consequently, our tests failed as they were expecting different boolean values due to this operator substitution.

## 4 - intersects(double b0, double b1)

**Mutation** 
negated conditional -> Killed

**Analysis**  
In the line "return (b1 > this.lower);", we negated the conditional statement. This change occurred within the intersects(double b0, double b1) method, which verifies if both b0 and b1 fall within the range specified by the object. By negating the conditional, we altered the logic determining whether the provided values intersect with the range. Consequently, the expected boolean values differed from the actual results, leading to test failures.


## 5 - combine(Range range1, Range range2)

**Mutation** 
Removed call to Math.min -> Killed

**Analysis**  
We removed the line "double l = Math.min(range1.getLowerBound(), range2.getLowerBound());", which was essential for combining two range objects by selecting the lowest value from each. As a result, our tests lost their expected behavior regarding the lower bounds of the combined range object, leading to test failures.

## 6 - contains(double value)

**Mutation** 
Less than to not equal -> SURVIVED

**Analysis**  
We modified the line "return (value >= this.lower && value <= this.upper);" by changing the "<=" operator to "!=". This transformation aimed to check if the provided value is not equal to the lower and upper bounds of the range. Despite the mutation, the test cases still passed, indicating that the method correctly handles cases where the value is not within the specified range.

## 7 - intersects(double b0, double b1)

**Mutation** 
negated conditional -> SURVIVED

**Analysis**  
We mutated the line "if (b0 <= this.lower)" by negating the conditional to "if (!(b0 <= this.lower))". This alteration aims to invert the logic, checking if the value of b0 is not less than or equal to the lower bound of the range. The test survived because the negated conditional still ensures correct behavior in handling cases where b0 exceeds the lower bound of the range.

## 8 - expandToInclude(range range, double value)

**Mutation** 
Removed conditional-replaced comparison check with false -> KILLED

**Analysis**  
We mutated the line "if (value < range.getLowerBound())" by replacing the comparison check with false. This change effectively bypasses the conditional logic and alters the function's behavior. Specifically, regardless of the value's relationship with the lower bound of the range, the function will always return a new range with the upper bound set to the specified value. This mutation introduces a significant deviation from the intended functionality, potentially resulting in incorrect outputs when expanding the range to include the specified value. This is cause of our failures on our tests.

## 9 - Range expand(Range range, double lowerMargin, double upperMargin)

**Mutation** 
Removed call to range.getUpperBound() -> Killed

**Analysis**  
By removing the call to range.getUpperBound() in the line "double upper = range.getUpperBound() + length * upperMargin;", the calculation of the upper bound for the expanded range solely relies on the length of the range and the upper margin. This change may result in incorrect expansions of the range, as the upper bound is no longer influenced by the original upper bound of the input range. This is the cause of our test failures.

## 10 - Range shift(Range base, double delta)

**Mutation** 
Removed call to range.shift -> Killed

**Analysis**  
The removal of the call to range.shift() in the line "return shift(base, delta, false);" resulted in the failure of our tests. This call is essential for shifting the base range by the specified amount, and removing it prevents the correct execution of the shift operation. As a consequence, the absence of this call disrupts the expected behavior of the method, leading to test failures.


# Report all the statistics and the mutation score for each test class

## Range

## Range before mutation tests added
![Alt text](/Images/Range_Before_Mutations_Tests.png)


![Alt text](/Images/Range_Before_Breakdown_Statistics.png)


## Range after mutation tests added
![Alt text](/Images/Range_After_Mutations_Tests.png)


![Alt text](/Images/Range_After_Breakdown_Statistics.png)

## DataUtilities

## DataUtilities before mutation tests added
![Alt text](/Images/DataUtilities_Before_Mutations_Tests.png)


![Alt text](/Images/DataUtilities_Before_Breakdown_Statistics.png)


## DataUtilities after mutation tests added
![Alt text](/Images/DataUtilities_After_Mutations_Tests.png)


![Alt text](/Images/DataUtilities_After_Breakdown_Statistics.png)

# Analysis drawn on the effectiveness of each of the test classes

## Range  
The Range class has significantly improved from 56% to 66% mutation coverage, primarily due to the addition of new test cases in our RangeTest.java file. Below is an example of one of the added tests:

```
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
```
This test, focusing on the intersect method within the Range class, has substantially enhanced our mutation coverage. It  handles a variety of conditions and boundary cases, bolstering the robustness of our test suite. By delving into scenarios previously overlooked, such as those involving "<=", ">=", and others, it significantly boosts our mutation coverage.  

```
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
```

This test suite serves to improve our testing  for the shift method in the Range class. By  addressing diverse checks and conditions pertinent to the shift method, we've significantly augmented our test coverage. This  approach has led us to explore scenarios that were previously overlooked, enabling us to attain a higher mutation coverage. By thoroughly examining all branches of the shift method, we're effectively covering a broader spectrum of mutations, thus, improving our test suite.

```
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
```

The prevalence of mutations involving incremental or decremental local variables across nearly every method in the Range class posed significant challenges in our efforts to boost mutation coverage. While the provided test case has contributed to a slight enhancement in coverage for the contain() method, we encountered difficulties in replicating this approach across other methods. Despite our best efforts, we faced hurdles in ensuring that all JUnit tests pass while effectively examining incremental and decremental scenarios with Pitest. 


```
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
```
The following code above helped us expand our mutation coverage for intersection in the range method. It was a simple test that just allowed us to check for scenarios involving mutations we had previously overlooked.  


Overall, the effectiveness of each of the test we added varied quite a bit. The tests we added that focused on all branches of the code and the conditional operators helped improve our mutation coverage significantly. This is because a lot of the mutations involving the code involved mutation the conditional operators. We also added some test cases for some branches that we had previously overlooked, this also helped us improve our mutation coverage. The tests involving focusing on incremental and decremental operators were not very effective. This was because many different methods had different ways of handling incremental and decremental operators, making it challenging to address them uniformly across all methods. Additionally, some of the mutations involving these operators may not have been critical in terms of impacting the behavior of the methods under test.

## DataUtilities
The DataUtilities class has significantly improved from 78% to 90% mutation coverage, primarily due to the addition of new test cases in our DataUtilitiesTest.java file. Let's analyze how each new test contributes to mutation coverage: 

The test below verifies that the method correctly calculates the column total when all provided rows are valid and contain non-null values. It ensures that the method sums up values correctly across all valid rows, enhancing coverage by testing the primary, happy path scenario.  
   
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
  
The test below checks the method's handling of invalid row indices, which are outside the range of the dataset's row count. The expected result is 0.0 since these rows do not exist. This test improves mutation coverage by ensuring the method correctly handles and ignores invalid row indices.  
    
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

The test below directly contributes to increasing it for the calculateColumnTotal method that accepts a Values2D object, a column index, and an array of valid row indices. Mutation testing involves making small changes to a program's code (mutants) and checking if the test suite can detect these changes. A test suite has high mutation coverage if it can detect most mutants, indicating it can catch defects that might be introduced into the tested method.  

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
  
The test below introduces null values in the dataset and checks if the method correctly skips these nulls while calculating the total. It enhances mutation coverage by verifying the method's resilience to null values within the dataset.  
    
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
  
The test below ensures the method correctly sums up values across all valid columns. This test adds coverage by confirming the method's ability to handle a basic, valid scenario for row total calculation.  
    
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
  
The test below examines how the method behaves when given column indices that are outside the dataset's column range. Ensuring a return of 0.0 for invalid columns increases mutation coverage by validating the method's ability to ignore out-of-range columns.  
    
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
  
The test below directly tests the method's response to null data input, expecting an `IllegalArgumentException`. This scenario significantly improves mutation coverage by ensuring that the method properly validates its input parameters, adhering to its contract of not permitting null data inputs.  
  
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotalNullData() {
        DefaultKeyedValues2D values = null;
        int[] validCols = {0, 1, 2};
        DataUtilities.calculateRowTotal(values, 0, validCols);
    }
  
In summary, all these tests contribute to enhancing mutation coverage by testing different pathways through the code—valid paths, edge cases.

# A discussion on the effect of equivalent mutants on mutation score accuracy

Mutation testing is a technique used to assess the effectiveness of a test suite. In this process, the original program is systematically modified to create mutants, which are slightly altered versions of the code. A mutant is considered "killed" when the test suite produces different outputs when running the mutant compared to the original program. The higher the number of killed mutants, the more effective the test suite is at revealing faults.
However, a phenomenon that can cause issues in mutation testing is the presence of equivalent mutants. Equivalent mutants are mutants that have the same behavior as the original program, meaning the test suite cannot distinguish them from the original. As a result, these equivalent mutants are not killed, even though the test suite may be effective at revealing other types of faults.
The presence of equivalent mutants can lead to an inaccurate representation of the mutation score, which is the ratio of killed mutants to the total number of mutants. Since the test suite cannot kill the equivalent mutants, the mutation score may be lower than the actual fault-revealing power of the test suite. This can be a challenge in accurately assessing the quality and effectiveness of the test suite.
To address this issue, researchers have developed techniques to identify and remove equivalent mutants, such as using program analysis and constraint solving methods. By improving the accuracy of mutation testing, developers can better understand the strengths and weaknesses of their test suites and make more informed decisions about improving the quality of their software.

# A discussion of what could have been done to improve the mutation score of the test suites

To enhance our mutation testing strategy for the Range class, we should prioritize addressing the survived mutations involving decremental or incremental scenarios. Developing targeted test cases to cover these scenarios comprehensively would help identify and eliminate these mutations. Additionally, we should consider creating more tests specifically aimed at handling conditional operator mutations. By incorporating test cases that thoroughly exercise conditional logic, we can improve our ability to detect and eliminate mutations related to these operators.  

Regarding our DataUtilities test suite, although our mutation coverage was already high, we could further strengthen it by adding test cases that cover boundary conditions and conditional operators. While it may be time-consuming to identify and address specific mutations, investing effort in refining our test suite can yield valuable insights into potential bugss our methods. 

# Why do we need mutation testing? Advantages and disadvantages of mutation testing

Mutation testing is a valuable technique for evaluating the effectiveness of test suites by introducing mutations, into the source code and assessing whether the tests can detect these mutations. One of the advantages of mutation testing is its ability to provide a measure of test suite quality, as it identifies areas where tests fail to detect changes in the code. This highlights missing coverage in our test suite and helps build better coverage for our source code.  By revealing weaknesses in test suites, mutation testing helps improve the overall quality and reliability of software systems. 

However, there are quite a few disadvatages with mutation testing. It can be computationally expensive, as it involves generating numerous mutants and executing the entire test suite against each mutant. Interpreting mutation testing results can be complex, and identifying false positives or false negatives may require manual effort. Moreover, the effectiveness of mutation testing depends heavily on the selection and implementation of mutation operators, and limited mutation operators may not adequately represent real-world faults, leading to incomplete testing.

In conclusion, while mutation testing offers valuable insights into test suite quality and helps enhance software reliability, it also presents challenges such as computational costs and complexity.

# Explain your SELENUIM test case design process

In the process of creating our Selenium test suite, we began by thoroughly exploring the website to get a good understanding of its diverse functionalities and features which can be used for testing. We documented these functions, actions like system login, add product to card, search local nearest store, product searches for both existing and non-existing items, among others. Subsequently, we systematically executed each function with varied test inputs if it was possible for the specific test. Once we had a deep familiarity of the website's operations, we methodically made the test scenarios into Selenium scripts one by one. Our efforts culminated in the creation of 14 distinct test cases aimed at scrutinizing the system major functionality. These cases ranged from comprehensive tests like valid and invalid username logins to standalone assessments such as validating gift card codes.

# Explain the use of assertions and checkpoints

Assertions and checkpoints play a crucial role in Selenium testing by verifying that the system functions as intended. These validation mechanisms are seamlessly integrated into Selenium, simplifying their implementation. Following user interactions like clicks, scrolls, or text inputs, Selenium automatically executes these validations. During test execution, if an unexpected or incorrect input is detected, the test case halts and flags the failure at that specific checkpoint.

# how did you test each functionaity with different test data

To thoroughly assess the functionality of each test, we opted to increase the number of tests we developed. For instance, when evaluating the Login feature on the Home Depot website, we diversified our test data. For this feature, we tested a valid login information that was generated to confirm correct functionality. Conversely, we also tested login information which was not registered as a invalid input, resulting in a distinct output. By incorporating diverse test data into each function test, we were able to observe varied outputs and enhance the robustness of our testing approach.

# How the team work/effort was divided and managed

Our team initiated our Selenium testing by individually exploring the Home Depot website to identify two specific functionalities for testing. Some tests required a sequence of actions, like logging in, selecting and adding an item to the cart, proceeding to checkout, and then logging out. Once we defined our test cases, we utilized Usman's computer to compile a test suite and document each test case. Subsequently, the test creator observed the execution of their respective test to confirm its expected performance. After completing all test cases and their executions, we ran the entire test suite to evaluate its overall performance.

Transitioning to mutation testing, we delved into examining and running the DataUtilities and Range test classes using PiTest. Upon grasping the software's operation, we paired up to investigate mutations and enhance mutation coverage scores. Robby and Jubayer, as well as Ehsan and Gopal, and Usman collaborated using pair programming to develop mutation tests. One member wrote the tests while the other provided assistance in formulating the necessary logic for testing, with roles alternating between pairs. Following the completion of all mutations, we conducted tests to check for a 10% improvement in mutation coverage for Range and achieving a 99% coverage for DataUtilities.

# Difficulties encountered, challenges overcome, and lessons learned

Our team encountered significant challenges while grappling with PiTest during the lab. The absence of documentation in the lab document made it difficult to divide and execute tests for each class effectively. Initially, we faced numerous hurdles running PiTest, prompting us to shift our focus to the Selenium section. A key obstacle was the inability to run the test class if any tests failed, necessitating resolution of these issues before proceeding with mutation coverage. Additionally, varying run times of PiTest on different machines posed a challenge. Another hurdle emerged with Selenium, particularly after the test was recored and rerun it did not work properly.

# Comments/feedback on the assignment itself

The lab proved to be engaging and beneficial for gaining insights into mutations and GUI testing. Nonetheless, our group encountered challenges in configuring our environment for mutation testing. Clear and detailed instructions regarding the setup of external libraries and file structures would have been invaluable in expediting our testing process.
