package com.jdbc;

import com.jdbc.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {


            session.beginTransaction();
            Course tempCourse = new Course("Pacman ");

            session.save(tempCourse);
            Student student1=new Student("Jon","Doe","jon@jdbc.com");
            Student student2=new Student("mary","jane","mary@jdbc.com");

            tempCourse.addStudent(student1);
            tempCourse.addStudent(student2);

            System.out.println("Saving");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getStudents());

            session.save(student1);
            session.save(student2);

            session.getTransaction().commit();

            System.out.println("Done");
        }
        finally {
            factory.close();
        }
    }

}
