package example.myPackage;

import example.annotations.Autowired;
import example.annotations.Qualifier;

public class Test {

    @Autowired(verbose = true)
    @Qualifier(MyLogger.class)
    private Logger logger;

    @Autowired(verbose = true)
    @Qualifier(ComponentLogger.class)
    private IComponentLogger logger2;

    public void log() {
        logger.log(" ");
        logger2.log(" ");
    }

}
