package middleware;

import util.message.Message;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

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

        public ResponseMessage invokeRemoteObject (InternMessage msg) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
            	
            var invokerKey = msg.getMethodType().toLowerCase();
            invokerKey = invokerKey + msg.getRoute();
            
    		ResponseMessage respMsg = RemoteObject.findMethod(invokerKey, msg.getBody());
            	        	
        	return respMsg;
        }
}
