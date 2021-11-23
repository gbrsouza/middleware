package application;
import org.json.JSONObject;
import middleware.annotations.Delete;
import middleware.annotations.Get;
import middleware.annotations.Post;
import middleware.annotations.Put;
import middleware.annotations.RequestMap;
import lombok.NoArgsConstructor;

/**
 *Class calculator, implemented by the client using the annotations
 *implemented by our middleware. Note that the return of all methods
 *is a JSONObject.
 */

@NoArgsConstructor

//RequestMap annotation, the attribute "router" is what sets the class route 
@RequestMap(router = "/calc")
public class Calculator {
		
	//Get method, the attribute "router" is what sets the endpoint route 
	@Get(router = "/add")
    public JSONObject add(JSONObject jsonObject) throws Throwable {
		
		//Get the variables from JSON
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var2");		
		
		//Build the return JSON
		JSONObject result = new JSONObject();
		result.put("result", a+b);
		
        return result;
    }
	
	//Post method, the attribute "router" is what sets the endpoint route 
	@Post(router = "/sub")
    public JSONObject sub(JSONObject jsonObject) throws Throwable {
		//Get the variables from JSON
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var2");
		//Build the return JSON
		JSONObject result = new JSONObject();
		result.put("result", a-b);
		
        return result;
    }
	
	//Put method, the attribute "router" is what sets the endpoint route 
	@Put(router = "/mul")
    public JSONObject mul(JSONObject jsonObject) throws Throwable {
		//Get the variables from JSON
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var2");
		//Build the return JSON
		JSONObject result = new JSONObject();
		result.put("result", a*b);
		
        return result;
    }

	//Delete method, the attribute "router" is what sets the endpoint route 
	@Delete(router = "/div")
    public JSONObject div(JSONObject jsonObject) throws Throwable {
		//Get the variables from JSON
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var2");
		//Build the return JSON
		JSONObject result = new JSONObject();
		result.put("result", a/b);
		
        return result;
    }
}
