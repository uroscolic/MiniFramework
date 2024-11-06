package example;

import example.annotations.Qualifier;

import java.util.HashMap;
import java.util.Map;

public class DependencyContainer {
    private static DependencyContainer instance;
    private final Map<Class<?>, Class<?>> implementations = new HashMap<>();
    private DependencyContainer() {}

    public static DependencyContainer getInstance() {
        if (instance == null) {
            instance = new DependencyContainer();
        }
        return instance;
    }

    private <T> void addInjection(Class<T> clazz, Class<? extends T> clazzImplementation) {
        implementations.put(clazz, clazzImplementation.asSubclass(clazz));
    }

    public <T> Class<? extends T> getInjection(Class<T> type) {
        if (!implementations.containsKey(type)) {
            createInjection(type);
        }
        Class<?> injection = implementations.get(type);

        if (injection == null)
            throw new RuntimeException("No Dependency Injection for type: " + type);

        return injection.asSubclass(type);
    }

    private <T> void createInjection(Class<T> type) {
        if (!type.isAnnotationPresent(Qualifier.class))
            return;

        Qualifier qualifier = type.getAnnotation(Qualifier.class);

        if (!type.isAssignableFrom(qualifier.value()))
            throw new RuntimeException("Invalid implementation for qualifier with type " + type);

        addInjection(type, qualifier.value().asSubclass(type));
    }
}
