package com.jdbc;

import com.jdbc.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class AddCourseForMary {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            int studentId = 4;
            Student tempStudent = session.get(Student.class, studentId);

            System.out.println("\nLoaded student: " + tempStudent);
            System.out.println("Cour- Game Developmentses: " + tempStudent.getCourses());

            Course tempCourse1 = new Course("Rubik's Cube");
            Course tempCourse2 = new Course("Atari 2600");

            tempCourse1.addStudent(tempStudent);
            tempCourse2.addStudent(tempStudent);

            System.out.println("\nSaving the courses ...");

            session.save(tempCourse1);
            session.save(tempCourse2);

            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {

            // add clean up code
            session.close();

            factory.close();
        }
    }

}

