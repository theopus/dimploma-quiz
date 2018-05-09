package com.ira.quizplatform.controller;


import com.ira.quizplatform.entity.Student;
import com.ira.quizplatform.entity.Teacher;
import com.ira.quizplatform.repository.GroupRepo;
import com.ira.quizplatform.repository.StudentRepo;
import com.ira.quizplatform.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepo teacherRepo;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView("teachers");
        modelAndView.addObject("teachers", teacherRepo.findAll());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestParam("name") String name, @RequestParam("password") String password) {
        teacherRepo.save(new Teacher(name, password));
        return "redirect:/teachers";
    }

    @RequestMapping("/delete/{id}")
    public String deleteBuyer(@PathVariable Long id){
        teacherRepo.deleteById(id);
        return "redirect:/teachers";
    }

}
