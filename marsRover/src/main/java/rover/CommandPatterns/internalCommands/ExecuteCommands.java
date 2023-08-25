package rover.CommandPatterns.internalCommands;


import common.Position;
import gridPlateau.GridPlateau;
import rover.CommandPatterns.userCommands.ChangeDirectionCommandLeft;
import rover.CommandPatterns.userCommands.ChangeDirectionCommandRight;
import rover.CommandPatterns.userCommands.MoveCommand;
import rover.CompassPoint;
import rover.RoverData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * A command to execute userCommands such as @Link { userCommands.ChangeDirectionCommandLeft }
 */
public class ExecuteCommands implements CommandInterface<RoverData> {

    private final String commandString;
    private final Position position;
    private final CompassPoint compassPoint;
    private final GridPlateau gridPlateau;

    private static final Map<String, Function<RoverData, RoverData>> functions = new HashMap<>();

    static {
        functions.put("L", (new ChangeDirectionCommandLeft()::execute));
        functions.put("R", (new ChangeDirectionCommandRight()::execute));
        functions.put("M", (new MoveCommand()::execute));
    }

    public ExecuteCommands(String commandString, RoverData initialRoverData) {
        this.commandString = commandString;
        this.compassPoint = initialRoverData.getCompassPoint();
        this.position = initialRoverData.getPosition();
        this.gridPlateau = initialRoverData.getGridPlateau();
    }

    @Override
    public RoverData execute() {
        RoverData roverData = new RoverData(compassPoint, position, Boolean.FALSE, gridPlateau);
        List<Function<RoverData, RoverData>> orderedCommands = new ArrayList<>();

        for ( Character command : commandString.toCharArray() ) {
            orderedCommands.add(functions.get(command.toString()));
        }

        for (Function<RoverData, RoverData> command : orderedCommands ) {
            roverData = command.apply(roverData);
        }

         return roverData;
    }

}
