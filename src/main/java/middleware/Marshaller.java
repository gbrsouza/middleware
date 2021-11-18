package middleware;

import middleware.communication.message.InternMessage;
import middleware.communication.message.ResponseMessage;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;


import java.io.*;

/**
 * A MARSHALLER converts remote invocations into byte streams. The
 * MARSHALLER provides a generic mechanism that is not specific to any
 * particular remote object type.
 * The REQUESTOR, INVOKER, and REQUEST HANDLERS use the MARSHALLER
 * to retrieve the invocation information contained in the message byte
 * stream.
 */
@NoArgsConstructor
@Slf4j
public class Marshaller {

    /**
     * This method converts a ResponseMethod in an HTTP response string
     * @param message The ResponseMessage object with all necessary information
     *                to add in the HTTP response message.
     * @return A string as an HTTP message
     */
    public String marshall(ResponseMessage message) {
        StringBuilder httpResponse = new StringBuilder();
        httpResponse.append("HTTP/1.1 ");
        httpResponse.append(message.getHttpCode());
        httpResponse.append(" ");
        httpResponse.append(message.getHttpMessage());
        httpResponse.append("\r\nUser-Agent: Autumn\r\nContent-Type: application/json\r\nContent-Length:");
        httpResponse.append(message.getContent().getBytes().length);
        httpResponse.append("\r\n\r\n");
        httpResponse.append(message.getContent());
        return httpResponse.toString();
    }

    /**
     * Parse an HTTP message receive by server in a InternMessage to
     * be used by all middleware components
     * @param in A BufferRead of socket
     * @return A InternMessage with all relevant information received by server
     * @throws IOException return an exception if an error occurs at read the socket
     */
    public InternMessage unmarshall(BufferedReader in) throws IOException {
    	Integer count = 0;
        String s = in.readLine(); // first line
        if(s == null) {
	        while(s == null) {
	        	count++;
	        	s = in.readLine();
	        	if(s != null || count > 5) {
	        		break;
	        	}
	        }
        }
        System.out.println("1-" + s);
        String[] parts = s.split(" ");

        InternMessage msg = new InternMessage();
        msg.setMethodType(parts[0]);
        msg.setRoute(parts[1]);

        // reader header
        while ((s = in.readLine()) != null) {
            if (s.isEmpty()) {
                break;
            }
        }

        // reader body
        StringBuilder payload = new StringBuilder();
        while(in.ready()){
            payload.append((char) in.read());
        }
        msg.setBody(new JSONObject(payload.toString()));
        return msg;
    }

}
