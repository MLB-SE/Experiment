//package caldat;

public class caldat {//test three,no connections
	public static int mm,id,iyyy;
	public static void caldat( int julian){
		final int IGREG=2299161;
		int ja,jalpha,jb,jc,jd,je;

		if (julian >= IGREG) {
			jalpha=(int) (((julian-1867216)-0.25)/36524.25);
			ja=(int) (julian+1+jalpha-(0.25*jalpha));
		} else if (julian < 0) {
			ja=julian+36525*(1-julian/36525);
		} else
			ja=julian;
		jb=ja+1524;
		jc=(int) (6680.0+((jb-2439870)-122.1)/365.25);
		jd=(int) (365*jc+(0.25*jc));
		je=(int) ((jb-jd)/30.6001);
		id=(int) (jb-jd-(30.6001*je));
		mm=je-1;
		if (mm > 12) mm -= 12;
		iyyy=jc-4715;
		if (mm > 2) --iyyy;
		if (iyyy <= 0) --iyyy;
		if (julian < 0) iyyy -= 100*(1-julian/36525);
	}
	
	
	public static int julday( int mmj,  int idj,  int iyyyj)
	{
		 int IGREG=15+31*(10+12*1582);
		int ja,jul,jy=iyyyj,jm;

		if (jy == 0) System.out.println("julday: there is no year zero.");
		if (jy < 0) ++jy;
		if (mmj > 2) {
			jm=mmj+1;
		} else {
			--jy;
			jm=mmj+13;
		}
		jul = (int) (Math.floor(365.25*jy)+Math.floor(30.6001*jm)+idj+1720995);
		if (idj+31*(mmj+12*iyyyj) >= IGREG) {
			ja=(int) (0.01*jy);
			jul += 2-ja+(0.25*ja);
		}
		return jul;
	}
	
	public static void flmoon( int n,  int nph)
	{
		final double RAD=3.141592653589793238/180.0;
		int i;
		double am,as,c,t,t2,xtra;

		c=n+nph/4.0;
		t=c/1236.85;
		t2=t*t;
		as=359.2242+29.105356*c;
		am=306.0253+385.816918*c+0.010730*t2;
		jd=2415020+28*n+7*nph;
		xtra=0.75933+1.53058868*c+((1.178e-4)-(1.55e-7)*t)*t2;
		if (nph == 0 || nph == 2)
			xtra += (0.1734-3.93e-4*t)*Math.sin(RAD*as)-0.4068*Math.sin(RAD*am);
		else if (nph == 1 || nph == 3)
			xtra += (0.1721-4.0e-4*t)*Math.sin(RAD*as)-0.6280*Math.sin(RAD*am);
		else System.out.println("nph is unknown in flmoon");
		i=(int) (xtra >= 0.0 ? Math.floor(xtra) : Math.ceil(xtra-1.0));
		jd += i;
		frac=xtra-i;
	}
	public static int jd = 0;
	public static double frac = 0.0;
	public static  int badluk(int IYBEG)	// Program badluk
	{
		int IYEND=IYBEG+1;
		final double ZON=-5.0;
		int ic,icon,idwk,im,iyyy,jd = 0,jday,n;
		double timzon=ZON/24.0,frac = 0;

		System.out.println("Full moons on Friday the 13th from ");
		System.out.println(IYBEG+" to "+IYEND);
		for (iyyy=IYBEG;iyyy<=IYEND;iyyy++) {
			for (im=1;im<=12;im++) {
				jday=julday(im,13,iyyy);
				idwk=((jday+1) % 7);
				if (idwk == 5) {
					n=(int) (12.37*(iyyy-1900+(im-0.5)/12.0));
					icon=0;
					for (int u = 0;u<12;u++) {
						flmoon(n,2);
						frac=24.0*(frac+timzon);
						if (frac < 0.0) {
							--jd;
							frac += 24.0;
						}
						if (frac > 12.0) {
							++jd;
							frac -= 12.0;
						} else
							frac += 12.0;
						if (jd == jday) {
							System.out.println(im);
							System.out.println(" /13/ "+ iyyy );
							System.out.println("Full moon " +frac);
							System.out.println(" hrs after midnight (EST)");
							break;
						} else {
							ic=(jday >= jd ? 1 : -1);
							if (ic == (-icon)) break;
							icon=ic;
							n += ic;
						}
					}
				}
			}
		}
		return 0;
	}
	
	
}

