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
     */
    public void requestRemoteObject(Message message, String resource) {
        var request = new Thread(new ClientHandler(message, resource));
    }

    @AllArgsConstructor
    private static class ClientHandler implements Runnable {
        private final String host = "localhost";
        private Socket connection = null;
        private final Marshaller marshaller = new Marshaller();
        private final Message message;
        private final String resource;

        public  ClientHandler(Message message, String resource){
            this.message = message;
            this.resource = resource;
        }


        @Override
        public void run() {
            log.info("\n ClientHandler started..." );
            handleRequest();
            // TO-DO resolve a specific handle to each request
            log.info("\n ClientHandler terminated");
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
                return marshaller.unmarshalFromSocket(connection.getInputStream());
            } catch (IOException e) {
                log.error("Error to receive data from server - returning a null message");
                return null;
            }
        }
    }

}
