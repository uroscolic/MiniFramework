package example.myPackage;


import example.annotations.Service;

@Service
public class MyLogger implements Logger{
    int counter = 0;

    @Override
    public void log(String message) {
        System.out.println("MyLogger: " + message + " " + ++counter);
    }
}
