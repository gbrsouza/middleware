package core;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import com.sun.nio.sctp.HandlerResult;

import util.message.Message;

public class Requestor {

    public Message invoke(int objId, String method, ArrayList<Object> params) {

        ClientRequestHandler handler = new ClientRequestHandler();
        
        Random id = new Random();

        Message requestMessage = new Message().NewRequestMessage(
        		true,
                id.nextInt(20000),
                method,
                params
        );

        Message response;
		try {
			response = handler.requestRemoteObject(requestMessage, "");
	        return response;

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
        

    }

}
