package com.ira.quizplatform.controller;


import com.ira.quizplatform.entity.Student;
import com.ira.quizplatform.repository.GroupRepo;
import com.ira.quizplatform.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/students")
public class StudentController {


    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private GroupRepo groupRepo;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView("students");
        modelAndView.addObject("students", studentRepo.findAll());
        modelAndView.addObject("groups", groupRepo.findAll());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("group") Long groupId) {
        studentRepo.save(new Student(name, password, groupRepo.getOne(groupId)));
        return "redirect:/students";
    }

    @RequestMapping("/delete/{id}")
    public String deleteBuyer(@PathVariable Long id){
        studentRepo.deleteById(id);
        return "redirect:/students";
    }

}
