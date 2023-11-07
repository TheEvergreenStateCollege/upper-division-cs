package ballTreeAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

class Point {
    double[] coordinates;

    Point(double[] coordinates) {
        this.coordinates = coordinates;
    }

    double distance(Point other) {
        double sum = 0;
        for (int i = 0; i < this.coordinates.length; i++) {
            sum += Math.pow(this.coordinates[i] - other.coordinates[i], 2);
        }
        return Math.sqrt(sum);
    }
}