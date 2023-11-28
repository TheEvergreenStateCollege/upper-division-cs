FUNCTION nonDivisibleSubset(k, s):
    // Step 1: Create an array to keep track of remainders
    remainderCount = new Array of Integers of size k, initialized to 0

    // Step 2: Count Remainders
    FOR each num IN s:
        remainderCount[num % k]++

    // Step 3: Handle special case (when k is even)
    IF k is even THEN
        remainderCount[k / 2] = 1

    // Step 4: Calculate the result
    result = minimum of remainderCount[0] and 1
    FOR i FROM 1 TO k / 2:
        result += maximum of remainderCount[i] and remainderCount[k - i]

    // Step 5: Return the result //change 
    RETURN result

// Main function to read input and call the nonDivisibleSubset function
FUNCTION main():
    firstMultipleInput = READ a line from input, split by space
    n = PARSE INT from firstMultipleInput[0]
    k = PARSE INT from firstMultipleInput[1]

    s = READ a line from input, split by space, and convert to a list of integers

    result = CALL nonDivisibleSubset(k, s)

    WRITE result to output
