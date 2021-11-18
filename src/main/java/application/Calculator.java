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
	
	private float result;
	
	
	@Get(router = "/add")
    public Calculator add(JSONObject jsonObject) throws Throwable {
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var2");
		
		Calculator calc = new Calculator();
		
		calc.setResult(a+b);
		
        return calc;
    }

	@Post(router = "/sub")
    public float sub(JSONObject jsonObject) throws Throwable {
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var2");
		
        return a-b;
    }
	
	@Put(router = "/mul")
    public float mul(JSONObject jsonObject) throws Throwable {
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var2");
		
        return a*b;
    }

	@Delete(router = "/div")
    public float div(JSONObject jsonObject) throws Throwable {
		float a = jsonObject.getFloat("var1");
		float b = jsonObject.getFloat("var2");
		
        return a/b;
    }

	public float getResult() {
		return result;
	}

	public void setResult(float result) {
		this.result = result;
	}
}
