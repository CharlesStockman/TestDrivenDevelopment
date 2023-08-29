package rover;

import gridPlateau.GridPlateau;
import utilities.Position;
import lombok.extern.java.Log;
import rover.CommandPatterns.History;
import rover.CommandPatterns.internalCommands.ExecuteCommands;
import rover.CommandPatterns.internalCommands.StartCommand;
import rover.CommandPatterns.internalCommands.ValidateCommand;

import java.util.List;

import static java.lang.Boolean.TRUE;

@Log
public class Rover {

    private GridPlateau gridPlateau;


    public Rover(GridPlateau gridPlateau) {
        this.gridPlateau = gridPlateau;
    }


    public String displayCoordinatesAndDirection(CompassPoint compassPoint, Position position, Boolean isObstructured) {
        return String.format("%s%d:%d:%s", ((isObstructured == TRUE ) ? "O:" : ""),  position.getX(), position.getY(), compassPoint);
    }

    public String executeInstructionsForRover(String commandString, CompassPoint compassPoint, Position position) {

        log.info("Entered ...");
        log.info("original rover command string is" + commandString);

        RoverData initialRoverData = new RoverData(compassPoint, position, false, gridPlateau);

        if (commandString == null)
            throw new NullPointerException("The input to the move function is null");

        (new StartCommand(Character.MIN_VALUE, initialRoverData)).execute();
        commandString = (new ValidateCommand(commandString)).execute();
        RoverData roverData = (new ExecuteCommands(commandString, initialRoverData)).execute();

        List<History.Event> events = History.getInstance().getHistory();
        events.forEach( (History.Event event) -> log.info(event.toString()));

        log.info("Exited: -----------------------------------");

        return displayCoordinatesAndDirection(roverData.getCompassPoint(), roverData.getPosition(), roverData.getIsObstructed());

    }
}
