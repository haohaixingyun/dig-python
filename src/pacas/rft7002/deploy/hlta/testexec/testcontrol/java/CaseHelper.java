package hlta.testexec.testcontrol.java;

import java.util.ArrayList;

import hlta.testexec.platform.rft7.web.common.CustomLogger;
import hlta.testexec.testcontrol.java.TestCase;

import com.rational.test.ft.script.RationalTestScript;
/**
 *
 * Description : A super helper class to extend script's functions 
 * @author wangzheng
 *
 */
public abstract class CaseHelper extends RationalTestScript {

	public int caseResult = TestCase.IResult.NOT_RUN;
	public TestCase theCase;
	public CustomLogger logger;
	protected ArrayList dependentCases = null;

	public ArrayList getDependentCases() {
		return dependentCases;
	}
	
	public abstract void testMain(Object[] args);
}