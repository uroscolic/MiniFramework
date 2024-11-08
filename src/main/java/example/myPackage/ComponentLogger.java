package example.myPackage;

import example.annotations.Component;

@Component
public class ComponentLogger implements IComponentLogger {

    int counter = 0;
    @Override
    public void log(String message) {
        System.out.println("ComponentLogger: " + message + " " + ++counter);
    }
}
