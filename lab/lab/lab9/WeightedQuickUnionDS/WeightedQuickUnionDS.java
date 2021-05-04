package lab9.WeightedQuickUnionDS;

public class WeightedQuickUnionDS {

    private int[] parent;

    /*
     * Returns the parent of element p.
     * If p is the root of a tree, returns the negative size
     * of the tree for which p is the root.
     */
    public int parent(int p) {
        return parent[p];
    }

    /* Prints the parents of the elements, separated by a space */
    public void printParent() {
        for (int element : parent) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    /*
     ***** HELPER METHODS START *****
     */

    // Add your own helper methods here
    // INCLUDE your helper methods in your submission !




    /*
     ***** HELPER METHODS END *****
     */


    // LAB EXERCISE 9.3  CONSTRUCTOR

    /**
     * Creates a Disjoint Sets data structure with n elements,
     * 0 through n-1.
     * Initially, each element is in its own set.
     *
     * @param N the number of elements
     */
    public WeightedQuickUnionDS(int N) {
        parent = new int[N];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
    }


    // LAB EXERCISE 9.4 VALIDATE

    /**
     * Validates that p is a valid element/index.
     *
     * @throws IllegalArgumentException if p is not a valid index.
     */
    public void validate(int p) {

        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException();
        }

    }


    // EXERCISE 9.1  SIZE OF

    /**
     * Returns the size of the set element p belongs to.
     *
     * @param p an element
     * @return the size of the set containing p
     */
    public int sizeOf(int p) {
        int a = find(p);
        return Math.abs(parent[a]);
    }


    // EXERCISE 9.2  IS CONNECTED

    public int find(int p) {
//        System.out.println(p);
        validate(p);
        int root = p;
        while (parent[root] > -1) {
//            System.out.println(root);
            root = parent[root];
        }
        return root;
    }

    /**
     * Returns true iff elements p and q are connected.
     *
     * @param p an element
     * @param q the other element
     * @return true if p and q are in the same set
     * false otherwise
     * @throws IllegalArgumentException if p or q is not a valid index.
     */
    public boolean isConnected(int p, int q) {
//        validate(p);
//        validate(q);
        int a = find(p);
        int b = find(q);
        return a == b;
    }


    // EXERCISE 9.3  CONNECT

    /**
     * Connects two elements p and q together,
     * by combining the sets containing them.
     *
     * @param p an element
     * @param q the other element
     * @throws IllegalArgumentException if p or q is not a valid index.
     */
    public void connect(int p, int q) {
        validate(p);
        validate(q);
        if (find(p) != find(q)) {
            int a = p;
            int b = q;
            while (parent[a] > -1) {
                a = parent[a];
            }
            while (parent[b] > -1) {
                b = parent[b];
            }

            if (parent[a] < parent[b]) {
                parent[a] = parent[a] + parent[b];
                parent[b] = a;
            } else if (parent[a] > parent[b]) {
                parent[b] = parent[a] + parent[b];
                parent[a] = b;
            } else {
                parent[b] = parent[a] + parent[b];
                parent[a] = b;
            }
        }
    }


    public static void main(String[] args) {
//        WeightedQuickUnionDS ds = new WeightedQuickUnionDS(4);
//        ds.connect(1, 0);
//        ds.connect(3, 2);
//        ds.connect(3, 1);
//        ds.printParent();

        WeightedQuickUnionDS ds = new WeightedQuickUnionDS(4);
        ds.connect(1, 0);
        ds.printParent();

        System.out.println(ds.isConnected(1, 0));
        System.out.println(ds.parent(1));
        System.out.println(ds.parent(0));
        ds.connect(3, 2);
        ds.printParent();

        System.out.println(ds.isConnected(2, 1));
        ds.connect(3, 1);
        System.out.println(ds.isConnected(2, 1));
        System.out.println("---- ");
        ds.printParent();
        System.out.println(ds.parent(2));
        System.out.println(ds.sizeOf(1));
        ds.printParent();

//        WeightedQuickUnionDS ds = new WeightedQuickUnionDS(5);
//        try {
//            ds.validate(10);
//        } catch (IllegalArgumentException e) {
//            System.out.println("IllegalArgumentException");
//        }
    }

}
