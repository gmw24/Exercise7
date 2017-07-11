import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.*;

import java.util.*;


//javac -cp ./junit-4.12.jar;./hamcrest-core-1.3.jar;./mockito-core-1.10.19.jar;./objenesis-2.4.jar *.java
//java -cp .;./junit-4.12.jar;./hamcrest-core-1.3.jar;./mockito-core-1.10.19.jar;./objenesis-2.4.jar TestRunner


public class MonkeySimTest {

    private MonkeySim sim;
	
	@Before
	public void setup(){
		sim = new MonkeySim();
	}
	
	//Pinning tests for getFirstMonkey
	//Tests that when given an even length monkeyList,
	//getFirstMonkey returns the first monkey of the list
	@Test
	public void TestGetFirstMonkeyEvenLength(){
		int s = 5;
		Monkey tmpMonkey = new Monkey(0);
		List<Monkey> monkeyList = new LinkedList<Monkey>();
		monkeyList.add(tmpMonkey);
	
		for (int j = 0; j < s; j++) {
			tmpMonkey = new Monkey();
			monkeyList.add(tmpMonkey);
		}
		Monkey firstMonkey = sim.getFirstMonkey(monkeyList);
		assertEquals(firstMonkey.getMonkeyNum(), 1);
	}
	
	//Tests that when given an odd length monkeyList,
	//getFirstMonkey returns the first monkey of the list
	@Test 
	public void TestGetFirstMonkeyOddLength(){
		int s = 6;
		Monkey tmpMonkey = new Monkey(0);
		List<Monkey> monkeyList = new LinkedList<Monkey>();
		monkeyList.add(tmpMonkey);
	
		for (int j = 0; j < s; j++) {
			tmpMonkey = new Monkey();
			monkeyList.add(tmpMonkey);
		}
		
		Monkey firstMonkey = sim.getFirstMonkey(monkeyList);
		assertEquals(firstMonkey.getMonkeyNum(), 1);
	}
	
	//Tests that when getFirstMonkey is called on an empty list,
	//a null monkey is returned
	@Test
	public void TestGetEmptyListMonkey(){
		Monkey firstMonkey = sim.getFirstMonkey(new LinkedList<Monkey>());
		assertNull(firstMonkey);
	}
	
	//Tests happy path of stringifying results, both monkeys return a proper id and number
	//Tests that the string returned is properly formatted
	@Test
	public void TestStringifyResults() throws NoIdException{
		Monkey m1 = mock(Monkey.class);
		Monkey m2 = mock(Monkey.class);
		when(m1.getMonkeyNum()).thenReturn(1);
		when(m1.getId()).thenReturn(1);
		when(m2.getMonkeyNum()).thenReturn(2);
		when(m2.getId()).thenReturn(2);
		String str = sim.stringifyResults(1, m1, m2);
		assertEquals(str, "//Round 1: Threw banana from Monkey (#1 / ID 1) to Monkey (#2 / ID 2)");
	}
	
	//Tests that when the first monkey has no id, an exception is thrown and caught
	@Test
	public void TestStringifyResultsInvalidId(){
		try{
		Monkey m1 = mock(Monkey.class);
		Monkey m2 = mock(Monkey.class);
		when(m1.getMonkeyNum()).thenReturn(1);
		when(m1.getId()).thenReturn(-1);
		when(m2.getMonkeyNum()).thenReturn(2);
		when(m2.getId()).thenReturn(2);
		String str = sim.stringifyResults(1, m1, m2);
		}
		catch(NoIdException e){
			assertTrue(true);
		}
	}
	
	//Tests that when the second monkey has no id, an exception is thrown and caught
	@Test
	public void TestStringifyResultsInvalid2() {
		try{
		Monkey m1 = mock(Monkey.class);
		Monkey m2 = mock(Monkey.class);
		when(m1.getMonkeyNum()).thenReturn(1);
		when(m1.getId()).thenReturn(1);
		when(m2.getMonkeyNum()).thenReturn(2);
		when(m2.getId()).thenReturn(-1);
		String str = sim.stringifyResults(1, m1, m2);
		}
		catch(NoIdException e){
			assertTrue(true);
		}
	}
}