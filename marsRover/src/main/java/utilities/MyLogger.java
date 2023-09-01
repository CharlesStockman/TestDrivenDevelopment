package utilities;

import java.util.logging.*;

/**
 * A Logger to load all notes from the gridPlateau, rover, utilities package
 */
public class MyLogger {

    private Logger logger;

    public Logger getLogger(Level loggingLevel) {
        if ( logger == null ) {
            logger= Logger.getLogger("marsRover");
            logger.setUseParentHandlers(false);
            logger.setLevel(loggingLevel);
            if ( logger.getHandlers().length == 0  )
                logger.addHandler(createConsoleHandlerWithFormatter(Level.ALL));
        }
        return logger;
    }
    public static ConsoleHandler createConsoleHandlerWithFormatter(Level loggingLevel) {
        Formatter formatter = new MyFormatter();
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(formatter);
        consoleHandler.setLevel(loggingLevel);

        return consoleHandler;
    }
}
