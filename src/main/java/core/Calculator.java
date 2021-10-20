package core;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Calculator {

    public float add(float a, float b) throws Throwable {
        return a+b;
    }

    public float sub(float a, float b) throws Throwable {
        return a-b;
    }

    public float mul(float a, float b) throws Throwable {
        return a*b;
    }

    public float div(float a, float b) throws Throwable {
        return a/b;
    }
}
