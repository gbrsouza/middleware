package core.pattern.reactor;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class AcceptEventHandler implements EventHandler{
    private final Selector demutiplexer;
    public AcceptEventHandler(Selector demultiplexer) {
        this.demutiplexer = demultiplexer;
    }

    @Override
    public void handleEvent(SelectionKey handle) throws Exception {
        System.out.println("===== Accept Event Handler =====");
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) handle.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        if (socketChannel != null) {
            socketChannel.configureBlocking(false);
            socketChannel.register(demutiplexer, SelectionKey.OP_READ);
        }
    }
}
