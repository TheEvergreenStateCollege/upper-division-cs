//merge sort
dev.codewithfriends;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BSTMain {

<<<<<<< HEAD
    public static void main(String[] args) {
      
        List<String> namesList = Arrays.asList("John", "Jane", "Bob", "Alice", "Zara", "Charlie");
        String[] sortedNames = MergeSort.mergeSort(namesList.toArray(new String[0]));
        System.out.println("Sorted Names: " + Arrays.toString(sortedNames));
    }
}
=======
    public static List<String> dfsDiagram(BinaryNode<Integer> node, List<String> diagramSoFar) {

        // mark the current node as visited
        //visited.add(node.data);

        // if there is a left, or left unvisited
        if (node.left != null) {
            diagramSoFar.add(String.format("    %s -->%s", node.data, node.left.data));
            diagramSoFar = dfsDiagram(node.left, diagramSoFar);
        }
        // if there is a right, or right unvisited
        if (node.right != null) {
            diagramSoFar.add(String.format("    %s -->%s", node.data, node.right.data));
            diagramSoFar = dfsDiagram(node.right, diagramSoFar);
        }
        // then return
        return diagramSoFar;
        // update
    }

    public static List<Integer> dfs(BinaryNode<Integer> node, List<Integer> visited) {
>>>>>>> 46ed45751fe022126556898b93f052add9f6aec3

class MergeSort {

    public static String[] mergeSort(String[] array) {
        int n = array.length;
        if (n <= 1) {
            return array;
        }

        int mid = n / 2;
        String[] left = new String[mid];
        String[] right = new String[n - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, n - mid);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

<<<<<<< HEAD
    private static String[] merge(String[] left, String[] right) {
        int leftSize = left.length;
        int rightSize = right.length;
        String[] merged = new String[leftSize + rightSize];
=======
  public static void main(String[] args) 
    {
        LinkedList<Integer> visited = new LinkedList<>();
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(73);
        bst.insert(22);
        List<Integer> result = dfs(bst.getRoot(), visited);
        for (Integer i: result){
            System.out.println(i);
        }

        List<String> diagramLines = dfsDiagram(bst.getRoot(), new LinkedList<>());
        for (String d : diagramLines) {
            System.out.println(d);
        }
>>>>>>> 46ed45751fe022126556898b93f052add9f6aec3

        int leftPointer = 0;
        int rightPointer = 0;
        int mergedPointer = 0;

        while (leftPointer < leftSize && rightPointer < rightSize) {
            if (left[leftPointer].compareTo(right[rightPointer]) <= 0) {
                merged[mergedPointer++] = left[leftPointer++];
            } else {
                merged[mergedPointer++] = right[rightPointer++];
            }
        }

        while (leftPointer < leftSize) {
            merged[mergedPointer++] = left[leftPointer++];
        }

        while (rightPointer < rightSize) {
            merged[mergedPointer++] = right[rightPointer++];
        }

        return merged;
    }
}

