package application;

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
    public float add(float a, float b) throws Throwable {
        return a+b;
    }

	@Post(router = "/sub")
    public float sub(float a, float b) throws Throwable {
        return a-b;
    }
	
	@Put(router = "/mul")
    public float mul(float a, float b) throws Throwable {
        return a*b;
    }

	@Delete(router = "/div")
    public float div(float a, float b) throws Throwable {
        return a/b;
    }
}
