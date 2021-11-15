package com.mayank.authentication.controller;

import com.mayank.authentication.dao.StudentDao;
import com.mayank.authentication.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class ClassesController {

    @Autowired
    private StudentDao studentDao;
    
    @RequestMapping("/test")
    public String testing()
    {
        try{
        Student student = studentDao.findById("P01").get();
        System.out.println(student.getScore().size());
        }catch(Exception e)
        {
            System.out.println("nothing there");
        }
       return "home";
    }
   
}
