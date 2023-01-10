package com.nathaniel.hibernate.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.nathaniel.hibernate.onetoone.entity.Instructor;
import com.nathaniel.hibernate.onetoone.entity.InstructorDetail;

public class CreateDemo {

    public static void main(String[] args) {
       SessionFactory factory = new Configuration()
                               .configure()
                               .addAnnotatedClass(Instructor.class)
                               .addAnnotatedClass(InstructorDetail.class)
                               .buildSessionFactory();
       Session session = factory.getCurrentSession();
       
       try {
           Instructor tempInstructor = new Instructor("Julius", "Buyson", "nat@yopmail.com");
           InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com", "keyboards");
           tempInstructor.setInstructorDetail(tempInstructorDetail);
           session.beginTransaction();
           session.save(tempInstructor);
           session.getTransaction().commit();
           
       } finally {
           factory.close();
       }
    }

}





