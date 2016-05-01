//package Ell;

public class ell {
	
	public static double SQR(double a) {
		return a*a;
	}
	
	public static double SIGN(double a, double b){
		return b >= 0 ? (a >= 0 ? a : -a) : (a >= 0 ? -a : a);
	}
	
	public static double MAX(double a, double b){
		return b > a ? (b) : (a);
	}
	
	public static double MIN(double a, double b){
		return b < a ? (b) : (a);
	}
	
	
	public static double zbrent(double x1, double x2, double tol)
	{
		final int ITMAX=100;
		final double EPS=1e-14;
		int iter;
		double a=x1,b=x2,c=x2,d=0,e=0,min1,min2;
		double fa=Math.sin(a),fb=Math.sin(b),fc,p,q,r,s,tol1,xm;

		if ((fa > 0.0 && fb > 0.0) || (fa < 0.0 && fb < 0.0))
			System.out.println("Root must be bracketed in zbrent");
		fc=fb;
		for (iter=0;iter<ITMAX;iter++) {
			if ((fb > 0.0 && fc > 0.0) || (fb < 0.0 && fc < 0.0)) {
				c=a;
				fc=fa;
				e=d=b-a;
			}
			if (Math.abs(fc) < Math.abs(fb)) {
				a=b;
				b=c;
				c=a;
				fa=fb;
				fb=fc;
				fc=fa;
			}
			tol1=2.0*EPS*Math.abs(b)+0.5*tol;
			xm=0.5*(c-b);
			if (Math.abs(xm) <= tol1 || fb == 0.0) return b;
			if (Math.abs(e) >= tol1 && Math.abs(fa) > Math.abs(fb)) {
				s=fb/fa;
				if (a == c) {
					p=2.0*xm*s;
					q=1.0-s;
				} else {
					q=fa/fc;
					r=fb/fc;
					p=s*(2.0*xm*q*(q-r)-(b-a)*(r-1.0));
					q=(q-1.0)*(r-1.0)*(s-1.0);
				}
				if (p > 0.0) q = -q;
				p=Math.abs(p);
				min1=3.0*xm*q-Math.abs(tol1*q);
				min2=Math.abs(e*q);
				if (2.0*p < (min1 < min2 ? min1 : min2)) {
					e=d;
					d=p/q;
				} else {
					d=xm;
					e=d;
				}
			} else {
				d=xm;
				e=d;
			}
			a=b;
			fa=fb;
			if (Math.abs(d) > tol1)
				b += d;
			else
				b += SIGN(tol1,xm);
				fb=Math.sin(b);
		}
		System.out.println("Maximum number of iterations exceeded in zbrent");
		return 0.0;
	}
	public static double bxmin;
	public static double brent(double ax, double bx, double cx,double tol){
			final int ITMAX = 100;
			final double CGOLD=0.3819660;
			double ZEPS=1e-14*1.0e-3;
			int iter;
			double a,b,d=0.0,etemp,fu,fv,fw,fx;
			double p,q,r,tol1,tol2,u,v,w,x,xm;
			double e=0.0;

			a=(ax < cx ? ax : cx);
			b=(ax > cx ? ax : cx);
			x=w=v=bx;
			fw=fv=fx=Math.sin(x);
			for (iter=0;iter<ITMAX;iter++) {
				xm=0.5*(a+b);
				tol2=2.0*(tol1=tol*Math.abs(x)+ZEPS);
				if (Math.abs(x-xm) <= (tol2-0.5*(b-a))) {
					bxmin=x;
					return fx;
				}
				if (Math.abs(e) > tol1) {
					r=(x-w)*(fx-fv);
					q=(x-v)*(fx-fw);
					p=(x-v)*q-(x-w)*r;
					q=2.0*(q-r);
					if (q > 0.0) p = -p;
					q=Math.abs(q);
					etemp=e;
					e=d;
					if (Math.abs(p) >= Math.abs(0.5*q*etemp) || p <= q*(a-x) || p >= q*(b-x))
						d=CGOLD*(e=(x >= xm ? a-x : b-x));
					else {
						d=p/q;
						u=x+d;
						if (u-a < tol2 || b-u < tol2)
							d=SIGN(tol1,xm-x);
					}
				} else {
					d=CGOLD*(e=(x >= xm ? a-x : b-x));
				}
				u=(Math.abs(d) >= tol1 ? x+d : x+SIGN(tol1,d));
				fu=Math.sin(u);
				if (fu <= fx) {
					if (u >= x) a=x; else b=x;
					v=w;w=x;x=u;//shft3(v,w,x,u);
					fv=fw;fw=fx;fx=fu;//shft3(fv,fw,fx,fu);
				} else {
					if (u < x) a=u; else b=u;
					if (fu <= fw || w == x) {
						v=w;
						w=u;
						fv=fw;
						fw=fu;
					} else if (fu <= fv || v == x || v == w) {
						v=u;
						fv=fu;
					}
				}
			}
			System.out.println("Too many iterations in brent");
			bxmin=x;
			return fx;
		}
	public static double dxmin;
	public static double dbrent(double ax, double bx, double cx, double tol){
		final int ITMAX=100;
		double ZEPS=1e-14*1.0e-3;
		boolean ok1,ok2;
		int iter;
		double a,b,d=0.0,d1,d2,du,dv,dw,dx,e=0.0;
		double fu,fv,fw,fx,olde,tol1,tol2,u,u1,u2,v,w,x,xm;

		a=(ax < cx ? ax : cx);
		b=(ax > cx ? ax : cx);
		x=w=v=bx;
		fw=fv=fx=Math.sin(x);
		dw=dv=dx=Math.cos(x);
		for (iter=0;iter<ITMAX;iter++) {
			xm=0.5*(a+b);
			tol1=tol*Math.abs(x)+ZEPS;
			tol2=2.0*tol1;
			if (Math.abs(x-xm) <= (tol2-0.5*(b-a))) {
				dxmin=x;
				return fx;
			}
			if (Math.abs(e) > tol1) {
				d1=2.0*(b-a);
				d2=d1;
				if (dw != dx) d1=(w-x)*dx/(dx-dw);
				if (dv != dx) d2=(v-x)*dx/(dx-dv);
				u1=x+d1;
				u2=x+d2;
				ok1 = (a-u1)*(u1-b) > 0.0 && dx*d1 <= 0.0;
				ok2 = (a-u2)*(u2-b) > 0.0 && dx*d2 <= 0.0;
				olde=e;
				e=d;
				if (ok1 || ok2) {
					if (ok1 && ok2)
						d=(Math.abs(d1) < Math.abs(d2) ? d1 : d2);
					else if (ok1)
						d=d1;
					else
						d=d2;
					if (Math.abs(d) <= Math.abs(0.5*olde)) {
						u=x+d;
						if (u-a < tol2 || b-u < tol2)
							d=SIGN(tol1,xm-x);
					} else {
						d=0.5*(e=(dx >= 0.0 ? a-x : b-x));
					}
				} else {
					d=0.5*(e=(dx >= 0.0 ? a-x : b-x));
				}
			} else {
				d=0.5*(e=(dx >= 0.0 ? a-x : b-x));
			}
			if (Math.abs(d) >= tol1) {
				u=x+d;
				fu=Math.sin(u);
			} else {
				u=x+SIGN(tol1,d);
				fu=Math.sin(u);
				if (fu > fx) {
					dxmin=x;
					return fx;
				}
			}
			du=Math.cos(u);
			if (fu <= fx) {
				if (u >= x) a=x; else b=x;
				v=w;fv=fw;dv=dw;//mov3(v,fv,dv,w,fw,dw);
				w=x;fw=fx;dw=dx;//mov3(w,fw,dw,x,fx,dx);
				x=u;fx=du;dx=du;//mov3(x,fx,dx,u,fu,du);
			} else {
				if (u < x) a=u; else b=u;
				if (fu <= fw || w == x) {
					v=w;fv=fw;dv=dw;//mov3(v,fv,dv,w,fw,dw);
					w=u;fw=fu;dw=du;//mov3(w,fw,dw,u,fu,du);
				} else if (fu < fv || v == x || v == w) {
					v=u;fv=fu;dv=du;//mov3(v,fv,dv,u,fu,du);
				}
			}
		}
		System.out.println("Too many iterations in routine dbrent");
		return 0.0;
	}
	
