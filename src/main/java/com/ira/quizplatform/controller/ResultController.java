package com.ira.quizplatform.controller;

import com.ira.quizplatform.entity.Group;
import com.ira.quizplatform.entity.Result;
import com.ira.quizplatform.repository.GroupRepo;
import com.ira.quizplatform.repository.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/")
    public @ResponseBody List<Result> resultsByGroup() {
        Group one = groupRepo.getOne(1L);
        return resultRepo.findByStudent_Group(one);
    }
}
