package middleware;

import middleware.communication.message.InternMessage;
import middleware.communication.message.MessageType;
import middleware.communication.message.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * the SERVER REQUEST HANDLER receive messages from the network,
 * combine the message fragments to complete messages, and dispatch the messages to the
 * correct INVOKER for further processing. The SERVER REQUEST
 * HANDLER will manage all the required resources, such as connections
 * and threads.
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ServerRequestHandler {

    private final int MAX_THREAD_NUMBER = Runtime.getRuntime().availableProcessors() / 2;
    private int SERVER_PORT = 7080;
  
    /**
     * Main function from Server Request Handler, wait for connections
     * and instantiates new thread for each connection
     */    
    public void run() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_THREAD_NUMBER);
        try {
            log.info("Server Request Handler starting on port " + SERVER_PORT);
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            while (true){
                log.info("Waiting for client requests...");
                Socket remote = serverSocket.accept();
                log.info("Connection done");
                executor.execute(new ServerHandler(remote));
            }
        } catch (IOException e) {
            log.error("[ERROR] problems to start the Server Request Handler");
            log.error(e.getMessage());
        }
    }

    /**
     * This class implements a Thread-per-connection model to Server Request Handler.
     * For each connection received by server, a new thread is created to instantiate
     * an appropriate invoker to access the resource
     */
    @AllArgsConstructor
    private static class ServerHandler implements Runnable {
        private final Socket socket;
        private final Marshaller marshaller = new Marshaller();

        @Override
        public void run() {
            log.info("\n ServerHandler started for" + this.socket);
			try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                var request = marshaller.unmarshall(in);
                ResponseMessage msg = new ResponseMessage();

                if (request.getType().equals(MessageType.ERROR)){
                    log.warn("Server handler could not interpret request");
                    msg.setHttpCode("400");
                    msg.setHttpMessage("Bad Request");
                    msg.setContent(request.getBody().toString());
                }else {
                    msg = handleRequest(request);
                }

                // response client
                String httpResponse = marshaller.marshall(msg);
                out.write(httpResponse);

                // close connection
                out.close();
                in.close();
                socket.close();

            } catch (Exception e1) {
				log.error("Error to receive data from handle requester");
                e1.printStackTrace();
			}

            log.info("\n ServerHandler terminated for" + this.socket + "\n");

        }

        /**
         * Recover and executes the commands received from client
         */
        private ResponseMessage handleRequest(InternMessage internMessage){
            try {
            	Invoker inv = new Invoker();
                return inv.invokeRemoteObject(internMessage);
            } catch (Exception e) {
                log.error("Error in recover data from received package");
				JSONObject response = new JSONObject();
				response.append("Error: ", "There was an error receiving the package.");
				return new ResponseMessage("500", "Internal Server Error", response.toString());            }
        }

    }



}
