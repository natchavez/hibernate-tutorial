import java.util.ArrayList;

public class Main {
	private static final String OPERATIONS = "*/+-";

	public static void main(String[] args) {
		String equation = "1+2-2*100/2/2/2/3/4*12";
		ArrayList<String> components=processEquation(equation);
		double result = getAnswer(components);
		System.out.println("\nResult: " + result);
	}
	
	public static ArrayList<String> processEquation(String equation) {
		ArrayList<String> components = new ArrayList<String>();
		String subString="";

		for(int i=0; i<equation.length();i++) {
			if(!(OPERATIONS.contains(String.valueOf(equation.charAt(i)))) && (i != equation.length()-1)) {
				subString += equation.charAt(i);
			}else {
				if(i == equation.length()-1) {
					subString += equation.charAt(i);
				}
				components.add(subString);
				if(OPERATIONS.contains(String.valueOf(equation.charAt(i)))) {
					components.add(String.valueOf(equation.charAt(i)));
					subString="";
				}
			}
		}
		return components;
	}
	public static ArrayList<String> operationsChecker(ArrayList<String> components, String op1, String op2, boolean isPrime) {
		int i=0;
		double temp=0.0;
		while(components.contains(op1) || components.contains(op2)) {
			if(op1.equals(components.get(i)) || op2.equals(components.get(i))) {
				if(op1.equals(components.get(i))) {
					temp = (isPrime) ? Double.parseDouble(components.get(i-1)) * Double.parseDouble(components.get(i+1))
							: Double.parseDouble(components.get(i-1)) + Double.parseDouble(components.get(i+1));
				} else if(op2.equals(components.get(i))) {
					temp = (isPrime) ? Double.parseDouble(components.get(i-1)) / Double.parseDouble(components.get(i+1))
							: Double.parseDouble(components.get(i-1)) - Double.parseDouble(components.get(i+1));
				}
				components.set(i-1, temp+"");
				components.remove(i+1);
				components.remove(i);
				i=0;
				
			}
			i++;
		}
		return components;
	}
	
	public static double getAnswer(ArrayList<String> components) {
		ArrayList<String> firstStage = operationsChecker(components, "*", "/", true);
		ArrayList<String> secondStage = operationsChecker(firstStage, "+", "-", false);
		
		return Double.parseDouble(secondStage.get(0));
	}
}