package middleware;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;

import middleware.communication.message.ResponseMessage;

/*
 *  Remote objects have a unique OBJECT ID in their local address space, as
	well as a means to construct an ABSOLUTE OBJECT REFERENCE . The ABSO -
	LUTE OBJECT REFERENCE is used to reference and subsequently access a
	remote object across the network.
 */

@Slf4j
public class RemoteObject {
	
	// Hashmap to save get methods
	private static ConcurrentHashMap<Object, Method> methodsGet = new ConcurrentHashMap<>();
	// Hashmap to save post methods
	private static ConcurrentHashMap<Object, Method> methodsPost = new ConcurrentHashMap<>();
	// Hashmap to save put methods
	private static ConcurrentHashMap<Object, Method> methodsPut = new ConcurrentHashMap<>();
	// Hashmap to save delete methods
	private static ConcurrentHashMap<Object, Method> methodsDelete = new ConcurrentHashMap<>();
	
	//Method that adds a remote object to posts hashmap	
	public static void addMethodPost(Object key, Method obj) {
		methodsPost.put(key, obj);
		System.out.println(methodsPost.keySet());

	}
	
	//Method that adds a remote object to gets hashmap	
	public static void addMethodGet(Object key, Method obj) {
		methodsGet.put(key, obj);
		System.out.println(methodsGet.keySet());
	}

	//Method that adds a remote object to puts hashmap	
	public static void addMethodPut(Object key, Method obj) {
		methodsPut.put(key, obj);
		System.out.println(methodsPut.keySet());

	}
	//Method that adds a remote object to deletes hashmap	
	public static void addMethodDelete(Object key, Method obj) {
		methodsDelete.put(key, obj);
		System.out.println(methodsDelete.keySet());
	}
	
	// Method responsible for finding and invoking remote objects.
	public static ResponseMessage findMethod(String method, JSONObject jsonObject) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		// Checks if the path of the method you want to invoke is of type get.
		if(method.toLowerCase().contains("get")) {
			// Checks the hashmap of get methods contains the passed key
			if(methodsGet.containsKey(method)){
				// Retrieve hashmap method
				Method runMethod = methodsGet.get(method);
				// Retrieve the method class
			    Class<?> clazz = runMethod.getDeclaringClass();
			    // Generate an instance of the class that method belongs to
			    Object instance = clazz.getDeclaredConstructor().newInstance();
			    // Try to run the method and bring its return
				try {
					// Invoke the method using the instance and parameters received
					JSONObject obj = (JSONObject) runMethod.invoke(instance, jsonObject);
					// Returns 200 along with method response
					ResponseMessage message = new ResponseMessage("200", "OK", obj.toString());
					return message;
				// Returns 500 in case of an error
				}catch (Exception e) {
					//Generates the error message
					log.error(e.getMessage());
					// Create a json to store the message
					JSONObject response = new JSONObject();
					response.append("Error: ", "An error occurred while processing the method.");
					// Return the error message
					return new ResponseMessage("500", "Internal Server Error", response.toString());
				} 
			// Returns 404 if it doesn't find the method	
			}else {
				JSONObject response = new JSONObject();
				response.append("Error: ", "Method not found");
				return new ResponseMessage("404", "Not Found", response.toString());			}
		// Checks if the path of the method you want to invoke is of type post.	 
		}else if(method.toLowerCase().contains("post") ) {
			// Checks the hashmap of post methods contains the passed key
			if(methodsPost.containsKey(method)){
				// Retrieve hashmap method
				Method runMethod = methodsPost.get(method);
				// Retrieve the method class
			    Class<?> clazz = runMethod.getDeclaringClass();
			    // Generate an instance of the class that method belongs to
			    Object instance = clazz.getDeclaredConstructor().newInstance();
			    // Try to run the method and bring its return
			    try {
					// Invoke the method using the instance and parameters received
					JSONObject obj = (JSONObject) runMethod.invoke(instance, jsonObject);
					// Returns 200 along with method response
					ResponseMessage message = new ResponseMessage("200", "OK", obj.toString());
					return message;
					// Returns 500 in case of an error
			    }  catch (Exception e) {
					//Generates the error message
					log.error(e.getMessage());
					// Create a json to store the message
					JSONObject response = new JSONObject();
					response.append("Error: ", "An error occurred while processing the method.");
					// Return the error message
					return new ResponseMessage("500", "Internal Server Error", response.toString());
				} 
			// Returns 404 if it doesn't find the method		
			}else {
				JSONObject response = new JSONObject();
				response.append("Error: ", "Method not found");
				return new ResponseMessage("404", "Not Found", response.toString());
			}
		// Checks if the path of the method you want to invoke is of type put.
		}else if(method.toLowerCase().contains("put") ) {
			// Checks the hashmap of put methods contains the passed key
			if(methodsPut.containsKey(method)){
				// Retrieve hashmap method
				Method runMethod = methodsPut.get(method);
				// Retrieve the method class
			    Class<?> clazz = runMethod.getDeclaringClass();
			    // Generate an instance of the class that method belongs to
			    Object instance = clazz.getDeclaredConstructor().newInstance();
			    // Try to run the method and bring its return
			    try {
					// Invoke the method using the instance and parameters received
					JSONObject obj = (JSONObject) runMethod.invoke(instance, jsonObject);
					// Returns 200 along with method response
					ResponseMessage message = new ResponseMessage("200", "OK", obj.toString());
					return message;
					// Returns 500 in case of an error
			    } catch (Exception e) {
					//Generates the error message
					log.error(e.getMessage());
					// Create a json to store the message
					JSONObject response = new JSONObject();
					response.append("Error: ", "An error occurred while processing the method.");
					// Return the error message
					return new ResponseMessage("500", "Internal Server Error", response.toString());
				} 
				
			}else {
				JSONObject response = new JSONObject();
				response.append("Error: ", "Method not found");
				return new ResponseMessage("404", "Not Found", response.toString());
			}
		// Checks if the path of the method you want to invoke is of type delete.
		}else if(method.toLowerCase().contains("delete") ) {
			// Checks the hashmap of delete methods contains the passed key
			if(methodsDelete.containsKey(method)){
				// Retrieve hashmap method
				Method runMethod = methodsDelete.get(method);
				// Retrieve the method class
			    Class<?> clazz = runMethod.getDeclaringClass();
			    // Generate an instance of the class that method belongs to
			    Object instance = clazz.getDeclaredConstructor().newInstance();
			    // Try to run the method and bring its return
			    try {
					// Invoke the method using the instance and parameters received
					JSONObject obj = (JSONObject) runMethod.invoke(instance, jsonObject);
					// Returns 200 along with method response
					ResponseMessage message = new ResponseMessage("200", "OK", obj.toString());
					return message;
					// Returns 500 in case of an error
				} catch (Exception e) {
					//Generates the error message
					log.error(e.getMessage());
					// Create a json to store the message
					JSONObject response = new JSONObject();
					response.append("Error: ", "An error occurred while processing the method.");
					// Return the error message
					return new ResponseMessage("500", "Internal Server Error", response.toString());
				} 
			// Returns 404 if it doesn't find the method	
			}else {
				JSONObject response = new JSONObject();
				response.append("Error: ", "Method not found");
				return new ResponseMessage("404", "Not Found", response.toString());
			}
		}
		// Returns 406 if required method is not of accepted types
		JSONObject response = new JSONObject();
		response.append("Error: ", "Method not acceptable");
		return new ResponseMessage("406", "Not Acceptable", response.toString());
	}
}
