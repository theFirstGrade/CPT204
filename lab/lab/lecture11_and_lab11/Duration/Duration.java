package lecture11_and_lab11.Duration;

public class Duration implements Comparable<Duration> {

    private final int mins;
    private final int secs;
    // rep invariant:
    //    mins >= 0, secs >= 0
    // abstraction function:
    //    represents a span of time of mins minutes and secs seconds

    /**
     * Make a duration lasting for m minutes and s seconds.
     */
    public Duration(int m, int s) {
        mins = m;
        secs = s;
    }

    /**
     * @return length of this duration in seconds
     */
    public int getLength() {
        return mins * 60 + secs;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null) return false;
        if (!(that instanceof Duration)) return false;
        Duration thatDuration = (Duration) that;
        return this.getLength() == thatDuration.getLength();
    }


    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    // LAB EXERCISE 11.1  COMPARETO

    @Override
    public int compareTo(Duration a) {
        int totalSecs1 = this.mins * 60 + this.secs;
        int totalSecs2 = a.mins * 60 + a.secs;
        return Integer.compare(totalSecs1, totalSecs2);
    }

    public static void main(String[] args) {
//        Duration d1 = new Duration(2, 5);
//        Duration d2 = new Duration(1, 2);
//        System.out.println(d1.compareTo(d2) > 0);
//        System.out.println(d2.compareTo(d1) < 0);

        Duration d1 = new Duration(1, 0);
        Duration d2 = new Duration(1, 0);
        Duration d3 = new Duration(0, 1);
        Duration d4 = new Duration(0, 1);
        System.out.println(d1.compareTo(d2) == 0);
        System.out.println(d3.compareTo(d4) == 0);
        d1 = new Duration(0, 0);
        d2 = new Duration(0, 0);
        d3 = new Duration(10, 100);
        d4 = new Duration(0, 700);
        Duration d5 = new Duration(2, 580);
        System.out.println(d1.compareTo(d2) == 0);
        System.out.println(d3.compareTo(d4) == 0);
        System.out.println(d1.compareTo(d2) < 0);
        System.out.println(d1.compareTo(d2) > 0);
        System.out.println(d3.compareTo(d5) == 0);
        System.out.println(d4.compareTo(d5) == 0);
    }

}