package core.pattern.reactor;

import java.nio.channels.SelectionKey;

public interface EventHandler {
    public void handleEvent(SelectionKey handle) throws Exception;
}
