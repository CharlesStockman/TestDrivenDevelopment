package rover.CommandPatterns.internalCommands;

import common.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rover.CompassPoint;
import rover.RoverData;

public class ExecuteCommandsTest {

    @Test
    public void verifySignatureOfExecuteCommand() {
        rover.CommandPatterns.userCommands.ExecuteCommands executeCommands = new rover.CommandPatterns.userCommands.ExecuteCommands();
        RoverData roverData = executeCommands.execute("LLLLM", CompassPoint.N, new Position(0,0 ));
        Assertions.assertEquals(new Position(0,1), roverData.getPosition());
        Assertions.assertEquals(CompassPoint.N, roverData.getCompassPoint());
        Assertions.assertEquals(Boolean.FALSE, roverData.getIsObstructed());
    }




}
