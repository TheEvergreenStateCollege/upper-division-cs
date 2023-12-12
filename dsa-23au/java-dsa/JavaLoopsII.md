https://www.hackerrank.com/challenges/java-loops/problem

Create a class to contain your solution
Create your main function that accepts a string
Create a new scanner
Define an integer that is the next integer the scanner encounters
Create a for-loop that is less than the length of the integer you just defined
Within that loop, create three more integer values 
Each on will take the next integer the scanner encounters
Create a new variable to contain the first of the three integer values
(From this point on, HackerRank did not pre-supply a code - past Line 11)
Nest another for-loop within the for-loop you've already created 
Have the newest for-loop be less than the length of the third integer value you created in the old loop
Define a new integer within the loop 
Make the new integer equal to the current iteration squared multiplied by the second integer value you defined in the old loop
Make it so that the new variable that contained the first of three integer values which you made prior to the nested loop equals itself plus the integer defined within the nested loop 
Within the nested loop, print out that last value and then a space after it
Outside the nested loop and within the first loop, print an empty line
Make sure to close the scanner

What should this do?
The input would be four types of values
The first that initiates the first for-loop is the number of sets of three numbers that will be used within that loop  
The next three are numbers used within a series equation 
The first accepted of the three is used as the number that starts the series and that the rest will be added to 
The second is the number that will be multiplied with each next step of the loop which is the square of the last one's result (first is one, second is two, third is four and so on)
The third is the number of times this process will repeat itself, ie the number of numbers in this series
The end result should be series of integers printed in a line, and there will be as many lines as the number of the first input
