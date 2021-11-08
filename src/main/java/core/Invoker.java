package core;

import util.message.Message;

import java.util.ArrayList;

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

        public Message invokeRemoteObject (Message msg) {
            if (msg.getHeader().getMessageType() == 0){
                        	
            	if(RemoteObject.findMethod(msg.getBody().getRequestHeader().getOperation())) {
            		return new Message(true, 1, "response", new ArrayList<>());
            	}else {
            		return new Message(true, 1, "error", new ArrayList<>());
            	}
            }else {
                return new Message(true, 1, "error", new ArrayList<>());
            }
        }
}
