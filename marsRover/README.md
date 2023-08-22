## Introduction

Implemented the Mars Rover Katra -- https://www.codurance.com/katas/mars-rover

### Lessons
<ol>
<li>Separate the creation of the test from the creation of the code.  The advantages 
were:
<ul>More confident about refactoring since there was a large set of test to verify my changes were 
correct</ul>
<li>Don't try to write all the tests at the same time.  It made the refactoring effort take longer.</li>
<li>Event during the refactoring segment you can still use Test Driven Development to add a feature.  
For example when I implemented the history for the Command Design Pattern.  I should have used TDD to 
implement the history.</li>
<li>Writing code this way, I realized that I consider both the present and future needs of the code.  
When I did programming in other ways a lot more weight is given to the present needs of the code.  
For example, The commands to move and change direction and I made the parameter list and return 
the same for each function so I could create a list of commands and then execute them.  In the future there 
could be 20 parameters needed to control the rover the code may have to be modified.</li>
<li>Remember save validation and error checking until end since certain public function could become private 
functions during and may need validation.</li>
<li>Notices that I am more selective when I am choosing a failed test to debug an issue with.  Usually look for the simpliest test</li>
</ol>
