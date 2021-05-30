package Test;

import java.util.ArrayList;

public class Test02 {

    public static void main(String[] args) {
        ArrayList a = new ArrayList();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.set(0, 20);
        System.out.println(a);
    }
}
