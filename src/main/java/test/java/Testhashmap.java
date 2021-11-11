package test.java;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import core.Calculator;
import core.Invoker;
import core.annotations.Autumn;
import util.message.Message;

class Testhashmap {

	@Test
    public void testHash() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
        Calculator calc = new Calculator();
        Autumn server = new Autumn();
        String jsonString = server.addMethods(calc);
        
        Invoker inv = new Invoker();
        ArrayList<Object> params = new ArrayList<>();
        params.add(3);
        params.add(3);
        Message msg = new Message(true, 0, "get-/calc/add", params);
        inv.invokeRemoteObject(msg);
        
        msg = new Message(true, 0, "post-/calc/sub", params);
        inv.invokeRemoteObject(msg);
        
        msg = new Message(true, 0, "put-/calc/mul", params);
        inv.invokeRemoteObject(msg);
        
        msg = new Message(true, 0, "delete-/calc/div", params);
        inv.invokeRemoteObject(msg);
        
        server.start(15205);
        
        assertEquals("sucess add methods", jsonString);
    }

}
