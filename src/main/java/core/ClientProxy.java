package core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

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