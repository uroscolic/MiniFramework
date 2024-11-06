package example.myPackage.controllers;

import example.annotations.Component;
import example.myPackage.IComponentLogger;

@Component
public class ComponentLogger implements IComponentLogger {

    int counter = 0;
    @Override
    public void log(String message) {
        System.out.println("ComponentLogger: " + message + " " + ++counter);
    }
}
