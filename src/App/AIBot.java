/*
 * 
 * 
 * Author : Rémy Kaloustian
 * Date : 11/11/15
 * GitHub : https://github.com/RemyKaloustian/Island.git
 * 
 * 
 */

package App;

/**
 *
 * @author Rémy Kaloustian
 */
public class AIBot 
{
    int activityPoints;
    int restingactivityPoints;
    
    Boat boat;
    Drone drone;
    Sailors sailors;
    
    /**
     *
     */
    public AIBot()
    {
        
    }//AIBot()
    
    /**
     *Updates the context, which is the result of the last exceuted action
     * @param results, the results of the executed action
     * @return the updated context in a JSON format
     */
    public JSONObject updateContext(String results)
    {
        return new JSONObject(results);
    }//updateContext()
    
    /**
     *Determines the action to execute
     * @param context, the result of the last executed action
     * @param initializing, if this is the first call to chooseAction()
     * @return
     */
    public String chooseAction(JSONObject context, boolean initializing)
    {
        if(initializing)
        {
           
            //Take a decision
        }
        
        else
        {
           //Take a decision
        }
        
    }//chooseAction()
    
    /**
     *Stops the game
     * @return the stopping statement
     */
    public String stop()
    {
        return "{ \"action\": \"stop\" }";
    }//stop()
    
}//class AIBot
