import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.*;

import java.util.*;


//javac -cp ./junit-4.12.jar;./hamcrest-core-1.3.jar;./mockito-core-1.10.19.jar;./objenesis-2.4.jar *.java
//java -cp .;./junit-4.12.jar;./hamcrest-core-1.3.jar;./mockito-core-1.10.19.jar;./objenesis-2.4.jar TestRunner


public class MonkeySimTest {

    private MonkeySim sim;// = new MonkeySim();
	
	@Before
	public void setup(){
		sim = new MonkeySim();
	}
	
	//Pinning tests for getFirstMonkey
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
		System.out.println("Getting first monkey");
		System.out.println(monkeyList.size());
		Monkey firstMonkey = sim.getFirstMonkey(monkeyList);
		System.out.println(firstMonkey.getMonkeyNum());
		assertEquals(firstMonkey.getMonkeyNum(), 1);
	}
	
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
	
	@Test
	public void TestGetEmptyListMonkey(){
		Monkey firstMonkey = sim.getFirstMonkey(new LinkedList<Monkey>());
		assertNull(firstMonkey);
	}
	
	//Tests happy path of stringifying results
	@Test
	public void TestStringifyResults() throws NoIdException{
		Monkey m1 = mock(Monkey.class);
		Monkey m2 = mock(Monkey.class);
		when(m1.getMonkeyNum()).thenReturn(1);
		when(m1.getId()).thenReturn(1);
		when(m2.getMonkeyNum()).thenReturn(2);
		when(m2.getId()).thenReturn(2);
		String str = sim.stringifyResults(1, m1, m2);
		//System.out.println(str);
		assertEquals(str, "//Round 1: Threw banana from Monkey (#1 / ID 1) to Monkey (#2 / ID 2)");
	}
	
	
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
			System.out.println("catchign");
			assertTrue(true);
		}
		//System.out.println(str);
		//assertEquals(str, "//Round 1: Threw banana from Monkey (#1 / ID 1) to Monkey (#2 / ID 2)");
	}
	
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
			System.out.println("catchign");
			assertTrue(true);
		}
	}
	
	
	// //Test RentACat is initialized with empty cat arrayList
	// @Test
	// public void testInitCatList(){
		// assertEquals(rentACat.getCats().size(), 0);
	// }
	
	// //Test RentACat is initialized with empty customer arrayList
	// @Test
	// public void testInitCustomerList(){
		// assertEquals(rentACat.getCustomers().size(), 0);
	// }
	
	// //Test init method creates Cat ArrayList with 3 cats
    // @Test
	// public void testSetupCatList(){
		// rentACat.init();
		// assertEquals(rentACat.getCats().size(), 3);
	// }
	
 	// //Test init method creates Customer ArrayList with 2 customers
	// @Test
	// public void testSetupCustomerList(){
		// rentACat.init();
		// assertEquals(rentACat.getCustomers().size(), 2);
	// }
	
	// //Test RentACat returns empty string if not initialized
	// @Test
	// public void testEmptyCatList(){
		// assertEquals(rentACat.listCats(), "");
	// }
	
	// //Test that the listCats method returns the toString method of the cats in listCats
	// //This test uses stubs and doubles!
	// @Test
	// public void testListCats(){
		// String testString = "I'm just a poor cat!";
		// Cat mockCat = mock(Cat.class);
		// when(mockCat.isAvailable()).thenReturn(true);
		// when(mockCat.toString()).thenReturn(testString);
		// rentACat.addCat(mockCat);
		// assertEquals(rentACat.listCats(), testString + "\n");
	// }
	
	// //Tests method checking to see whether an invalid customer does not exist
	// @Test
	// public void testisInvalidCustomer(){
		// rentACat.init();
		// assertEquals(rentACat.isValidCustomer(0), false);
	// }
	
	// //Tests method checking to see whether a valid customer
	// @Test
	// public void testisValidCustomer(){
		// rentACat.init();
		// assertEquals(rentACat.isValidCustomer(100), true);
	// }
	
	// //Tests renting a valid cat to a valid customer
	// //This test uses stubs and doubles
	// @Test
	// public void testRentCat() {
		// Cat mockCat = mock(Cat.class);
		// Customer mockCustomer = mock(Customer.class);
		// when(mockCustomer.getId()).thenReturn(1);
		// when(mockCat.getCatId()).thenReturn(1);
		// rentACat.addCat(mockCat);
		// rentACat.addCustomer(mockCustomer);
		// rentACat.rentCat(1, 1);
		// verify(mockCat).rentCat(1);
	// }
	
	// //Tests renting an invalid cat to a customer
	// @Test
	// public void testBadRentCat() {
		// Cat mockCat = mock(Cat.class);
		// Customer mockCustomer = mock(Customer.class);
		// when(mockCustomer.getId()).thenReturn(1);
		// when(mockCat.getCatId()).thenReturn(1);
		// rentACat.addCat(mockCat);
		// rentACat.addCustomer(mockCustomer);
		// rentACat.rentCat(2, 1);
		// verify(mockCat, never()).rentCat(1);
	// }
	
	// //Tests returning a valid cat from a valid customer
	// @Test
	// public void testReturnCat() {
		// Cat mockCat = mock(Cat.class);
		// Customer mockCustomer = mock(Customer.class);
		// when(mockCustomer.getId()).thenReturn(1);
		// when(mockCat.getCatId()).thenReturn(1);
		// rentACat.addCat(mockCat);
		// rentACat.addCustomer(mockCustomer);
		// rentACat.returnCat(1);
		// verify(mockCat).returnCat();
	// }

	// //Tests returning an invalid cat from a customer
	// @Test
	// public void testReturnBadCat() {
		// Cat mockCat = mock(Cat.class);
		// Customer mockCustomer = mock(Customer.class);
		// when(mockCustomer.getId()).thenReturn(1);
		// when(mockCat.getCatId()).thenReturn(1);
		// rentACat.addCat(mockCat);
		// rentACat.addCustomer(mockCustomer);
		// rentACat.returnCat(2);
		// verify(mockCat, never()).returnCat();
	// }
}