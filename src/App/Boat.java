/*
 * 
 * 
 * Author : Remy Kaloustian
 * Date : 
 * GitHub : https://github.com/RemyKaloustian/Island.git
 * 
 * 
 */
package App;


public class Boat 
{
    public Boat()
    {
        
    }//Boat()
    
    public String land(int creekid, int nbofpeople)
    {
        return "{ \"action\": \"land\", \"parameters\": { \"creek\": \""+ creekid +"\", \"people\":"+ nbofpeople +"}}";
    }//land()
        
}//class Boat
