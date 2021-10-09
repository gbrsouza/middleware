package core.pattern.reactor;

import lombok.Getter;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * It is the job of the CLIENT REQUEST HANDLER to ensure scalability. That
 * is, it has to be designed in such a way that it handles concurrent
 * requests efficiently. To do this, the Reactor [SSRB00] pattern is used to
 * demultiplex and dispatch reply messages. A reactor uses the connection
 * handle to notify the responsible handler about available results.
 */
@Getter
public class Reactor {
    private final Map<Integer, EventHandler> registeredHandlers = new ConcurrentHashMap<Integer, EventHandler>();
    private final Selector demultiplexer;

    /**
     * Instantiates a reactor and open the NIO selector
     * @throws Exception if any error occurs when open the selector return a exception
     */
    public Reactor() throws Exception {
        demultiplexer = Selector.open();
    }

    /**
     * Register a new handler
     * @param eventType the event type
     * @param eventHandler the handler
     */
    public void registerEventHandler(int eventType, EventHandler eventHandler){
        registeredHandlers.put(eventType, eventHandler);
    }

    /**
     * register a new channel with the demutiplexer
     * @param eventType the event type
     * @param channel the channel that will register
     * @throws Exception
     */
    public void registerChannel(int eventType, SelectableChannel channel) throws Exception {
        channel.register(demultiplexer, eventType);
    }

    /**
     * Main function from Reactor
     */
    public void run() {
        try {
            while (true){
                demultiplexer.select();

                Set<SelectionKey> readyHandles = demultiplexer.selectedKeys();
                Iterator<SelectionKey> handleIterator = readyHandles.iterator();

                while (handleIterator.hasNext()){
                    SelectionKey handle = handleIterator.next();

                    if (handle.isAcceptable()){
                        EventHandler handler = registeredHandlers.get(SelectionKey.OP_ACCEPT);
                        handler.handleEvent(handle);
                    }

                    if (handle.isReadable()){
                        EventHandler handler = registeredHandlers.get(SelectionKey.OP_READ);
                        handler.handleEvent(handle);
                        handleIterator.remove();
                    }

                    if (handle.isWritable()){
                        EventHandler handler = registeredHandlers.get(SelectionKey.OP_WRITE);
                        handler.handleEvent(handle);
                        handleIterator.remove();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
