package core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import util.message.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * It is the job of the CLIENT REQUEST HANDLER to ensure scalability. That
 * is, it has to be designed in such a way that it handles concurrent
 * requests efficiently. To do this, the Reactor pattern is used to
 * demultiplex and dispatch reply messages. A reactor uses the connection
 * handle to notify the responsible handler about available results.
 */
@Getter
@Slf4j
public class ClientRequestHandler {
    private static final int SERVER_PORT = 7080;

    /**
     * This class implements a Thread-per-connection model to Client Request Handler.
     * For each connection received by requestor, a new thread is created to instantiate
     * an request to Sever Request Handler
     * @throws ExecutionException 
     * @throws InterruptedException 
     */
    public Message requestRemoteObject(Message message, String resource) throws InterruptedException, ExecutionException {
    	
    	FutureTask<Message> future;
	   	Callable callable = new ClientHandler(message,resource);
	   
	      // Create the FutureTask with Callable
	    future = new FutureTask(callable);
	  
	      // As it implements Runnable, create Thread
	      // with FutureTask
	    Thread t = new Thread(future);
	    t.start();
	    
	    return future.get();
    }

    @AllArgsConstructor
    private static class ClientHandler implements Callable<Message> {
        private final String host = "localhost";
        private Socket connection = null;
        private final Marshaller marshaller = new Marshaller();
        private final Message message;
        private Message respmessage;        
        private final String resource;

        public  ClientHandler(Message message, String resource){
            this.message = message;
            this.resource = resource;
        }


        @Override
        public Message call() {
            log.info("\n ClientHandler started..." );
            handleRequest();
            // TO-DO resolve a specific handle to each request
            log.info("\n ClientHandler terminated");
            return getResponseMessage();
        }

        private void handleRequest(){
            try{
                connection = new Socket(host, SERVER_PORT);
                OutputStream out = connection.getOutputStream();
                out.write(marshaller.marshalToSocket(message));
                log.info("Response from server: " + getResponse(connection));
                connection.close();
            } catch (UnknownHostException e) {
                log.error("Server undefined on host " + host + " and port " + SERVER_PORT);
            } catch (IOException e) {
                log.error("Error in starting connection on host " + host + " and port " + SERVER_PORT);
            }
        }

        /**
         * Receive a response from server
         * @param connection The socket connection with the server
         * @return A Message object send by server
         */
        private Message getResponse(Socket connection) {
            try{
                respmessage = marshaller.unmarshalFromSocket(connection.getInputStream());
                return respmessage;
            } catch (IOException e) {
                log.error("Error to receive data from server - returning a null message");
                return null;
            }
        }
        
        private Message getResponseMessage() {
        	return respmessage;
        }
    }

}
