import junit.framework.TestCase;


public class CalcTest extends TestCase {
	
	Calc testCalc;
	
	public void setUp() throws Exception{
		testCalc = new Calc();
	}	

	public void test(){
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testCalc.go();
		assertTrue(Math.abs(testCalc.beta0 + 22.55) < 0.01);
		assertTrue(Math.abs(testCalc.beta1 - 1.7279) < 0.01);
		assertTrue(Math.abs(testCalc.r - 0.9545) < 0.01);
		assertTrue(Math.abs(testCalc.y - 644.429) < 0.01);
	}
}
