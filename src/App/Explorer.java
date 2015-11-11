/*
 * 
 * 
 * Author : Remy Kaloustian
 * Date : 11/11/2015
 * GitHub : https://github.com/RemyKaloustian/Island.git
 * 
 * 
 */
package App;

/**
 *The class that will communicate with the game
 * NB : We're trying to do things as short as possible here 
 * @author Rémy Kaloustian
 */
public class Explorer implements IExplorerRaid
{
    //For the moment, the attributes are package, we will discuss about their visibility.
    
    AIBot bot; //That thing right here makes all the calculations and all the harsh stuff 
    JSONObject context; //The results of the last action executed
    boolean initializing;
    
    /**
     *Constructor
     */
    public Explorer()
    {
        bot = new AIBot();
        initializing = true;
        
    }//Explorer()
    
    /**
     * Initializes the current context with the first piece of information available
     * @param context, the starting information put in the current context
     */
    public void initialize(String context)
    {
        this.context = bot.updateContext(context);
    }//initilaize()
    
    /**
     *Tells to the game which action to execute 
     * @return the action to do in a String format
     */
    public String takeDecision()
    {
        if(initializing)
        {
            this.initializing = false;
            return bot.chooseAction(this.context, true);
            
        }
        else
        {
            return bot.chooseAction(this.context, this.initializing);
        }
        
    }//takeDecision()
    
    /**
     *Saves the results of the action executed and updates the current context
     * @param results, the results of the action executed
     */
    public void acknowledgeResults(String results)
    {
        this.context = bot.updateContext(results);
    }//acknowledgeResults()
    
}//class Explorer
