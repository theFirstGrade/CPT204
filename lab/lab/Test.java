public class Test {

    public static void main(String[] args) {
        int[][] array = {
                {1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}
        };
        boolean a = Find(5, array);
        System.out.println(a);
    }


    static boolean Find(int target, int[][] array) {
        // write code here
        for (int i = 0; i < array.length; i++) {
            int last = array[i].length;
            int mid = last / 2;
            int first = 0;
            while (mid >= 0 && mid < last) {
                if (array[i][mid] > target) {
                    last = mid;
                    mid = (first + mid) / 2;
                } else if (array[i][mid] < target) {
                    first = mid + 1;
                    mid = (mid + 1 + last) / 2;
                } else {
                    return true;
                }
            }

        }
        return false;
    }
}
