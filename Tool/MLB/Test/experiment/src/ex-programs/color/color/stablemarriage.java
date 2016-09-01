package color;

public class stablemarriage {
	public static boolean check_domain(int x){
		if(x>=1 && x<=5) return true;
		return false;
	}
	public static void solve(int m, int w){	
		int[][] rankWomen ={ {1, 2, 4, 3, 5},{3, 5, 1, 2, 4},{5, 4, 2, 1, 3},{1, 3, 5, 4, 2},{4, 2, 3, 5, 1}};
		int[][] rankMen ={{5, 1, 2, 4, 3},{4, 1, 3, 2, 5},{5, 3, 2, 4, 1},{1, 5, 4, 3, 2},{4, 3, 2, 1, 5}};
		//System.out.println(rankMen[3][3]+"  "+rankWomen[1][1]);
		if(check_domain(m)&&check_domain(w))
		if((rankMen[m][w] >= rankMen[m][m]||rankWomen[w][w]<rankWomen[w][m])&&(rankWomen[w][m]>=rankWomen[w][w] || rankMen[m][m]<rankMen[m][w]))
						System.out.println("Find Solution");
	}
}
