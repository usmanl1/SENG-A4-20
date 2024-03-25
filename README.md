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

## Range before mutation tests Added
![Alt text](/Images/Range_Before_Mutation_Tests.png)


# Analysis drawn on the effectiveness of each of the test classes

# A discussion on the effect of equivalent mutants on mutation score accuracy

# A discussion of what could have been done to improve the mutation score of the test suites

# Why do we need mutation testing? Advantages and disadvantages of mutation testing

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
