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
     //initialize counter
        int counter = 0;    
    //while loop to find the two lowest numbers
        while(cookies.peek() < k) {
        //cookies.size < 3
            if(cookie.size < 3) {
        //if there are fewer than two cookies, return -1
                return -1;
            }
            //create two integers for first and second cookie and pop from stack
            int firstCookie = cookies.poll;
            int secondCookie = cookies.poll;
        
            //mix two cookies into a new sweeter cookie = (1 x firstCookie + 2 x secondCookie)
            int sweeterCookie = firstCookie + 2 * secondCookie;
            //add new cookie into queue
            cookies.offer(sweeterCookie);
            //increment counter
            counter++;

        }
        
    //return the number of times the cookies had to be mixed
    return counter;
    
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
