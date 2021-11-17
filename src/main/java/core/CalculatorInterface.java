package core;

import org.json.JSONObject;

public interface CalculatorInterface {
    float add(JSONObject jsonObject) throws Throwable;
    float sub(JSONObject jsonObject) throws Throwable;
    float mul(JSONObject jsonObject) throws Throwable;
    float div(JSONObject jsonObject) throws Throwable;
}
