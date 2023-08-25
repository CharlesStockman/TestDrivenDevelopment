package rover;

import gridPlateau.GridPlateau;
import common.Position;
import rover.CommandPatterns.internalCommands.ExecuteCommands;
import rover.CommandPatterns.internalCommands.StartCommand;
import rover.CommandPatterns.internalCommands.ValidateCommand;

import static java.lang.Boolean.TRUE;

public class Rover {

    private GridPlateau gridPlateau;


    public Rover(GridPlateau gridPlateau) {
        this.gridPlateau = gridPlateau;
    }


    public String displayCoordinatesAndDirection(CompassPoint compassPoint, Position position, Boolean isObstructured) {
        return String.format("%s%d:%d:%s", ((isObstructured == TRUE ) ? "O:" : ""),  position.getX(), position.getY(), compassPoint);
    }

    public String executeInstructionsForRover(String commandString, CompassPoint compassPoint, Position position) {

        if (commandString == null)
            throw new NullPointerException("The input to the move function is null");

        (new StartCommand(Character.MIN_VALUE, CompassPoint.N, new Position(0, 0))).execute();
        commandString = (new ValidateCommand(commandString)).execute();
        RoverData roverData = (new ExecuteCommands(commandString, compassPoint, position, gridPlateau)).execute();

        return displayCoordinatesAndDirection(roverData.getCompassPoint(), roverData.getPosition(), roverData.getIsObstructed());
    }
}
