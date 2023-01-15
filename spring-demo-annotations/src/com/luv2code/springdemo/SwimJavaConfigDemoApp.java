package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SwimJavaConfigDemoApp {

	public static void main(String[] args) {
		
		// read the spring java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);
		
		// get the bean from spring container
		SwimCoach swimCoach = context.getBean("swimCoach", SwimCoach.class);
//		Coach golfCoach = context.getBean("golfCoach", Coach.class);
		// call a method on the bean
//		System.out.println(golfCoach.getDailyWorkout());
		
		// call method to get the daily fortune
		System.out.println(swimCoach.getDailyWorkout());
		System.out.println(swimCoach.getDailyFortune());
		System.out.println(swimCoach.getTeam());
		System.out.println(swimCoach.getEmail());
		
		// close the context
		context.close();
	}

}
