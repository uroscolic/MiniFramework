package example.myPackage;

import example.annotations.Qualifier;
import example.myPackage.controllers.ComponentLogger;

@Qualifier(value = ComponentLogger.class)
public interface IComponentLogger {

    void log(String message);
}
