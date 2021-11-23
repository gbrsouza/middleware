package middleware;

import java.lang.reflect.InvocationTargetException;

import middleware.communication.message.InternMessage;
import middleware.communication.message.ResponseMessage;


/**
 * Provide an INVOKER that accepts client invocations from REQUESTORS.
 * REQUESTORS send requests across the network, containing the ID of
 * the remote object, operation name, operation parameters, as well as
 * additional contextual information. The INVOKER reads the request
 * and demarshals it to obtain the OBJECT ID and the name of the operation.
 * It then dispatches the invocation with demarshaled invocation
 * parameters to the targeted remote object. That is, it looks up the
 * correct local object and its operation implementation, as described by
 * the remote invocation, and invokes it.
 */
public class Invoker {
		// Method that invokes a remote object, receiving an InternMessage and returning a ResponseMessage
        public ResponseMessage invokeRemoteObject (InternMessage msg) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
            // Separates the method type and concatenates with the path to form the hashmap key	
            var invokerKey = msg.getMethodType().toLowerCase();
            invokerKey = invokerKey + msg.getRoute();
            
            // Calls the invoke method passing the JSON key and parameters.
    		ResponseMessage respMsg = RemoteObject.findMethod(invokerKey, msg.getBody());
            	        	
        	return respMsg;
        }
}
