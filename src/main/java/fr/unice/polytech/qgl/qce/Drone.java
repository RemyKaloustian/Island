/*
 * 
 * Author : Rémy Kaloustian
 * Date : 7/11/15
 * GitHub : https://github.com/RemyKaloustian/Island.git
 * 
 */


package fr.unice.polytech.qgl.qce;

import org.json.*;


public class Drone 
{
    
    //Directions, no enums because enums are like classes and 4 lines of static > 15 lines of enum
    public final static char North = 'N';
    public final static char South = 'S';
    public final static char West = 'W';
    public final static char East = 'E';
    
    public String currentObjective; //the current objective 
    
    public Long distanceFromSouthBoundary;
    public Long distanceFromNorthBoundary;
    public Long distanceFromWestBoundary;
    public Long distanceFromEastBoundary;
    
    public String actionToDo; //Is used in takeDroneDecision, so that we can separate in subfunctions
   
    
    public char currentHeading; //former name currentlyHeading
    
    public long distanceToDo; //we don't use this anymore
    
    public int turnCounter;  // what for ??
    /**
     * turnDirection
     *it represents the direction we just turned TO 
     */
    public char turnDirection; 
    
    public boolean wevJustturned;
    public boolean hasTurnedLeft;// Is used during findingCorner, to see if we already turend left, dunno if could be replaced by wevJustturned;
    public boolean onTheMove; // Is used to know whether we do a scan or not, we do a scan only after moving 
    
    public  char headingStart;
    
    /**
     * distanceLim
     * fr: écart minimal entre la bordure et le point où le drone a besoin de tourner
     */
    public int distanceLim; 
    
    public Drone()
    {
        //Initializing members
        distanceFromNorthBoundary = null;
        distanceFromSouthBoundary = null;
        distanceFromEastBoundary = null;
        distanceFromWestBoundary = null;      
        
    	distanceLim=1;
        wevJustturned=false;
        hasTurnedLeft = false;
        onTheMove = false;
        
        this.currentObjective = "findingDistances";
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
        return "{ \"action\": \"echo\", \"parameters\": { \"direction\": \""+ direction +"\" } }";
    }//echo()
    
    public String scan()
    {
        return "{ \"action\": \"scan\" }";
    }//scan()
    
    
    /**
     * méthode Update qui met à jour toutes les instances qui changeront au cours
     * de la phase aérienne. Elle dépendra de la méthode (acknowledge) et sera appelée
     * après chaque retour d'information JSON depuis le moteur de Jeu  
     */
    public void update(){
    	//mise à jour des instance de distances.
    	//màj de currentHeading
    }
    
    
    
    public String takeDroneDecision (JSONObject context, JSONObject data)
    {
    	        
        if(this.currentObjective.equals("findingDistances")) //If our objective is finding distances
        {
            this.findingDistancesManagement(data);         
        }//currentObjective == findingDistances
        
        if(this.currentObjective.equals("findingCorner"))
        {
            this.findingCornerManagement();
        }      
        
        if(this.currentObjective.equals("findingCreek"))
        {
            this.findingCreekManagement();
        }
        
        return this.actionToDo;
    }//takeDroneDecision()
    
    
    public void findingDistancesManagement(JSONObject data)
    {
         //If all directions to null => the first echo ever
            if(this.distanceFromNorthBoundary == null && this.distanceFromSouthBoundary == null &&
                this.distanceFromWestBoundary == null && this.distanceFromEastBoundary == null)
            {
                initializeDistances(true, data);
            }
            else initializeDistances(false, data); // to aknowledge the data from the echo performed

            // We do the echo on null distances
            if(this.distanceFromNorthBoundary == null)
                this.actionToDo =  this.echo(Drone.North);
            
            else if(this.distanceFromSouthBoundary == null)
                this.actionToDo = this.echo(Drone.South);
            
            else if(this.distanceFromWestBoundary == null)
                this.actionToDo = this.echo(Drone.West);
            
            else if(this.distanceFromEastBoundary == null)
                this.actionToDo = this.echo(Drone.East);
            
            //If we did all the echo (even if they returned -1, in that case we will also need to find a corner to do an echo)
            if(this.distanceFromNorthBoundary != null && this.distanceFromSouthBoundary != null &&
                    this.distanceFromWestBoundary != null && this.distanceFromEastBoundary != null)
                this.currentObjective = "findingCorner";   
    }//findingDistancesManagement()
    
