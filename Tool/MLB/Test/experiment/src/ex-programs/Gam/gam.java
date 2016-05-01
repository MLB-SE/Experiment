//package Gam;

public class gam {//bico beta
	public static double gammln(double xx)
	{
		int j;
		double x,y,tmp,ser;
		final double[] cof={76.18009172947146,-86.50532032941677,
			24.01409824083091,-1.231739572450155,0.1208650973866179e-2,
			-0.5395239384953e-5};

		y=x=xx;
		tmp=x+5.5;
		tmp -= (x+0.5)*Math.log(tmp);
		ser=1.000000000190015;
		for (j=0;j<6;j++) ser += cof[j]/++y;
		return -tmp+Math.log(2.5066282746310005*ser/x);
	}
	
	public static double factrl(int n){
		int ntop=4;
		double[] a ={1.0,1.0,2.0,6.0,24.0};
		int j;

		if (n < 0) System.out.println("Negative factorial in routine factrl");
		if (n > 32) return Math.exp(gammln(n+1.0));
		while (ntop<n) {
			j=ntop++;
			a[ntop]=a[j]*ntop;
		}
		return a[n];
	}
	
	public static double factln( int n)
	{
		double[] a = new double[101];

		if (n < 0) System.out.println("Negative factorial in routine factln");
		if (n <= 1) return 0.0;
		if (n <= 100)
			return (a[n] != 0.0 ? a[n] : (a[n]=gammln(n+1.0)));
		else return gammln(n+1.0);
	}
	
	public static double bico( int n, int k)
	{
		return Math.floor(0.5+Math.exp(factln(n)-factln(k)-factln(n-k)));
	}

	public static double erff(double x){
		return x < 0.0 ? -gammp(0.5,x*x) : gammp(0.5,x*x);
	}
	public static double erffc(double x){
		return x < 0.0 ? 1.0+gammp(0.5,x*x) : gammq(0.5,x*x);
	}
	
	static double  gamser,gammcf,gln;
	public static double gammp(double a, double x){
		if (x < 0.0 || a <= 0.0)
			System.out.println("Invalid arguments in routine gammp");
		if (x < a+1.0) {
			gser(a,x);
			return gamser;
		} else {
			gcf(a,x);
			return 1.0-gammcf;
		}
	}
	

	public static double gammq(double a, double x){
		if (x < 0.0 || a <= 0.0)
			System.out.println("Invalid arguments in routine gammq");
		if (x < a+1.0) {
			gser(a,x);
			return 1.0-gamser;
		} else {
			gcf(a,x);
			return gammcf;
		}
	}
	
	public static void gser(double a, double x){
		final int ITMAX=100;
		final double EPS=1e-14;
		int n;
		double sum,del,ap;

		gln=gammln(a);
		if (x <= 0.0) {
			if (x < 0.0) System.out.println("x less than 0 in routine gser");
			gamser=0.0;
			return;
		} else {
			ap=a;
			del=sum=1.0/a;
			for (n=0;n<ITMAX;n++) {
				++ap;
				del *= x/ap;
				sum += del;
				if (Math.abs(del) < Math.abs(sum)*EPS) {
					gamser=sum*Math.exp(-x+a*Math.log(x)-gln);
					return;
				}
			}
			System.out.println("a too large, ITMAX too small in routine gser");
			return;
		}
	}
	public static void gcf(double a, double x){
		final int ITMAX=100;
		final double EPS=1e-14;
		final double FPMIN=Double.MIN_VALUE/EPS;
		int i;
		double an,b,c,d,del,h;

		gln=gammln(a);
		b=x+1.0-a;
		c=1.0/FPMIN;
		d=1.0/b;
		h=d;
		for (i=1;i<=ITMAX;i++) {
			an = -i*(i-a);
			b += 2.0;
			d=an*d+b;
			if (Math.abs(d) < FPMIN) d=FPMIN;
			c=b+an/c;
			if (Math.abs(c) < FPMIN) c=FPMIN;
			d=1.0/d;
			del=d*c;
			h *= del;
			if (Math.abs(del-1.0) <= EPS) break;
		}
		if (i > ITMAX) System.out.println("a too large, ITMAX too small in gcf");
		gammcf=Math.exp(-x+a*Math.log(x)-gln)*h;
	}
}
