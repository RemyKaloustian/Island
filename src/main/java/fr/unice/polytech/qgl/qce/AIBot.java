/*
 * 
 * 
 * Author : Rémy Kaloustian
 * Date : 11/11/15
 * GitHub : https://github.com/RemyKaloustian/Island.git
 * 
 * 
 */

package fr.unice.polytech.qgl.qce;

import org.json.*;

/**
 *
 * @author Rémy Kaloustian
 */
public class AIBot 
{
    int activityPoints; //Points at the beginning
    int activityPointsLeft; //Current points left
    boolean isCreekFound; //Is the creek to land to found ?
    String creekId; //we need that to pass it in parameter of land
    int higherCost;
    
    Boat boat; //Dah Boat
    Drone drone; //Dah Drone
    Sailors sailors; //Dem Sailors
    
    JSONObject context;
    JSONObject data;
    
    /**
     *Constructor
     */
    public AIBot()
    {
        drone = new Drone();
        sailors = new Sailors();
        
        context = new JSONObject();
        data = new JSONObject();
        
    }//AIBot()
    
    /**
     *Updates the context, which is the result of the last exceuted action
     * @param results, the results of the executed action
     * @return the updated context in a JSON format
     */
    public void update(String results, boolean initializing)
    {
        if(initializing)
            this.updateData(results);
        else if(!initializing)
        {
            this.updateData(results); 
            //this.updateContext(results); USEFUL ?????
        }
           
       
    }//updateContext()
    
    public void updateData(String results) //Updates key intel 
    {
        JSONObject newdata = new JSONObject(results);
        
        if(!newdata.has("cost")) //If there is no cost, this is the first result ever
        {
            //Setting the attributes
            this.sailors.numberOfSailors = newdata.getInt("men");
            this.activityPoints = newdata.getInt("budget");
            this.drone.currentHeading = newdata.get("heading").toString().charAt(0);
            this.higherCost = 0;
            
            //Saving in a JSONObject
            this.data.put("men", this.sailors.numberOfSailors);
            this.data.put("activityPoints", this.activityPoints);
            
        }
        
        else if(newdata.has("cost")) //If there is a cost, we performed an action
        {
            int activityPointsSpent = newdata.getInt("cost");
            
            if (activityPointsSpent > this.higherCost) this.higherCost = activityPointsSpent;
            
            this.activityPointsLeft -= activityPointsSpent; //Reducing the activity points
            
            this.context.put("activityPointsLeft", this.activityPointsLeft);//Saving in JSON
            
            
            if(newdata.has("extras")) //These lines must be confirmed
            {
                if(newdata.getJSONObject("extras").has("found")){
                    this.data.put("echo_Results", newdata.getJSONObject("extras").getInt("range"));
                }
                if(newdata.getJSONObject("extras").has("creeks") && newdata.getJSONObject("extras").getJSONArray("creeks").get(0) != null)//if there is a creek
                {                    
                    this.creekId = newdata.getJSONObject("extras").getJSONArray("creeks").getString(0); //saving the id of the creek
                    isCreekFound = true;
                    this.context.put("creek", this.creekId); //Saving in JSON
                }
            }
        }
    }//updateData()
    
    /**
     *Determines the action to execute
     * @param context, the result of the last executed action
     * @param initializing, if this is the first call to chooseAction()
     * @return
     */
    public String chooseAction(boolean initializing)
    {
        if(initializing)
        {
           isCreekFound = false;
           return drone.takeDroneDecision(this.context, data);
           
        }
        
        else if(this.context.getInt("activityPointsLeft") <= higherCost)
        { /* par mesure de securité si le nombre de points qu'il me reste est inférieur ou egal au cout
            de l'action la plus coûteuse que j'ai réalisé jusqu'a présent je rentre */
            return this.stop();
        }

        else
        {
            if(!isCreekFound)
                return drone.takeDroneDecision(this.context, data);
            
            else if(isCreekFound)
            {
                String sailorsDecision =  sailors.takeSailorsDecision(this.context,data); //Stores the result of takeSailorsDecision
                
                if(sailorsDecision.equals("stop") || (this.context.getInt("activityPointsLeft") <= 10*higherCost))
                { /* If the sailors stop, or we have less action Points than 10 times the cost of the most expensive action performed so far 
                    we stop the game */
                    return this.stop();
                }
                else  // we do something else
                    return sailorsDecision;
            }
        }
        
        return this.stop();
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
