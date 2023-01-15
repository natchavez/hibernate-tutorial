package com.nathaniel.hibernate.joins;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.nathaniel.hibernate.joins.entity.Course;
import com.nathaniel.hibernate.joins.entity.Instructor;
import com.nathaniel.hibernate.joins.entity.InstructorDetail;

public class FetchJoinDemo {

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
            
            Query<Instructor> query = session.createQuery(
                    "select i from Instructor i " + "JOIN FETCH i.courses " + "where i.id= :theInstructorId",
                    Instructor.class);
            query.setParameter("theInstructorId", theId);
            Instructor tempInstructor = query.getSingleResult();
            System.out.println("Instructor: " + tempInstructor);

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
