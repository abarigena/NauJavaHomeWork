package org.example.ex3;

import java.util.ArrayList;

public class ex3 {
    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();

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
    public static void sortedSalary(ArrayList<Employee> employees) {
        for (int i = 0; i < employees.size() - 1; i++) {
            for (int j = 0; j < employees.size() - i - 1; j++) {
                if (employees.get(j).getSalary() > employees.get(j + 1).getSalary()) {
                    Employee temp = employees.get(j);
                    employees.set(j, employees.get(j + 1));
                    employees.set(j + 1, temp);
                }
            }
        }
    }
}
