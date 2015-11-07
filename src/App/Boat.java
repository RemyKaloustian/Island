/*
 * 
 * 
 * Author : Remy Kaloustian
 * Date : 7/11/15
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
