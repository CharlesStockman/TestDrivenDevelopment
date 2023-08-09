Write a function that takes positive integers and outputs their string representation.

Your function should comply with the following additional rules:

If the number is a multiple of three, return the string "Fizz".
If the number is a multiple of five, return the string "Buzz".
If the number is a multiple of both three and five, return the string "FizzBuzz".
For example, given the numbers from 1 to 15 in order, the function would return:

1<br>
2<br>
Fizz<br>
4<br>
Buzz<br>
Fizz<br>
7<br>
8<br>
Fizz<br>
Buzz<br>
11<br>
Fizz<br>
13<br>
14<br>
FizzBuzz<br>

| Step   | Description                               |
|--------|-------------------------------------------|
| Step 1 | Create a test that fails                  |
 | Step 2 | Fix the test so it passes                 | 
| Step 3 | Refactor the code to make it clean        |
| Step 4 | Repeat Steps 1-3 until program fully done |

The flow usually is one way.  Write the test first the first priority
is to have it fail.  Sometimes the code is written so well that test will 
not fail.  

For example,  since I append the fizz for modulus 3 and the buzz for 
modules 5.  I got fizzbuzz for free since the test passed for modulus 15.
Remember 3 * 5 is 15

The CaptureOutput code will capture the text from the STDOUT and 
allow to assign to a String in a variable program.

