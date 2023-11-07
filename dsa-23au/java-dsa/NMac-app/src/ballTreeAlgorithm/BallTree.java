// A ball tree is a binary tree in which every node defines a D-dimensional ball containing a subset of the points
// to be searched.
// Each internal node of the tree partitions the data points into two disjoint sets which are associated
// with different balls.
// While the balls themselves may intersect,
// each point is assigned to one or the other ball in the partition according to its distance from the ball's center.
// Each leaf node in the tree defines a ball and enumerates all data points inside that ball.
//
//
// Base Case: If the input array D contains only a single point, the function creates a leaf node B containing
// this single point and returns B.
//
// Recursive Case: If there is more than one point:
//
// The program identifies the dimension c with the greatest spread among the points in D.
// The spread could be calculated as the difference between the maximum and minimum values along each dimension.
// It selects a central point p based on dimension c. Typically, this could be the median point when the points
// are sorted along dimension c.
// The set of points D is divided into two subsets L and R, where L contains points lying to the left (or less than)
// the central point's value along dimension c, and R contains points lying to the right (or greater than).
// It then creates a new node B, setting p as its pivot point.
// The function calls itself recursively to construct the left and right subtrees from the subsets L and R,
// and these subtrees are assigned as the children of B.
// It calculates the radius of B, which is the maximum distance from the pivot point p
// to any of the points in its children.

// Finally, it returns the node B.
// Constructing the Tree: The function will be called initially with the entire dataset D, and through recursion,
// it will build up the Ball Tree by splitting the dataset into smaller and smaller subsets until it reaches the base case.
//
// Output: The output of the function is the root node B of the constructed Ball Tree.
// This root node will have attributes such as pivot, child1, child2,
// and radius, which are set during the construction process.
//

package ballTreeAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

public class BallTree {
    public static BallTreeNode constructBallTree(Point[] points) {
        if (points.length == 1) {
            // Create a leaf B containing the single point
            return new BallTreeNode(points[0]);
        } else {
            // Determine the dimension of greatest spread
            int c = dimensionOfGreatestSpread(points);

            // Find the central point
            Point p = findCentralPoint(points, c);

            // Partition the dataset around the median point
            Point[] leftPoints;
            Point[] rightPoints;
            Point[] medianPoints = partitionDataSet(points, c);
            leftPoints = Arrays.copyOfRange(medianPoints, 0, medianPoints.length / 2);
            rightPoints = Arrays.copyOfRange(medianPoints, medianPoints.length / 2, medianPoints.length);

            // Recursively construct the child trees
            BallTreeNode B = new BallTreeNode(p);
            B.child1 = constructBallTree(leftPoints);
            B.child2 = constructBallTree(rightPoints);

            // Set the radius of B
            B.radius = calculateRadius(B, points);

            return B;
        }
    }

    // Helper methods
    private static int dimensionOfGreatestSpread(Point[] points) {
        // Implementation needed
        return 0; // Placeholder
    }

    private static Point findCentralPoint(Point[] points, int dimension) {
        // Implementation needed
        return points[points.length / 2]; // Placeholder
    }

    private static Point[] partitionDataSet(Point[] points, int dimension) {
        // Sort points based on specified dimension
        Arrays.sort(points, Comparator.comparingDouble(p -> p.coordinates[dimension]));
        return points;
    }

    private static double calculateRadius(BallTreeNode B, Point[] points) {
        double maxDistance = 0;
        for (Point p : points) {
            double distance = B.pivot.distance(p);
            if (distance > maxDistance) {
                maxDistance = distance;
            }
        }
        return maxDistance;
    }

    public static void main(String[] args) {
        // Matrix of points
        Point[] points = {
                new Point(new double[]{3.0, 2.0}),
                new Point(new double[]{6.0, 7.0}),
                new Point(new double[]{9.0, 1.0}),
                //new Point(new double[]{7.0, 33.0}),
                //new Point(new double[]{12.0, 20.0}),
                //new Point(new double[]{18.0, 26.0}),

        };

        BallTreeNode root = constructBallTree(points);
        System.out.println("BallTree constructed with root pivot at: " + Arrays.toString(root.pivot.coordinates));
    }
}
