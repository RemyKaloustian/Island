/*
 * 
 * Author : Rémy Kaloustian
 * Date : 
 * GitHub : https://github.com/RemyKaloustian/Island.git
 * 
 */


package App;

public class Drone 
{
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
