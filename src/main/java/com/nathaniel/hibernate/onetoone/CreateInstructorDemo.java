package com.nathaniel.hibernate.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nathaniel.hibernate.onetoone.entity.Course;
import com.nathaniel.hibernate.onetoone.entity.Instructor;
import com.nathaniel.hibernate.onetoone.entity.InstructorDetail;

public class CreateInstructorDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            Instructor tempInstructor = new Instructor("Nathaniel", "Chavez", "nat@yopmail.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "Video games");
            tempInstructor.setInstructorDetail(tempInstructorDetail);
            session.beginTransaction();
            session.save(tempInstructor);
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }

}