    public void findingCornerManagement()
    {
        
        //We check if we're not already in a corner
        //If the left boundary is to 0 or 1
        if(this.currentHeading == Drone.North && this.distanceFromWestBoundary < 2)
            this.currentObjective = "findingCreek";

        else if(this.currentHeading == Drone.South && this.distanceFromEastBoundary < 2)
            this.currentObjective = "findingCreek";

        else if(this.currentHeading == Drone.West && this.distanceFromSouthBoundary < 2)
            this.currentObjective = "findingCreek";

        else if(this.currentHeading == Drone.East && this.distanceFromNorthBoundary < 2)
            this.currentObjective = "findingCreek";

        else //We must move to a corner
        {
            if(!hasTurnedLeft) //If we did not already turned, then we turn left
            {
                if(this.currentHeading == Drone.North)
                {
                    this.actionToDo = this.heading(Drone.West); 
                    this.turnUpDistances("left");                
                    this.currentHeading = Drone.West;
                }                  

                else if(this.currentHeading == Drone.South)
                {   
                    this.actionToDo = this.heading(Drone.East);
                    this.turnUpDistances("left");                
                    this.currentHeading = Drone.East;
                                    
                }

                else if(this.currentHeading == Drone.West)
                {    
                    this.actionToDo = this.heading(Drone.South);
                    this.turnUpDistances("left");
                    this.currentHeading = Drone.South;
                }

                else if(this.currentHeading == Drone.East)
                {
                    this.actionToDo = this.heading(Drone.North);
                    this.turnUpDistances("left");
                    this.currentHeading  = Drone.North;
                }

                this.hasTurnedLeft = true;
            }//!hasTurnedLeft

            else if(hasTurnedLeft) //If we already turned left, we fly until the corner
            {
                if(this.currentHeading == Drone.North)
                {
                    if(this.distanceFromNorthBoundary > 1)
                    {   
                        this.actionToDo = this.fly();
                        this.updateDistances();
                    }

                    else if( this.distanceFromNorthBoundary == 1)
                        this.currentObjective = "findingCreek";
                }

                else if(this.currentHeading == Drone.South )
                {
                    if(this.distanceFromSouthBoundary > 1)
                    {    
                        this.actionToDo = this.fly();
                        this.updateDistances();
                    }

                    else if(this.distanceFromSouthBoundary == 1)
                        this.currentObjective = "findingCreek";
                }

                else if(this.currentHeading == Drone.West )
                {
                    if(this.distanceFromWestBoundary > 1)
                    {    
                        this.actionToDo = this.fly();
                        this.updateDistances();
                    }

                    else if(this.distanceFromWestBoundary == 1)
                        this.currentObjective = "findingCreek";
                }

                 else if(this.currentHeading == Drone.East )
                {
                    if(this.distanceFromEastBoundary > 1)
                    {    
                        this.actionToDo = this.fly();
                        this.updateDistances();
                    }

                    else if(this.distanceFromEastBoundary == 1)
                        this.currentObjective = "findingCreek";
                }                   
            }//hasTurnedLeft
        }            

    }//findingCornerManagement()
    
