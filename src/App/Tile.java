/*
 * 
 * 
 * Author : Remy Kaloustian
 * Date : 7/11/15
 * GitHub : https://github.com/RemyKaloustian/Island.git
 * 
 * 
 */





/**
 * Tiles are the atomic elements in the map.
 * A tile contains a collection of ressources unordered in a Map
 * x and y are the position of the tile in the map.
 * z is the altitude.
 */

import java.util.HashMap;
import java.util.Map;


public class Tile 
{
    private Map<String,String> resources;
    private int x, y;
    private float z; // initialised once we have the info about altitude
    
    public Tile()
    {
        resources= new HashMap<String,String>();
    }//Tile()
    
}//class Tile()
