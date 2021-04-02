public class LLDeque<T> {

    private class Node {
        Node prev;
        T item;
        Node next;

        Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

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


    // LAB EXERCISE 5.1 EMPTY CONSTRUCTOR

    /**
     * Creates an empty deque.
     */
    public LLDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

    }


    // LAB EXERCISE 5.2 ADD TO FRONT

    /**
     * Adds an item of type T to the front of the deque.
     *
     * @param item is a type T object added to the deque.
     */
    public void addFirst(T item) {
        sentinel.next = new Node(null, item, sentinel.next);
        size += 1;

    }


    // LAB EXERCISE 5.3 PRINT ITEMS

    /**
     * Prints the items in the deque from first to last,
     * separated by a space, ended with a new line.
     */
    public void printDeque() {
        Node temp = sentinel;
        while (temp.next != null && temp.next != temp) {
            System.out.print(temp.next.item + " ");
            temp = temp.next;
        }
        System.out.println();


    }


    // LAB EXERCISE 5.4 ITERATIVE GET ITEM

    /**
     * Gets the item at the given index.
     * If no such item exists, returns null.
     * Does not mutate the deque.
     *
     * @param index is an index where 0 is the front.
     * @return the ith item of the deque, null if it does not exist.
     */
    public T iterGet(int index) {
        Node temp = sentinel.next;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return temp.item;
            }
            temp = temp.next;
        }

        return null;
    }


    // EXERCISE 5.1 ADD TO BACK

    /**
     * Adds an item of type T to the back of the deque.
     *
     * @param item is a type T object added to the deque.
     */
    public void addLast(T item) {

//        Node temp = sentinel;
//        while(temp.next!=null && temp.next!=temp){
//            System.out.println(temp.item);
//            temp = temp.next;
//        }
//
//		temp.next = new Node(temp, item, null);

//       Node last = new Node(sentinel.prev,item,sentinel);
//       sentinel.prev.next = last;
//       sentinel.prev = last;
//       size++;

        Node node = new Node(null, item, null);
        node.prev = sentinel.prev;
        node.next = sentinel;
        sentinel.prev.next = node;
        sentinel.prev = node;
        size++;
    }


    // EXERCISE 5.2 DELETE FRONT

    /**
     * Deletes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     *
     * @return the first item of the deque, null if it does not exist.
     */
    public T delFirst() {
        if (sentinel.next == null || sentinel.next.item == null) {
            return null;
        }

        Node delNode = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return delNode.item;

    }


    // EXERCISE 5.3 DELETE BACK

    /**
     * Deletes and returns the item at the back  of the deque.
     * If no such item exists, returns null.
     *
     * @return the last item of the deque, null if it does not exist.
     */
    public T delLast() {
        if (sentinel.next == null || sentinel.next.item == null) {
            return null;
        }

        Node delNode = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return delNode.item;
    }


    // EXERCISE 5.4 RECURSIVE GET ITEM

    /**
     * Gets the item at the given index.
     * If no such item exists, returns null.
     * Does not mutate the deque.
     *
     * @param index is an index where 0 is the front.
     * @return the ith item of the deque, null if it does not exist.
     */
    public T recGet(int index) {

        if (index < 0 || index >= size){
            return null;
        }
        T temp = null;
        if (index == 0){
            Node recNode = sentinel.next;
            return recNode.item;
        }
        sentinel = sentinel.next;
        temp = recGet(index - 1);
        sentinel = sentinel.prev;
        return temp;
    }


    public static void main(String[] args) {
//        LLDeque<String> deque = new LLDeque<>();
//        System.out.println(deque.isEmpty());
//        deque.addFirst("b");
//        deque.addFirst("a");
//        System.out.println(deque.size());
//
//        System.out.println(deque.iterGet(0));
//        System.out.println(deque.iterGet(1));
//        deque.printDeque();
//        deque.delFirst();
//        System.out.println(deque.iterGet(0));


        LLDeque<String> deque = new LLDeque<>();
        deque.addLast("a");

        deque.addLast("b");

        deque.addLast("c");
//        deque.printDeque();
//        System.out.println(deque.iterGet(0) + "--");
//        System.out.println(deque.iterGet(1) + "--");
//        System.out.println(deque.iterGet(2) + "--");
        System.out.println(deque.recGet(2));
        System.out.println(deque.delFirst());
        System.out.println(deque.delLast());
        System.out.println(deque.recGet(0));
    }

}
