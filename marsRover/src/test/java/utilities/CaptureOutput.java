package utilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * A class to create a PrintStream that can be substituted for STDOUT and the characters sent to the stream can be
 * captured by the text program.
 */
public class CaptureOutput {
    private PrintStream originalStream;
    private PrintStream currentStdOut;
    private ByteArrayOutputStream outputStream;

    public void start(PrintStream substitutedStream) {
        originalStream = substitutedStream;
        outputStream = new ByteArrayOutputStream();
        currentStdOut = new PrintStream(outputStream);

        System.setErr(currentStdOut);
    }

    public void end() throws IOException {
        System.setOut(originalStream);
        outputStream.close();
        currentStdOut.close();
    }

    public String getText() {
        return outputStream.toString();
    }
}
