/**
 *
 * @author Alex Soffa
 */
public class Location {
    Location sideStreet;
    Location mainStreet;
    String locationName;
    String avenue;
    String street;
    boolean visited;
    
    public Location(String name)
    {
        this.locationName = name;
    }
    
    public void addSideLoc(Location side, String viaStreet)
    {
        this.sideStreet = side;
        this.street = viaStreet;
    }
    
    public void addMainLoc(Location main, String viaAvenue)
    {
        this.mainStreet = main;
        this.avenue = viaAvenue;
    }
    
}