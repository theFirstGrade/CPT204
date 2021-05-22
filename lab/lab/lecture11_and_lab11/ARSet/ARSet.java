package lecture11_and_lab11.ARSet;

import java.util.Iterator;

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

    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    // Copy paste your code from Week 10 here

    public void add(T item) {
        if (item == null) throw new IllegalArgumentException("IllegalArgumentException caught!");
        if (contains(item)) {
            return;
        }
        items[size] = item;
        size++;

    }

    public boolean contains(T item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                return true;
            }
        }

        return false;
    }


    // EXERCISE 11.2  EQUALS

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object that) {
        if (!(that instanceof ARSet)) {
            return false;
        }
        ARSet set = (ARSet) that;
        if (this.size() == 0 && set.size() == 0) {
            return true;
        }

        if (this.size() != set.size()) {
            return false;
        }

        for (int i = 0; i < this.size(); i++) {
            if (!set.contains(this.items[i])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ARSet<String> set1 = new ARSet<>();
        set1.add("a");
        set1.add("b");
        set1.add("c");
        ARSet<String> set2 = new ARSet<>();
        set2.add("b");
        set2.add("c");
        set2.add("a");
        System.out.println(set1.equals(set2));
        set2.add("d");
        System.out.println(set1.equals(set2));
    }

}