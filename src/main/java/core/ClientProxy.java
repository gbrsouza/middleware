package core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Provide a CLIENT PROXY to the client developer that supports the
 * same interface as the remote object. For remote invocations, clients
 * only interact with the local CLIENT PROXY. The CLIENT PROXY translates
 * the local invocation into parameters for the REQUESTOR, and
 * triggers the invocation. The CLIENT PROXY receives the result from
 * the REQUESTOR, and hands it over to the client using an ordinary
 * return value.
 */
@AllArgsConstructor
@Getter
@Setter
public class ClientProxy implements Serializable {
    private int objectID;
    
    public Object operate(ArrayList<Object> parameters, String methodName) {
        Requestor requestor = new Requestor();
        return requestor.invoke(this.getObjectID(),methodName, parameters);
    }

    
}