package color;

public class laplace {
	public static boolean check_cons(float a, float b, float c, float d, float e){
		if(4.0*a == b+c+d+e) return true;
		else return false;
	}
	public static void solve(float t11, float t12,float t13, float t21,float t22, float t23,float t31, float t32, float t33){	
		int w= 4, h = 4;
		float[][] t = new float[w+1][h+1];
		float left = 0.0f, right = 0.0f, top = 100.0f, bottom = 0.0f;
		
		for(int i =1;i < w;i++){
			t[i][0] = left;
			t[i][h] = right;
		}
		
		for(int j = 1;j < h;j++){
			t[0][j] = top;
			t[w][j] = bottom;
		}
		
		t[0][0] = 0.0f;
		t[0][h] = 0.0f;
		t[w][0] = 0.0f;
		t[w][h] = 0.0f;
		
		t[1][1] = t11;t[1][2] = t12;t[1][3] = t13;
		t[2][1] = t21;t[2][2] = t22;t[2][3] = t23;
		t[3][1] = t31;t[3][2] = t32;t[3][3] = t33;
		
		boolean result = true;
		for(int i = 1; i<w; i++){
			for(int j = 1; j<h; j++)
				if(!check_cons(t[i][j],t[i-1][j],t[i][j-1],t[i+1][j],t[i][j+1])){
					result = false;
					break;
				}
		}
		if(result)	System.out.println("Find Solution");
	}
}
