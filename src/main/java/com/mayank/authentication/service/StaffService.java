package com.mayank.authentication.service;

import com.mayank.authentication.dao.CourseNameDao;
import com.mayank.authentication.dao.RoleDao;
import com.mayank.authentication.dao.StaffDao;
import com.mayank.authentication.model.CourseName;
import com.mayank.authentication.model.Role;
import com.mayank.authentication.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired 
    private ClassesService classesService;
    @Autowired
    private CourseNameDao courseNameDao;

    public void admin(){
        
        classesService.initClass();
        Role role = new Role();
        role.setRole("Admin");
        role.setDescription("Super User");
        roleDao.save(role);
        Role staff = new Role();
        staff.setRole("Staff");
        staff.setDescription("You can access students profile");
        roleDao.save(staff);
        Role student = new Role();
        student.setRole("Student");
        student.setDescription("You can View their Performance.");
        roleDao.save(student);
  
        Staff staff1 = new Staff();
        staff1.setJobId("P01");
        staff1.setFirstName("Himanshu");
        staff1.setMiddleName("Kumar");
        staff1.setLastName("Mayank");
        staff1.setDesignation("Principal");
        staff1.setDob("8 Jan 2002");
        staff1.setPhoneNo("8302324307");
        staff1.setLandlineNo("8389-345532");
        staff1.setGender("Male");
        staff1.setAddress("Nawdiha,Near Shiv Mandir");
        staff1.setDistrict("Hazaribagh");
        staff1.setState("Jharkhand");
        staff1.setEmail("himanshu.krmayank.cse19@itbhu.ac.in");
        staff1.setRole(role);
        staff1.setPassword("81023m@@");
        staff1.setPin("810217");
        staff1.setPreviousJobDetail("P.G.T Maths");  
        CourseName course = courseNameDao.findById("001").get();
        staff1.setTeaching(course);
        staffDao.save(staff1);
        course.setStaff(staff1);
        courseNameDao.save(course);
    }
}
