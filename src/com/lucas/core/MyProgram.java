package com.lucas.core;

import com.lucas.models.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MyProgram {

    private final Enterprise enterprise;

    public MyProgram(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    // Mostra o número de funcionarios da lista
    public void printEmployeesCount() {
        int count = enterprise.getEmployeesCount();
        System.out.println(String.format(Locale.getDefault(), "%s %d %s%s %s", "Há", count, "funcionario", count == 1 ? "" : "s", "na lista."));
    }

    public void addictEmployeesToList() {
        System.out.println("Adicionando funcionários a lista");
        enterprise.addEmployeesFromCsv("employees.csv");
        printEmployeesCount();
    }

    public void removeEmployeeFromList(String employeeName) {
        System.out.println("Removendo " + employeeName + " da lista de funcionários.");
        enterprise.removeEmployee(employeeName);
        printEmployeesCount();
    }

    public void showAllListEmployees(String title) {
        System.out.println("-------------------------------------------------------------");
        System.out.println("|\t\t\t\t\t" + title + "\t\t\t\t\t|");
        List<Employee> employees = enterprise.getAllEmployees();
        List<String> titles = enterprise.getListTitles();
        System.out.println("-------------------------------------------------------------");
        for (String title2 : titles) System.out.print("|\t" + title2 + "\t");
        System.out.println("\t|");
        System.out.println("-------------------------------------------------------------");
        for (Employee employee : employees) System.out.println(employee);
        System.out.println("-------------------------------------------------------------");
    }

    public void addictPercentageToWages(int percentage) {
        System.out.println("Adicionando " + percentage + "% aos salários.");
        BigDecimal multiple = new BigDecimal((100 + percentage) / 100.0d);
        List<Employee> employees = enterprise.getAllEmployees();
        for (Employee employee : employees) {

            BigDecimal oldWage = employee.getWage();
            BigDecimal newWage = oldWage.multiply(multiple);
            employee.setWage(newWage);
        }
    }

    public void showEmployeesBy(String tag) {
        System.out.println("\n\n");
        System.out.println(" Funcionários agrupados por função");
        System.out.println("-------------------------------------------------------------");
        Map<String, List<Employee>> map = enterprise.groupBy(tag);
        for (String key : map.keySet()) {
            System.out.println("|\tFuncionários com cargo de " + key);
            for (Employee employee : map.get(key)) System.out.println("|\t" + employee.getName());
            System.out.println("-------------------------------------------------------------");
        }
    }

    public void showEmployeesWithBirthIn(int[] months) {
        StringBuilder builder = new StringBuilder();
        for (int month : months) {
            if (!builder.isEmpty()) builder.append(", ");
            builder.append(month);
        }
        System.out.println("\n\n");
        System.out.println(" Funcionários fazem aniversário nos meses de " + builder);
        System.out.println("-------------------------------------------------------------");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Employee> employees = enterprise.getAllEmployees();
        for (Employee employee : employees) {
            for (int month : months) {
                if (employee.getBirthDate().getMonth().getValue() == month) {
                    System.out.println("|\t" + employee.getName() + " | " + employee.getBirthDate().format(formatter));
                }
            }
        }
        System.out.println("-------------------------------------------------------------");
    }

    public void showOlderEmployee() {
        List<Employee> employees = enterprise.getAllEmployees();
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getBirthDate().compareTo(o2.getBirthDate());
            }
        });
        Employee older = employees.getFirst();
        LocalDate now = LocalDate.now();
        Period age = Period.between(older.getBirthDate(), now);
        System.out.println("Funcionário mais velho");
        System.out.println(older.getName() + " | Idade: " + age.getYears() + " Anos, " + age.getMonths() + " Meses e " + age.getDays() + " dias.");
    }
}