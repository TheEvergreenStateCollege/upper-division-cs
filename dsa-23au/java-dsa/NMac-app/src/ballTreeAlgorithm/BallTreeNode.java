package ballTreeAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

class BallTreeNode {
    Point pivot;
    BallTreeNode child1;
    BallTreeNode child2;
    double radius;

    BallTreeNode(Point pivot) {
        this.pivot = pivot;
    }
}
