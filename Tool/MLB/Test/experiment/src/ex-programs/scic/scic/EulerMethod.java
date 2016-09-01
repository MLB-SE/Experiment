package scic;
/*
 * Uses Euler's method to solve ODE
 * with given initial value
 */
public class EulerMethod {

	public static double myDeriv(double function){
		double derivative;
		//the function
		derivative = -9.8 -.002*(Math.pow(function,2)/.11);
		//derivative = .1*.02*(1-function);
		return derivative;
	}
	
	public static void euler(double y){
		double step = .1;
		double n = 10;
		for (int i = 0; i< n; i++){
			y = y + step*myDeriv(y);
			System.out.println(y);
		}	
		if(y > myDeriv(y)) System.out.println("Test Get");
	}
	
	public static void main (String[] args){
		//euler(.01);
		euler(8);
	}
}
