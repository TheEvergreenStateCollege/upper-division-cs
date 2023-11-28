FUNCTION divisibleSubsetSize(k, s):
    // Step 1: Create an array to track the count of remainders
    remainderCount = Array of Zeros with size k

   // Step 2: Count the remainders in the array
    FOR i FROM 0 TO size(s) - 1:
        remainderCount[s[i] % k] += 1

    // Step 3: Initialize the result as the count of numbers divisible by k
    result = remainderCount[0]

    // Step 4: Calculate the size of the divisible subset
    FOR i FROM 1 TO k / 2:
        result += Maximum of remainderCount[i] and remainderCount[k - i]


    // Step 5: Return the result
    RETURN result

FUNCTION main():
    // Step 1: Read input values
    PRINT "Enter the value of k:"
    READ k

    PRINT "Enter the array of integers (space-separated):"
    READ inputArray

    // Convert the input string to an array of integers
    s = CONVERT_STRING_TO_ARRAY(inputArray)

    // Step 2: Call the divisibleSubsetSize function
    subsetSize = divisibleSubsetSize(k, s)

    // Step 3: Display the result
    PRINT "The size of the smallest divisible subset is:", subsetSize
END FUNCTION
