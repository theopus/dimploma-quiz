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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    private QuizRepo quizRepo;

    @RequestMapping("/all")
    public @ResponseBody
    List<Result> all() {
        return resultRepo.findAll();
    }


    @RequestMapping
    public ModelAndView groups() {
        List<Group> all = groupRepo.findAll();
        ModelAndView modelAndView = new ModelAndView("results");
        modelAndView.addObject("groups", all);
        return modelAndView;
    }


    @RequestMapping("/quiz/{id}")
    public ModelAndView resultsByQuiz(@PathVariable("id") Long id) {
        Set<Group> collect = resultRepo.findByQuizId(id)
                .stream().map(result -> result.getStudent().getGroup())
                .collect(Collectors.toSet());

        ModelAndView modelAndView = new ModelAndView("quiz-results-groups");
        modelAndView.addObject("quiz", quizRepo.getOne(id));
        modelAndView.addObject("groups", collect);
        return modelAndView;
    }


    @RequestMapping("/quiz/{quizId}/group/{groupId}")
    public ModelAndView resultsByGroupAndQuiz(@PathVariable("quizId") Long quizId,
                                              @PathVariable("groupId") Long groupId) {
        Group one = groupRepo.getOne(groupId);
        Quiz one1 = quizRepo.getOne(quizId);
        ModelAndView modelAndView = resultsByQuiz(quizId);
        List<Result> byStudent_groupAndQuiz = resultRepo.findByStudent_GroupAndQuiz(one, one1);
        System.out.println(byStudent_groupAndQuiz);
        modelAndView.addObject("studentsResults", byStudent_groupAndQuiz);
        modelAndView.addObject("group", one);
        return modelAndView;

    }

    @RequestMapping("/studentByGroup/{id}")
    public ModelAndView studentByGroup(@PathVariable("id") Long id) {
        Group one = groupRepo.getOne(id);
        List<Result> byStudent_group = resultRepo.findByStudent_Group(one);
        Map<Student, List<Result>> collect = byStudent_group.stream().collect(Collectors.groupingBy(Result::getStudent));
        ModelAndView modelAndView = groups();
        if (collect.isEmpty()) {
            return modelAndView;
        }
        modelAndView.addObject("resultsByStudent", collect);
        return modelAndView;
    }

    @RequestMapping("/student/{studentId}")
    public @ResponseBody
    List<Result> student(@PathVariable("studentId") Long studentId) {
        Student one = studentRepo.getOne(studentId);
        return resultRepo.findByStudent(one);
    }

}
