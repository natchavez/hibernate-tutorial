package com.nathaniel.hibernate.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nathaniel.hibernate.onetoone.entity.Course;
import com.nathaniel.hibernate.onetoone.entity.Instructor;
import com.nathaniel.hibernate.onetoone.entity.InstructorDetail;

public class DeleteCourseDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();
            
            int theId = 10;
            Course tempCourse = session.get(Course.class, theId);
            System.out.println("Deleting course: " + tempCourse);
            
            session.delete(tempCourse);

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }

}
