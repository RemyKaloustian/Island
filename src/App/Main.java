/*
*
*Author : Rémy Kaloustian
*Date : 7/11/15
*GitHub: https://github.com/RemyKaloustian/Island.git
*
*/

package App;

import org.json.simple.parser.ParseException;

/**
 *
 * @author Rémy Kaloustian
 */
public class Main 
{
    
    public static void main (String args[]) throws ParseException
    {
        System.out.println("Initializing ");
        
        JSONInterpreter inter =  new JSONInterpreter();
        inter.TestInterpreter();
    }
    
}