	public static double elle(double phi, double ak){
		double cc,q,s;

		s=Math.sin(phi);
		cc=SQR(Math.cos(phi));
		q=(1.0-s*ak)*(1.0+s*ak);
		return s*(rf(cc,q,1.0)-(SQR(s*ak))*rd(cc,q,1.0)/3.0);
	}
	
	public static double ellf(double phi, double ak){
		double s;

		s=Math.sin(phi);
		return s*rf(SQR(Math.cos(phi)),(1.0-s*ak)*(1.0+s*ak),1.0);
	}
	
	public static double ellpi(double phi, double en, double ak){
		double cc,enss,q,s;

		s=Math.sin(phi);
		enss=en*s*s;
		cc=SQR(Math.cos(phi));
		q=(1.0-s*ak)*(1.0+s*ak);
		return s*(rf(cc,q,1.0)-enss*rj(cc,q,1.0,1.0+enss)/3.0);
	}
	
	private static double rj(double x, double y, double z, double p){
		final double ERRTOL=0.0015, TINY=2.5e-13, BIG=9.0e11;
		final double C1=3.0/14.0, C2=1.0/3.0, C3=3.0/22.0, C4=3.0/26.0;
		final double C5=0.75*C3, C6=1.5*C4, C7=0.5*C2, C8=C3+C3;
		double a = 0,alamb,alpha,ans,ave,b = 0,beta,delp,delx,dely,delz,ea,eb,ec,ed,ee,
			fac,pt,rcx = 0,rho,sqrtx,sqrty,sqrtz,sum,tau,xt,yt,zt;

		if (MIN(MIN(x,y),z) < 0.0 || MIN(MIN(x+y,x+z),MIN(y+z,Math.abs(p))) < TINY
			|| MAX(MAX(x,y),MAX(z,Math.abs(p))) > BIG)
				System.out.println("invalid arguments in rj");
		sum=0.0;
		fac=1.0;
		if (p > 0.0) {
			xt=x;
			yt=y;
			zt=z;
			pt=p;
		} else {
			xt=MIN(MIN(x,y),z);
			zt=MAX(MAX(x,y),z);
			yt=x+y+z-xt-zt;
			a=1.0/(yt-p);
			b=a*(zt-yt)*(yt-xt);
			pt=yt+b;
			rho=xt*zt/yt;
			tau=p*pt/yt;
			rcx=rc(rho,tau);
		}
		do {
			sqrtx=Math.sqrt(xt);
			sqrty=Math.sqrt(yt);
			sqrtz=Math.sqrt(zt);
			alamb=sqrtx*(sqrty+sqrtz)+sqrty*sqrtz;
			alpha=SQR(pt*(sqrtx+sqrty+sqrtz)+sqrtx*sqrty*sqrtz);
			beta=pt*SQR(pt+alamb);
			sum += fac*rc(alpha,beta);
			fac=0.25*fac;
			xt=0.25*(xt+alamb);
			yt=0.25*(yt+alamb);
			zt=0.25*(zt+alamb);
			pt=0.25*(pt+alamb);
			ave=0.2*(xt+yt+zt+pt+pt);
			delx=(ave-xt)/ave;
			dely=(ave-yt)/ave;
			delz=(ave-zt)/ave;
			delp=(ave-pt)/ave;
		} while (MAX(MAX(Math.abs(delx),Math.abs(dely)),
			MAX(Math.abs(delz),Math.abs(delp))) > ERRTOL);
		ea=delx*(dely+delz)+dely*delz;
		eb=delx*dely*delz;
		ec=delp*delp;
		ed=ea-3.0*ec;
		ee=eb+2.0*delp*(ea-ec);
		ans=3.0*sum+fac*(1.0+ed*(-C1+C5*ed-C6*ee)+eb*(C7+delp*(-C8+delp*C4))
			+delp*ea*(C2-delp*C3)-C2*delp*ec)/(ave*Math.sqrt(ave));
		if (p <= 0.0) ans=a*(b*ans+3.0*(rcx-rf(xt,yt,zt)));
		return ans;
	}
	
