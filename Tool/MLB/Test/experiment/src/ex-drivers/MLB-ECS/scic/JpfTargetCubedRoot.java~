package scic;/*
 * Example of binary search zero finding
 * find the cubed root
 */
public class CubedRoot {
	public static void main (String[] args){
		check(2);
	}
	public static void check (double test){
		int numEval = 18; // found using lg((b-a)*10^4) with b-a = 25; didn't subtract one because best estimate one behind
		double N = 25; // number who's cubed root we want to find
		double minVal = 0; // setting lower and upper bound for the bisection theorem
		double maxVal = N; // lower bound is 0 and upper bound is the original number
		double midVal = 0; // initiailizing middle value
		double pCube;
		double error = .0001; 
		
		for (int i=0; i<numEval; i++){ 
			midVal = (maxVal-minVal)/2 + minVal; //finds the number in the middle of the range
			pCube = midVal*midVal*midVal; //cubes this middle number
			
			if (pCube - N < 0){minVal = midVal;} //if this number is smaller than the orig number then lower half 
			else if (pCube - N > 0){maxVal = midVal;} // if "" larger than upper half
		} 		
		
		boolean withinErr;//	= (midVal - Math.pow(test, 1.0/3) <= error)? true: false; 
		if(midVal - Math.pow(test, 1.0/3) <= error) withinErr = true;
		else withinErr = false;
		
		System.out.println("the answer is " + midVal);
		System.out.println(withinErr);
		//System.out.println("minVal = " + minVal + " maxVal " + maxVal);
	}
}
	

