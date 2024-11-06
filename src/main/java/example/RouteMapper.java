package example;

import example.annotations.Controller;
import example.annotations.GET;
import example.annotations.POST;
import example.annotations.Path;
import example.framework.request.enums.MethodEnum;
import example.server.RestPair;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RouteMapper {
    private final Map<String, RestPair> routes = new HashMap<>();

    public <T> void registerRoutes(Class<T> controllerClazz, T object) {
        if (!controllerClazz.isAnnotationPresent(Controller.class)) {
            return;
        }
        if (!controllerClazz.isAnnotationPresent(Path.class)) {
            throw new RuntimeException("Controller must have a path annotation");
        }

        Path basePath = controllerClazz.getAnnotation(Path.class);

        for (Method method : controllerClazz.getDeclaredMethods()) {
            MethodEnum httpMethod = null;
            if (method.isAnnotationPresent(GET.class))
                httpMethod = MethodEnum.GET;
            else if (method.isAnnotationPresent(POST.class))
                httpMethod = MethodEnum.POST;
            else
                continue;


            StringBuilder routeKey = new StringBuilder(httpMethod.toString())
                    .append(" ")
                    .append(basePath.value());

            if (method.isAnnotationPresent(Path.class)) {
                Path methodPath = method.getAnnotation(Path.class);
                routeKey.append(methodPath.value());
            }

            routes.put(routeKey.toString(), new RestPair(object, method));
        }
    }



    public void sendRequest(String url)  {
        if (url.equals("GET /favicon.ico"))
            return;

        if(!routes.containsKey(url)) {
            throw new RuntimeException("No route found for " + url);
        }

        RestPair restPair = routes.get(url);
        Method method = restPair.getMethod();
        method.setAccessible(true);
        try {
            method.invoke(restPair.getController());
            method.setAccessible(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
