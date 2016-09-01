package color;

public class sendmoremoney {
	public static boolean check_domain_0(int x){
		if(x>=0 && x<=9) return true;
		return false;
	}
	public static boolean check_domain_1(int x){
		if(x>=1 && x<=9) return true;
		return false;
	}

	public static boolean check_diff(int S, int E, int N,  int D, int M, int O, int R, int Y){
		int[] d = {S, E, N,  D, M, O, R, Y};
		for(int i=0;i<7;i++){
			for(int j = i+1;j<8;j++)
				if(d[i]==d[j]) return false;
		}
		return true;
	}
	
	public static void solve(int S, int E, int N,  int D, int M, int O, int R, int Y){	
		if(check_domain_1(S)&&check_domain_0(E)&&check_domain_0(N)&&check_domain_0(D)&&
				check_domain_1(M)&&check_domain_0(O)&&check_domain_0(R)&&check_domain_0(Y)&&
				(1000 * S + 100 * E + 10 * N + D+ 1000 * M + 100 * O + 10 * R + E == 10000 * M + 1000 * O + 100 * N + 10 * E + Y)
			&& check_diff(S, E, N,  D, M, O, R, Y))
						System.out.println("Find Solution");
	}
}
