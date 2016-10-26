/*
 * Created on May 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 * SetText_ for batch run
 * It is better for using this action when batch run
 */
package actions;

import hlta.testexec.testcontrol.java.ActionBase;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import ibm.widgets.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.AWTException;

/**
 * @author 
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SetText_ extends ActionBase {

	/**
	 * @param object	GuiTestObject.
	 * @param param		String. The value to be inputted.
	 * @param extParam	Boolean.If check to see value is right after input.
	 */
	public SetText_(Object object, Object param, Object extParam) {
		super(object, param, extParam);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see hlta.testexec.testcontrol.java.ActionBase#doAction()
	 */
	public void doAction(Object object, Object param, Object extParam, Object... args) {
		// Three types of controls could be accepted
		// textfield, checkbox, radiobox
		try {
			if (object==null) {
				System.err.println("SetText: object is null");
			} else {
				((WTextField)object).setText(new String());
				//((WTextField)object).setText((String)param);
				
				Robot robot = new Robot();
				robot.delay(1000);
				for(int i = 0; i < param.toString().length(); i++){
					switch(param.toString().charAt(i)){
					case '`':
						robot.keyPress(KeyEvent.VK_BACK_QUOTE);
						robot.keyRelease(KeyEvent.VK_BACK_QUOTE);
						break;
					case '~':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_BACK_QUOTE);
						robot.keyRelease(KeyEvent.VK_BACK_QUOTE);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '1':
						robot.keyPress(KeyEvent.VK_1);
						robot.keyRelease(KeyEvent.VK_1);
						break;
					case '!':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_1);
						robot.keyRelease(KeyEvent.VK_1);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '2':
						robot.keyPress(KeyEvent.VK_2);
						robot.keyRelease(KeyEvent.VK_2);
						break;
					case '@':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_2);
						robot.keyRelease(KeyEvent.VK_2);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '3':
						robot.keyPress(KeyEvent.VK_3);
						robot.keyRelease(KeyEvent.VK_3);
						break;
					case '#':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_3);
						robot.keyRelease(KeyEvent.VK_3);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '4':
						robot.keyPress(KeyEvent.VK_4);
						robot.keyRelease(KeyEvent.VK_4);
						break;
					case '$':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_4);
						robot.keyRelease(KeyEvent.VK_4);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '5':
						robot.keyPress(KeyEvent.VK_5);
						robot.keyRelease(KeyEvent.VK_5);
						break;
					case '%':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_5);
						robot.keyRelease(KeyEvent.VK_5);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '6':
						robot.keyPress(KeyEvent.VK_6);
						robot.keyRelease(KeyEvent.VK_6);
						break;
					case '^':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_6);
						robot.keyRelease(KeyEvent.VK_6);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '7':
						robot.keyPress(KeyEvent.VK_7);
						robot.keyRelease(KeyEvent.VK_7);
						break;
					case '&':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_7);
						robot.keyRelease(KeyEvent.VK_7);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '8':
						robot.keyPress(KeyEvent.VK_8);
						robot.keyRelease(KeyEvent.VK_8);
						break;
					case '*':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_8);
						robot.keyRelease(KeyEvent.VK_8);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '9':
						robot.keyPress(KeyEvent.VK_9);
						robot.keyRelease(KeyEvent.VK_9);
						break;
					case '(':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_9);
						robot.keyRelease(KeyEvent.VK_9);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '0':
						robot.keyPress(KeyEvent.VK_0);
						robot.keyRelease(KeyEvent.VK_0);
						break;
					case ')':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_0);
						robot.keyRelease(KeyEvent.VK_0);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '-':
						robot.keyPress(KeyEvent.VK_MINUS);
						robot.keyRelease(KeyEvent.VK_MINUS);
						break;
					case '_':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_MINUS);
						robot.keyRelease(KeyEvent.VK_MINUS);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '=':
						robot.keyPress(KeyEvent.VK_EQUALS);
						robot.keyRelease(KeyEvent.VK_EQUALS);
						break;
					case '+':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_EQUALS);
						robot.keyRelease(KeyEvent.VK_EQUALS);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'a':
						robot.keyPress(KeyEvent.VK_A);
						robot.keyRelease(KeyEvent.VK_A);
						break;
					case 'A':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_A);
						robot.keyRelease(KeyEvent.VK_A);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'b':
						robot.keyPress(KeyEvent.VK_B);
						robot.keyRelease(KeyEvent.VK_B);
						break;
					case 'B':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_B);
						robot.keyRelease(KeyEvent.VK_B);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'c':
						robot.keyPress(KeyEvent.VK_C);
						robot.keyRelease(KeyEvent.VK_C);
						break;
					case 'C':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_C);
						robot.keyRelease(KeyEvent.VK_C);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'd':
						robot.keyPress(KeyEvent.VK_D);
						robot.keyRelease(KeyEvent.VK_D);
						break;
					case 'D':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_D);
						robot.keyRelease(KeyEvent.VK_D);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'e':
						robot.keyPress(KeyEvent.VK_E);
						robot.keyRelease(KeyEvent.VK_E);
						break;
					case 'E':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_E);
						robot.keyRelease(KeyEvent.VK_E);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'f':
						robot.keyPress(KeyEvent.VK_F);
						robot.keyRelease(KeyEvent.VK_F);
						break;
					case 'F':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_F);
						robot.keyRelease(KeyEvent.VK_F);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'g':
						robot.keyPress(KeyEvent.VK_G);
						robot.keyRelease(KeyEvent.VK_G);
						break;
					case 'G':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_G);
						robot.keyRelease(KeyEvent.VK_G);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'h':
						robot.keyPress(KeyEvent.VK_H);
						robot.keyRelease(KeyEvent.VK_H);
						break;
					case 'H':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_H);
						robot.keyRelease(KeyEvent.VK_H);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'i':
						robot.keyPress(KeyEvent.VK_I);
						robot.keyRelease(KeyEvent.VK_I);
						break;
					case 'I':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_I);
						robot.keyRelease(KeyEvent.VK_I);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'j':
						robot.keyPress(KeyEvent.VK_J);
						robot.keyRelease(KeyEvent.VK_J);
						break;
					case 'J':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_J);
						robot.keyRelease(KeyEvent.VK_J);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'k':
						robot.keyPress(KeyEvent.VK_K);
						robot.keyRelease(KeyEvent.VK_K);
						break;
					case 'K':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_K);
						robot.keyRelease(KeyEvent.VK_K);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'l':
						robot.keyPress(KeyEvent.VK_L);
						robot.keyRelease(KeyEvent.VK_L);
						break;
					case 'L':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_L);
						robot.keyRelease(KeyEvent.VK_L);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'm':
						robot.keyPress(KeyEvent.VK_M);
						robot.keyRelease(KeyEvent.VK_M);
						break;
					case 'M':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_M);
						robot.keyRelease(KeyEvent.VK_M);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'n':
						robot.keyPress(KeyEvent.VK_N);
						robot.keyRelease(KeyEvent.VK_N);
						break;
					case 'N':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_N);
						robot.keyRelease(KeyEvent.VK_N);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'o':
						robot.keyPress(KeyEvent.VK_O);
						robot.keyRelease(KeyEvent.VK_O);
						break;
					case 'O':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_O);
						robot.keyRelease(KeyEvent.VK_O);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'p':
						robot.keyPress(KeyEvent.VK_P);
						robot.keyRelease(KeyEvent.VK_P);
						break;
					case 'P':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_P);
						robot.keyRelease(KeyEvent.VK_P);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'q':
						robot.keyPress(KeyEvent.VK_Q);
						robot.keyRelease(KeyEvent.VK_Q);
						break;
					case 'Q':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_Q);
						robot.keyRelease(KeyEvent.VK_Q);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'r':
						robot.keyPress(KeyEvent.VK_R);
						robot.keyRelease(KeyEvent.VK_R);
						break;
					case 'R':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_R);
						robot.keyRelease(KeyEvent.VK_R);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 's':
						robot.keyPress(KeyEvent.VK_S);
						robot.keyRelease(KeyEvent.VK_S);
						break;
					case 'S':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_S);
						robot.keyRelease(KeyEvent.VK_S);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 't':
						robot.keyPress(KeyEvent.VK_T);
						robot.keyRelease(KeyEvent.VK_T);
						break;
					case 'T':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_T);
						robot.keyRelease(KeyEvent.VK_T);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'u':
						robot.keyPress(KeyEvent.VK_U);
						robot.keyRelease(KeyEvent.VK_U);
						break;
					case 'U':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_U);
						robot.keyRelease(KeyEvent.VK_U);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'v':
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						break;
					case 'V':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'w':
						robot.keyPress(KeyEvent.VK_W);
						robot.keyRelease(KeyEvent.VK_W);
						break;
					case 'W':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_W);
						robot.keyRelease(KeyEvent.VK_W);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'x':
						robot.keyPress(KeyEvent.VK_X);
						robot.keyRelease(KeyEvent.VK_X);
						break;
					case 'X':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_X);
						robot.keyRelease(KeyEvent.VK_X);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'y':
						robot.keyPress(KeyEvent.VK_Y);
						robot.keyRelease(KeyEvent.VK_Y);
						break;
					case 'Y':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_Y);
						robot.keyRelease(KeyEvent.VK_Y);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case 'z':
						robot.keyPress(KeyEvent.VK_Z);
						robot.keyRelease(KeyEvent.VK_Z);
						break;
					case 'Z':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_Z);
						robot.keyRelease(KeyEvent.VK_Z);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '[':
						robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
						robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
						break;
					case '{':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
						robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case ']':
						robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
						robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
						break;
					case '}':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
						robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '\\':
						robot.keyPress(KeyEvent.VK_BACK_SLASH);
						robot.keyRelease(KeyEvent.VK_BACK_SLASH);
						break;
					case '|':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_BACK_SLASH);
						robot.keyRelease(KeyEvent.VK_BACK_SLASH);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case ';':
						robot.keyPress(KeyEvent.VK_SEMICOLON);
						robot.keyRelease(KeyEvent.VK_SEMICOLON);
						break;
					case ':':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_SEMICOLON);
						robot.keyRelease(KeyEvent.VK_SEMICOLON);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '\'':
						robot.keyPress(KeyEvent.VK_QUOTE);
						robot.keyRelease(KeyEvent.VK_QUOTE);
						break;
					case '"':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_QUOTE);
						robot.keyRelease(KeyEvent.VK_QUOTE);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case ',':
						robot.keyPress(KeyEvent.VK_COMMA);
						robot.keyRelease(KeyEvent.VK_COMMA);
						break;
					case '<':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_COMMA);
						robot.keyRelease(KeyEvent.VK_COMMA);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '.':
						robot.keyPress(KeyEvent.VK_PERIOD);
						robot.keyRelease(KeyEvent.VK_PERIOD);
						break;
					case '>':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_PERIOD);
						robot.keyRelease(KeyEvent.VK_PERIOD);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case '/':
						robot.keyPress(KeyEvent.VK_SLASH);
						robot.keyRelease(KeyEvent.VK_SLASH);
						break;
					case '?':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_SLASH);
						robot.keyRelease(KeyEvent.VK_SLASH);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case ' ':
						robot.keyPress(KeyEvent.VK_SPACE);
						robot.keyRelease(KeyEvent.VK_SPACE);
						break;
					}
				}
			}
		} catch (NullPointerException e) {
			
		} catch (ClassCastException e) {
			System.err.println("Could not perform action \'" + desc + "\'");
		} catch (AWTException e){
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
	}
}

