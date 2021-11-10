package test.java;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import core.Calculator;
import core.Invoker;
import core.annotations.AddMethods;
import util.message.Message;

class Testhashmap {

	 @Test
	    public void testHash() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
	        Calculator calc = new Calculator();
	        AddMethods serializer = new AddMethods();
	        String jsonString = serializer.separateMethods(calc);
	        
	        Invoker inv = new Invoker();
	        ArrayList<Object> params = new ArrayList<>();
	        params.add(3);
	        params.add(3);
	        Message msg = new Message(true, 0, "core.Calculator-get-add", params);
	        inv.invokeRemoteObject(msg);
	        
	        msg = new Message(true, 0, "core.Calculator-post-sub", params);
	        inv.invokeRemoteObject(msg);
	        
	        msg = new Message(true, 0, "core.Calculator-put-mul", params);
	        inv.invokeRemoteObject(msg);
	        
	        msg = new Message(true, 0, "core.Calculator-delete-div", params);
	        inv.invokeRemoteObject(msg);
	        
	        assertEquals("sucess add methods", jsonString);
	    }

}
