package core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import util.message.Message;

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
	
	public static Message findMethod(String method, ArrayList<Object> params) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(method.toLowerCase().contains("get")) {
			
			if(methodsGet.containsKey(method)){
				Method runMethod = methodsGet.get(method);
			    Class<?> clazz = runMethod.getDeclaringClass();
			    Object instance = clazz.getDeclaredConstructor().newInstance();
				try {
					Object obj = runMethod.invoke(instance, params.get(0), params.get(1));
					System.out.println(obj);
					return new Message(true, 1, "200 - Sucess", new ArrayList<>());
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					return new Message(true, 1, "500 - Internal Server Error (IllegalAccessException)", new ArrayList<>());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new Message(true, 1, "500 - Internal Server Error (IllegalArgumentException)", new ArrayList<>());
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					return new Message(true, 1, "500 - Internal Server Error (InvocationTargetException)", new ArrayList<>());
				}
				
			}else {
				return new Message(true, 1, "404 - Not Found", new ArrayList<>());
			}
			 
		}else if(method.toLowerCase().contains("post") ) {
			if(methodsPost.containsKey(method)){
				Method runMethod = methodsPost.get(method);
			    Class<?> clazz = runMethod.getDeclaringClass();
			    Object instance = clazz.getDeclaredConstructor().newInstance();
				try {
					Object obj = runMethod.invoke(instance, params.get(0), params.get(1));
					System.out.println(obj);
					return new Message(true, 1, "200 - Sucess", new ArrayList<>());
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					return new Message(true, 1, "500 - Internal Server Error", new ArrayList<>());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					return new Message(true, 1, "500 - Internal Server Error", new ArrayList<>());
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					return new Message(true, 1, "500 - Internal Server Error", new ArrayList<>());
				}				
			}else {
				return new Message(true, 1, "404 - Not Found", new ArrayList<>());
			}
		}else if(method.toLowerCase().contains("put") ) {
			if(methodsPut.containsKey(method)){
				Method runMethod = methodsPut.get(method);
			    Class<?> clazz = runMethod.getDeclaringClass();
			    Object instance = clazz.getDeclaredConstructor().newInstance();
				try {
					Object obj = runMethod.invoke(instance, params.get(0), params.get(1));
				    System.out.println(obj);
					return new Message(true, 1, "200 - Sucess", new ArrayList<>());
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					return new Message(true, 1, "500 - Internal Server Error", new ArrayList<>());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					return new Message(true, 1, "500 - Internal Server Error", new ArrayList<>());
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					return new Message(true, 1, "500 - Internal Server Error", new ArrayList<>());
				}
			}else {
				return new Message(true, 1, "404 - Not Found", new ArrayList<>());
			}
		}else if(method.toLowerCase().contains("delete") ) {
			if(methodsDelete.containsKey(method)){
				Method runMethod = methodsDelete.get(method);
			    Class<?> clazz = runMethod.getDeclaringClass();
			    Object instance = clazz.getDeclaredConstructor().newInstance();
				try {
					Object obj = runMethod.invoke(instance, params.get(0), params.get(1));
				    System.out.println(obj);
					return new Message(true, 1, "200 - Sucess", new ArrayList<>());
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					return new Message(true, 1, "500 - Internal Server Error", new ArrayList<>());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					return new Message(true, 1, "500 - Internal Server Error", new ArrayList<>());
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					return new Message(true, 1, "500 - Internal Server Error", new ArrayList<>());
				}
			}else {
				return new Message(true, 1, "404 - Not Found", new ArrayList<>());
			}
		}
		return new Message(true, 1, "405 - Method Not Allowed", new ArrayList<>());
	}
}
