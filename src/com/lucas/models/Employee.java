package com.lucas.models;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee extends People {
    private BigDecimal wage;
    private final String role;

    public Employee(String name, LocalDate birthDate, BigDecimal wage, String role) {
        super(name, birthDate);
        this.wage = wage;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String tabs = "\t";
        String tabs2 = "\t";
        if (name.length() < 6) tabs += "\t";
        if (role.length() < 11) tabs2 += "\t";
        return "| " + name + tabs + "|\t" + birthDate.format(formatter) + "\t\t" + "| " + df.format(wage) + "\t" + "| " + role + tabs2 + "|";
    }
}
