package lab10.RSLList;// LAB EXERCISE 10.2  RSLLIST ROTATE RIGHT

/**
 * Rotating Singly-Linked List
 */

// Complete the class declaration so that
// RSLList becomes a subclass of SLList

public class RSLList<T> extends SLList<T> {

    public static void main(String[] args) {
        RSLList<String> rlist = new RSLList<>();
        rlist.addLast("a");
        rlist.addLast("b");
        rlist.addLast("c");
        rlist.rotateRight();
        System.out.println(rlist.get(0));
        System.out.println(rlist.get(1));
        System.out.println(rlist.get(2));
    }

    public void rotateRight() {
        T item = delLast();
        if (item == null){
            return;
        }
        addFirst(item);
    }

}