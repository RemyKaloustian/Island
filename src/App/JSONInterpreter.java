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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONInterpreter 
{
    public JSONInterpreter()
    {
        
    }//JSONInterpreter()
    
    public JSONObject Encode(String s)throws ParseException
    {
        JSONParser parser  = new JSONParser();
        Object obj = parser.parse(s);
        JSONObject jsonOb = (JSONObject)obj;
        return jsonOb;
    }//Encode()
    
   
    //There is no decode because we need to seee how we will exploit the data returning from the game
    
    public void TestInterpreter() throws ParseException
    {
        String s = "{\"action\":\"glimpse\",\"parameters\":[\"S\",\"TAR\"]}";
        System.out.println("L'encodage en JSON de " + s + " donne : \n"
                + "\t " + Encode(s).toJSONString());        
                
    }//TestInterpreter()
    
    
}//class JSONInterpreter
