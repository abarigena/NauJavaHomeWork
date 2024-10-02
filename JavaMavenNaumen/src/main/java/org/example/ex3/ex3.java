package org.example.ex3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ex3 {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Иванов Иван Иванович", 30, "Отдел продаж", 50000.0));
        employees.add(new Employee("Петров Петр Петрович", 45, "Бухгалтерия", 70000.0));
        employees.add(new Employee("Сидоров Сидор Сидорович", 28, "Маркетинг", 55000.0));
        employees.add(new Employee("Кузнецов Алексей Сергеевич", 35, "ИТ-отдел", 90000.0));
        employees.add(new Employee("Смирнова Анна Викторовна", 40, "Кадровый отдел", 60000.0));


        sortedSalary(employees);
        for (Employee employee : employees) {
            System.out.println(employee.getFullName() + " " + employee.getSalary()+ " " + employee.getDepartment()+ " " + employee.getAge());
        }
    }
    public static void sortedSalary(List<Employee> employees) {
        List<Employee> sorted = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .toList();

        employees.clear();
        employees.addAll(sorted);
    }
}
