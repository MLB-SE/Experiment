package color;

public class loan {
	public static void solve(float B4){
		float R = (float) 260.0;
		float I = (float) 0.04;
		float P = 1000;
		
		float B1 = (float) (P*(1.0 + I) - R);
		float B2 = (float) (B1*(1.0 + I) - R);
		float B3 = (float) (B2*(1.0 + I) - R);
		
		if(B4 == B3*(1.0 + I) - R)
			System.out.println("Find Solution");
	}
}
