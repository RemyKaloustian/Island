/*
 * 
 * Author : Rémy Kaloustian
 * Date : 7/11/15
 * GitHub : https://github.com/RemyKaloustian/Island.git
 * 
 */


package App;

public class Drone 
{
    long distanceFromWestBoundary;
    long distanceFromEastBoundary;
    long distanceFromSounthBoundary;
    long distanceFromNorthBoundary;
    char currentlyHeading;
    long distanceToDo;
    int turnCounter;
    char turnDirection;
    
    public Drone()
    {
        
    }//Drone()
    
    public String fly()
    {
        return "{\"action\" : \"fly\"}";
    }//fly()
    
    public String heading(char direction)
    {
        return "{ \"action\": \"heading\", \"parameters\": { \"direction\": \""+ direction +"\" } }";
    }//heading()
    
    public String echo (char direction)
    {
        return "{ \"action\": \"echo\", \"parameters\": { \""+ direction+ "\": \"+ +\" } }";
    }//echo()
    
    public String scan()
    {
        return "{ \"action\": \"scan\" }";
    }//scan()
    
}//class Drone
