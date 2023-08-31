package utilities;

import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 * A Logger to load all notes from the gridPlateau, rover, utilities package
 */
public class MyLogger {

    private Logger logger;

    private static ConsoleHandler consoleHandler;

    public MyLogger(ConsoleHandler inConsoleHandler) {
            if ( consoleHandler == null )
                consoleHandler = inConsoleHandler;
    }

    public Logger getLogger() {
        if ( logger == null ) {
            logger= Logger.getLogger("marsRover");
            if ( consoleHandler != null && logger.getHandlers().length < 1) logger.addHandler(consoleHandler);
        } else {
            System.out.println("Retrieving the logger already created");
        }
        return logger;
    }
}
