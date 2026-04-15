package com.lucas.models;

import java.time.LocalDate;

public class People {
    protected final String name;
    protected final LocalDate birthDate;

    public People(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }
}
