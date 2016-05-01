//package Gam;

public class expint {
	
	public static double expint( int n, double x){
		final int MAXIT=100;
		final double EULER=0.577215664901533;
		final double EPS=1e-14;
		final double BIG=Double.MAX_VALUE*EPS;
		int i,ii,nm1;
		double a,b,c,d,del,fact,h,psi,ans = 0;

		nm1=n-1;
		if (n < 0 || x < 0.0 || (x==0.0 && (n==0 || n==1)))
		System.out.println("bad arguments in expint");
		else {
			if (n == 0) ans=Math.exp(-x)/x;
			else {
				if (x == 0.0) ans=1.0/nm1;
				else {
					if (x > 1.0) {
						b=x+n;
						c=BIG;
						d=1.0/b;
						h=d;
						for (i=1;i<=MAXIT;i++) {
							a = -i*(nm1+i);
							b += 2.0;
							d=1.0/(a*d+b);
							c=b+a/c;
							del=c*d;
							h *= del;
							if (Math.abs(del-1.0) <= EPS) {
								ans=h*Math.exp(-x);
								return ans;
							}
						}
						System.out.println("continued fraction failed in expint");
					} else {
						ans = (nm1!=0 ? 1.0/nm1 : -Math.log(x)-EULER);
						fact=1.0;
						for (i=1;i<=MAXIT;i++) {
							fact *= -x/i;
							if (i != nm1) del = -fact/(i-nm1);
							else {
								psi = -EULER;
								for (ii=1;ii<=nm1;ii++) psi += 1.0/ii;
								del=fact*(-Math.log(x)+psi);
							}
							ans += del;
							if (Math.abs(del) < Math.abs(ans)*EPS) return ans;
						}
						System.out.println("series failed in expint");
					}
				}
			}
		}
		return ans;
	}
	
	public static double ei(double x)
	{
		final int MAXIT=100;
		final double EULER=0.577215664901533;
		final double EPS=1e-14;
		final double FPMIN=Double.MIN_VALUE/EPS;
		int k;
		double fact,prev,sum,term;

		if (x <= 0.0) System.out.println("Bad argument in ei");
		if (x < FPMIN) return Math.log(x)+EULER;
		if (x <= -Math.log(EPS)) {
			sum=0.0;
			fact=1.0;
			for (k=1;k<=MAXIT;k++) {
				fact *= x/k;
				term=fact/k;
				sum += term;
				if (term < EPS*sum) break;
			}
			if (k > MAXIT) System.out.println("Series failed in ei");
			return sum+Math.log(x)+EULER;
		} else {
			sum=0.0;
			term=1.0;
			for (k=1;k<=MAXIT;k++) {
				prev=term;
				term *= k/x;
				if (term < EPS) break;
				if (term < prev) sum += term;
				else {
					sum -= prev;
					break;
				}
			}
			return Math.exp(x)*(1.0+sum)/x;
		}
	}
	
	
	
}
