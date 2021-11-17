package middleware;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
import com.google.gson.Gson;

import middleware.communication.message.ResponseMessage;

public class RemoteObject {
	
	private static ConcurrentHashMap<Object, Method> methodsGet = new ConcurrentHashMap<>();
	
	private static ConcurrentHashMap<Object, Method> methodsPost = new ConcurrentHashMap<>();
	
	private static ConcurrentHashMap<Object, Method> methodsPut = new ConcurrentHashMap<>();
	
	private static ConcurrentHashMap<Object, Method> methodsDelete = new ConcurrentHashMap<>();
	
		
	public static void addMethodPost(Object key, Method obj) {
		methodsPost.put(key, obj);
		System.out.println(methodsPost.keySet());

	}
	
	public static void addMethodGet(Object key, Method obj) {
		methodsGet.put(key, obj);
		System.out.println(methodsGet.keySet());
	}

	public static void addMethodPut(Object key, Method obj) {
		methodsPut.put(key, obj);
		System.out.println(methodsPut.keySet());

	}
	
	public static void addMethodDelete(Object key, Method obj) {
		methodsDelete.put(key, obj);
		System.out.println(methodsDelete.keySet());
	}
	
	public static ResponseMessage findMethod(String method, JSONObject jsonObject) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(method.toLowerCase().contains("get")) {
			
			if(methodsGet.containsKey(method)){
				Method runMethod = methodsGet.get(method);
			    Class<?> clazz = runMethod.getDeclaringClass();
			    Object instance = clazz.getDeclaredConstructor().newInstance();
				try {
					Object obj = runMethod.invoke(instance, jsonObject);
					
					String jsonObj = new Gson().toJson(obj);
					JSONObject response = new JSONObject(jsonObj);
					
					ResponseMessage message = new ResponseMessage("200", "OK", response.toString());
					
					return message;
				} catch (Exception e) {
					JSONObject response = new JSONObject();
					response.append("Error: ", "An error occurred while processing the method.");
					return new ResponseMessage("500", "Internal Server Error", response.toString());
				} 
				
			}else {
				JSONObject response = new JSONObject();
				response.append("Error: ", "Method not found");
				return new ResponseMessage("404", "Not Found", response.toString());			}
			 
		}else if(method.toLowerCase().contains("post") ) {
			if(methodsPost.containsKey(method)){
				Method runMethod = methodsPost.get(method);
			    Class<?> clazz = runMethod.getDeclaringClass();
			    Object instance = clazz.getDeclaredConstructor().newInstance();
			    try {
					Object obj = runMethod.invoke(instance, jsonObject);
					
					String jsonObj = new Gson().toJson(obj);
					JSONObject response = new JSONObject(jsonObj);
					
					ResponseMessage message = new ResponseMessage("200", "OK", response.toString());
					
					return message;
				} catch (Exception e) {
					JSONObject response = new JSONObject();
					response.append("Error: ", "An error occurred while processing the method.");
					return new ResponseMessage("500", "Internal Server Error", response.toString());
				} 
				
			}else {
				JSONObject response = new JSONObject();
				response.append("Error: ", "Method not found");
				return new ResponseMessage("404", "Not Found", response.toString());
			}
		}else if(method.toLowerCase().contains("put") ) {
			if(methodsPut.containsKey(method)){
				Method runMethod = methodsPut.get(method);
			    Class<?> clazz = runMethod.getDeclaringClass();
			    Object instance = clazz.getDeclaredConstructor().newInstance();
			    try {
					Object obj = runMethod.invoke(instance, jsonObject);
					
					String jsonObj = new Gson().toJson(obj);
					JSONObject response = new JSONObject(jsonObj);
					
					ResponseMessage message = new ResponseMessage("200", "OK", response.toString());
					
					return message;
				} catch (Exception e) {
					JSONObject response = new JSONObject();
					response.append("Error: ", "An error occurred while processing the method.");
					return new ResponseMessage("500", "Internal Server Error", response.toString());
				} 
				
			}else {
				JSONObject response = new JSONObject();
				response.append("Error: ", "Method not found");
				return new ResponseMessage("404", "Not Found", response.toString());
			}
		}else if(method.toLowerCase().contains("delete") ) {
			if(methodsDelete.containsKey(method)){
				Method runMethod = methodsDelete.get(method);
			    Class<?> clazz = runMethod.getDeclaringClass();
			    Object instance = clazz.getDeclaredConstructor().newInstance();
			    try {
					Object obj = runMethod.invoke(instance, jsonObject);
					
					String jsonObj = new Gson().toJson(obj);
					JSONObject response = new JSONObject(jsonObj);
					
					ResponseMessage message = new ResponseMessage("200", "OK", response.toString());
					
					return message;
				} catch (Exception e) {
					JSONObject response = new JSONObject();
					response.append("Error: ", "An error occurred while processing the method.");
					return new ResponseMessage("500", "Internal Server Error", response.toString());
				} 
				
			}else {
				JSONObject response = new JSONObject();
				response.append("Error: ", "Method not found");
				return new ResponseMessage("404", "Not Found", response.toString());
			}
		}
		
		JSONObject response = new JSONObject();
		response.append("Error: ", "Method not acceptable");
		return new ResponseMessage("406", "Not Acceptable", response.toString());
	}
}
