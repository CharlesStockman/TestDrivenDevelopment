package rover.CommandPatterns.internalCommands;

import gridPlateau.GridPlateau;
import rover.GridPlateauTestFactory;
import utilities.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rover.CommandPatterns.userCommands.ChangeDirectionCommandLeft;
import rover.CommandPatterns.userCommands.ChangeDirectionCommandRight;
import rover.CommandPatterns.userCommands.MoveCommand;
import rover.CompassPoint;
import rover.RoverData;

import java.util.ArrayList;
import java.util.function.Function;

public class ExecuteCommandsTest {

    @Test
    public void verifySignatureOfExecuteCommand() {
        GridPlateau gridPlateau = ( new GridPlateauTestFactory()).create_standard_grid_with_no_obstructions();
        RoverData roverData = new RoverData(CompassPoint.N, new Position(0,0), false, gridPlateau);
        ExecuteCommands executeCommands = new ExecuteCommands("M", roverData);
        RoverData roverDataFinal = executeCommands.execute();

        Assertions.assertEquals(new Position(0,1), roverDataFinal.getPosition());
        Assertions.assertEquals(CompassPoint.N, roverDataFinal.getCompassPoint());
        Assertions.assertEquals(Boolean.FALSE, roverDataFinal.getIsObstructed());
    }

    @Test
    public void executeCommands() {

        ChangeDirectionCommandLeft  changeDirectionCommandLeft = new ChangeDirectionCommandLeft();
        ChangeDirectionCommandRight changeDirectionCommandRight = new ChangeDirectionCommandRight();
        MoveCommand moveCommand = new MoveCommand();

        ArrayList<Function<RoverData, RoverData>> functions = new ArrayList<>();
        functions.add(changeDirectionCommandLeft::execute);
        functions.add(changeDirectionCommandRight::execute);
        functions.add(changeDirectionCommandLeft::execute);
        functions.add(changeDirectionCommandRight::execute);
        functions.add(moveCommand::execute);

        GridPlateau gridPlateau = ( new GridPlateauTestFactory()).create_standard_grid_with_no_obstructions();
        RoverData roverData = new RoverData(CompassPoint.N, new Position(0,0), false, gridPlateau);

        ExecuteCommands executeCommands = new ExecuteCommands( "LLLLM", roverData);
        roverData = executeCommands.execute();

        Assertions.assertEquals(new Position(0,1), roverData.getPosition());
        Assertions.assertEquals(CompassPoint.N, roverData.getCompassPoint());
        Assertions.assertEquals(Boolean.FALSE, roverData.getIsObstructed());
    }
}
