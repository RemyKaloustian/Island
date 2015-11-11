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


public class Sailors 
{
    public Sailors()
    {
        
    }//Explorers()
    
    public String move_To(char direction)
    {
        return "{ \"action\": \"move_to\", \"parameters\": { \"" + direction +  "\": \"N\" } }";
    }//move_To()
    
    public String scout(char direction)
    {
        return "{ \"action\": \"scout\", \"parameters\": { \"" + direction + "\": \"N\" } }";
    }//scout()
    
    public String glimpse(char direction, int range)
    {
        return "{ \"action\": \"glimpse\", \"parameters\": { \"" + direction + "\": \"N\", \"" + range + "\": 2 } }";
    }//glimpse()
    
    public String explore()
    {
        return "{ \"action\": \"explore\" }";
    }//explore()
    
    public String exploit(String resource)
    {
        return "{ \"action\": \"exploit\", \"parameters\": { \"resource\": \"" + resource + "\" }}";
    }//exploit()
    
    public String Transform()
    {
        return "";
    }//Transform()
    
}//Explorers
