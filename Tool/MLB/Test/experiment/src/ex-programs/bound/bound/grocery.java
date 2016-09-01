package bound;

public class grocery {
	public static boolean check_domain(int x){
		if(x>=0 && x<=711) return true;
		return false;
	}
	public static void solve(int item1, int item2, int item3, int item4){	
		if(check_domain(item1)&&check_domain(item2)&&check_domain(item3)&&check_domain(item4)
			&& item1 + item2 + item3 + item4 == 711 && (item1+0.0) * item2 * item3 * item4 == 711.0 * 100 * 100 * 100  &&
			 item1 <= item2 &&  item2 <= item3 &&  item3 <= item4)
						System.out.println("Find Solution");
	}
}
