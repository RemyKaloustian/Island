/*
 * 
 * 
 * Author : Remy Kaloustian
 * Date : 7/11/15
 * GitHub : https://github.com/RemyKaloustian/Island.git
 * 
 * 
 */
package fr.unice.polytech.qgl.qce;

import java.util.HashMap;
import java.util.Map;
import org.json.*;



public class Sailors 
{
    int numberOfSailors;
    boolean isLanded;
    
    
    public Sailors()
    {
        isLanded = false;
    }//Explorers()
    
    
    public String takeSailorsDecision(JSONObject context, JSONObject data)
    {
        if(!isLanded) //If not landed, then land
            return this.land(context.getString("creek"), this.numberOfSailors);
        
        else //else stop the game
            return this.stop();
    }//takeSailorsDecision()
    
     public String land(String creekid, int nbofpeople)
     { 
    	 Map<String,Object> parameters = new HashMap<String,Object>();
         parameters.put("creek", creekid);
         parameters.put("people", nbofpeople);

    	 Speaker msg = new Speaker("land",parameters);
    	 this.isLanded = true;
    	 return msg.toString();
    }
     
    public String stop()
    {
    	JSONObject msg= new JSONObject();
    	msg.put("action","stop");
        return msg.toString();
    }//stop()
    
    public String move_To(char direction)
    {
    	Map<String,Object> parameters = new HashMap<String,Object>();
    	parameters.put("direction",direction);
    	
    	Speaker msg = new Speaker("move_to",parameters);
        return msg.toString();
    
    }//move_To()
    
    public String scout(char direction)
    {
       	Map<String,Object> parameters = new HashMap<String,Object>();
    	parameters.put("direction",direction);
    	
    	Speaker msg = new Speaker("scout",parameters);
    	return msg.toString();
    }//scout()
    
    public String glimpse(char direction, int range)
    {
    	Map<String,Object> parameters = new HashMap<String,Object>();
    	parameters.put("direction",direction);
    	parameters.put("range",range);
    	
    	Speaker msg = new Speaker("glimpse",parameters);
    	return msg.toString();
    }//glimpse()
    
    public String explore()
    {
    	return (new Speaker("explore")).toString();
    }//explore()
    
    public String exploit(String ressource)
    {
    	Map<String,Object> parameters = new HashMap<String,Object>();
    	parameters.put("ressource",ressource);
    	
    	Speaker msg = new Speaker("exploit",parameters);
    	return msg.toString();
    }//exploit()
    
    /**
     * Transform takes multiple resources. Still need to be improved.
     * @param ressource , it's a map containing the resources to be transformed
     */
    public String Transform(Map<String,Object> ressource)
    {
    	return (new Speaker("transform",ressource)).toString();
    }//Transform()
    
}//Sailors
