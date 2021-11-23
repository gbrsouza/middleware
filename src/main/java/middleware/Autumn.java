package middleware;

import java.lang.reflect.Method;
import middleware.annotations.*;

/*
 * Class that encapsulates the middleware, responsible
 *  for storing the methods in hashmaps and starting the 
 *  server on the correct port.
 */


public class Autumn {
	
	// calls the method that filters and saves remote objects
	 public String addMethods(Object object) {
	       try {
			return filterMethods(object);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	  }
	
	//	Method that filters and saves remote objects
	private String filterMethods(Object object) throws IllegalAccessException, IllegalArgumentException {
		//	Extract the remote object class
		Class<?> clazz = object.getClass();
		
		//	Walks through all the methods of the class and checks if they have annotations
        for (Method method : clazz.getDeclaredMethods()) {
        	// Checks if the annotation is a Get
            if (method.isAnnotationPresent(Get.class)) {
            	// Allows the method to be accessed and invoked later
                method.setAccessible(true);
            	// Saves the method to the hashmap following the key pattern "get + class route + method route"
                RemoteObject.addMethodGet("get" + clazz.getAnnotation(RequestMap.class).router() + method.getAnnotation(Get.class).router(), method);
            // Checks if the annotation is a Post
            }else if (method.isAnnotationPresent(Post.class)) {
            	// Allows the method to be accessed and invoked later
                method.setAccessible(true);
            	// Saves the method to the hashmap following the key pattern "post + class route + method route"
                RemoteObject.addMethodPost("post" + clazz.getAnnotation(RequestMap.class).router() + method.getAnnotation(Post.class).router(), method);            	
            // Checks if the annotation is a Put
            }else if (method.isAnnotationPresent(Put.class)) {
            	// Allows the method to be accessed and invoked later
                method.setAccessible(true);
            	// Saves the method to the hashmap following the key pattern "put + class route + method route"
                RemoteObject.addMethodPut("put" + clazz.getAnnotation(RequestMap.class).router() + method.getAnnotation(Put.class).router(), method);           	
            // Checks if the annotation is a Delete
            }else if (method.isAnnotationPresent(Delete.class)) {
            	// Allows the method to be accessed and invoked later
                method.setAccessible(true);
            	// Saves the method to the hashmap following the key pattern "delete + class route + method route"
                RemoteObject.addMethodDelete("delete" + clazz.getAnnotation(RequestMap.class).router() + method.getAnnotation(Delete.class).router(), method);            }            
        }
        return "sucess add methods";
    }
	
	//	Method that starts the server on the chosen port
	public void start(int port) {
		// ServerRequestHandler instance on the chosen port
		ServerRequestHandler server = new ServerRequestHandler(port);
		// call start method
		server.run();
	}

}
