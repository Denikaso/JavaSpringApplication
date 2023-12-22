package com.ru.vyatsu.controllers;

import com.ru.vyatsu.dao.EnrolleeListDao;
import com.ru.vyatsu.dao.ExamListDao;
import com.ru.vyatsu.models.Enrollee;
import com.ru.vyatsu.models.Exam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EnrolleeController {
    int idExam;
    EnrolleeListDao enrolleeDao = new EnrolleeListDao();
    ExamListDao examDao = new ExamListDao();

    @GetMapping("/enrollees")
    public String enrollees(Model model){
        model.addAttribute("title","Список абитуриентов");
        List<Enrollee> enrollees = enrolleeDao.getAll();
        model.addAttribute("enrollees", enrollees);
        return "enrollees";
    }

    @GetMapping("/enrollee/{id}")
    public String enrollee(@PathVariable int id, Model model){
        Enrollee enrollee = enrolleeDao.getAll().get(id); // получение абитуриента с номером id
        model.addAttribute("enrollee", enrollee);
        List<Exam> exams = examDao.getExamsByEnrolleeId(id);
        model.addAttribute("exams", exams);
        return "enrollee";
    }

    @GetMapping("/add")
    public String enrolleeForm(Model model) {
        model.addAttribute("title","Добавление абитуриента");
        Enrollee enrollee = new Enrollee ();
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("number", enrolleeDao.getAll().size());
        return "add";
    }
    @PostMapping("/add")
    public String enrolleeSubmit(@ModelAttribute Enrollee enrollee, Model model) {
        enrolleeDao.save(enrollee);
        List<Enrollee> enrollees = enrolleeDao.getAll();
        model.addAttribute("enrollees", enrollees);
        return "redirect:/enrollees";
    }

    @GetMapping("/exam{id}")
    public String examForm(@PathVariable int id, Model model) {
        idExam = id;
        model.addAttribute("title","Добавление экзамена'");
        List<Exam> exams = examDao.getAll();
        List<String> subjects = exams.stream()
                .map(Exam::getSubject)
                .distinct()
                .toList();
                model.addAttribute("subjects", subjects);
        Exam exam = new Exam();
        model.addAttribute("exam", exam);
        model.addAttribute("id", id);
        return "exam";
    }

    @PostMapping("/exam")
    public String examSubmit(@ModelAttribute Exam exam, Model model) {
        exam.setIdEnrollee(idExam);
        examDao.save(exam);
        model.addAttribute("exam", exam);
        return "redirect:/enrollees";
    }
}
