package com.nathaniel.hibernate.joins;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nathaniel.hibernate.joins.entity.Course;
import com.nathaniel.hibernate.joins.entity.Instructor;
import com.nathaniel.hibernate.joins.entity.InstructorDetail;
import com.nathaniel.hibernate.joins.entity.Review;
import com.nathaniel.hibernate.joins.entity.Student;

public class CreateCourseAndStudentsDemo {

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

            // create course
            Course tempCourse = new Course("Pacman - How To Score One Million Points");

            // save the course ... and leverage the cascade all
            System.out.println("\nSaving the course");
            session.save(tempCourse);
            System.out.println("Saved the course: " + tempCourse);

            // create the students
            Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
            Student tempStudent2 = new Student("Mary", "Republic", "mary@luv2code.com");

            // add student to the course
            tempCourse.add(tempStudent1);
            tempCourse.add(tempStudent2);

            // save the students
            System.out.println("\nSaving students ... ");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved Students: " + tempCourse.getStudents());

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }

}
