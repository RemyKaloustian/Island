/*
 * 
 * 
 * Author : Remy Kaloustian
 * Date : 11/11/2015
 * GitHub : https://github.com/RemyKaloustian/Island.git
 * 
 * 
 */
package fr.unice.polytech.qgl.qce;

import eu.ace_design.island.bot.IExplorerRaid;

/**
 *The class that will communicate with the game
 * NB : We're trying to do things as short as possible here 
 * @author Rémy Kaloustian
 */
public class  Explorer implements IExplorerRaid
{
    //For the moment, the attributes are package, we will discuss about their visibility.
    
    AIBot bot; //That thing right here makes all the calculations and all the harsh stuff 
    //NB I don't think that context is useful in this class, makes more sense in AIBot
   
    boolean initializing;//Is the result returned form the game the first ever ?
    
    /**
     *Constructor
     */
    public Explorer()
    {        
        
    }//Explorer()
    
    public void buildExplorer() //Initializes attributes
    {
        bot = new AIBot();
        this.initializing = true;
    }//buildExplorer()
    
    /**
     * Initializes the current context with the first piece of information available
     * @param context, the starting information put in the current context
     */
    public void initialize(String context)
    {
        this.buildExplorer();
        bot.update(context,true);
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
            return bot.chooseAction(true);
            
        }
        else
        {
            return bot.chooseAction(this.initializing);
        }
        
    }//takeDecision()
    
    /**
     *Saves the results of the action executed and updates the current context
     * @param results, the results of the action executed
     */
    public void acknowledgeResults(String results)
    {
       bot.update(results,false);
    }//acknowledgeResults()
    
}//class Explorer
