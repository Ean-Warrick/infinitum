package infinitum.dev;

import infinitum.bind.*;

import java.util.HashMap;
import java.util.function.Consumer;

public class Instance {
    private HashMap<String, BindableEvent.Connection> connectionHashMap;

    public Instance() {
        this.connectionHashMap = new HashMap<>();
    }

    public void connect(BindableEvent event, String key, Consumer<Bundle> func) {
        this.disconnect(key);
        this.connectionHashMap.put(key, event.connect(func));
    }

    public void disconnect(String key) {
        BindableEvent.Connection connection = this.connectionHashMap.remove(key);
        if (connection != null) {
            connection.disconnect();
        }
    }
}
