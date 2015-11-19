

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.qgl.qce.Drone;

import static org.junit.Assert.*;

public class DroneTest 
{

	
	Drone drone;

	@Before
	public void defineContext() {
		drone = new Drone();
	}


	//Testing the initialization of tha distances
	//--------------------------------------------------------------------------------

	@Test
	public void testfindingDistancesManagement1() 
	{
		drone.currentHeading = Drone.North;
		drone.findingDistancesManagement();
		assertEquals(drone.distanceFromSouthBoundary, new Long(0));
	}//1

	@Test
	public void testfindingDistancesManagement2() 
	{
		drone.currentHeading = Drone.South;
		drone.findingDistancesManagement();
		assertEquals(drone.distanceFromNorthBoundary, new Long(0));
	}//2


	@Test
	public void testfindingDistancesManagement3() 
	{
		drone.currentHeading = Drone.West;
		drone.findingDistancesManagement();
		assertEquals(drone.distanceFromEastBoundary, new Long(0));
	}//3

	@Test
	public void testfindingDistancesManagement4() 
	{
		drone.currentHeading = Drone.East;
		drone.findingDistancesManagement();
		assertEquals(drone.distanceFromWestBoundary, new Long(0));
	}//4

	//Testing the choice of echo
	//------------------------------------------------------------------------------

	@Test
	public void testfindingDistancesManagement5() 
	{
		drone.currentHeading = Drone.North;
		drone.findingDistancesManagement();
		assertEquals(drone.actionToDo, "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"N\" } }");
	}//5


	@Test
	public void testfindingDistancesManagement6() 
	{
		drone.currentHeading = Drone.North;		
		drone.distanceFromEastBoundary = new Long(0);
		drone.distanceFromNorthBoundary = new Long(0);
		drone.distanceFromSouthBoundary = new Long(0);
		drone.findingDistancesManagement();
		assertEquals(drone.actionToDo, "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"W\" } }");
	}//6

	@Test
	public void testfindingDistancesManagement7() 
	{
		drone.currentHeading = Drone.North;
		drone.distanceFromWestBoundary = new Long(0);
		drone.distanceFromNorthBoundary = new Long(0);
		drone.distanceFromSouthBoundary = new Long(0);
		drone.findingDistancesManagement();
		assertEquals(drone.actionToDo, "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"E\" } }");
	}//7

	@Test
	public void testfindingDistancesManagement8() 
	{
		drone.currentHeading = Drone.West;
		drone.distanceFromNorthBoundary = new Long(0);
		drone.distanceFromEastBoundary = new Long(0);
		drone.distanceFromWestBoundary = new Long(0);
		drone.findingDistancesManagement();
		assertEquals(drone.actionToDo, "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"S\" } }");
	}//8

	@Test
	public void testfindingDistancesManagement9() 
	{
		drone.currentHeading = Drone.West;
		drone.distanceFromNorthBoundary = new Long(0);
		drone.distanceFromEastBoundary = new Long(0);
		drone.distanceFromWestBoundary = new Long(0);
		drone.distanceFromSouthBoundary =  new Long(0);
		drone.findingDistancesManagement();
		assertEquals(drone.currentObjective, "findingCorner");
	}//9


	//Testing the changes of objective
	//--------------------------------------------------------------------------------


	@Test
	public void testfindingCornerManagement1()
	{
		drone.currentHeading = Drone.North;
		drone.distanceFromWestBoundary = new Long(1);
		drone.findingCornerManagement();
		assertEquals(drone.currentObjective, "findingCreek");
	}//10

	@Test
	public void testfindingCornerManagement2()
	{
		drone.currentHeading = Drone.South;
		drone.distanceFromEastBoundary = new Long(1);
		drone.findingCornerManagement();
		assertEquals(drone.currentObjective, "findingCreek");
	}//11

