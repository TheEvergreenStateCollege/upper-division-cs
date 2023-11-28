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

class Result {

    /*
     * Complete the 'cookies' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY A
     */

    public static int cookies(int k, List<Integer> A) {
    //A PriorityQueue is made for cookies from list A
    PriorityQueue<Integer> cookies = new PriorityQueue<>();
    
    //all elements are added in to the list A
    cookies.addAll(A);
    
     cookies.size();
    int counter = 0;
    //while loop to find the two lowest numbers
    //cookies.size < 2
    while(cookies.peek() < k ){
        if (cookies.size() < 2 ) {
            return -1;
        }
        int firstCookie = cookies.poll();
        int secondCookie = cookies.poll();
        //sweetness = (1 x firstCookie + 2 x secondCookie)
        int newCookie = firstCookie + (2 * secondCookie);
        
        cookies.add(newCookie);
        
        counter++;
        
    } 
   return counter;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> A = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.cookies(k, A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
