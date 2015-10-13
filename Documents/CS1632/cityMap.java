import java.util.Random;

/**
 *
 * @author Alex Soffa
 */
public class cityMap 
{
    Location outsideCity = new Location("Outside City");
    Location mall = new Location("Mall");
    Location bookstore = new Location("Book Store");
    Location university = new Location("University");
    Location coffee = new Location("Coffee Shop");
    int seed;
    public Location cityMapBuilder(int seed1)
    {
        Location startLoc;
		this.seed = seed1;
        Random generator = new Random(seed);
		int choice = generator.nextInt();
        startLoc = generateStart(choice);
        buildAttachments();
        return startLoc;
    }
    public void ifOutsideStart(Location outsideStart, int choice)
    {
        if(choice < 0)
            outsideStart.addMainLoc(mall, "Fourth Ave");
        else
            outsideStart.addMainLoc(university, "Fifth Ave");
    }
    public Location generateStart(int choice)
    {
        Location [] locs = {outsideCity, mall, bookstore, university, coffee};
        Location start;
		int save = choice;
        if(choice < 0)
            choice = choice * -1;
        choice = choice % 4;
        start = locs[choice];
		if(start == outsideCity)
			ifOutsideStart(start, save);
        return start;  
    }
    public void buildAttachments()
    {
        mall.addMainLoc(bookstore, "Fourth Ave");
        mall.addSideLoc(coffee, "Meow St.");
        bookstore.addMainLoc(outsideCity, "Fourth Ave");
        bookstore.addSideLoc(university, "Chirp St.");
        university.addMainLoc(coffee, "Fifth Ave");
        university.addSideLoc(bookstore, "Chirp St.");
        coffee.addMainLoc(outsideCity, "Fifth Ave");
        coffee.addSideLoc(mall, "Meow St.");  
    }
}