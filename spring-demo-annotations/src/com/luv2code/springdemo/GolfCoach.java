package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

@Component
public class GolfCoach implements Coach {

	@Override
	public String getDailyWorkout() {
		return "Practice golf";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return null;
	}

}