	private static double rf(double x, double y, double z){
		final double ERRTOL=0.0025, TINY=1.5e-38, BIG=3.0e37, THIRD=1.0/3.0;
		final double C1=1.0/24.0, C2=0.1, C3=3.0/44.0, C4=1.0/14.0;
		double alamb,ave,delx,dely,delz,e2,e3,sqrtx,sqrty,sqrtz,xt,yt,zt;

		if (MIN(MIN(x,y),z) < 0.0 || MIN(MIN(x+y,x+z),y+z) < TINY ||
			MAX(MAX(x,y),z) > BIG)
				System.out.println("invalid arguments in rf");
		xt=x;
		yt=y;
		zt=z;
		do {
			sqrtx=Math.sqrt(xt);
			sqrty=Math.sqrt(yt);
			sqrtz=Math.sqrt(zt);
			alamb=sqrtx*(sqrty+sqrtz)+sqrty*sqrtz;
			xt=0.25*(xt+alamb);
			yt=0.25*(yt+alamb);
			zt=0.25*(zt+alamb);
			ave=THIRD*(xt+yt+zt);
			delx=(ave-xt)/ave;
			dely=(ave-yt)/ave;
			delz=(ave-zt)/ave;
		} while (MAX(MAX(Math.abs(delx),Math.abs(dely)),Math.abs(delz)) > ERRTOL);
		e2=delx*dely-delz*delz;
		e3=delx*dely*delz;
		return (1.0+(C1*e2-C2-C3*e3)*e2+C4*e3)/Math.sqrt(ave);
	}
	
