/**
 * This class avoids us to create a new JASONOBject each time we need to send a message.
 * It also allows us to use any Markup language. All we need is to adapt this class.
 */

package fr.unice.polytech.qgl.qce;


import java.util.Map;

import org.json.JSONObject;

/**
 * @author Zineb
 *
 */
public class Speaker {

	private String action;
	private Map<String,Object> parameters;
	private JSONObject msg;
	
	public Speaker(String action, Map<String, Object> parameters) {
		this.action = action;
		this.parameters = parameters;
		
		this.msg.put("action",action);
		this.msg.put("parameters",parameters);
	}

	/**
	 * @param action
	 */
	public Speaker(String action) {
		this.action = action;
		this.msg.put("action",action);
	}
	
	@Override
	public String toString() {
		return this.msg.toString();
	}	
}
