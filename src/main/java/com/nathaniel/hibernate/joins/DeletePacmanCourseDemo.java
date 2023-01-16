package com.nathaniel.hibernate.joins;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nathaniel.hibernate.joins.entity.Course;
import com.nathaniel.hibernate.joins.entity.Instructor;
import com.nathaniel.hibernate.joins.entity.InstructorDetail;
import com.nathaniel.hibernate.joins.entity.Review;
import com.nathaniel.hibernate.joins.entity.Student;

public class DeletePacmanCourseDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                                .configure()
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .addAnnotatedClass(Review.class)
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            // get the pacman course from db
            Course pacmanCourse = session.get(Course.class, 10);
            System.out.println("Deleting course: " + pacmanCourse);
            session.delete(pacmanCourse);
            // delete the course

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }

}
