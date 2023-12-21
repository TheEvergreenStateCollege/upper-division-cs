import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


//HARLEE NOTE:
//Psuedo code was pretty straight forward. All I had to discover was the Big Integer data type and some math functions.
//Tested the code on the HackerRank page and it passed!
//Thank you!
class Result {

    // public longFactorial(int n)
    public static void extraLongFactorials(int n) {

        // largeN = n
        BigInteger largeN = BigInteger.valueOf(n);
        // count = n - 1
        int count = n - 1;

        
        // while count is bigger than 0 
        while(count > 0){
        //      largeN = largeN * count
        largeN = largeN.multiply(BigInteger.valueOf(count));
        //      count--
        count--;
        }
        // print largeN
        System.out.println(largeN);

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        Result.extraLongFactorials(n);

        bufferedReader.close();
    }
}
