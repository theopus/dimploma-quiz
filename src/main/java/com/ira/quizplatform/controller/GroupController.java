package com.ira.quizplatform.controller;


import com.ira.quizplatform.entity.Student;
import com.ira.quizplatform.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/students")
public class StudentController {


    @Autowired
    private StudentRepo studentRepo;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView("students");
        modelAndView.addObject("students", studentRepo.findAll());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestParam("name") String name) {
        studentRepo.save(new Student(name));
        return "redirect:/students";
    }

}
