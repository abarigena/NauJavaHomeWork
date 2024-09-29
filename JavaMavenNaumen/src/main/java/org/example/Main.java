package org.example;

import org.example.ex1.ex1;
import org.example.ex2.ex2;
import org.example.ex3.ex3;
import org.example.ex4.reqHeaders;
import org.example.ex5.ex5;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Вариант 3");
        System.out.println("Задание No1. Работа с массивом. ");
        ex1.main(new String[]{});

        System.out.println("\n Задание No2. Работа со списками.");
        ex2.main(new String[]{});

        System.out.println("\n \n Задание No3. Stream API. ");
        ex3.main(new String[]{});

        System.out.println("\n Задание No 4. HTTP клиент и JSON.");

        reqHeaders.main(new String[]{});

        System.out.println("\n Задание No 5. Реализация интерфейса “Task”.");

        ex5.main(new String[]{});
    }
}
