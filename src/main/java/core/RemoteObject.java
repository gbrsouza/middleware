package core;

import java.util.concurrent.ConcurrentHashMap;

public class RemoteObject {
	
	private static ConcurrentHashMap<Object, Object> methodsGet = new ConcurrentHashMap<>();
	
	private static ConcurrentHashMap<Object, Object> methodsPost = new ConcurrentHashMap<>();
	
	private static ConcurrentHashMap<Object, Object> methodsPut = new ConcurrentHashMap<>();
	
	private static ConcurrentHashMap<Object, Object> methodsDelete = new ConcurrentHashMap<>();
	
		
	public static void addMethodPost(Object key, Object obj) {
		methodsPost.put(key, obj);
	}
	
	public static void addMethodGet(Object key, Object obj) {
		methodsGet.put(key, obj);
	}

	public static void addMethodPut(Object key, Object obj) {
		methodsPut.put(key, obj);
	}
	
	public static void addMethodDelete(Object key, Object obj) {
		methodsDelete.put(key, obj);
	}
	
	public static boolean findMethod(String method) {
		if(method.toLowerCase().contains("get")) {
			return methodsGet.containsKey(method);
		}else if(method.toLowerCase().contains("post") ) {
			return methodsPost.containsKey(method);
		}else if(method.toLowerCase().contains("put") ) {
			return methodsPut.containsKey(method);
		}else {
			return methodsDelete.containsKey(method);
		}
	}
}