	@Test
	public void testfindingCornerManagement3()
	{
		drone.currentHeading = Drone.West;
		drone.distanceFromSouthBoundary = new Long(1);
		drone.findingCornerManagement();
		assertEquals(drone.currentObjective, "findingCreek");
	}//12

	@Test
	public void testfindingCornerManagement4()
	{
		drone.currentHeading = Drone.East;
		drone.distanceFromNorthBoundary = new Long(1);
		drone.findingCornerManagement();
		assertEquals(drone.currentObjective, "findingCreek");
	}//13


	//Testing the changes of heading
	//-------------------------------------------------------------------------------

	@Test
	public void testfindingCornerManagement5()
	{
		drone.currentHeading = Drone.North;
		drone.distanceFromEastBoundary = new Long(12);
		drone.distanceFromSouthBoundary = new Long(12);
		drone.distanceFromNorthBoundary = new Long(12);
		drone.distanceFromWestBoundary  = new Long(12);
		drone.findingCornerManagement();
		assertEquals(drone.actionToDo, "{ \"action\": \"heading\", \"parameters\": { \"direction\": \"W\" } }");
	}//14

	@Test
	public void testfindingCornerManagement6()
	{
		drone.currentHeading = Drone.South;
		drone.distanceFromEastBoundary = new Long(12);
		drone.distanceFromSouthBoundary = new Long(12);
		drone.distanceFromNorthBoundary = new Long(12);
		drone.distanceFromWestBoundary  = new Long(12);
		drone.findingCornerManagement();
		assertEquals(drone.actionToDo, "{ \"action\": \"heading\", \"parameters\": { \"direction\": \"E\" } }");
	}//15

	@Test
	public void testfindingCornerManagement7()
	{
		drone.currentHeading = Drone.West;
		drone.distanceFromEastBoundary = new Long(12);
		drone.distanceFromSouthBoundary = new Long(12);
		drone.distanceFromNorthBoundary = new Long(12);
		drone.distanceFromWestBoundary  = new Long(12);
		drone.findingCornerManagement();
		assertEquals(drone.actionToDo, "{ \"action\": \"heading\", \"parameters\": { \"direction\": \"S\" } }");
	}//16


	@Test
	public void testfindingCornerManagement8()
	{
		drone.currentHeading = Drone.East;
		drone.distanceFromEastBoundary = new Long(12);
		drone.distanceFromSouthBoundary = new Long(12);
		drone.distanceFromNorthBoundary = new Long(12);
		drone.distanceFromWestBoundary  = new Long(12);
		drone.findingCornerManagement();
		assertEquals(drone.actionToDo, "{ \"action\": \"heading\", \"parameters\": { \"direction\": \"N\" } }");
	}//17


	@Test
	public void testfindingCornerManagement9()
	{
		drone.currentHeading = Drone.East;
		drone.distanceFromEastBoundary = new Long(12);
		drone.distanceFromSouthBoundary = new Long(12);
		drone.distanceFromNorthBoundary = new Long(12);
		drone.distanceFromWestBoundary  = new Long(12);
		drone.findingCornerManagement();
		assertEquals(drone.actionToDo, "{ \"action\": \"heading\", \"parameters\": { \"direction\": \"N\" } }");
	}//18

	//Testing the changement of hasTurnedLeft
	//-----------------------------------------------------------------------

	@Test
	public void testfindingCornerManagement10()
	{
		drone.currentHeading = Drone.East;
		drone.distanceFromEastBoundary = new Long(12);
		drone.distanceFromSouthBoundary = new Long(12);
		drone.distanceFromNorthBoundary = new Long(12);
		drone.distanceFromWestBoundary  = new Long(12);
		drone.findingCornerManagement();
		assertEquals(drone.hasTurnedLeft, true);
	}//19

	//Testing the choice of fly()
	//----------------------------------------------------------------------------

