# Instructions

Create a simple String calculator with a method signature:

int Add(string numbers)

The method can take up to two numbers, separated by commas, and will 
return their sum. for example “” or “1” or “1,2” as inputs.
(for an empty string it will return 0)

Hints:
- Start with the simplest test case of an empty string and move to one and two numbers
- Remember to solve things as simply as possible so that you force yourself to write tests you did not think about
- Remember to refactor after each passing test<br>
  ———————————————————————————————<br>
  Allow the Add method to handle an unknown amount of numbers<br>
  ————————————————————————————————<br>
  Allow the Add method to handle new lines between numbers (instead of commas).<br>
  the following input is ok: “1\n2,3” (will equal 6)<br>
  the following input is NOT ok: “1,\n” (not need to prove it - just clarifying)<br>
  ——————————————————————————————-<br>
  Support different delimiters<br>
  to change a delimiter, the beginning of the string will contain a separate line that looks like this: “//[delimiter]\n[numbers…]” for example “//;\n1;2” should return three - where the default delimiter is ‘;’ .<br>
  the first line is optional. all existing scenarios should still be supported
  ————————————————————————————————<br>
  Calling Add with a negative number will throw an exception “negatives not allowed” - and the negative that was passed.
  if there are multiple negatives, show all of them in the exception message.

# Lessons 

1. Don't worry about what the code looks like when your moving the test from fail to pass.  
2. Before writing anything think about it.  For example there is a katra that does a add by using a stringl
| Way it could be resolved | 
