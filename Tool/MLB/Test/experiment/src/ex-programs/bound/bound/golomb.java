package bound;

public class golomb {
	public static int n = 4;
	public static int m = 6;
	public static boolean check_domain(int x){
		if(x>=0 && x<=m) return true;
		return false;
	}
	
	public static boolean check_mark(int[] mark){
		boolean check_mark = true;
		for(int i = 0;i<n-1;i++){
			if(mark[i]>=mark[i+1]) check_mark = false;
		}
		return check_mark;
	}
	
	public static boolean check_diffs(int[][] diffs){
		boolean check_mark = true;
		int[] di = {diffs[1][0],diffs[2][0],diffs[3][0],diffs[2][1],diffs[3][1],diffs[3][2]};
		
		for(int i = 0; i<6; i++)
			for(int j = i; j<6; j++){
				if(di[i] == di[j]) return false;
			}
		return check_mark;
	}
	
	public static void solve(int mark2, int mark3, int mark4){	
		int mark1 = 0;
		int[] mark = {mark1,mark2,mark3,mark4};
		int[][] diffs = new int[n][n];
		
		for(int i = 0; i<n;i++){
			for(int j = 0;j<i;j++)
				diffs[i][j] = mark[i] - mark[j];
		}
		
		if(check_domain(mark2)&&check_domain(mark3)&&check_domain(mark4))
			if(check_mark(mark)&&check_diffs(diffs)&& diffs[1][0]<diffs[3][2])
						System.out.println("Find Solution");
	}
}
