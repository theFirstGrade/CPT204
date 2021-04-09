package lab6.LLDeque;

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
    public void addLast(T item) {

        Node node = new Node(null, item, null);
        node.prev = sentinel.prev;
        node.next = sentinel;
        sentinel.prev.next = node;
        sentinel.prev = node;
        size++;
    }

    // EXERCISE 6.1 COPY CONSTRUCTOR

    /**
     * Creates a (deep) copy of another Deque object.
     *
     * @param other is another LLDeque<T> object.
     */
    public LLDeque(LLDeque<T> other) {
        this();
        Node q = other.sentinel;
        while (q.next != null && q.next.item != null) {
            this.addLast(q.next.item);
            q = q.next;
        }

//        sentinel = new Node(null, null, null);
//        size = 0;
//
//        Node p = sentinel;
//        Node q = other.sentinel;
//        while (q.next != null && q.next.item != null) {
//            p.next = new Node(p, q.next.item, sentinel);
//            p = p.next;
//            sentinel.prev.next = p;
//            q = q.next;
//            size += 1;
//        }

    }

    public LLDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

    }
    // EXERCISE 6.2 ADD NOT NULL TO FRONT

    /**
     * Adds an non-null item of type T to the front of the deque.
     *
     * @param item is a type T object.
     * @throws IllegalArgumentException if the item is null.
     */
    public void addFirst(T item) {
        if (item == null){
            throw new IllegalArgumentException();
        }
        sentinel.next = new Node(null, item, sentinel.next);
        size += 1;
    }

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

    // EXERCISE 6.3 ADD LEGAL ITEM TO FRONT

    /**
     * Adds the first item of type T to the front of the deque,
     * or the second item of type T instead if the first item is illegal.
     *
     * @param item1 is a type T object.
     * @param item2 is a type T object.
     */
    public void addLegalFirst(T item1, T item2) {
        try{
            addFirst(item1);
        } catch (Exception e) {
            addFirst(item2);
        }

    }

    public static void main(String[] args) {
//        LLDeque<String> deque = new LLDeque<>();
//        deque.addFirst("a");
//        LLDeque<String> copyDeque = new LLDeque<>(deque);
//        deque.addFirst("x");
//        copyDeque.addFirst("y");
//        System.out.println(deque.iterGet(0));
//        System.out.println(deque.iterGet(1));
//        System.out.println(copyDeque.iterGet(0));
//        System.out.println(copyDeque.iterGet(1));

        LLDeque<String> deque = new LLDeque<>();
        deque.addFirst("c");
        deque.addLegalFirst("b", "x");
        deque.addLegalFirst(null, "a");
        System.out.println(deque.iterGet(0));
        System.out.println(deque.iterGet(1));
        System.out.println(deque.iterGet(2));
    }


    /*
     *************************************************************
     * You can copy paste Lab 5 codes below if you want to use it
     *************************************************************
     */


}
