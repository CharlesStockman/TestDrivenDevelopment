package utilities;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.Console;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.logging.*;

public class MyFormatterTest {

    /**********************************************************************************************
     * Experiment 1 : Decided to use the @Log, but each @Log would need to have a custom Formatter
     * This would result in configuring the same code over and over again.
     **********************************************************************************************/

    /**************************************************************************************************
     * Experiment 2: Setup a Singleton Logger
     **************************************************************************************************/
    @Test
    public void getSingletonWithNoConsoleLogger() {
        Logger log1 = (new MyLogger(null)).getLogger();
        Logger log2 = (new MyLogger(null)).getLogger();
        Assertions.assertEquals(true, log1 == log2 );
    }

    @Test
    public void getSingletonWithCustomConsoleLogger() {

        MyLogger myLogger = new MyLogger(createConsoleHandlerWithFormatter(Level.INFO));
        Logger log1 = myLogger.getLogger();
        Logger log2 = myLogger.getLogger();

        Assertions.assertEquals(true, log1.getHandlers()[0].getFormatter() == log2.getHandlers()[0].getFormatter());


    }

    // This test only worked when it is executed as a single test.
    @Test
    public void printInfoSingletonWithCustomConsoleLogger() throws IOException {

        CaptureOutput captureOutput = new CaptureOutput();
        captureOutput.start(System.err);

        Logger log1 = createLoggerWithConsoleHandler(createConsoleHandlerWithFormatter(Level.INFO), Level.INFO);
        log1.info("Hello Chuck");
        Assertions.assertEquals("utilities.MyFormatterTest.printInfoSingletonWithCustomConsoleLogger : Hello Chuck", captureOutput.getText());
        captureOutput.end();
    }

    @Test
    public void printFineSingletonWithCustomConsoleLogger() throws IOException {
        CaptureOutput captureOutput = new CaptureOutput();
        captureOutput.start(System.err);

        Logger log1 = createLoggerWithConsoleHandler(createConsoleHandlerWithFormatter(Level.INFO), Level.INFO);
        log1.info("Hello Chuck");
        Assertions.assertEquals("utilities.MyFormatterTest.printFineSingletonWithCustomConsoleLogger : Hello Chuck", captureOutput.getText());
        captureOutput.end();
    }

    private ConsoleHandler createConsoleHandlerWithFormatter(Level loggingLevel) {
        Formatter formatter = new MyFormatter();
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(formatter);
        consoleHandler.setLevel(loggingLevel);

        return consoleHandler;
    }

    private Logger createLoggerWithConsoleHandler(ConsoleHandler consoleHandler, Level loggingLevel) {
        MyLogger myLogger = new MyLogger(createConsoleHandlerWithFormatter(Level.FINE));
        Logger log1 = myLogger.getLogger();
        log1.setUseParentHandlers(false);
        log1.setLevel(Level.FINE);

        return log1;
    }


}
