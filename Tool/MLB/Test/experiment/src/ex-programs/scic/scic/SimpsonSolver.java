package scic;
/*
*Using composite simpson to solve an integral
*text found here: http://iop.vast.ac.vn/~nvthanh/cours/numerical_methods/CH5_fb_p1.pdf
*
*/
public class SimpsonSolver {
	public static double pi= Math.PI;
	public static double e = Math.E;
	public static int  n = 10;
	public static double myFunct(double x){
		// the standard deviation was omitted because it disappears 
		// in the integral answer
		double answer = (1/(Math.sqrt(2*pi)))*Math.pow(e, -Math.pow(x, 2)/2);
		
		return answer;
	}
	
	public static double  myFunctDeriv(double a, double b){
		float answer = 1/3;
		
		return answer;
	}
	
	public static double simpson(double a, double b){ 
		//specific to equation
		double h = (b-a)/n;
		double sum_1 = 0;
		double sum_2 = 0;
		
		for (int j = 1; j < n/2; j++){
			sum_1 += myFunct(( 2*j*h) + a);
		}
		
		for (int j = 1; j < n/2 + 1 ; j++){
			sum_2 += myFunct( a + ( 2*j - 1) *h);
		}
		
		double answer = (h/3)*(myFunct(a) + 2*sum_1 + 4*sum_2 + myFunct(b));
			
		if(answer+error(a,b,n) > 1)
			System.out.println("Simpson Computation Error");
		return answer;
	
	}
	
	public static double error(double a, double b, int n){ 
		double h = (b-a)/n;
		double answer = (-(b-a)/180)*Math.pow(h,4)*myFunctDeriv(a,b);
		System.out.println("ErrorAnswer"+answer);
		return answer;
	}
	
	public static void main(String[] args){
		
		// again the sigma was omitted in the interval because it goes away in the end
		System.out.println("Answer: " + simpson(-2, 2));
		
	}

}
