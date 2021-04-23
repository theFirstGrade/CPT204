package lab8.ARDeque;

import lab6.LLDeque.LLDeque;

import java.util.Arrays;

public class ARDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;


    /**
     * @return the size of the array used in the deque.
     */
    public int itemsLength() {
        return items.length;
    }

    /**
     * @return the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * @return true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    /*
     ******************* HELPER METHODS START *******************
     ***** Include your helper method(s) in EACH Submission *****
     *********************** that uses it ***********************
     */

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

    /*
     ******************** HELPER METHODS END ********************
     */


    // LAB EXERCISE 8.1 EMPTY CONSTRUCTOR

    /**
     * Creates an empty deque.
     */
    @SuppressWarnings("unchecked")
    public ARDeque() {
        this.items = (T[]) new Object[4];
        this.nextFirst = 1;
        this.nextLast = 2;
        this.size = 0;


    }


    // LAB EXERCISE 8.2 ADD TO BACK

    /**
     * Adds an item of type T to the back of the deque.
     *
     * @param item is a type T object to be added.
     */
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


    // EXERCISE 8.3 DELETE BACK

    /**
     * Deletes and returns the item at the back  of the deque.
     * If no such item exists, returns null.
     *
     * @return the last item of the deque, null if it does not exist.
     */
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


    // EXERCISE 8.4 COPY CONSTRUCTOR

    /**
     * Creates a (deep) copy of another Deque object.
     *
     * @param other is another ARDeque<T> object.
     */
    @SuppressWarnings("unchecked")
    public ARDeque(ARDeque<T> other) {
        this();
        nextFirst = other.nextFirst;
        int cur = plusOne(other.nextFirst);

        items = (T[]) new Object[other.items.length];
        for (int i = 0; i < other.size; i++) {
            items[i] = other.items[cur];
            cur = plusOne(cur);
            size++;
        }
        nextLast = size;
        nextFirst = items.length - 1;

    }

    public static void main(String[] args) {
//        ARDeque<String> deque = new ARDeque<>();
//        System.out.println(deque.isEmpty());
//        System.out.println(deque.size());
//        System.out.println(deque.itemsLength());
//        deque.addLast("a");
//        deque.addLast("b");
//        deque.addLast("c");
//        deque.addLast("d");
//        System.out.println(deque.size());
//        System.out.println(deque.itemsLength());
//        System.out.println(deque.get(0));
//        System.out.println(deque.get(1));
//        System.out.println(deque.get(2));
//        System.out.println(deque.get(3));
//        deque.printDeque();
//        deque.addLast("e");
//        System.out.println(deque.size());
//        System.out.println(deque.itemsLength());
//        System.out.println(deque.get(0));
//        System.out.println(deque.get(3));
//        System.out.println(deque.get(4));
//        deque.printDeque();

//        ARDeque<String> deque = new ARDeque<>();
//        deque.addFirst("a");
//        try {
//            System.out.println(deque.get(1));
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println(e.getMessage());
//        }

//        ARDeque<String> deque = new ARDeque<>();
//        for (int i = 0; i < 8; i++) {
//            deque.addFirst("test");
//        }
//        System.out.println(deque.size());
//        System.out.println(deque.itemsLength());
//        deque.addLast("test");
//        System.out.println(deque.size());
//        System.out.println(deque.itemsLength());
//        deque.addFirst("test");
//        System.out.println(deque.size());
//        System.out.println(deque.itemsLength());
//        for (int i = 0; i < 5; i++) {
//            deque.delFirst();
//        }
//        System.out.println(deque.size());
//        System.out.println(deque.itemsLength());
//        deque.delLast();
//        System.out.println(deque.size());
//        System.out.println(deque.itemsLength());
//        deque.delLast();
//        System.out.println(deque.size());
//        System.out.println(deque.itemsLength());
//        deque.delLast();
//        System.out.println(deque.size());
//        System.out.println(deque.itemsLength());

        ARDeque<String> deque = new ARDeque<>();
        deque.addFirst("a");
        ARDeque<String> copyDeque = new ARDeque<>(deque);
        deque.addFirst("x");
        copyDeque.addFirst("y");
        System.out.println(deque.get(0));
        System.out.println(deque.get(1));
        System.out.println(copyDeque.get(0));
        System.out.println(copyDeque.get(1));
    }

}
