package infinitum.bind;

import java.util.HashMap;
import java.util.Objects;

public class Bundle {
    private HashMap<String, Object> data;

    public Bundle() {
        this.data = new HashMap<>();
    }

    public void set(String key, Object value) {
        this.data.put(key, value);
    }

    public Object get(String key) {
        return this.data.get(key);
    }

    public String getString(String key) {
        return (String) this.data.get(key);
    }

    public int getInt(String key) {
        return (int) this.data.get(key);
    }
}
