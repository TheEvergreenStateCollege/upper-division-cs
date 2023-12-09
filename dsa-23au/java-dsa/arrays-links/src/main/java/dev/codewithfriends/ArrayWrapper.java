package dev.codewithfriends;

import java.util.*;

public class ArrayWrapper<T> implements List<T> {

    private T[] _array;
    private int maxSize;
    private int currentSize;
    private String l1;

    public ArrayWrapper(int maxSize) {
        this.maxSize = maxSize;
        this._array = (T[])new Object[maxSize];
        this.currentSize = 0;
    }
    
    
    @Override
    // Returns the current size when called
    public int size() {
        return currentSize;
    }

    @Override
    // Returns boolean if empty
    public boolean isEmpty() {
        if (currentSize == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {

        for (int i = 0; i < currentSize; i++) {
            if (_array[i] == o) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator iterator() {
        throw new RuntimeException("Not yet implemented.");
    }

    @Override
    public Object[] toArray() {
        // implementation from https://bard.google.com/chat/eea8cb83d404423a
        Object[] dest = new Object[size()];

        for (int i = 0; i < dest.length; i++) {
            dest[i] = get(i);
        }

        return dest;
    }

    @Override
    public <T1> T1[] toArray(T1[] dest) {

        // Copy elements to the destination array
        int size = Math.min(size(), dest.length);
        try {
            for (int i = 0; i < size; i++) {
                dest[i] = (T1)get(i);
            }
        } catch(ClassCastException cce) {
            return null;
        }

        // If the destination array is too small, create a new array of the appropriate size
        if (size < dest.length) {
            return Arrays.copyOf(dest, size);
        }

        return dest;
    }

    @Override
    public boolean add(T o) {
        // check to ensure array has enough space, resize (2x previous max size) if not
        if (currentSize >= (maxSize / 2)) {
            T[] resized = (T[])new Object[maxSize * 2];
            for (int i = 0; i < currentSize; i++){
                resized[i] = _array[i];
            }

            maxSize = maxSize * 2;
            _array = resized;
        }

        // add element, increment currentSize
        _array[currentSize] = o;
        currentSize++;
        return true;
    }

    //TODO: finish method after implementing remove(int i)
    @Override
    //iterate through the array
    public boolean remove(Object o) {
        int count = 0; // index of object to be removed
        for (int i = 0; i < _array.length; i++) {
            if (o == _array[i]) {
                count = i;
                break;
            }
        }

        Object result = remove(count);

        if (result != null) {
            return true;
        }

        return false;

        /*//Once found, create an array and add all elements except the removed one
        int newArrSize = maxSize - 1;
        Object[] newArr = new Object[newArrSize];


        for (int i = 0; i <= maxSize; i++) {
            if (array[i] != count) {
                newArr.add(i);
            }
        }

        // if element does not exist, return false
        if (count == null) {
            return false)
        }
        this._array = newArr;*/
    }

    @Override
    public boolean addAll(Collection c) {
        for (Object o : c ) {
            try {
                add((T)o);
            } catch (ClassCastException cce) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size()) {
        throw new ArrayIndexOutOfBoundsException();
        }
        return (T)this._array[index];
    }

    @Override
    public T set(int index, T newVal) {

        if (index < 0 || index >= _array.length){
            throw new IndexOutOfBoundsException("IooR");
        }
        T oldVal = _array[index];
        _array[index] = newVal;
        return oldVal;
    }


    @Override
    public void add(int insertPoint, T newVal) {

        @SuppressWarnings("unchecked")
        T[] newArr = (T[]) new Object[_array.length + 1];

        
        if (insertPoint < 0 || insertPoint >= _array.length){
            throw new IndexOutOfBoundsException("IooR");
        }
        
        for (int i = 0; i <= _array.length; i++){
            if (i != insertPoint) {
                if (i<insertPoint){
                    newArr[i] = _array[i];
                }
                else {
                    newArr[i] = _array[i-1];
                }
            }
            else if (i == insertPoint){
                newArr[i] = newVal;
            }
        }

    }
    // TODO: check if currentSize is less than half maxSize, resize array
    // Returns null if index is out of bounds of used array indices
    @Override
    public T remove(int index) {
        // check that the index is in range
        // save the Object to be returned
        // loop from end of list to index, shifting items back one slot
        // return the saved Object

        if (index > 0 && index < currentSize) {
            T removed = (T)_array[index];

            for (int i = index + 1; i < currentSize; i++) {
                _array[i - 1] = _array[i];
            }

            return removed;
        }

        return null;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0 ; i < _array.length; i++){
            if (_array[i].equals(o)) {
                return i;
            }
        }
        // return -1 if no match found
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

}