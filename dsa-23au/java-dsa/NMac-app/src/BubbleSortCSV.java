// The purpose of this program:
// Use the Bubble Sort algorithm to sort the CSV file

// Bubble sort is a simple comparison-based sorting algorithm.
// Its name derives from the way smaller elements "bubble" to the top of the list.
// The basic idea behind bubble sort is to repeatedly step through the list,
// compare adjacent items, and swap them if they are in the wrong order.
// This process is repeated until no more swaps are needed, which means the list is sorted.


package bubbleSortCSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BubbleSortCSV {

    // Class to represent each driving record headers from the CSV file
    public static class DrivingRecord {
        String time;
        String date;
        String elapsed;
        String orig;
        String dest;
        String detourEnroute;
        String driver;
        String distance;

        // Overriding toString to print each of the driving record columns
        @Override
        public String toString() {
            return time + ", " + date + ", " + elapsed + ", " + orig + ", " + dest + ", " + detourEnroute + ", " + driver + ", " + distance;
        }
    }


    // Main method of the program
    public static void main(String[] args) {
        // Read CSV file and get a list of driving records
        List<DrivingRecord> records = readCSV("Time_Driving_Spreadsheet_test2.csv");

        // Sort the records based on time
        bubbleSort(records);

        // Print sorted records
        System.out.println("Sorted Records by Time:");
        for (DrivingRecord record : records) {
            System.out.println(record);
        }
    }

    // Method to read the CSV and return a list of DrivingRecord
    public static List<DrivingRecord> readCSV(String filename) {
        List<DrivingRecord> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // Skip the header of the CSV
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                DrivingRecord record = new DrivingRecord();
                // Assign values from CSV to the DrivingRecord object
                record.time = values[0];
                record.date = values[1];
                record.elapsed = values[2];
                record.orig = values[3];
                record.dest = values[4];
                record.detourEnroute = values[5];
                record.driver = values[6];
                record.distance = values[7];
                // Add record to the list
                records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    // Method to sort the list of DrivingRecord using BubbleSort algorithm
    public static void bubbleSort(List<DrivingRecord> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Compare based on time value and swap if required
                if (list.get(j).time.compareTo(list.get(j + 1).time) > 0) {
                    // Swapping logic
                    DrivingRecord temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}
