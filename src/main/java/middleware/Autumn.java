package middleware;

import java.lang.reflect.Method;
import middleware.annotations.*;

public class Autumn {
		
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
	
	private String filterMethods(Object object) throws IllegalAccessException, IllegalArgumentException {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Get.class)) {
                method.setAccessible(true);
                RemoteObject.addMethodGet("get" + clazz.getAnnotation(RequestMap.class).router() + method.getAnnotation(Get.class).router(), method);
            }else if (method.isAnnotationPresent(Post.class)) {
                method.setAccessible(true);
                RemoteObject.addMethodPost("post" + clazz.getAnnotation(RequestMap.class).router() + method.getAnnotation(Post.class).router(), method);            	
            }else if (method.isAnnotationPresent(Put.class)) {
                method.setAccessible(true);
                RemoteObject.addMethodPut("put" + clazz.getAnnotation(RequestMap.class).router() + method.getAnnotation(Put.class).router(), method);           	
            }else if (method.isAnnotationPresent(Delete.class)) {
                method.setAccessible(true);
                RemoteObject.addMethodDelete("delete" + clazz.getAnnotation(RequestMap.class).router() + method.getAnnotation(Delete.class).router(), method);            }            
        }
        return "sucess add methods";
    }
	
	public void start(int port) {
		ServerRequestHandler server = new ServerRequestHandler(port);
		server.run();
	}

}
