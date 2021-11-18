package application;
import org.json.JSONObject;
import middleware.annotations.Delete;
import middleware.annotations.Get;
import middleware.annotations.Post;
import middleware.annotations.Put;
import middleware.annotations.RequestMap;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@RequestMap(router = "/calc")
public class Calculator {
		
	
	@Get(router = "/add")
    public JSONObject add(JSONObject jsonObject) throws Throwable {
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var2");		
		
		JSONObject result = new JSONObject();
		result.put("result", a+b);
		
        return result;
    }

	@Post(router = "/sub")
    public JSONObject sub(JSONObject jsonObject) throws Throwable {
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var2");
		
		JSONObject result = new JSONObject();
		result.put("result", a-b);
		
        return result;
    }
	
	@Put(router = "/mul")
    public JSONObject mul(JSONObject jsonObject) throws Throwable {
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var2");
		
		JSONObject result = new JSONObject();
		result.put("result", a*b);
		
        return result;    }

	@Delete(router = "/div")
    public JSONObject div(JSONObject jsonObject) throws Throwable {
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var2");
		
		JSONObject result = new JSONObject();
		result.put("result", a/b);
		
        return result;    }
}
