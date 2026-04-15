package com.lucas.core;

import com.lucas.models.Employee;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Enterprise {

    private List<Employee> employees;
    private List<String> titles;

    public Enterprise() {
        titles = new LinkedList<>();
        employees = new LinkedList<>();
    }

    public int getEmployeesCount() {
        return employees.size();
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public List<String> getListTitles() {
        return titles;
    }

    public void addEmployeesFromCsv(String csv) {
        employees.clear();
        try {
            InputStream inputStream = Enterprise.class.getClassLoader().getResourceAsStream("resources/" + csv);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                if (titles.isEmpty()) {
                    String[] cells = line.split(",");
                    for (String cell : cells) titles.add(cell);
                    continue;
                }
                String[] cells = line.split(",");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                employees.add(new Employee(cells[0], LocalDate.parse(cells[1], formatter), new BigDecimal(cells[2]), cells[3]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeEmployee(String name) {
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                employees.remove(employee);
                break;
            }
        }
    }

    public Map<String, List<Employee>> groupBy(String tag) {
        Map<String, List<Employee>> map = new HashMap<>();
        if (tag.equals("Função")) {
            for (Employee employee : employees) {
                if (map.containsKey(employee.getRole())) {
                    List<Employee> list = map.get(employee.getRole());
                    list.add(employee);
                } else {
                    List<Employee> list = new LinkedList<>();
                    list.add(employee);
                    map.put(employee.getRole(), list);
                }
            }
        }
        return map;
    }
}
