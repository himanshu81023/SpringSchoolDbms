package com.mayank.authentication.service;

import com.mayank.authentication.dao.ClassesDao;
import com.mayank.authentication.dao.CourseNameDao;
import com.mayank.authentication.model.Classes;
import com.mayank.authentication.model.CourseName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClassesService {


    @Autowired
    private ClassesDao classesDao;
    @Autowired
    private CourseNameDao courseNameDao;
    public void initClass()
    {
       this.initializeClass(1);
       this.initializeClass(2);
       this.initializeClass(3);
       this.initializeClass(4);
       this.initializeClass(5);
       this.initializeClass(6);
       this.initializeClass(7);
       this.initializeClass(8);
       this.initializeClass(9);
       this.initializeClass(10);


    }
    private void initializeClass(int x)
    {
        Integer c = x;

        Classes clas = new Classes();
        clas.setClas(c.toString());
        c--;

        Set <CourseName> courses = new HashSet<>();
        CourseName courseName1= new CourseName();
        courseName1.setCourseId(c.toString()+"01");
        courseName1.setCourseName("English");
        CourseName courseName2 = new CourseName();
        courseName2.setCourseId(c.toString()+"02");
        courseName2.setCourseName("Mathematics");
        CourseName courseName3 = new CourseName();
        courseName3.setCourseName("Hindi");
        courseName3.setCourseId(c.toString()+"03");
        CourseName courseName4 = new CourseName();
        courseName4.setCourseName("Science");
        courseName4.setCourseId(c.toString()+"04");
        CourseName courseName5 = new CourseName();
        courseName5.setCourseId(c.toString()+"05");
        courseName5.setCourseName("Social Science");

        courses.add(courseName2);
        courses.add(courseName1);
        courses.add(courseName3);
        courses.add(courseName4);
        courses.add(courseName5);
        clas.setCourseNames(courses);
        classesDao.save(clas);

        courseName1.setClas(clas);
        courseName2.setClas(clas);
        courseName3.setClas(clas);
        courseName4.setClas(clas);
        courseName5.setClas(clas);
        List<CourseName> list = List.of(courseName1,courseName2,courseName3,courseName4,courseName5);
        courseNameDao.saveAll(list);
//        courseNameDao.save(courseName1);
//        courseNameDao.save(courseName2);
//        courseNameDao.save(courseName3);
//        courseNameDao.save(courseName4);
//        courseNameDao.save(courseName5);
    }
}
