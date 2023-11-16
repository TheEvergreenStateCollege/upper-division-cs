// The purpose of this program:
// Using the CSV file
// Print out the table
// Calculate the total elapsed time driving divided by the total distance

// So far the program does not read the csv file correctly.
// The program is reading driver although set to read the distance
// Not sure if the elapsed time being double minutes will conflict with it assumes to be in hours


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Driving_Analysis {

    public static class DrivingRecord {
        String time;
        String date;
        double elapsed; // Assumes this is in hours // the csv data is in minutes... that could be a problem
        String orig;
        String dest;
        String detourEnroute;
        String driver;
        double distance; // distance is in miles

        @Override
        public String toString() {
            return time + ", " + date + ", " + elapsed + ", " + orig + " ," + dest + " ," + detourEnroute + " ," + driver + " ," + distance;
        }
    }


        public static void main(String[] args) {
            List<DrivingRecord> records = readCSV("Time_Driving_Spreadsheet_test_4.csv");

            // Calculate the total elapsed time and total distance
            double totalElapsedTime = 0;
            double totalDistance = 0;
            for (DrivingRecord record : records) {
                totalElapsedTime += record.elapsed;
                totalDistance += record.distance;
            }
            double result = totalElapsedTime / totalDistance;
            System.out.println("Total Elapsed Time Driving divided by Total Distance: " + result);
        }

        public static List<DrivingRecord> readCSV(String filename) {
        List<DrivingRecord> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                DrivingRecord record = new DrivingRecord();

                record.time = values[0];
                record.date = values[1];

                // Extracting the number from "xx min" and convert to a double
                String elaspsedStr = values[2].trim().replace(" min", "");
                record.elapsed = Double.parseDouble(elaspsedStr);

                record.orig = values[3];
                record.dest = values[4];
                record.detourEnroute = values[5];
                record.driver = values[6];
                record.distance = Double.parseDouble(values[7].trim());

                records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;


    }

}