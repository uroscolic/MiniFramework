package example.myPackage;

import example.annotations.Qualifier;

@Qualifier(value = ComponentLogger.class)
public interface IComponentLogger {

    void log(String message);
}
