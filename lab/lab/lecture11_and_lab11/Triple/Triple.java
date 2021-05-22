package lecture11_and_lab11.Triple;

public class Triple<T extends Comparable<T>, S extends Comparable<S>, U extends Comparable<U>>
        implements Comparable<Triple<T, S, U>> {

    private T first;
    private S second;
    private U third;

    public Triple(T firstElement, S secondElement, U thirdElement) {
        first = firstElement;
        second = secondElement;
        third = thirdElement;
    }

    public T getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public U getThird() {
        return third;
    }


    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    // EXERCISE 14.1  COMPARETO

    public Object change(Object a) {
        Object ele;
        if (a instanceof String) {
            ele = (String) a;
        } else if (a instanceof Integer) {
            ele = Integer.parseInt(a.toString());
        } else {
            ele = Double.parseDouble(a.toString());
        }

        return ele;
    }

    @Override
    public int compareTo(Triple<T, S, U> other) {
//        System.out.println(this.first instanceof Integer);

//        if (this.first instanceof String) {
//            String first1 = (String) this.first;
//        } else if (this.first instanceof Integer) {
//            int first1 = Integer.parseInt(this.getFirst().toString());
//        } else {
//            double first1 = Double.parseDouble(this.third.toString());
//        }

//        Object first1 = change(this.first);
//        System.out.print("------");
//        System.out.println(this.first);

//        int first1 = Integer.parseInt(this.getFirst().toString());
//        int first2 = Integer.parseInt(other.getFirst().toString());
//        int second1 = Integer.parseInt(this.getSecond().toString());
//        int second2 = Integer.parseInt(other.getSecond().toString());
//        int third1 = Integer.parseInt(this.getThird().toString());
//        int third2 = Integer.parseInt(other.getThird().toString());
////
//        if (first1 > first2) {
//            return 1;
//        } else if (first1 < first2) {
//            return -1;
//        } else {
//            if (second1 > second2) {
//                return 1;
//            } else if (second1 < second2) {
//                return -1;
//            } else {
//                if (third1 > third2) {
//                    return 1;
//                } else if (third1 < third2) {
//                    return -1;
//                } else {
//                    return 0;
//                }
//            }
//        }

        if (this.first.compareTo(other.first) > 0) {
            return 1;
        } else if (this.first.compareTo(other.first) < 0) {
            return -1;
        } else {
            if (this.second.compareTo(other.second) > 0) {
                return 1;
            } else if (this.second.compareTo(other.second) < 0) {
                return -1;
            } else {
                if (this.third.compareTo(other.third) > 0) {
                    return 1;
                } else if (this.third.compareTo(other.third) < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    public static void main(String[] args) {
//        Triple<Integer, String, Double> t1 = new Triple<>(1, "b", 2.0);
//        Triple<Integer, String, Double> t2 = new Triple<>(1, "a", 5.0);
//        Triple<Integer, String, Double> t3 = new Triple<>(1, "b", 2.0001);
//        System.out.println(t1.compareTo(t2) > 0);
//        System.out.println(t1.compareTo(t3) < 0);

        Triple<String, String, String> t1 = new Triple<>("abc", "ab", "c");
        Triple<String, String, String> t2 = new Triple<>("abc", "ab", "c");
        Triple<String, String, String> t3 = new Triple<>("abc", "abc", "abc");
        Triple<String, String, String> t4 = new Triple<>("abc", "abc", "abc");
        System.out.println(t1.compareTo(t2) == 0);
        System.out.println(t1.compareTo(t3) > 0);
        System.out.println(t1.compareTo(t3) < 0);
        System.out.println(t3.compareTo(t4) == 0);
        Triple<String, String, String> t5 = new Triple<>("b", "abc", "abc");
        Triple<String, String, String> t6 = new Triple<>("aa", "abc", "abc");
        System.out.println(t5.compareTo(t6) > 0);
        System.out.println(t6.compareTo(t5) < 0);
        Triple<String, String, String> t7 = new Triple<>("abc", "b", "abc");
        Triple<String, String, String> t8 = new Triple<>("abc", "aa", "abc");
        System.out.println(t7.compareTo(t8) > 0);
        System.out.println(t8.compareTo(t7) < 0);
        Triple<String, String, String> t9 = new Triple<>("abc", "abc", "b");
        Triple<String, String, String> t10 = new Triple<>("abc", "abc", "aa");
        System.out.println(t9.compareTo(t10) > 0);
        System.out.println(t10.compareTo(t9) < 0);
    }

}