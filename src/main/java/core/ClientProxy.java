package core;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientProxy implements Serializable {

    private int objectID;

    public ClientProxy(int objectid) {
        this.objectID = objectid;
    }

    public void setobjectID(int objectID) {
        this.objectID = objectID;
    }

    public int getobjectID() {
        return objectID;
    }

    public String getMethodName(Object obj) {
        return obj.getClass().getEnclosingMethod().getName();
    }        

    
    public Object operate(ArrayList<Object> parameters, String methodName) {
        Requestor requestor = new Requestor();

        return requestor.invoke(this.getobjectID(),methodName, parameters);

    }

    
}