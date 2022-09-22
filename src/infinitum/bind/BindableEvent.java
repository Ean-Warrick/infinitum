package infinitum.bind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

public class BindableEvent {
    public static class Connection {
        String id;
        Consumer<Bundle> func;
        BindableEvent event;
        boolean connected;
        public Connection(BindableEvent bindableEvent, Consumer<Bundle> func, String id) {
            this.func = func;
            this.id = id;
            this.event = bindableEvent;
            this.connected = true;
        }

        void fire(Bundle bundle) {
            this.func.accept(bundle);
        }

        public void disconnect() {
            this.event.disconnect(this.id);
            this.func = null;
            this.id = null;
            this.event = null;
            this.connected = false;
        }
    }

    public static class OnceConnection extends Connection {
        public OnceConnection(BindableEvent bindableEvent, Consumer<Bundle> func, String id) {
            super(bindableEvent, func, id);
        }

        @Override
        void fire(Bundle bundle) {
            super.fire(bundle);
            this.disconnect();
        }
    }

    private HashMap<String, Connection> connections;

    public BindableEvent() {
        connections = new HashMap<>();
    }

    public Connection connect(Consumer<Bundle> func) {
        String uuid = UUID.randomUUID().toString();
        Connection connection = new Connection(this, func, uuid);
        this.connections.put(uuid, connection);
        return connection;
    }

    public Connection once(Consumer<Bundle> func) {
        String uuid = UUID.randomUUID().toString();
        Connection connection = new OnceConnection(this, func, uuid);
        this.connections.put(uuid, connection);
        return connection;
    }

    public void fire(Bundle bundle) {
        ArrayList<Thread> threadArrayList = new ArrayList<>();
        for (Connection connection : this.connections.values()) {
            Thread thread = new Thread(() -> connection.fire(bundle));
            threadArrayList.add(thread);
            thread.start();
        }

        for (Thread thread : threadArrayList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void disconnect(String key) {
        Connection connection = this.connections.remove(key);
    }
}
