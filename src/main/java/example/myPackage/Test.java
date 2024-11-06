package example.myPackage;

import example.annotations.Autowired;

public class Test {

    @Autowired(verbose = true)
    private Logger logger;

    @Autowired(verbose = true)
    private IComponentLogger logger2;

    public void log() {
        logger.log(" ");
        logger2.log(" ");
    }

}