    public void initializeDistances(boolean firstTime, JSONObject data)
    {
        if(firstTime){
            if(this.currentHeading == Drone.North) //if facing North, then South is behind
                this.distanceFromSouthBoundary = new Long(0);
                
            else if(this.currentHeading == Drone.South)//if facing South, then North is behind
                this.distanceFromNorthBoundary = new Long(0);
                
            else if(this.currentHeading == Drone.West)//if facing West, then East is behind
                this.distanceFromEastBoundary = new Long(0);
            
            else if(this.currentHeading == Drone.East)// if facing East, then West is behind
                this.distanceFromWestBoundary = new Long(0);
        }
        else{ 

            if(this.distanceFromNorthBoundary == null){ // Another verification possible would be using a variable to know to which direction we did the echo
                if(data.getJSONObject("extras").getString("found").equals("OUT_OF_RANGE"))
                    this.distanceFromNorthBoundary = new Long(-1);
                else
                    this.distanceFromNorthBoundary = (long) data.getInt("echo_Results");
            }

            else if(this.distanceFromSouthBoundary == null)
            {
                if(data.getJSONObject("extras").getString("found").equals("OUT_OF_RANGE"))
                    this.distanceFromSouthBoundary = new Long(-1);
                else
                    this.distanceFromSouthBoundary = (long) data.getInt("echo_Results");
            
            }
            else if(this.distanceFromWestBoundary == null)
            {
                if(data.getJSONObject("extras").getString("found").equals("OUT_OF_RANGE"))
                    this.distanceFromWestBoundary = new Long(-1);
                else
                    this.distanceFromWestBoundary = (long) data.getInt("echo_Results");
            
            }
            else if(this.distanceFromEastBoundary == null)
            {
                if(data.getJSONObject("extras").getString("found").equals("OUT_OF_RANGE"))
                    this.distanceFromEastBoundary = new Long(-1);
                else
                    this.distanceFromEastBoundary = (long) data.getInt("echo_Results");
            }
        }
    }

    public void updateDistances() 
    { 
    	//Is used after fly
        
        if(this.currentHeading == Drone.North)
        {
            this.distanceFromNorthBoundary--;
            this.distanceFromSouthBoundary++;
        }
        
        else if(this.currentHeading == Drone.South)
        {
            this.distanceFromNorthBoundary++;
            this.distanceFromSouthBoundary--;
        }
        
        else if(this.currentHeading ==  Drone.West)
        {
            this.distanceFromWestBoundary--;
            this.distanceFromEastBoundary++;
        }
        
        else if(this.currentHeading == Drone.East)
        {
            this.distanceFromWestBoundary++;
            this.distanceFromEastBoundary--;            
        }
        
    }//updateDistances()

    public void turnUpDistances(String turningDirection)
    {
    	if(turningDirection.equals("right")){
    		if(this.currentHeading == Drone.North)
    		{ 
	    		this.distanceFromNorthBoundary--;
	            this.distanceFromSouthBoundary++;
	            this.distanceFromWestBoundary++;
	            this.distanceFromEastBoundary--;
    		}

    		else if(this.currentHeading == Drone.South)
    		{ 
				this.distanceFromNorthBoundary++;
                this.distanceFromSouthBoundary--;
                this.distanceFromWestBoundary--;
                this.distanceFromEastBoundary++;
    		}

    		else if(this.currentHeading == Drone.West)
    		{ 
	    		this.distanceFromNorthBoundary--;
                this.distanceFromSouthBoundary++;
                this.distanceFromWestBoundary--;
                this.distanceFromEastBoundary++; 
    		}

    		else if(this.currentHeading == Drone.East)
    		{ 
	    		this.distanceFromNorthBoundary++;
                this.distanceFromSouthBoundary--;
                this.distanceFromWestBoundary++;
                this.distanceFromEastBoundary--;
    		}

    	}

    	else if(turningDirection.equals("left")){
    		if(this.currentHeading == Drone.North)
    		{ 
	    		this.distanceFromNorthBoundary--;
	            this.distanceFromSouthBoundary++;
	            this.distanceFromWestBoundary--;
	            this.distanceFromEastBoundary++;
    		}

    		else if(this.currentHeading == Drone.South)
    		{ 
				this.distanceFromNorthBoundary++;
                this.distanceFromSouthBoundary--;
                this.distanceFromWestBoundary++;
                this.distanceFromEastBoundary--;
    		}

    		else if(this.currentHeading == Drone.West)
    		{ 
	    		this.distanceFromNorthBoundary++;
                this.distanceFromSouthBoundary--;
                this.distanceFromWestBoundary--;
                this.distanceFromEastBoundary++; 
    		}

    		else if(this.currentHeading == Drone.East)
    		{ 
	    		this.distanceFromNorthBoundary--;
                this.distanceFromSouthBoundary++;
                this.distanceFromWestBoundary++;
                this.distanceFromEastBoundary--;
    		}
    	}
    }
    
    
    
    
    public void findingCreekManagement()
    {
        
        //ZINEB, CHARLY, KHADIM, DO WONDERS.
    	
        if(onTheMove) // If we've just moved we do a scan
        {
        	onTheMove = false;
        	this.actionToDo = scan(); 
        }
        else tournerEnRond(); // otherwise we move forward
        
    }//findingCreekManagement()
    
    
    
