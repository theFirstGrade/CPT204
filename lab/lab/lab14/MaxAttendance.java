package lab14;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxAttendance {

    private ALBinHeap<CourseActivity> minPQ1;
    private ALBinHeap<CourseActivity> minPQ2;


    // LAB 14 PART B MAX ATTENDANCE

    public MaxAttendance(ArrayList<CourseActivity> activities) {
        minPQ1 = new ALBinHeap<>();
        minPQ2 = new ALBinHeap<>();
        for (CourseActivity item : activities) {
//            System.out.println(item);
            minPQ1.add(item, (item.getDay() - 1) * 24 + item.getStartTime());
            minPQ2.add(item, (item.getDay() - 1) * 24 + item.getEndTime());
        }

//        System.out.println(Arrays.toString(minPQ1.toArray()));;
//        System.out.println(Arrays.toString(minPQ2.toArray()));
//        System.out.println(Arrays.toString(minPQ2.toPriority()));
    }

    public int maxAttendance() {
        int max = 0;
        int temp = 0;
        int size = minPQ1.size();
        for (int i = 0; i < size; i++) {

            CourseActivity ca1 = minPQ1.delMin();
//            System.out.println(ca1);
//            CourseActivity ca2 = minPQ2.getMin();
            while (ca1.getDay() != minPQ2.getMin().getDay()) {
                temp -= minPQ2.delMin().getNumStudents();
            }
            while (ca1.getDay() == minPQ2.getMin().getDay() && minPQ2.getMin().getEndTime() <= ca1.getStartTime()) {
                temp -= minPQ2.getMin().getNumStudents();
                minPQ2.delMin();
            }

            if (ca1.getDay() == minPQ2.getMin().getDay()) {
                temp += ca1.getNumStudents();
            }

            if (max < temp) {
                max = temp;
//                System.out.println(max);
            }
        }
        return max;
//        int size = minPQ1.size();
//        int max = 0;
//        int lastEndTime = 0;
//        int lastDay = 0;
////        System.out.println("size" + minPQ1.size());
//        for (int i = 0; i < size; i++) {
//            CourseActivity min = minPQ1.getMin();
//            int endTime1 = min.getEndTime();
//            int day1 = min.getDay();
//            int temp = 0;
//
//            if (i > 0 && day1 == lastDay && endTime1 > lastEndTime){
//                temp += max;
//            }
//            int count = 0;
//            while(minPQ2.size() > 0){
//                CourseActivity minPQ2Min = minPQ2.getMin();
//                int numStudents2 = minPQ2Min.getNumStudents();
//                int endTime2 = minPQ2Min.getEndTime();
//                int day2 = minPQ2Min.getDay();
//                if (day1 == day2 && endTime1 >= endTime2){
//                    temp += numStudents2;
//                    lastDay = day2;
//                    lastEndTime = endTime2;
//                }else{
//                    break;
//                }
//                minPQ2.delMin();
//            }
//            if (max < temp){
//                max = temp;
//            }
//            System.out.println(max + " ---");
//            minPQ1.delMin();

//        }

//        return max;
    }

    private static ArrayList<CourseActivity> createSchedule(int[] days, int[] startTimes, int[] endTimes, int[] studentNumbers) {
        ArrayList<CourseActivity> schedule = new ArrayList<>();
        for (int i = 0; i < startTimes.length; i++) {
            schedule.add(new CourseActivity("", "", days[i], startTimes[i], endTimes[i], studentNumbers[i], ""));
        }
        return schedule;
    }

    public static void main(String[] args) {
        int[] days = {1, 1, 1, 1, 1, 1, 3};
        int[] startTimes = {10, 14, 15, 16, 18, 19, 2};
        int[] endTimes = {11, 17, 16, 17, 20, 22, 3};
        int[] studentNumbers = {30, 10, 15, 30, 30, 20, 51};
        MaxAttendance maxAtt = new MaxAttendance(createSchedule(days, startTimes, endTimes, studentNumbers));
        HeapVisualizer.heapVisualize(maxAtt.minPQ2.toArray());
        int a = maxAtt.maxAttendance();
        System.out.println(a);

    }

}
