package com.pswishcorp.app;

public class HeapSort {
    /**
     * Method for HeapSort 
     * @ param 1 the index of an item in the heap
     * @ return the index of the left child 
     * */
    

    private static int leftChild (int i) {
        return 2 * i +1 ;
    }

    /**
     * Method for heapsort that is used in deleteMax and buildHeap.
     * @param a an array of Comparable items.
     * @int i the position from which to percolate down.
     * @int n the logical size of the binary heap.  
     */

    private static <AnyType extends Comparable<? super AnyType>> void percDown (AnyType [] a, int i, int n){
        AnyType tmp;
        int child;
        for (tmp = a[i]; leftChild(i) < n; i = child){
            child = leftChild(i);
            if(child != n -1 && a[child].compareTo(a[child+1]) < 0){
                child++;
            }
            if(tmp.compareTo(a[child])< 0){
                a[i] = a[child];
            }            
            else
                break;
        }
        a[i] = tmp;
    }

    /**
     * Standard heapsort.
     * @param a an array of Comparable items
     */
    public static <AnyType extends Comparable<? super AnyType>> void heapsort(AnyType [] a){
        for (int i = a.length /2 -1; i >= 0; i--){    /*buildHeap */
            percDown(a, i, a.length);
        }
        for (int i = a.length - 1; i > 0; i--){    /* deleteMax*/
            swapReferences (a, 0, i);
            percDown(a, 0, i);
        }
    }

    private static <AnyType extends Comparable<? super AnyType>> void swapReferences(AnyType[] a, int i, int j) {
        AnyType tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
