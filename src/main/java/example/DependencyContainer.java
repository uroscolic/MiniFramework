package example;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DependencyContainer {

    private Map<String, Class<?>> container = new HashMap<>();
    private static DependencyContainer instance;

    private DependencyContainer() {}

    public static DependencyContainer getInstance() {
        if (instance == null) {
            instance = new DependencyContainer();
        }
        return instance;
    }

    public <T> Class<? extends T> getInjection(Field field, Class<?> qualifier) {
        Class<T> type = (Class<T>) field.getType();
        String key = type.getName() + ":" + qualifier.getName();
        if (!container.containsKey(key))
            container.put(key, qualifier.asSubclass(type));

        Class<?> injection = container.get(key);

        if (injection == null)
            throw new RuntimeException("No Dependency Injection for type: " + type);
        return injection.asSubclass(type);
    }

}
