package com.ira.quizplatform.controller;

import com.ira.quizplatform.entity.*;
import com.ira.quizplatform.repository.GroupRepo;
import com.ira.quizplatform.repository.QuizRepo;
import com.ira.quizplatform.repository.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/quizzes")
public class QuizController {


    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private ResultRepo resultRepo;


    private HashMap<Long, Set<Long>> permitions = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView("quizzes");
        modelAndView.addObject("quizzes", quizRepo.findAll());
        modelAndView.addObject("groups", groupRepo.findAll());
        return modelAndView;
    }

    @RequestMapping(path = "/creation", method = RequestMethod.GET)
    public ModelAndView creation() {
        ModelAndView modelAndView = new ModelAndView("quizzes-creation");
        return modelAndView;
    }

    @RequestMapping(path = "/creation", method = RequestMethod.POST, consumes = "application/json")
    public String creationAdd(@RequestBody Quiz quiz) {
        System.out.println(quiz.toString());
        quizRepo.save(quiz);
        ModelAndView modelAndView = new ModelAndView("quizzes-creation");
        return "redirect:/quizzes";
    }

    @RequestMapping(path = "/permissions", method = RequestMethod.POST)
    public String creationAdd(@RequestParam("group") Long group, @RequestParam("quiz") Long quiz) {
        System.out.println(permitions);
        if (permitions.containsKey(quiz)) {
            permitions.get(quiz).add(group);
        } else {
            permitions.put(quiz, new HashSet<>());
            permitions.get(quiz).add(group);
        }



        Group groupObj = groupRepo.getOne(group);

        for (Student student : groupObj.getStudents()) {
            Quiz quizObj = quizRepo.getOne(quiz);
            Student studentObj = student;
            Result result = new Result();

            List<Result> byStudentAndQuiz = resultRepo.findByStudentAndQuiz(studentObj, quizObj);
            if (!byStudentAndQuiz.isEmpty()) {
                continue;
            }

            result.setBalance(0);
            result.setStudent(studentObj);
            result.setPassed(false);
            result.setQuiz(quizObj);
            resultRepo.save(result);
        }

        System.out.println(resultRepo.findAll());
        ModelAndView modelAndView = new ModelAndView("quizzes");
        return "redirect:/quizzes";
    }
}
