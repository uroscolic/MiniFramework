package example.myPackage;

import example.annotations.Qualifier;

@Qualifier(value = MyLogger.class)
public interface Logger {

    void log(String message);
}
