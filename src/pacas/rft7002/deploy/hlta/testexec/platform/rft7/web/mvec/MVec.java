/*
 * Created on Apr 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package hlta.testexec.platform.rft7.web.mvec;

/**
 * @author Ryx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MVec {
	public long deterVal = -1;
	public int Xc = 0;
	public int Yc = 0;
	public int X1 = 0;
	public int Y1 = 0;
	public int X2 = 0;
	public int Y2 = 0;
	
	public MVec (int xc, int yc, int x1, int y1, int x2, int y2) {
		Xc = xc;
		Yc = yc;
		X1 = x1;
		Y1 = y1;
		X2 = x2;
		Y2 = y2;
	}
	public long deter() {
		return deter(new float[]{1,1,1,1,1,1});
	}
	public long deter(float[] weight_diag) {
//		if (deterVal==-1) {
			// Calculate deter
			int xcp = (int)(Xc * weight_diag[0]);
			int ycp = (int)(Yc * weight_diag[1]);
			int x1p = (int)(X1 * weight_diag[2]);
			int y1p = (int)(Y1 * weight_diag[3]);
			int x2p = (int)(X2 * weight_diag[4]);
			int y2p = (int)(Y2 * weight_diag[5]);
			
			deterVal = xcp * xcp + ycp * ycp + 
						x1p * x1p + y1p * y1p +
						x2p * x2p + y2p * y2p;
	//	}
		
		return deterVal;
	}
	public static int distance1 (int n1, int n2) {
		return Math.abs(n1-n2);
	}
	public static MVec makeMetricVect(MVec objVec, MVec oriVec) {
		MVec mvec = new MVec (
				distance1(objVec.Xc, oriVec.Xc),
				distance1(objVec.Yc, oriVec.Yc),
				distance1(objVec.X1, oriVec.X1),
				distance1(objVec.Y1, oriVec.Y1),
				distance1(objVec.X2, oriVec.X2),
				distance1(objVec.Y2, oriVec.Y2)
			);
		return mvec;
	}

	public static int findBestMVec(MVec[] vs) {
		return findBestMVec(vs,new float[] {1,1,1,1,1,1});
	}
	
	public static int findBestMVec(MVec[] vs,float[] diag) {
		int result = -1;
		long r = -1;
		for (int i = 0; i<vs.length;++i) {
			long q = vs[i].deter(diag);
			if (r > q || r==-1) {
				r = q;
				result = i;
			}
		}
		return result;
	}
	
}