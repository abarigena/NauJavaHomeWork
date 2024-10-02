package org.example.ex1;

public class ex1 {
    public static void main(String[] args) {
        int rand =  (int) (Math.random() * 100);

        int[] arr = new int[rand];
        for (int i = 0; i < rand; i++) {
            arr[i] = -100 + (int) (Math.random() * 201);
        }
        for (int i = 0; i < rand; i++) {
            System.out.print(arr[i]+ " ");
        }

        System.out.println("\n" + findAverage(arr));


    }

    public static double findAverage(int[] arr) {
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum / arr.length;
    }



}
