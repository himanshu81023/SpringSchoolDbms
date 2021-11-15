package com.mayank.authentication.service;

import javax.servlet.http.HttpSession;

import com.mayank.authentication.dao.StaffDao;
import com.mayank.authentication.dao.StudentDao;
import com.mayank.authentication.model.Staff;
import com.mayank.authentication.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StaffDao staffDao;
    
    public  boolean verifyUser(HttpSession session)
    {
        String Role,Uid,Password;
        
        try
        {
         Role= session.getAttribute("Role").toString();
         Uid = session.getAttribute("Uid").toString();
         Password  = session.getAttribute("Password").toString();
        }
        catch(Exception e)
        {
            session.invalidate();
            return false;
        }
        if(Role.equals("Student")&& studentDao.findById(Uid).isPresent())
        {
                Student student = studentDao.findById(Uid).get();
                if(student.getStudentPassword().equals(Password)) 
                  { 
                      session.setAttribute("loggedin", true);
                      return true;
                  }

        }
        else if(Role.equals("Staff") && staffDao.findById(Uid).isPresent())
        {
                Staff staff = staffDao.findById(Uid).get();
                if(staff.getPassword().equals(Password)) 
                 {
                        session.setAttribute("loggedin", true);
                       return true;
                 }
        }
        else if(Role.equals("Admin") && staffDao.findById(Uid).isPresent())
        {
                Staff staff = staffDao.findById(Uid).get();
                if(staff.getPassword().equals(Password)) 
                  {
                    session.setAttribute("loggedin", true);
                       return true;
                  }
        }
        session.invalidate();
        return false;

    }
    
}
