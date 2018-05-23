package com.ira.quizplatform.controller;

import com.ira.quizplatform.entity.Group;
import com.ira.quizplatform.entity.Quiz;
import com.ira.quizplatform.entity.Result;
import com.ira.quizplatform.entity.Student;
import com.ira.quizplatform.repository.GroupRepo;
import com.ira.quizplatform.repository.QuizRepo;
import com.ira.quizplatform.repository.ResultRepo;
import com.ira.quizplatform.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Oleksandr_Tkachov on 20.05.2018.
 */
@Controller
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultRepo resultRepo;
    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private StudentRepo studentRepo;

    @RequestMapping("/all")
    public @ResponseBody List<Result> all() {
        return resultRepo.findAll();
    }

    @RequestMapping("/studentByGroup/{id}")
    public @ResponseBody List<Result> studentByGroup(@PathVariable("id") Long id) {
        Group one = groupRepo.getOne(id);
        return resultRepo.findByStudent_Group(one);
    }

    @RequestMapping("/student/{studentId}")
    public @ResponseBody List<Result> student(@PathVariable("studentId") Long studentId) {
        Student one = studentRepo.getOne(studentId);
        return resultRepo.findByStudent(one);
    }

    @RequestMapping("/test/{id}")
    public @ResponseBody List<Quiz> test(@PathVariable("id") Long id) {
        return resultRepo.uniqueQuizzes(id);
    }


}
