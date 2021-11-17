package core;

import org.json.JSONObject;

import core.annotations.Delete;
import core.annotations.Get;
import core.annotations.Post;
import core.annotations.Put;
import core.annotations.RequestMap;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@RequestMap(router = "/calc")
public class Calculator {
	
	
	@Get(router = "/add")
    public float add(JSONObject jsonObject) throws Throwable {
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var1");
		
        return a+b;
    }

	@Post(router = "/sub")
    public float sub(JSONObject jsonObject) throws Throwable {
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var1");
		
        return a-b;
    }
	
	@Put(router = "/mul")
    public float mul(JSONObject jsonObject) throws Throwable {
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var1");
		
        return a*b;
    }

	@Delete(router = "/div")
    public float div(JSONObject jsonObject) throws Throwable {
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var1");
		
        return a/b;
    }
}
