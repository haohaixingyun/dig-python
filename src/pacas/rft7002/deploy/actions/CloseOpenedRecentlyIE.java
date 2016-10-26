package actions;

import hlta.testexec.platform.win32.Win32IEHelper;
import hlta.testexec.testcontrol.java.ActionBase;
import java.util.Calendar;
import ibm.util.BrowserOps;
import hlta.testexec.testcontrol.java.TestCase;
public class CloseOpenedRecentlyIE extends ActionBase {

	public CloseOpenedRecentlyIE(Object object, Object param, Object extParam, Object[] args) {
		super(object, param, extParam, args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(Object object, Object param, Object extParam, TestCase.Step step,Object... args) {}
	public void doAction(Object object, Object param, Object extParam,
			Object... args) {
		// TODO Auto-generated method stub
		int[] ies = Win32IEHelper.getIEWnds();
		Calendar ca = Calendar.getInstance();
		System.out.println(ca.toString());
		int k=0;
		int hWin = 0;
		for (int a : ies) {
			System.out.printf("0x%x\n",a);
			System.out.println(Win32IEHelper.getIEWndCreationTime(a));
			int []dataArray = Win32IEHelper.getIEWndCreationTime(a);
			System.out.println(dataArray[0]);
			System.out.println(dataArray[1]);
			System.out.println(dataArray[2]);
			System.out.println(dataArray[3]);
			System.out.println(dataArray[4]);
			System.out.println(dataArray[5]);
			System.out.println(dataArray[6]);
			if(k==0){
				ca.set(Calendar.YEAR, dataArray[6]);
				ca.set(Calendar.MONTH,dataArray[5] );
				ca.set(Calendar.DATE, dataArray[4]);
				ca.set(Calendar.HOUR, dataArray[3]);
				ca.set(Calendar.MINUTE, dataArray[2]);
				ca.set(Calendar.SECOND, dataArray[1]);
				ca.set(Calendar.MILLISECOND, dataArray[0]);
				k++;
				hWin = a;
				continue;	
			}
			Calendar ca1 = Calendar.getInstance();
			ca1.set(Calendar.YEAR, dataArray[6]);
			ca1.set(Calendar.MONTH,dataArray[5] );
			ca1.set(Calendar.DATE, dataArray[4]);
			ca1.set(Calendar.HOUR, dataArray[3]);
			ca1.set(Calendar.MINUTE, dataArray[2]);
			ca1.set(Calendar.SECOND, dataArray[1]);
			ca1.set(Calendar.MILLISECOND, dataArray[0]);
			int result = ca.compareTo(ca1);
			if(result<=0){
				ca.set(Calendar.YEAR, ca1.get(Calendar.YEAR));
				ca.set(Calendar.MONTH,ca1.get(Calendar.MONTH));
				ca.set(Calendar.DATE, ca1.get(Calendar.DATE));
				ca.set(Calendar.HOUR, ca1.get(Calendar.HOUR));
				ca.set(Calendar.MINUTE, ca1.get(Calendar.MINUTE));
				ca.set(Calendar.SECOND, ca1.get(Calendar.SECOND));
				ca.set(Calendar.MILLISECOND, ca1.get(Calendar.MILLISECOND));
				hWin = a;
			}
		}
		Win32IEHelper.getBrowserTestObjectFromHWND(hWin).close();
	}

}
