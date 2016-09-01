package bound;

public class allinterval {
	public static int n = 4;
	public static boolean check_domain(int x){
		if(x>=1 && x<=4) return true;
		return false;
	}
	public static boolean check_diffs_x(int x1,int x2,int x3,int x4){
		boolean check_mark = true;
		int[] di = {x1,x2,x3,x4};
		
		for(int i = 0; i<4; i++)
			for(int j = i; j<4; j++){
				if(di[i] == di[j]) return false;
			}
		return check_mark;
	}
	public static boolean check_diffs_u(int x1,int x2,int x3){
		boolean check_mark = true;
		int[] di = {x1,x2,x3};
		
		for(int i = 0; i<3; i++)
			for(int j = i; j<3; j++){
				if(di[i] == di[j]) return false;
			}
		return check_mark;
	}
	public static void solve(int x1, int x2, int x3, int x4){	
		if(check_domain(x1)&&check_domain(x2)&&check_domain(x3)&&check_domain(x4)){
			if(check_diffs_x(x1,x2,x3,x4)){
				int u1 = Math.abs(x2-x1);
				int u2 = Math.abs(x3-x2);
				int u3 = Math.abs(x4-x3);
				if(check_diffs_u(u1,u2,u3))
					System.out.println("Find Solution");
			}	
		}
	}
}