	@Test
	public void testfindingCornerManagement11()
	{
		drone.currentHeading = Drone.North;
		drone.distanceFromEastBoundary = new Long(12);
		drone.distanceFromSouthBoundary = new Long(12);
		drone.distanceFromNorthBoundary = new Long(12);
		drone.distanceFromWestBoundary  = new Long(12);
		drone.hasTurnedLeft = true;
		drone.findingCornerManagement();
		assertEquals(drone.actionToDo, "{\"action\" : \"fly\"}");
	}//20


	@Test
	public void testfindingCornerManagement12()
	{
		drone.currentHeading = Drone.South;
		drone.distanceFromEastBoundary = new Long(12);
		drone.distanceFromSouthBoundary = new Long(12);
		drone.distanceFromNorthBoundary = new Long(12);
		drone.distanceFromWestBoundary  = new Long(12);
		drone.hasTurnedLeft = true;
		drone.findingCornerManagement();
		assertEquals(drone.actionToDo, "{\"action\" : \"fly\"}");
	}//21

	@Test
	public void testfindingCornerManagement13()
	{
		drone.currentHeading = Drone.East;
		drone.distanceFromEastBoundary = new Long(12);
		drone.distanceFromSouthBoundary = new Long(12);
		drone.distanceFromNorthBoundary = new Long(12);
		drone.distanceFromWestBoundary  = new Long(12);
		drone.hasTurnedLeft = true;
		drone.findingCornerManagement();
		assertEquals(drone.actionToDo, "{\"action\" : \"fly\"}");
	}//22

	@Test
	public void testfindingCornerManagement14()
	{
		drone.currentHeading = Drone.West;
		drone.distanceFromEastBoundary = new Long(12);
		drone.distanceFromSouthBoundary = new Long(12);
		drone.distanceFromNorthBoundary = new Long(12);
		drone.distanceFromWestBoundary  = new Long(12);
		drone.hasTurnedLeft = true;
		drone.findingCornerManagement();
		assertEquals(drone.actionToDo, "{\"action\" : \"fly\"}");
	}//23


	//Testing the currentObjective
	//------------------------------------------------------------------------------

	@Test
	public void testfindingCornerManagement15()
	{
		drone.currentHeading = Drone.North;
		drone.distanceFromEastBoundary = new Long(12);
		drone.distanceFromSouthBoundary = new Long(12);
		drone.distanceFromNorthBoundary = new Long(1);
		drone.distanceFromWestBoundary  = new Long(12);
		drone.hasTurnedLeft = true;
		drone.findingCornerManagement();
		assertEquals(drone.currentObjective, "findingCreek");
	}//24


	@Test
	public void testfindingCornerManagement16()
	{
		drone.currentHeading = Drone.South;
		drone.distanceFromEastBoundary = new Long(12);
		drone.distanceFromSouthBoundary = new Long(1);
		drone.distanceFromNorthBoundary = new Long(12);
		drone.distanceFromWestBoundary  = new Long(12);
		drone.hasTurnedLeft = true;
		drone.findingCornerManagement();
		assertEquals(drone.currentObjective, "findingCreek");
	}//25

	@Test
	public void testfindingCornerManagement17()
	{
		drone.currentHeading = Drone.East;
		drone.distanceFromEastBoundary = new Long(1);
		drone.distanceFromSouthBoundary = new Long(12);
		drone.distanceFromNorthBoundary = new Long(12);
		drone.distanceFromWestBoundary  = new Long(12);
		drone.hasTurnedLeft = true;
		drone.findingCornerManagement();
		assertEquals(drone.currentObjective, "findingCreek");
	}//26

	@Test
	public void testfindingCornerManagement18()
	{
		drone.currentHeading = Drone.West;
		drone.distanceFromEastBoundary = new Long(12);
		drone.distanceFromSouthBoundary = new Long(12);
		drone.distanceFromNorthBoundary = new Long(12);
		drone.distanceFromWestBoundary  = new Long(1);
		drone.hasTurnedLeft = true;
		drone.findingCornerManagement();
		assertEquals(drone.currentObjective, "findingCreek");
	}//27


