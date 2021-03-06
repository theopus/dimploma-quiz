package com.ira.quizplatform.controller;


import com.ira.quizplatform.entity.Group;
import com.ira.quizplatform.entity.Student;
import com.ira.quizplatform.repository.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {


    @Autowired
    private GroupRepo groupRepo;

    @RequestMapping("/all")
    public List<Group> all(){
        return groupRepo.findAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView("groups");
        modelAndView.addObject("groups", groupRepo.findAll());
        return modelAndView;
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public ModelAndView info(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("group-info");
        modelAndView.addObject("group", groupRepo.getOne(id));
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestParam("name") String name) {
        groupRepo.save(new Group(name));
        return "redirect:/groups";
    }

}
