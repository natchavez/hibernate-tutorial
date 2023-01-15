package com.nathaniel.hibernatepractice.hibernate_practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.nathaniel.hibernate.practice.entity.Employee;

/**
 * Hello world!
 *
 */
public class App {
    static SessionFactory factory = new Configuration()
            .configure()
            .addAnnotatedClass(Employee.class)
            .buildSessionFactory();
    static Scanner stringScanner = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner intScanner = new Scanner(System.in);

        
        
        Session session = factory.getCurrentSession();
        
        System.out.println("Welcome! What would you like to do?");
        System.out.println("1. Create\n2. Read\n3. Update\n4. Delete");
        System.out.print("\nEnter choice: ");
        int choice = intScanner.nextInt();
        System.out.println("You picked number: " + choice);

        switch (choice) {
        case 1:
            System.out.println("Please provide details for employee table.");
            List<String> employeeDetails = employeeDetails();
            createMethod(session, employeeDetails);
            break;
        case 2:
            System.out.print("Employee ID: ");
            int idToView = intScanner.nextInt();
            readMethod(session, idToView);
            break;
        case 3:
            System.out.print("Employee ID: ");
            int idToUpdate = intScanner.nextInt();
            System.out.print("Column name: ");
            String columnName = stringScanner.nextLine();
            System.out.print("Value: ");
            String value = stringScanner.nextLine();
            updateMethod(session, idToUpdate, columnName, value);
            break;
        case 4:
            System.out.print("Employee ID: ");
            int idToDelete = intScanner.nextInt();
            deleteMethod(session, idToDelete);
            break;
        default:
            System.out.println("Only numbers 1 through 4");
        }
    }

    public static void createMethod(Session session, List<String> employeeDetails) {
        Employee employee = new Employee(employeeDetails.get(0), employeeDetails.get(1), employeeDetails.get(2));
        session.beginTransaction();
        System.out.println("Saving the employee...");
        System.out.println(employee);
        session.save(employee);
        session.getTransaction().commit();
        
        // find out the student's id: primary key
        viewDetails("POST",session.createQuery("from Employee"));
        System.out.println("Saved employee. Generated id: " + employee.getId() + "\nDone!");
    }

    public static void readMethod(Session session, int idToView) {
        session.beginTransaction();
        Query<Employee> query = (idToView == 0) ? session.createQuery("from Employee")
                : session.createQuery("from Employee s where s.id='" + idToView + "'");
        viewDetails("GET", query);

    }

    public static void updateMethod(Session session, int idToUpdate, String columnName, String value) {
        session.beginTransaction();
        session.createQuery(String.format("update Employee set %s='%s' where id=%s", columnName, value, Integer.toString(idToUpdate)))
                .executeUpdate();
        session.getTransaction().commit();
        
        session = factory.getCurrentSession();
        session.beginTransaction();
        viewDetails("PUT",session.createQuery("from Employee"));
    }

    public static void deleteMethod(Session session, int idToDelete) {
        session.beginTransaction();
        session.createQuery(String.format("delete from Employee where id=%s", Integer.toString(idToDelete)))
                .executeUpdate();
        session.getTransaction().commit();
        
        session = factory.getCurrentSession();
        session.beginTransaction();
        viewDetails("DELETE", session.createQuery("from Employee"));

    }
    
    public static List<String> employeeDetails() {
        System.out.print("First name: ");
        String firstName = stringScanner.nextLine();
        System.out.print("Last name: ");
        String lastName = stringScanner.nextLine();
        System.out.print("Company name: ");
        String companyName = stringScanner.nextLine();
        return new ArrayList<>(Arrays.asList(firstName, lastName, companyName));

    }
    
    private static void viewDetails(String requestType, Query<Employee> query) {
        List<Employee> employees = query.getResultList();
        System.out.println("\n\nResult from " + requestType + ": ");
        for(Employee e: employees) {
            System.out.println(e);
        }
        System.out.println();
    }

}
