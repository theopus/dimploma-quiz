package com.ira.quizplatform.controller;

import com.ira.quizplatform.entity.Answer;
import com.ira.quizplatform.entity.Question;
import com.ira.quizplatform.entity.Quiz;
import com.ira.quizplatform.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/quizzes")
public class QuizController {


    @Autowired
    private QuizRepo quizRepo;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView("quizzes");
        modelAndView.addObject("quizzes", quizRepo.findAll());
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
        ModelAndView modelAndView = new ModelAndView("quizzes-creation");
        return "redirect:/quizzes";
    }
}
