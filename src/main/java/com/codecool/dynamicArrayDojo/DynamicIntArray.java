package com.codecool.dynamicArrayDojo;

import java.util.Arrays;

// put your code here!
class DynamicIntArray {
    private static final int INITIAL_CAPACITY = 10;

    private int arraySize;
    private int arrayCapacity = INITIAL_CAPACITY;

    private int[] array;


    /**
     * Dynamic array with initial capacity
     */
    public DynamicIntArray() {
        array = new int[INITIAL_CAPACITY];
    }

    /**
     * Dynamic array with given capacity
     */
    public DynamicIntArray(int arrayCapacity) {
        if (arrayCapacity <= 0 ) {
            throw new IllegalArgumentException("Array capacity cannot be 0 or less. Given: " + arrayCapacity);
        }

        this.arrayCapacity = arrayCapacity;
        array = new int[arrayCapacity];
    }

    /**
     *  Dynamic array from pre-existing array
     */
    public DynamicIntArray(int[] inputArray) {
        this(inputArray.length + INITIAL_CAPACITY);

        for(int i = 0; i < inputArray.length; i++) {
            array[i] = inputArray[i];
            arraySize++;
        }
    }

    /**
     * Adding element to the end of the dynamic array
     */

    void add(int element) {
        checkSize();
        array[arraySize] = element;
        arraySize++;

    }

    /**
     *  Removing specific element
     */

    boolean remove(int element) {
        int index = this.indexOf(element);
        checkRange(index);

        if (index < 0) return false;


        int valueToMove = arraySize - index - 1;
        if(valueToMove > 0) {
            System.arraycopy(array, index + 1, array, index, valueToMove);
        }

        this.arraySize--;
        this.array = Arrays.copyOf(array, arraySize);

        return true;
    }


    /**
     *  Insert element at specific index
     */
    boolean insert(int index, int element) {
        if (index > arraySize) {
            add(element);
            return true;
        }

        add(0);
        int prevElement = element;
        for (int i = index; i < arraySize; i++) {
            int tmp = prevElement;
            prevElement = array[i];
            array[i] = tmp;
        }

        this.array[index] = element;
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int element : array) {
            sb.append(" ").append(element);
        }
        return sb.toString();
    }

    private void checkSize() {
        if(arraySize + 1 >= arrayCapacity) {
            expand();
        }
    }

    private void expand() {
        arrayCapacity += 1;
        array = Arrays.copyOf(array, arrayCapacity - 1);
    }

    private void checkRange(int index) {
        if(index < 0 || index > arraySize) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private int indexOf(int element) {
        for(int i = 0; i < arraySize; i++) {
            if(element == array[i]) {
                return i;
            }
        }
        return -1;
    }
}
