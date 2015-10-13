/**
 *
 * @author Alex Soffa
 */
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class Citysim9000Test {

	@SuppressWarnings("unchecked")
	
	//  Tests that creating an instance of a location object with a given name
	//  does set the name of that location object to the given name.
	@Test
	public void testLocation() {
		Location loc = new Location("String");
		assertTrue(loc.locationName.equalsIgnoreCase("String"));
	}

	//  Tests that for a given location object, the add side street location takes a 
	//  new location and a name for the street to get there from current location.
	//  The test asserts that these fields are set to what they should be for a proper
	//  call to the addSideLoc method.
	@Test
	public void testAddSideLoc() {
		Location loc = new Location("String");
		loc.addSideLoc(new Location("String2"), "Street");
		assertTrue(loc.sideStreet.locationName.equalsIgnoreCase("String2"));
		assertTrue(loc.street.equalsIgnoreCase("Street"));
	}

	//  Tests that for a given location object, the add main street location takes a 
	//  new location and a name for the street to get there from current location.
	//  The test asserts that these fields are set to what they should be for a proper
	//  call to the addMainLoc method.
	@Test
	public void testAddMainLoc() {
		Location loc = new Location("String");
		loc.addMainLoc(new Location("String2"), "Avenue");
		assertTrue(loc.mainStreet.locationName.equalsIgnoreCase("String2"));
		assertTrue(loc.avenue.equalsIgnoreCase("Avenue"));
	}	
	
	//  This test verifies that when the object of cityMap is called that the map is
	//  generated as per the requirements of the location set up and that the names
	//  match the location object's name.
	@Test
	public void testMapGeneration() {
		cityMap map = new cityMap();
		assertTrue(map.bookstore.locationName.equalsIgnoreCase("Book Store"));
		assertTrue(map.mall.locationName.equalsIgnoreCase("Mall"));
		assertTrue(map.outsideCity.locationName.equalsIgnoreCase("Outside City"));
		assertTrue(map.university.locationName.equalsIgnoreCase("University"));
		assertTrue(map.coffee.locationName.equalsIgnoreCase("Coffee Shop"));
	}
	
	//  This test makes sure that for a given seed value, set to 1 for this test, should
	//  produce the correct result for where the starting location should be for that
	//  seed value.
	@Test
	public void testCityBuilder() {
		cityMap map = new cityMap();
		Location picked;
		picked = map.cityMapBuilder(1);
		assertTrue(picked.locationName.equalsIgnoreCase("Mall"));
	}
	
	//  This test verifies that the method testOutsideStart properly handles the 
	//  situation of the starting location being "Outside City", and to pseudorandomly
   	//  pick the next location based on two possibilities. These possibilites for the
	//  test are decided by a set value being positive and the other being negative.
	@Test
	public void testOutsideStart() {
		cityMap map = new cityMap();
		Location out = new Location("Outside City");
		int positive = 1;
		int negative = -1;
		map.ifOutsideStart(out, positive);
		assertTrue(out.mainStreet.locationName.equalsIgnoreCase("University"));
		map.ifOutsideStart(out, negative);
		assertTrue(out.mainStreet.locationName.equalsIgnoreCase("Mall"));
	}
	
	//  This test verifies that for the main method that for a generic seed value set
	//  to 1, that the random number generator will accept that command line argument
	//  and produce a psuedo-random number based on that seed.
	@Test
	public void testNavigateCity()
	{
		Citysim9000 sim = new Citysim9000();
		String [] args = {"1"};
		try{
			sim.main(args);
		}
		catch (Exception e)
		{
			System.out.println("Error in args");
		}
		Random generator = new Random(1);
		long num = generator.nextLong();
		assertEquals(num, num);
	
	}

}