	//Testing updateDistances
	//------------------------------------------------------------------------------

	@Test
	public void testupdateDistances()
	{
		drone.currentHeading = Drone.North;
		drone.distanceFromEastBoundary = new Long(2);
		drone.distanceFromSouthBoundary = new Long(2);
		drone.distanceFromNorthBoundary = new Long(2);
		drone.distanceFromWestBoundary  = new Long(2);
		
		drone.updateDistances();
		assertEquals(drone.distanceFromNorthBoundary,new Long(1) );
	}//28

	@Test
	public void testupdateDistances2()
	{
		drone.currentHeading = Drone.North;
		drone.distanceFromEastBoundary = new Long(2);
		drone.distanceFromSouthBoundary = new Long(2);
		drone.distanceFromNorthBoundary = new Long(2);
		drone.distanceFromWestBoundary  = new Long(2);
		
		drone.updateDistances();
		assertEquals(drone.distanceFromSouthBoundary,new Long(3) );
	}//29

	@Test
	public void testupdateDistances3()
	{
		drone.currentHeading = Drone.South;
		drone.distanceFromEastBoundary = new Long(2);
		drone.distanceFromSouthBoundary = new Long(2);
		drone.distanceFromNorthBoundary = new Long(2);
		drone.distanceFromWestBoundary  = new Long(2);
		
		drone.updateDistances();
		assertEquals(drone.distanceFromNorthBoundary,new Long(3) );
	}//30

	@Test
	public void testupdateDistances4()
	{
		drone.currentHeading = Drone.South;
		drone.distanceFromEastBoundary = new Long(2);
		drone.distanceFromSouthBoundary = new Long(2);
		drone.distanceFromNorthBoundary = new Long(2);
		drone.distanceFromWestBoundary  = new Long(2);
		
		drone.updateDistances();
		assertEquals(drone.distanceFromSouthBoundary,new Long(1) );
	}//31

	@Test
	public void testupdateDistances5()
	{
		drone.currentHeading = Drone.West;
		drone.distanceFromEastBoundary = new Long(2);
		drone.distanceFromSouthBoundary = new Long(2);
		drone.distanceFromNorthBoundary = new Long(2);
		drone.distanceFromWestBoundary  = new Long(2);
		
		drone.updateDistances();
		assertEquals(drone.distanceFromWestBoundary,new Long(1) );
	}//32

	@Test
	public void testupdateDistances6()
	{
		drone.currentHeading = Drone.West;
		drone.distanceFromEastBoundary = new Long(2);
		drone.distanceFromSouthBoundary = new Long(2);
		drone.distanceFromNorthBoundary = new Long(2);
		drone.distanceFromWestBoundary  = new Long(2);
		
		drone.updateDistances();
		assertEquals(drone.distanceFromEastBoundary,new Long(3) );
	}//33

	@Test
	public void testupdateDistances7()
	{
		drone.currentHeading = Drone.East;
		drone.distanceFromEastBoundary = new Long(2);
		drone.distanceFromSouthBoundary = new Long(2);
		drone.distanceFromNorthBoundary = new Long(2);
		drone.distanceFromWestBoundary  = new Long(2);
		
		drone.updateDistances();
		assertEquals(drone.distanceFromEastBoundary,new Long(1) );
	}//34

	@Test
	public void testupdateDistances8()
	{
		drone.currentHeading = Drone.East;
		drone.distanceFromEastBoundary = new Long(2);
		drone.distanceFromSouthBoundary = new Long(2);
		drone.distanceFromNorthBoundary = new Long(2);
		drone.distanceFromWestBoundary  = new Long(2);
		
		drone.updateDistances();
		assertEquals(drone.distanceFromWestBoundary,new Long(3) );
	}//35


}//class DroneTest
