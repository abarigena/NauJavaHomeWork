package org.example.ex2;

import java.util.ArrayList;

public class ex2 {
    public static void main(String[] args) {
        ArrayList<Double> list = new ArrayList<>();

        int rand =  (int) (Math.random() * 100);

        for (int i = 0; i < rand; i++) {
            double num = -100 + (Math.random() * 201);
            list.add(num);

        }
        for (int i = 0; i < rand; i++) {
            System.out.print(list.get(i) + " ");
        }
        bubbleSort(list);
        System.out.println();
        for (int i = 0; i < rand; i++) {
            System.out.print(list.get(i) + " ");
        }
    }
    public static void bubbleSort(ArrayList<Double> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i  - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    double save = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, save);
                }
            }
        }
    }
}
