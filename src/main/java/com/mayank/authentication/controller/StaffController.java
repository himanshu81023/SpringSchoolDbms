package com.mayank.authentication.controller;


import com.mayank.authentication.dao.CourseNameDao;
import com.mayank.authentication.dao.RoleDao;
import com.mayank.authentication.dao.StaffDao;
import com.mayank.authentication.model.CourseName;
import com.mayank.authentication.model.Role;
import com.mayank.authentication.model.Staff;
import com.mayank.authentication.service.AuthenticationService;
// import com.mayank.authentication.model.Student;
import com.mayank.authentication.service.StaffService;
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



import javax.annotation.PostConstruct;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;


@Controller
public class StaffController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private RoleDao roleDao;
    @ Autowired
    private CourseNameDao courseNameDao;
    @PostConstruct
    void admin() {
        staffService.admin();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {

        return "home";
    }

    @RequestMapping("/login")
    public String logIn(HttpSession session) {

        return "login";
    }

    @RequestMapping("/logout")
    public String logOut(HttpSession session)
    {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/profile")
    public ModelAndView profile(HttpSession session, @RequestParam("id") String uid,
            @RequestParam("password") String pass, @RequestParam("role") String role) {
        session.setAttribute("Role", role);
        session.setAttribute("Uid", uid);
        session.setAttribute("Password", pass);
        print(session);
        ModelAndView mv = new ModelAndView();
        if (authenticationService.verifyUser(session)) {
            
            System.out.println("OHKKK");
            if (session.getAttribute("Role").equals("Student")) {
                mv.setViewName("redirect:/studentProfile");
            } else {
                mv.setViewName("redirect:/staffProfile");
            }
            return mv;
        }
        mv.addObject("error", "Not a valid credentials.");
        mv.setViewName("redirect:/login");
        return mv;
    }
    @GetMapping("/profile")
    public ModelAndView profileGet(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if (authenticationService.verifyUser(session)) {
            
            System.out.println("OHKKK");
            if (session.getAttribute("Role").equals("Student")) {
                mv.setViewName("redirect:/studentProfile");
            } else {
                mv.setViewName("redirect:/staffProfile");
            }
            return mv;
        }
        mv.addObject("error", "Not a valid credentials.");
        mv.setViewName("redirect:/login");
        return mv;
    }

    @GetMapping("/addStaff")
    public ModelAndView staffForm(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if (authenticationService.verifyUser(session)) {
            if (session.getAttribute("Role").equals("Admin")) {
                mv.setViewName("addStaff");
            } else {
                mv.addObject("error", "You cant access this page");
                mv.setViewName("redirect:/");
            }
        } else {
            mv.addObject("error", "Please First Login");
            mv.setViewName("redirect:/login");
        }
        return mv;
    }

    @PostMapping("/addStaff")
    public ModelAndView addStaff(@RequestParam("role")String role,@RequestParam("teaching")String courseCode,@ModelAttribute Staff staff,HttpSession session)
    {
       ModelAndView mv = new ModelAndView();
       if(authenticationService.verifyUser(session))
       {
          Role staffRole = roleDao.findById(role).get();
          staff.setRole(staffRole);
          CourseName course = courseNameDao.findById(courseCode).get();
          staff.setTeaching(course);
          Staff storedStaff = staffDao.save(staff);
          course.setStaff(storedStaff);
          courseNameDao.save(course);
          mv.addObject("staff",storedStaff);
          mv.setViewName("redirect:/staffList");
       }
       else{
           mv.addObject("error", "Please First Login");
           mv.setViewName("redirect:/login");
       }
         return mv;
    }

    @GetMapping("/staffProfile")
    public ModelAndView staffProfile(HttpSession session) {
        ModelAndView mv = new ModelAndView();

        if (authenticationService.verifyUser(session)) {
            if (session.getAttribute("Role").equals("Staff") || session.getAttribute("Role").equals("Admin")) {
                String uid = session.getAttribute("Uid").toString();
                Staff staff = staffDao.findById(uid).get();
                mv.addObject("staff", staff);
                mv.setViewName("staffProfile");
                return mv;
            } else {
                mv.addObject("error", "You can't access requested page");
                mv.setViewName("redirect:/");
                return mv;
            }
        }
        mv.addObject("error", "Not a valid credentials.");
        mv.setViewName("redirect:/login");
        return mv;

    }

    @RequestMapping(value = "/staffList", method = RequestMethod.GET)
    public String staffList(HttpSession session,Model model) {
        // ModelAndView mv = new ModelAndView();
        if (authenticationService.verifyUser(session)) {
            Iterable<Staff> staffList = staffDao.findAll();
            model.addAttribute("staffList", staffList);
            return "staffList";
        }

        session.setAttribute("error","Pleas first get log in!");
        return "redirect:/login";

    }

    private void print(HttpSession session) {
        System.out.println(session.getAttribute("Role"));
        System.out.println(session.getAttribute("Uid"));
        System.out.println(session.getAttribute("Password"));
    }

}

// @PostMapping("/staffProfile")
// public ModelAndView addStaff(@ModelAttribute Staff staff) {

// ModelAndView mv = new ModelAndView();
// try {
// if (staff.getJobId().length() < 1)
// throw new Exception("ID not found");
// Staff staf1;
// staf1 = staffDao.save(staff);
// mv.setViewName("staffProfile");
// mv.addObject("staff", staf1);
// return mv;
// } catch (Exception e) {
// System.out.println(e);
// System.out.println("eroor occured");
// mv.setViewName("redirect:/home/addStaff");
// mv.addObject("error", "Enter once Again");
// return mv;
// }

// }