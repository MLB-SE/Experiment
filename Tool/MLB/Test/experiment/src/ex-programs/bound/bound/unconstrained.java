package bound;

public class unconstrained {
	public static boolean check_domain(int x){
		if(x>=1 && x<=10) return true;
		return false;
	}
	public static boolean check_diffs(int x1,int x2,int x3,int x4){
		boolean check_mark = true;
		int[] di = {x1,x2,x3,x4};
		
		for(int i = 0; i<4; i++)
			for(int j = i; j<4; j++){
				if(di[i] == di[j]) return false;
			}
		return check_mark;
	}
	public static void solve(int x1, int x2, int x3, int x4){	
		if(check_domain(x1)&&check_domain(x2)&&check_domain(x3)&&check_domain(x4)
				&&check_diffs(x1,x2,x3,x4)	&& x1 + x2 + x3 + x4 == 9)
						System.out.println("Find Solution");
	}
}
