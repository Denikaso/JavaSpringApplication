package com.ru.vyatsu.dao;

import com.ru.vyatsu.models.Exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExamListDao implements Dao<Exam>{
    private List<Exam> exams;
    public ExamListDao() {
        this.exams = new ArrayList<>();

        // Пример добавления нескольких экзаменов
        Exam exam1 = new Exam("Математика", 90, 0);
        Exam exam2 = new Exam("Физика", 85, 0);
        Exam exam3 = new Exam("Химия", 75, 2);

        exams.add(exam1);
        exams.add(exam2);
        exams.add(exam3);
    }

    public List<Exam> getExamsByEnrolleeId(int idEnrollee) {
        List<Exam> examList;
        examList = exams.stream().filter(x -> x.getIdEnrollee() == idEnrollee).toList();
        return examList;
    }

    @Override
    public Optional<Exam> get(int id) {
        return Optional.of(exams.get(id));
    }

    @Override
    public List<Exam> getAll() {
        return exams;
    }

    @Override
    public void save(Exam exam) {
        exams.add(exam);
    }

    @Override
    public void delete(Exam exam) {
        exams.remove(exam);
    }
}