    /**
     *     #####modification  #TBC 13/11  #####
     */

    /**
     * 
     * tournerenrond : à utiliser quand if(currentObjective==tournerenrond)
     * pour l'instant je sais pas comment on récupère les info. ça dépends d'acknowledge.
     * dans le doute, et en attendant d'avancer sur la conception, j'ai mis data.
     * 
     * @param data
     */    
    public void tournerEnRond(){
    	
    	//vérifier qu'on vient pas de tourner. DONE  
    	//si on vient de tourner incrément DONE
    	//vérifier que la distance limite ...  DONE

    	//void
    	
    	if (wevJustturned && oppositeToheadingStart()) {
    		wevJustturned = false;
    		distanceLim++;
    	}
    	
    	if (distanceLim == distanceForward()){
    		wevJustturned = true;
    		this.turnUpDistances("right");
    		this.actionToDo = this.turnRight();
    		this.currentHeading = this.getNewDirection("right");
    	}
    	else {
    		wevJustturned = false;
    		this.updateDistances();
    		this.actionToDo = this.fly();
    	}

    	onTheMove = true;

    }// tournerEnRond()
    
    public long distanceForward()
    {
    	switch(this.currentHeading)
    	{
	    	case 'N' : return this.distanceFromNorthBoundary ;
	    	case 'S' : return this.distanceFromSouthBoundary ;
	    	case 'E' : return this.distanceFromEastBoundary ;
	    	case 'W' : return this.distanceFromWestBoundary ;
    	    default : return 0;
        }
    }
    
    public boolean oppositeToheadingStart(){
    	switch(this.currentHeading){
    	case 'N' : return (this.headingStart =='S');
    	case 'S' : return (this.headingStart =='N');
    	case 'E' : return (this.headingStart =='W');
    	case 'W' : return (this.headingStart =='E');
    	default : return false;
        }
    }
    
    public String turnLeft(){
    	switch(this.currentHeading){
    	case 'N' : return heading('W');
    	case 'S' : return heading('E');
    	case 'E' : return heading('N');
    	case 'W' : return heading('S');
        default : return "";
        }
    }
    
    public String turnRight(){
    	switch(this.currentHeading){
    	case 'N' : return heading('E');
    	case 'S' : return heading('W');
    	case 'E' : return heading('S');
    	case 'W' : return heading('N');
        default : return "";
        }
    }

    public char getNewDirection(String turningDirection){
    	if(turningDirection.equals("left"))
    	{
    		switch(this.currentHeading){
	    	case 'N' : return Drone.West;
	    	case 'S' : return Drone.East;
	    	case 'E' : return Drone.North;
	    	case 'W' : return Drone.South;
    		}
    	}
    	else if(turningDirection.equals("right")){
    		switch(this.currentHeading){
	    	case 'N' : return Drone.East;
	    	case 'S' : return Drone.West;
	    	case 'E' : return Drone.South;
	    	case 'W' : return Drone.North;
    		}
    	}
        return '\0';
    }
    
    public boolean alreadyTurned(){
    	return this.currentHeading==turnDirection ;
    }
    
    
}//class Drone
