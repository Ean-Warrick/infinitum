<img src="https://github.com/user-attachments/assets/f2a62108-2679-4cd0-91fe-f0522c591039" width="400" />

## About
Infinitum is a java module that includes events, protected method calling, easy console printing, and more. 

## Usage
```java
import infinitum.dev.Instance;
import infinitum.bind.BindableEvent;
import infinitum.bind.Bundle;
import infinitum.lua.LuaCore;

public class Counter {
    static private class Listener extends Instance {
        public void counterChanged(Bundle bundle) {
            int newValue = bundle.getInt("NewValue");
            LuaCore.print("New Value:", newValue);
        }
    }

    private int counter;
    BindableEvent counterChanged;

    public Counter() {
        this.counter = 0;
        this.counterChanged = new BindableEvent();
    }

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        this.counter += 1;
        Bundle bundle = new Bundle();
        bundle.set("NewValue", this.counter);
        counterChanged.fire(bundle);
    }

    public static void main(String[] args) {
        Counter myCounter = new Counter();
        LuaCore.print(myCounter.getCounter());
        Listener listener = new Listener();
        listener.connect(myCounter.counterChanged, "counterChanged", listener::counterChanged);
        myCounter.incrementCounter();
        myCounter.incrementCounter();
        myCounter.incrementCounter();
    }
}

// --Output--
// 0
// New Value: 1
// New Value: 2
// New Value: 3 

```