	private static double rd(double x, double y, double z){
		final double ERRTOL=0.0015, TINY=1.0e-25, BIG=4.5e21;
		final double C1=3.0/14.0, C2=1.0/6.0, C3=9.0/22.0;
		final double C4=3.0/26.0, C5=0.25*C3, C6=1.5*C4;
		double alamb,ave,delx,dely,delz,ea,eb,ec,ed,ee,fac,sqrtx,sqrty,
			sqrtz,sum,xt,yt,zt;

		if (MIN(x,y) < 0.0 || MIN(x+y,z) < TINY || MAX(MAX(x,y),z) > BIG)
			System.out.println("invalid arguments in rd");
		xt=x;
		yt=y;
		zt=z;
		sum=0.0;
		fac=1.0;
		do {
			sqrtx=Math.sqrt(xt);
			sqrty=Math.sqrt(yt);
			sqrtz=Math.sqrt(zt);
			alamb=sqrtx*(sqrty+sqrtz)+sqrty*sqrtz;
			sum += fac/(sqrtz*(zt+alamb));
			fac=0.25*fac;
			xt=0.25*(xt+alamb);
			yt=0.25*(yt+alamb);
			zt=0.25*(zt+alamb);
			ave=0.2*(xt+yt+3.0*zt);
			delx=(ave-xt)/ave;
			dely=(ave-yt)/ave;
			delz=(ave-zt)/ave;
		} while (MAX(MAX(Math.abs(delx),Math.abs(dely)),Math.abs(delz)) > ERRTOL);
		ea=delx*dely;
		eb=delz*delz;
		ec=ea-eb;
		ed=ea-6.0*eb;
		ee=ed+ec+ec;
		return 3.0*sum+fac*(1.0+ed*(-C1+C5*ed-C6*delz*ee)
			+delz*(C2*ee+delz*(-C3*ec+delz*C4*ea)))/(ave*Math.sqrt(ave));
	}
	
	private static double rc(double x, double y){
		final double ERRTOL=0.0012, TINY=1.69e-38, SQRTNY=1.3e-19, BIG=3.0e37;
		final double TNBG=TINY*BIG, COMP1=2.236/SQRTNY, COMP2=TNBG*TNBG/25.0;
		final double THIRD=1.0/3.0, C1=0.3, C2=1.0/7.0, C3=0.375, C4=9.0/22.0;
		double alamb,ave,s,w,xt,yt;

		if (x < 0.0 || y == 0.0 || (x+Math.abs(y)) < TINY || (x+Math.abs(y)) > BIG ||
			(y<-COMP1 && x > 0.0 && x < COMP2))
			System.out.println("invalid arguments in rc");
		if (y > 0.0) {
			xt=x;
			yt=y;
			w=1.0;
		} else {
			xt=x-y;
			yt= -y;
			w=Math.sqrt(x)/Math.sqrt(xt);
		}
		do {
			alamb=2.0*Math.sqrt(xt)*Math.sqrt(yt)+yt;
			xt=0.25*(xt+alamb);
			yt=0.25*(yt+alamb);
			ave=THIRD*(xt+yt+yt);
			s=(yt-ave)/ave;
		} while (Math.abs(s) > ERRTOL);
		return w*(1.0+s*s*(C1+s*(C2+s*(C3+s*C4))))/Math.sqrt(ave);
	}
	public static double sn, cn, dn;
	public static void sncndn(double uu, double emmc){
		final double CA=1.0e-8;
		boolean bo;
		int i,ii,l = 0;
		double a,b,c = 0,d = 0,emc,u;
		double[] em = new double[13],en =new double[13];

		emc=emmc;
		u=uu;
		if (emc != 0.0) {
			bo=(emc < 0.0);
			if (bo) {
				d=1.0-emc;
				emc /= -1.0/d;
				u *= (d=Math.sqrt(d));
			}
			a=1.0;
			dn=1.0;
			for (i=0;i<13;i++) {
				l=i;
				em[i]=a;
				en[i]=(emc=Math.sqrt(emc));
				c=0.5*(a+emc);
				if (Math.abs(a-emc) <= CA*a) break;
				emc *= a;
				a=c;
			}
			u *= c;
			sn=Math.sin(u);
			cn=Math.cos(u);
			if (sn != 0.0) {
				a=cn/sn;
				c *= a;
				for (ii=l;ii>=0;ii--) {
					b=em[ii];
					a *= c;
					c *= dn;
					dn=(en[ii]+a)/(b+a);
					a=c/b;
				}
				a=1.0/Math.sqrt(c*c+1.0);
				sn=(sn >= 0.0 ? a : -a);
				cn=c*sn;
			}
			if (bo) {
				a=dn;
				dn=cn;
				cn=a;
				sn /= d;
			}
		} else {
			cn=1.0/Math.cosh(u);
			dn=cn;
			sn=Math.tanh(u);
		}
	}
	
}
