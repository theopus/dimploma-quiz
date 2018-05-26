package com.ira.quizplatform.controller;


import com.google.common.collect.Sets;
import com.ira.quizplatform.entity.*;
import com.ira.quizplatform.repository.ResultRepo;
import com.ira.quizplatform.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/test")
public class TestController {


    @Autowired
    private ResultRepo resultRepo;
    @Autowired
    private StudentRepo studentRepo;


    @RequestMapping
    public ModelAndView modelAndView(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Student byName = studentRepo.findByName(name);
        List<Result> avalible = resultRepo.findByStudent(byName);
        ModelAndView modelAndView = new ModelAndView("tests");
        modelAndView.addObject("avalible", avalible);
        return modelAndView;
    }

    @RequestMapping("/{id}")
    public ModelAndView test(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.addObject("quiz", resultRepo.getOne(id).getQuiz());
        return modelAndView;
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.POST, consumes = "application/json")
    public String recieve(@PathVariable("id") Long id, @RequestBody List<List<Integer>> qAnswers) {
        Result one = resultRepo.getOne(id);
        Double perOnePoint = perOnePoint(one.getQuiz());
        System.out.println(qAnswers);

        List<Question> questions = one.getQuiz().getList();
        List<List<Answer>> extracted = extract(questions, qAnswers);


        float total = 0.0f;


        for (int i = 0; i < questions.size(); i++) {
            Set<Answer> answers = new HashSet<>(questions.get(i).getCorrect());
            Set<Answer> given = new HashSet<>(extracted.get(i));

            Sets.SetView<Answer> symmetricDifference = Sets.symmetricDifference(answers, given);
            Sets.SetView<Answer> difference = Sets.difference(answers, given);
            System.out.println("-------------" + i + "-------------");
            System.out.println(symmetricDifference);
            System.out.println(difference);

            Float weitgh = questions.get(i).getWeitgh();
            float perOneRight = weitgh / answers.size();
            int rightOnes = answers.size() - (symmetricDifference.size());
            double thisQestion = rightOnes * perOneRight * perOnePoint;
            System.out.println(thisQestion);
            total += thisQestion;
            System.out.println(total);

        }

        if (total > 100.0f){
            total = 100.f;
        }

        one.setPassed(true);
        one.setBalance((int) total);
        resultRepo.save(one);
        return "results";
    }


    /**
     * 4 + 5 + 6 = 15 - total points
     * 100 / 15 = 6,3 - per point
     *
     */

    private Double perOnePoint(Quiz quiz) {
        double sum = quiz.getList().stream().mapToDouble(Question::getWeitgh).sum();
        System.out.println("All weights: " + sum);
        double perOnePoint = 100.0 / sum;
        System.out.println("PerOne Point:" + perOnePoint);
        return perOnePoint;
    }


    private List<List<Answer>> extract(List<Question> questions, List<List<Integer>> qAnswers) {
        List<List<Answer>> answers = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {

            List<Answer> all = questions.get(i).getAll();
            List<Integer> given = qAnswers.get(i);
            List<Answer> extracted = new ArrayList<>();

            for (int j = 0; j < all.size(); j++) {
                boolean contains = given.contains(j);
                if (contains) {
                    extracted.add(all.get(j));
                }
            }

            answers.add(extracted);
        }
        return answers;
    }


}
