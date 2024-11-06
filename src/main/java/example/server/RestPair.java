package example.server;

import java.lang.reflect.Method;

public class RestPair {

    private Object controller;
    private Method method;

    public RestPair(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public Method getMethod() {
        return method;
    }
}
