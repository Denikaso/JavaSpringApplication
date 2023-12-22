package com.ru.vyatsu.dao;

import com.ru.vyatsu.models.Enrollee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnrolleeListDao implements Dao<Enrollee>{
    private List<Enrollee> enrollees;
    public EnrolleeListDao() {
        this.enrollees = new ArrayList<>();

        LocalDate birthday1 = LocalDate.of(2000, 1, 1);  // 1 января 2000 года
        LocalDate birthday2 = LocalDate.of(2002, 6, 15); // 15 июня 2002 года
        LocalDate birthday3 = LocalDate.of(2001, 9, 28); // 28 сентября 2001 года
        LocalDate birthday4 = LocalDate.of(2000, 3, 3);  // 3 марта 2000 года

        Enrollee enrollee1 = new Enrollee(0, "Александр Белый", birthday1);
        Enrollee enrollee2 = new Enrollee(1, "Федор Сумкин", birthday2);
        Enrollee enrollee3 = new Enrollee(2, "Логоваз Безотцовщина", birthday3);
        Enrollee enrollee4 = new Enrollee(3, "Агроном Агрономов", birthday4);

        enrollees.add(enrollee1);
        enrollees.add(enrollee2);
        enrollees.add(enrollee3);
        enrollees.add(enrollee4);
    }
    @Override
    public Optional<Enrollee> get(int id) {
        return Optional.of(enrollees.get(id));
    }

    @Override
    public List<Enrollee> getAll() {
        return enrollees;
    }

    @Override
    public void save(Enrollee enrollee) {
        enrollees.add(enrollee);
    }

    @Override
    public void delete(Enrollee enrollee) {
        enrollees.remove(enrollee);
    }
}
