package core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import util.message.Message;

import java.io.IOException;
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
     * @param message The message with all resources required from Requestor
     * @return A Message with the response from Sever Request Handler
     */
    public Message requestRemoteObject(Message message){
    	
    	FutureTask<Message> future;
	   	Callable<Message> callable = new ClientHandler(message);

        // Create the FutureTask with Callable
	    future = new FutureTask(callable);
	  
	    // As it implements Runnable, create Thread
	    // with FutureTask
	    Thread t = new Thread(future);
	    t.start();

        try {
            return future.get();
        } catch (InterruptedException e) {
            log.error("Error to receive data from server");
            return new Message();
        } catch (ExecutionException e) {
            log.error("Error to execute a receive data from server");
            return new Message();
        }
    }

    /**
     * This class implements a callable and it is response to execute the process
     * to establish a connection with the server, receive responses and handler
     * exceptions
     */
    @AllArgsConstructor
    private static class ClientHandler implements Callable<Message> {
        private Socket connection = null;
        private final Marshaller marshaller = new Marshaller();
        private final Message message;

        public  ClientHandler(Message message){
            this.message = message;
        }


        @Override
        public Message call() {
            log.info("\n ClientHandler started..." );
            Message resp = handleRequest();
            // TO-DO resolve a specific handle to each request
            log.info("\n ClientHandler terminated");
            return resp;
        }

        private Message handleRequest(){
            String host = "localhost";
            try{
                connection = new Socket(host, SERVER_PORT);

                // sending message to server
                OutputStream out = connection.getOutputStream();
                out.write(marshaller.marshalToSocket(message));

                // receiving data from server
                Message resp = marshaller.unmarshalFromSocket(connection.getInputStream());
                log.info("Response from server: " + resp);
                connection.close();
                return resp;
            } catch (UnknownHostException e) {
                log.error("Server undefined on host " + host + " and port " + SERVER_PORT);
                return new Message();
            } catch (IOException e) {
                log.error("Error in starting connection on host " + host + " and port " + SERVER_PORT);
                return new Message();
            }
        }

    }

}
