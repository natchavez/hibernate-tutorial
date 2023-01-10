package com.nathaniel.hibernate.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.nathaniel.hibernate.onetoone.entity.Instructor;
import com.nathaniel.hibernate.onetoone.entity.InstructorDetail;

public class DeleteDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // get instrutor by primary key
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);
            System.out.println("Found instructor: " + tempInstructor);

            // delete the instructors
            if (tempInstructor != null) {
                System.out.println("Deleting: " + tempInstructor);
                // Note: will ALSO delete associated "details" object because of CascadeType.ALL
                session.delete(tempInstructor);
            }
            session.getTransaction().commit();

            // deleting using query
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete from Instructor  where email like 'nat%'").executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
