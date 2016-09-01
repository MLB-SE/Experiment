package color;

public class magicseries {
	public static boolean check_domain(int x){
		if(x>=0 && x<=4) return true;
		return false;
	}
	
	public static boolean check_sum(int s0, int s1, int s2,  int s3){
		int[] s = {s0,s1,s2,s3};
		for(int i =0; i<4; i++){
			int sum = 0;
			for(int j = 0;j<4;j++)
				if(s[j]==i) sum++;
			if(sum!=s[i]) return false;
		}
		return true;
	}
	
	public static void solve(int s0, int s1, int s2,  int s3){	
		if(check_domain(s0)&&check_domain(s1)&&check_domain(s2)&&check_domain(s3)){
			if(check_sum(s0,s1,s2,s3))
				System.out.println("Find Solution");
		}
	}
	public static void solve2(int s0, int s1, int s2,  int s3){
		if(check_domain(s0)&&check_domain(s1)&&check_domain(s2)&&check_domain(s3)){
			if(s0+s1+s2+s3 == 4 && s0*0+s1*1+s2*2+s3*3 == 4 && check_sum(s0,s1,s2,s3))
				System.out.println("Find Solution");
		}
	}
}
