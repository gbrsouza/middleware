package core;
import lombok.Getter;


/**
 * It is the job of the CLIENT REQUEST HANDLER to ensure scalability. That
 * is, it has to be designed in such a way that it handles concurrent
 * requests efficiently. To do this, the Reactor pattern is used to
 * demultiplex and dispatch reply messages. A reactor uses the connection
 * handle to notify the responsible handler about available results.
 */
@Getter
public class ClientRequestHandler {
    private static final int SERVER_PORT = 7080;

}
