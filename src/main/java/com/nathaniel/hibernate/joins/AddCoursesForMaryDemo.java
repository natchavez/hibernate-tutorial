package com.nathaniel.hibernate.joins;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nathaniel.hibernate.joins.entity.Course;
import com.nathaniel.hibernate.joins.entity.Instructor;
import com.nathaniel.hibernate.joins.entity.InstructorDetail;
import com.nathaniel.hibernate.joins.entity.Review;
import com.nathaniel.hibernate.joins.entity.Student;

public class AddCoursesForMaryDemo {

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

            // get the student mary from database
            int maryId = 2;
            Student mary = session.get(Student.class, maryId);
            System.out.println("\nLoaded student: " + mary);
            System.out.println("Course: " + mary.getCourses());

            // create more courses
            Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
            Course tempCourse2 = new Course("Atari 2600 - Game Development");

            // add mary to courses
            tempCourse1.add(mary);
            tempCourse2.add(mary);

            // save the courses
            System.out.println("\nSaving the courses ... ");
            session.save(tempCourse1);
            session.save(tempCourse2);
            
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }

}
