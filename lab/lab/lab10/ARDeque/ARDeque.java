package lab10.ARDeque;

import java.util.Iterator;

public class ARDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ARDeque() {
        this.items = (T[]) new Object[4];
        this.nextFirst = 1;
        this.nextLast = 2;
        this.size = 0;


    }

    /**
     * @return the size of the array used in the deque.
     */
    public int itemsLength() {
        return items.length;
    }

    /**
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void addLast(T item) {
//        System.out.println("---" + nextLast);
        if (size >= itemsLength()) {
            resize(itemsLength() * 2);
        }


        items[nextLast] = item;
        if (nextLast + 1 == itemsLength()) {
            nextLast = 0;
        } else {
            nextLast++;
        }
        size++;

    }


    // LAB EXERCISE 8.3 PRINT ITEMS

    /**
     * Prints the items in the deque from first to last,
     * separated by a space, ended with a new line.
     */
    @Override
    public void printDeque() {
        if (itemsLength() == 0) {
            System.out.println();
            return;
        }

        int cur = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[cur] + " ");
            cur = plusOne(cur);
        }
        System.out.println();
    }


    // LAB EXERCISE 8.4 GET ITEM

    /**
     * Gets the item at the given index.
     * Does not mutate the deque.
     *
     * @param index is an index where 0 is the front.
     * @return the index-th item of the deque.
     * @throws IndexOutOfBoundsException if no item exists at the given index.
     */
    @Override
    public T get(int index) {

        if (index < 0 || index >= size || index >= items.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is not valid");
        }
        int cur = plusOne(nextFirst + index);
        return items[cur];
    }


    // EXERCISE 8.1 ADD TO FRONT

    /**
     * Adds an item of type T to the front of the deque.
     *
     * @param item is a type T object to be added.
     */
    @Override
    public void addFirst(T item) {

        if (size >= items.length) {
            resize(items.length * 2);
        }

        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;

    }


    // EXERCISE 8.2 DELETE FRONT

    /**
     * Deletes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     *
     * @return the first item of the deque, null if it does not exist.
     */
    @Override
    public T delFirst() {
        if (size == 0) {
            return null;
        }
        int cur = plusOne(nextFirst);
        T del = items[cur];
        items[cur] = null;
        nextFirst = plusOne(nextFirst);
        size--;

        if (size <= items.length / 4) {
            resize(items.length / 2);
        }
        return del;
    }

    @Override
    public T delLast() {

        if (items.length == 0) {
            return null;
        }

        int cur = minusOne(nextLast);
        T del = items[cur];
        items[cur] = null;
        size--;
        nextLast = minusOne(nextLast);
        if (size <= items.length / 4) {
            resize(items.length / 2);
        }


        return del;
    }

    private int plusOne(int next) {
        return (next + 1) % items.length;
    }

    private int minusOne(int next) {
        return (next - 1 + items.length) % items.length;
    }


    /* Resizes the underlying array to the target capacity. */
    @SuppressWarnings({"unchecked"})
    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        int cur = plusOne(nextFirst);
        if (capacity > items.length) {
            for (int i = 0; i < items.length; i++) {
                temp[i] = items[cur];
                cur = plusOne(cur);
            }
            items = temp;
            nextLast = size;
            nextFirst = items.length - 1;
        } else {
            for (int i = 0; i < size; i++) {
                temp[i] = items[cur];
                cur = plusOne(cur);
            }
            items = temp;
            nextFirst = items.length - 1;
            nextLast = size;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new ARDequeIterator();
    }

    // EXERCISE 10.3  ITERATOR

    /**
     * Make an iterator
     */

    private class ARDequeIterator implements Iterator<T> {
        int index;
        int count;

        public ARDequeIterator() {
            index = plusOne(nextFirst);
            count = 0;
        }

        // 1 2 3 4 5 6
        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            T item = items[index];
            index = plusOne(index);
            count++;
            return item;
        }
    }

    public static void main(String[] args) {
        ARDeque<String> deque = new ARDeque<>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        deque.printDeque();
        for (String item : deque) {
            System.out.print(item + " ");
        }
    }

}