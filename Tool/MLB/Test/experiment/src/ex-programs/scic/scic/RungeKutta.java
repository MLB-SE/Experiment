package scic;
/*
 * text here: http://iop.vast.ac.vn/~nvthanh/cours/numerical_methods/CH6_fb_p1.pdf
 * modified Taylor methods to preserve high-order error bounds 
 * but eliminates high-order partial derivative evaluations
 */
public class RungeKutta {
	
	public static void rungeKutta(double t){
		//double t = 0;
		double h = .01;
		double w = h/2;
		double n = 2;
		
		for (int i = 0; i<= n; i++){
			//System.out.println(w);
			w = w + h* (myFunct(t + (h/2), w + (h/2) * myFunct(t, w)));
			t += i*h;
		}
	}
	
	public static double myFunct(double t, double x){

		
		double k = 6.22 * Math.pow(10, -19);
		double n_1 = 2000;
		double n_2 = 2000;
		double n_3 = 3000;
		
		double myFunct;
		myFunct = k*Math.pow((n_1 - x/2),2)*Math.pow((n_2 - x/2),2)*Math.pow((n_3 - 3*x/4),3);
		
		if(t > myFunct) System.out.println("Test Get");
		return myFunct;
	}
	
	public static void main(String[] args){
		rungeKutta(0);
	}
}
