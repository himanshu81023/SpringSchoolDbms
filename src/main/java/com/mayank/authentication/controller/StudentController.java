package com.mayank.authentication.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;


import com.mayank.authentication.dao.ClassesDao;
import com.mayank.authentication.dao.CourseNameDao;
import com.mayank.authentication.dao.RoleDao;
import com.mayank.authentication.dao.ScoreDao;
import com.mayank.authentication.dao.StudentDao;
import com.mayank.authentication.model.Classes;
import com.mayank.authentication.model.CompositeKeyForScore;
import com.mayank.authentication.model.CourseName;
import com.mayank.authentication.model.Role;
import com.mayank.authentication.model.Score;
import com.mayank.authentication.model.Student;
import com.mayank.authentication.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ClassesDao classesDao;
    @Autowired
    private ScoreDao scoreDao;
    @Autowired 
    private CourseNameDao courseNameDao;

    @GetMapping("/addStudent")
    public ModelAndView enterStudent(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if (authenticationService.verifyUser(session)) {
            System.out.println("ohkk authentication pass");
            if (session.getAttribute("Role").equals("student")) {
                mv.addObject("error", "You do not have permission to open the requested page");
                mv.setViewName("redirect:/");
                return mv;
            } else {
                mv.setViewName("addStudent");
                return mv;
            }
        } else {
            mv.addObject("error", "First,Please authenticate yourself First");
            mv.setViewName("redirect:/login");
            return mv;
        }
    }

    @PostMapping("/addStudent")
    public ModelAndView saveStudent(@RequestParam("class") String clas, @ModelAttribute Student student,
            HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if (authenticationService.verifyUser(session)) {

            try {
                if (session.getAttribute("Role").equals("Student"))
                    throw new Exception("Not valid");

                Role role = roleDao.findById("Student").get();
                System.out.println(student);
                student.setRole(role);
                Integer sz = studentDao.findSizeByClass(clas);
                System.out.println(sz);
                sz=sz+1;
                student.setStudentRollNo(sz.toString());
                Classes classes = classesDao.findById(clas).get();
                student.setClas(classes);
                studentDao.save(student);
                
                Set<Score> scoreForStudent= new HashSet<>();
               
                classes.getCourseNames().forEach((CourseName c) -> {
                    Score score = new Score();
                    /// yaha jan mujke abhi set nhin kiya hain composite key manually
                    // dekhna hain ki ye kudh se le pata hain ya nhin;
                    // answer mil gaya aisa nhin ho sakta

                    CompositeKeyForScore compositeKeyForScore = new CompositeKeyForScore();
                    compositeKeyForScore.setCourseId(c.getCourseId());
                    compositeKeyForScore.setStudentId(student.getStudentId());
                    score.setCompositeKeyForScore(compositeKeyForScore);
                    score.setStudent(student);
                    score.setCourseName(c);

                    // jan mujhke age likh rha hoon   { working}
                    scoreForStudent.add(score);
                    Set<Score> ct = c.getScore();
                    ct.add(score);
                    c.setScore(ct);

                    // scoreDao.save(score);
                    // --> i dont have save score since its getting propagated  from 
                    // coursename to score; see entity
                    // there i mention cascade = cascadetype.all
                    courseNameDao.save(c);
            
                });
                student.setScore(scoreForStudent);
                studentDao.save(student);

                mv.setViewName("redirect:/studentList");
                mv.addObject("student", student);
                return mv;
            } catch (Exception e) {
                System.out.println(e);
                mv.addObject("error", "Enter once Again");
                mv.setViewName("redirect:/addStudent");
                return mv;
            }
        } else {
            mv.addObject("error", "Please first login yourself");
            mv.setViewName("redirect:/login");
        }
        return mv;

    }

    @RequestMapping(value = "/studentList", method = RequestMethod.GET)
    public String studentList(Model model) {
        
        Iterable<Student> s = studentDao.findAll();
        model.addAttribute("studentList", s);
        return "studentList";
    }
    
    
    @GetMapping(value = "/studentProfile")
    public String studentProfile(HttpSession session,Model model)
    {
        if(authenticationService.verifyUser(session))
        {
           if(session.getAttribute("Role").equals("Student"))
           {
               try{
               Student student = studentDao.findById(session.getAttribute("Uid").toString()).get();
               model.addAttribute("student",student);
               return "studentProfile";}
               catch(Exception e)
               {
                   System.out.println("error occured in accessing the page");
                    session.setAttribute("error","Not a valid Request");
                    return "redirect:/";
               }
           }
           else
           {
               session.setAttribute("error","You are Not Authorized for visiting this page");
               return "redirect:/";
           }

        }   
        else{
            session.setAttribute("error","Please First Login Your Self.");
            return  "redirect:/";
        }
       
    }
}
