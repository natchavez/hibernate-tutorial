package com.nathaniel.hibernate.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.nathaniel.hibernate.onetoone.entity.Instructor;
import com.nathaniel.hibernate.onetoone.entity.InstructorDetail;

public class GetInstructorDetailDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            
            int theId = 5;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
            System.out.println("tempInstructorDetail: " + tempInstructorDetail);
            
            System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());


            session.getTransaction().commit();

            System.out.println("Done!");
        } catch(Exception exc) {
            exc.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
