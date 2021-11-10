package core;

import core.annotations.Delete;
import core.annotations.Get;
import core.annotations.Post;
import core.annotations.Put;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Calculator {
	
	
	@Get
    public float add(float a, float b) throws Throwable {
        return a+b;
    }

	@Post
    public float sub(float a, float b) throws Throwable {
        return a-b;
    }
	
	@Put
    public float mul(float a, float b) throws Throwable {
        return a*b;
    }

	@Delete
    public float div(float a, float b) throws Throwable {
        return a/b;
    }
}
