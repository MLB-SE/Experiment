//package Bess;

public class dawson {
	public static double dawson(double x){
		final int NMAX=6;
		final double H=0.4, A1=2.0/3.0, A2=0.4, A3=2.0/7.0;
		int i,n0;
		boolean init = true;
		double d1,d2,e1,e2,sum,x2,xp,xx,ans;
		final double[] c = new double[NMAX];

		if (init) {
			init=false;
			for (i=0;i<NMAX;i++) c[i]=Math.exp(-SQR((2.0*i+1.0)*H));
		}
		if (Math.abs(x) < 0.2) {
			x2=x*x;
			ans=x*(1.0-A1*x2*(1.0-A2*x2*(1.0-A3*x2)));
		} else {
			xx=Math.abs(x);
			n0=(int) (2*(0.5*xx/H+0.5));
			xp=xx-n0*H;
			e1=Math.exp(2.0*xp*H);
			e2=e1*e1;
			d1=n0+1;
			d2=d1-2.0;
			sum=0.0;
			for (i=0;i<NMAX;i++,d1+=2.0,d2-=2.0,e1*=e2)
				sum += c[i]*(e1/d1+1.0/(d2*e1));
			ans=0.5641895835*SIGN(Math.exp(-xp*xp),x)*sum;
		}
		return ans;
	}
		
	public static double pythag(double a, double b)
	{
		double absa,absb;

		absa=Math.abs(a);
		absb=Math.abs(b);
		if (absa > absb) return absa*Math.sqrt(1.0+SQR(absb/absa));
		else return (absb == 0.0 ? 0.0 : absb*Math.sqrt(1.0+SQR(absa/absb)));
	}
	
	public static double probks(double alam)
	{
		final double EPS1=1.0e-6,EPS2=1.0e-16;
		int j;
		double a2,fac=2.0,sum=0.0,term,termbf=0.0;

		a2 = -2.0*alam*alam;
		for (j=1;j<=100;j++) {
			term=fac*Math.exp(a2*j*j);
			sum += term;
			if (Math.abs(term) <= EPS1*termbf || Math.abs(term) <= EPS2*sum) return sum;
			fac = -fac;
			termbf=Math.abs(term);
		}
		return 1.0;
	}
	public static double SIGN(double a, double b){
		return b >= 0 ? (a >= 0 ? a : -a) : (a >= 0 ? -a : a);
	}
	
	public static double SQR(double a) {
		return a*a;
	}
}
