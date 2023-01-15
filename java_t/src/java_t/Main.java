package java_t;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String username="admin";
        String password="password123";
        
        System.out.print("Enter username: ");
        String uName = sc.nextLine();
        System.out.print("Enter password: ");
        String pWord = sc.nextLine();
        
        if(username.equals(uName) && password.equals(pWord)) {
            System.out.println("You are now logged in");
        } else {
            System.out.println("incorrect username or password");
        }
    }
}
 
    