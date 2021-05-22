package lecture11_and_lab11.Dog;

public class Dog implements Comparable<Dog> {

    private String name;
    private int weight;

    public Dog(String n, int w) {
        name = n;
        weight = w;
    }

    /**
     * @param other another Dog
     * @return negative number if this is smaller than other
     * 0 if this equals other
     * positive number if this is larger than other
     */
    @Override
    public int compareTo(Dog other) {

//        if (this.weight < other.weight) {
//            return -1;
//        } else if (this.weight == other.weight) {
//            return 0;
//        }
//        return 1;

        return this.weight - other.weight;

    }

    public void bark() {
        System.out.println(name + ": bark!");
    }


    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    // LAB EXERCISE 11.2  EQUALS

    @Override
    public boolean equals(Object a) {
        if (a instanceof Dog) {
            Dog d = (Dog) a;
            return this.name.equals(d.name) && this.weight == d.weight;
        }
        return false;

    }

    public static void main(String[] args) {
//        Dog d1 = new Dog("Baobei", 5);
//        Dog d2 = new Dog("Baobei", 5);
//        Dog d3 = new Dog("Jiaozi", 7);
//        System.out.println(d1.equals(d2));
//        System.out.println(d1.equals(d3));

        Dog d1 = new Dog("", 100);
        Dog d2 = new Dog("", 100);
        System.out.println(d1.equals(""));
        System.out.println(d1.equals(new String("")));
        System.out.println(d1.equals(100));
        System.out.println(d1.equals(null));
        System.out.println(d1.equals(d2));
        Dog d3 = new Dog("abcd", 7);
        String name = new String("abcd");
        Dog d4 = new Dog(name, 7);
        System.out.println(d3.equals(d4));
        System.out.println(d4.equals(d3));
        System.out.println(d4.equals(new Dog("abcd", 7)));
    }

}