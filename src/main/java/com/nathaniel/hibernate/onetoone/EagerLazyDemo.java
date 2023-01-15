package com.nathaniel.hibernate.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nathaniel.hibernate.onetoone.entity.Course;
import com.nathaniel.hibernate.onetoone.entity.Instructor;
import com.nathaniel.hibernate.onetoone.entity.InstructorDetail;

public class EagerLazyDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                                .configure()
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("Instructor: " + tempInstructor);

            System.out.println("Courses: " + tempInstructor.getCourses());

            session.getTransaction().commit();

            // close the session
            session.close();
            System.out.println("\nThe session is now closed!\n");
            System.out.println("Courses: " + tempInstructor.getCourses());

            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }

}
