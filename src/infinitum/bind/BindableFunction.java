package infinitum.bind;

import java.util.function.Function;

public class BindableFunction {

    Function<Bundle, Bundle> invokeable;
    boolean isInitialized;

    public BindableFunction() {
        this.isInitialized = false;
    }

    public void setFunc(Function<Bundle, Bundle> func) {
        this.invokeable = func;
        this.isInitialized = true;
    }

    public Bundle invoke(Bundle bundle) {
        if (this.isInitialized) {
            return this.invokeable.apply(bundle);
        } else {
            return null;
        }
    }
}
