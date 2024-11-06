package example;

import example.annotations.Controller;
import example.myPackage.Test;
import example.server.Server;

import java.io.IOException;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        DiEngine diEngine = DiEngine.getInstance();
        RouteMapper routeMapper = new RouteMapper();

        Test test = diEngine.inject(Test.class);
        Test test2 = diEngine.inject(Test.class);

        test.log();
        test.log();
        test2.log();

        try {

            Set<Class<?>> controllers = AnnotationSearch.findAllClassesWithAnnotation(Controller.class);
            for (Class<?> controllerClazz : controllers) {
                createAndRegisterRoutes(diEngine, routeMapper, controllerClazz);
            }
            Server.run(routeMapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <T> void createAndRegisterRoutes(DiEngine diEngine, RouteMapper routeMapper, Class<T> controllerClazz) {
        T obj = diEngine.inject(controllerClazz);
        routeMapper.registerRoutes(controllerClazz, obj);
    }
}

