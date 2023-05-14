package com.samuelraphael.datastructures;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

// TODO: add tests for this data structure
@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 8;

    private T[] array;
    private int length = 0;
    private int capacity;

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Invalid capacity: " + capacity);
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public int size() { return length; }
    public boolean isEmpty() { return size() == 0; }
    public T get(int index) {
        if (index >= size()) throw new IllegalArgumentException("Invalid index: " + index);
        return array[index];
    }
    public void set(int index, T element) {
        if (index >= capacity) throw new IllegalArgumentException("Invalid index: " + index);
        array[index] = element;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++)
            array[i] = null;
        length = 0;
    }

    private void resize(int size) {
        T[] newArray = (T[]) new Object[size];
        if (length >= 0) System.arraycopy(array, 0, newArray, 0, length);
        array = newArray;
        capacity = size;
    }

    public void add(T element) {
        if (length + 1 >= capacity) {
            resize((capacity == 0) ? 1 : capacity * 2);
        }
        array[length++] = element;
    }

    public T removeAt(int index) {
        if (index >= length || index < 0) throw new IndexOutOfBoundsException();
        T data = array[index];
        T[] newArray = (T[]) new Object[length - 1];
        for (int i = 0, j = 0; i < length; i++, j++) {
            if (i == index) j--;
            else newArray[i] = array[i];
        }
        array = newArray;
        capacity = --length;
        return data;
    }

    public T remove(T object) {
        for (int i = 0; i < length; i++){
            if (array[i].equals(object)) {
                return removeAt(i);
            }
        }
        return null;
    }

    public int indexOf(T object) {
        for (int i = 0; i < length; i++) {
            if (array[i].equals(object)) return i;
        }
        return -1;
    }

    public boolean contains(T object) {
        return indexOf(object) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public T next() {
                return array[index++];
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        // TODO: implement this method
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        // TODO: implement this method
        return Iterable.super.spliterator();
    }

    @Override
    public String toString() {
        return "[]";
    }
}
