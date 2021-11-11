package core.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import core.RemoteObject;

public class AddMethods {
		
	 public String separateMethods(Object object) {
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
                RemoteObject.addMethodGet("get-" + method.getAnnotation(Get.class).router(), method);
            }else if (method.isAnnotationPresent(Post.class)) {
                method.setAccessible(true);
                RemoteObject.addMethodPost("post-" + method.getAnnotation(Post.class).router(), method);            	
            }else if (method.isAnnotationPresent(Put.class)) {
                method.setAccessible(true);
                RemoteObject.addMethodPut("put-" + method.getAnnotation(Put.class).router(), method);           	
            }else if (method.isAnnotationPresent(Delete.class)) {
                method.setAccessible(true);
                RemoteObject.addMethodDelete("delete-" + method.getAnnotation(Delete.class).router(), method);            }            
        }
        return "sucess add methods";
    }

}
