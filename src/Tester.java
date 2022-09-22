import infinitum.lua.KeyValuePair;
import infinitum.lua.LuaCore;

import java.util.HashMap;
import java.util.function.Consumer;

public class Tester {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Test", "Hello");
        hashMap.put("Test 2", "There");
        LuaCore.forKVInPairs(hashMap, (KeyValuePair keyValuePair) -> {
            String key = keyValuePair.key.toString();
            String value = keyValuePair.value.toString();
            LuaCore.print(key, value);
        });
    }
}
