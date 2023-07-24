package util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * A class to create a PrintStream that can be substituted for STDOUT and the characters sent to the stream can be
 * captured by the text program.
 */
public class CaptureOutput {
    private PrintStream previousStdOut;
    private PrintStream currentStdOut;
    private ByteArrayOutputStream outputStream;

    public void start() {
        previousStdOut = System.out;
        outputStream = new ByteArrayOutputStream();
        currentStdOut = new PrintStream(outputStream);

        System.setOut(currentStdOut);
    }

    public void end() {
        System.setOut(previousStdOut);
        outputStream = null;
        currentStdOut.close();
    }

    public String getText() {
        return outputStream.toString();
    }
}
