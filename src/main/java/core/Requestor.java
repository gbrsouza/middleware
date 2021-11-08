package core;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import com.sun.nio.sctp.HandlerResult;

import lombok.extern.slf4j.Slf4j;
import util.message.Message;

/**
 * In the client application, use a REQUESTOR for accessing the remote
 * object. The REQUESTOR is supplied with the ABSOLUTE OBJECT REFERENCE
 * of the remote object, the operation name, and its arguments. It
 * constructs a remote invocation from these parameters and sends the
 * invocation to the remote object. The REQUESTOR hides the details of
 * the client side distributed object communication from clients.
 */
@Slf4j
public class Requestor {

	/**
	 * Stabilise a connection with Client Request Handler and request a remote object
	 * @param objId Request ID
	 * @param method The remote object name
	 * @param params The remote object params
	 * @return A Message provided by the Client Request Handler about request
	 */
    public Message invoke(int objId, String method, ArrayList<Object> params) {

        //ClientRequestHandler handler = new ClientRequestHandler();
        /*Message requestMessage = Message.newRequestMessage(
       # 		true,
                objId,
                method,
                params
        );*/

        //Message response;

		//response = handler.requestRemoteObject(requestMessage);
		return null;
    }

}
