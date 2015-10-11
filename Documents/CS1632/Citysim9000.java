import java.util.Random;

/**
 *
 * @author Alex Soffa
 */
public class Citysim9000 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int seed = Integer.parseInt(args[0]);
        long choice;
        Random generator = new Random(seed);
        choice = generator.nextLong();
		int nextDriver;
		if(choice < 0)
			choice = choice * -1;
        cityMap map = new cityMap();
        Location driver = map.cityMapBuilder(seed);
        for(int driverNum = 0; driverNum < 5; driverNum++)
		{
            navigateCity(driver, choice++, driverNum);
			nextDriver = generator.nextInt();
			driver = map.generateStart(nextDriver);
		}
    }
    public static void navigateCity(Location driver, long choice, int driverNum)
    {
        while(driver.visited == false)
        {
            if(Long.lowestOneBit(choice) == 1 || driver.locationName.equalsIgnoreCase("outside city"))
            {
                System.out.println("Driver " + driverNum + " heading from " + driver.locationName + " to " + driver.mainStreet.locationName + " via " + driver.avenue);
                driver = driver.mainStreet;
                if(driver.locationName.equalsIgnoreCase("outside city"))
                    driver.visited = true;
            }
            else
            {
                System.out.println("Driver " + driverNum + " heading from " + driver.locationName + " to " + driver.sideStreet.locationName + " via " + driver.street);
                driver = driver.sideStreet;
            }
            if(driver.visited)
            {
                System.out.println("Driver " + driverNum + " has left the city!");
				System.out.println("----------------");
            }
            choice = choice >> 1;
        }
		driver.visited = false;
    }

}