package rover;

import gridPlateau.GridPlateau;
import utilities.MyLogger;
import utilities.Position;
import rover.CommandPatterns.History;
import rover.CommandPatterns.internalCommands.ExecuteCommands;
import rover.CommandPatterns.internalCommands.StartCommand;
import rover.CommandPatterns.internalCommands.ValidateCommand;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Boolean.TRUE;

public class Rover {

    private final GridPlateau gridPlateau;

    private final Logger logger;

    public Rover(GridPlateau gridPlateau) {
        this.gridPlateau = gridPlateau;
        logger = (new MyLogger()).getLogger(Level.ALL);
    }

    public String displayCoordinatesAndDirection(CompassPoint compassPoint, Position position, Boolean isObstructed) {
        return String.format("%s%d:%d:%s", ((isObstructed == TRUE ) ? "O:" : ""),  position.getX(), position.getY(), compassPoint);
    }

    public String executeInstructionsForRover(String commandString, CompassPoint compassPoint, Position position) {

        logger.setLevel(Level.FINER);

        logger.log(Level.INFO, "Begin executeInstructionsForRover\n");
        logger.fine("original rover command string            -- " + commandString + "\n");

        RoverData initialRoverData = new RoverData(compassPoint, position, false, gridPlateau);

        if (commandString == null)
            throw new NullPointerException("The input to the move function is null");

        (new StartCommand(Character.MIN_VALUE, initialRoverData)).execute();
        commandString = (new ValidateCommand(commandString)).execute();
        RoverData roverData = (new ExecuteCommands(commandString, initialRoverData)).execute();
        logger.fine("validated and fixed rover command string -- " + commandString + "\n");

        logger.fine("Commands Executed...\n");
        List<History.Event> events = History.getInstance().getHistory();
        events.forEach( (History.Event event) -> logger.info("\t" + event.toString() + "\n"));

        String result =  displayCoordinatesAndDirection(roverData.getCompassPoint(), roverData.getPosition(), roverData.getIsObstructed());
        logger.info("Final Compass Point and Grid Coordinates -- " + result +"\n");
        logger.info("Exited: -----------------------------------\n");

        return result;

    }
}
