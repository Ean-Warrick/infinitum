package infinitum.lua;

import java.time.Instant;
import java.util.function.Supplier;

public class LuaCore {
    public static void print(Object ...objects) {
        for (Object nextObject : objects) {
            System.out.print(nextObject.toString());
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public static void println(Object ...objects) {
        for (Object nextObject : objects) {
            System.out.print(nextObject.toString());
            System.out.println();
        }
    }

    public static long tick() {
        return Instant.now().getEpochSecond();
    }

    public static Thread spawn(Runnable runnable) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                runnable.run();
            }
        };
        thread.start();
        return thread ;
    }

    public static long wait(float seconds) {
        long start = tick();
        try {
            Thread.sleep((long) seconds * 1000);
        } catch (InterruptedException ignored) {}
        long end = tick();
        long delta = end - start;
        return delta;
    }

    public static PCallResult pcall(Supplier<Object> supplier) {
        try {
            Object result = supplier.get();
            return new PCallResult(true, result);
        } catch (Exception exception){
            return new PCallResult(false, null);
        }
    }

    public static PCallResult pcall(Runnable runnable) {
        try {
            runnable.run();
            return new PCallResult(true, null);
        } catch (Error error){
            return new PCallResult(false, null);
        }
    }
}
