package dev.codewithfriends;
<<<<<<< HEAD

import java.util.Comparator;
import java.util.Random;
=======
import java.util.Arrays;

>>>>>>> 46ed45751fe022126556898b93f052add9f6aec3
/**
 * Hello world!
 *
 */
<<<<<<< HEAD
public class App
{

    public static void main( String[] args )
    {   
        int[] valArr = {1, 3, 5, 9};
        mergeSort(valArr);
    }

    public static <T extends Comparable<T>> T[] insertionSort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].compareTo(key) > 0) {
=======
public class App 
{
    public static void main( String[] args )
    {
        int[] array = {1,0,2,9,3,7,5,8};

        System.out.println(Arrays.toString(array));

        int[] newArray = mergeSort(array);
        
        System.out.println(Arrays.toString(newArray));
        
    }

    public static int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
>>>>>>> 46ed45751fe022126556898b93f052add9f6aec3
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        return arr;
    }
<<<<<<< HEAD
    public static int[] mergeSort(int[] array){
        return mergeSortHelper(array,0, array.length - 1);

    }
        // @param arr - 
        // @parm start - begniing / lefmost inde, inclsice, to start mergesort
        // @param end - 
        // @return 
        
    public static int[] mergeSortHelper(int[] arr, int start, int end){
        int[] newArray = new int[end - start + 1]; 
                
        // Check if arr requested part of the array in size 1
        if (start == end) // Base Case
            return arr;  
        
        if (start < end){
            int center = (start + end) /2;

            mergeSortHelper(arr, start, start + center);       // left half
            mergeSortHelper(arr, start, center + 1);     // right half
            merge(arr, newArray, start, center, end);

            int leftHalfPointer = start;
            int rightHalfPointer = start + center;
            for(int i = 0; leftHalfPointer < rightHalfPointer; i++){
                if(arr[leftHalfPointer] <= arr[rightHalfPointer]){
                    newArray[i] = arr[leftHalfPointer];
                }
                else{
                    newArray[i] = arr[rightHalfPointer];
                }
            }
            System.out.println(newArray);
            return arr;
        }
        //Split into left and right
        //Recursively call mergeSortHelper on left and right halves
        return newArray;
    }
    
        public static int[] merge(int[] arr, int[] newArray, int start, int center, int end)
        {
            int leftEnd = end -1;
            int tmpPos = start;
            int numElements = end - start + 1;

            // Main Loop

            while(start <= leftEnd && center <= end){
                if (arr[start] < arr[end]){
                    newArray[tmpPos++] = arr[start++];
                } else
                    newArray[tmpPos++] = arr[end++];
                }
            while(start <= leftEnd){
                newArray[tmpPos++] = arr[start++];
            }
            while(center <= end){
                newArray[tmpPos++] = arr[end++];
            }
            for (int i =0; i < numElements; i++, end--)
                arr[end] = newArray[end];

            return arr;

    }

    public static void quickSort(int[] array, int low, int high){
        if(low < high){
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }

    }

    public static int partition(int[] array, int low, int high){

        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}

=======
    
    
    public static int[] mergeSort(int[] array){
        return mergeSortHelper(array, 0, array.length);

    }

    public static int[] mergeSortHelper(int[] arr, int start, int end){

        //System.out.println(Arrays.toString(arr));

       
        int length = end - start;
        int mid = start + (length / 2);
        int[] newArray = new int[length]; 

        //if there is only one element

        if(length <= 1){
            newArray[0] = arr[start];
            return newArray;
        }

        //Split into left and right
        //Recursively call mergeSortHelper on left and right halves

        int[] left  = mergeSortHelper(arr, start, mid);       // left half
        int[] right = mergeSortHelper(arr, mid, end);     // right half
        
        int leftIndex = 0;
        int rightIndex = 0;

        for(int i = 0; i < length; i++){
            if ((leftIndex >= mid - start) || rightIndex >= end - mid || left[leftIndex] <= right[rightIndex]) {
                newArray[i] = left[leftIndex];
                leftIndex++;
                
            } else {
                newArray[i] = right[rightIndex];
                rightIndex++;
            }
        }

        left = null;
        right = null;

        return newArray;
    } 
}
>>>>>>> 46ed45751fe022126556898b93f052add9f6aec3
