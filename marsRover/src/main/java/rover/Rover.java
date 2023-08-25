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

        RoverData initialRoverData = new RoverData(compassPoint, position, false, gridPlateau);

        if (commandString == null)
            throw new NullPointerException("The input to the move function is null");

        (new StartCommand(Character.MIN_VALUE, initialRoverData)).execute();
        commandString = (new ValidateCommand(commandString)).execute();
        RoverData roverData = (new ExecuteCommands(commandString, initialRoverData)).execute();

        return displayCoordinatesAndDirection(roverData.getCompassPoint(), roverData.getPosition(), roverData.getIsObstructed());
    }
}
