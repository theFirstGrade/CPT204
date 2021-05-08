package lab10.ARSet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ARSet<T> implements Iterable<T> {
    private T[] items;
    private int size;

    /**
     * Create an empty set.
     */
    @SuppressWarnings("unchecked")
    public ARSet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /**
     * @return the number of items in the set
     */
    public int size() {
        return size;
    }

    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    // LAB EXERCISE 10.3  ITERATOR

    /**
     * Make an iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new ARSetIterator();
    }

    private class ARSetIterator implements Iterator<T> {
        int index;

        public ARSetIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T item = items[index];
            index++;
            return item;
        }
    }


    // EXERCISE 10.1  CONTAINS

    /**
     * Checks whether an item is inside the set.
     *
     * @param item to be checked
     * @return true iff the set contains the item
     */
    public boolean contains(T item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                return true;
            }
        }

        return false;
    }


    // EXERCISE 10.2  ADD

    /**
     * Adds an item into the set if it is not already inside.
     *
     * @param item to be added inside the set.
     * @throws IllegalArgumentException if item is null.
     */
    public void add(T item) {
        if (item == null) throw new IllegalArgumentException("IllegalArgumentException caught!");
        if (contains(item)) {
            return;
        }
        items[size] = item;
        size++;

    }

    public static void main(String[] args) {
//        List<String> list1 = new ArrayList<>();
//        list1.add("a");
//        List<String> list2 = new ArrayList<>();
//        list2.add("a");
//
//        ARSet<List<String>> set = new ARSet<>();
//        set.add(list1);
//        System.out.println(set.size());
//        System.out.println(set.contains(list2));
//        System.out.println(set.contains(list1));
//        set.add(list2);
//        System.out.println(set.size());
//        System.out.println(set.contains(list2));
//        System.out.println(set.contains(list1));

        ARSet<String> set = new ARSet<>();
        System.out.println(set.size());
        for (String item : set) {
            System.out.println(item);
        }
        System.out.println("---");
        System.out.println(set.contains(""));
        System.out.println(set.contains("a"));
        set.add("a");
        System.out.println(set.size());
        System.out.println(set.contains(""));
        System.out.println(set.contains("a"));
        set.add("a");
        System.out.println(set.contains("a"));
        System.out.println(set.size());
        set.add("a");
        set.add("a");
        System.out.println(set.contains("a"));
        System.out.println(set.size());
        System.out.println(set.contains("aa"));
        set.add("aa");
        System.out.println(set.size());
        System.out.println(set.contains("a"));
        set.add("a");
        System.out.println(set.size());
        System.out.println(set.contains("aa"));
        System.out.println(set.contains("a"));
        System.out.println(set.size());

        try {
            set.add(null);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException caught!");
        }

        System.out.println(set.size());
        set.add("aa");
        set.add("a");
        System.out.println(set.contains("aaa"));
        set.add("aaa");
        System.out.println(set.size());
        System.out.println(set.contains("aaa"));
    }

}