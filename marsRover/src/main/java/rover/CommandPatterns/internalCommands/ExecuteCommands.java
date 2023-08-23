package rover.CommandPatterns.internalCommands;


import common.Position;
import gridPlateau.GridPlateau;
import rover.CommandPatterns.userCommands.ChangeDirectionCommandLeft;
import rover.CommandPatterns.userCommands.ChangeDirectionCommandRight;
import rover.CommandPatterns.userCommands.MoveCommand;
import rover.CompassPoint;
import rover.RoverData;

/**
 * A command to execute userCommands such as @Link { userCommands.ChangeDirectionCommandLeft }
 */
public class ExecuteCommands implements CommandInterface<RoverData> {

    private final String commandString;
    private final Position position;
    private final CompassPoint compassPoint;
    private final GridPlateau gridPlateau;

    public ExecuteCommands(String commandString, CompassPoint compassPoint, Position position, GridPlateau gridPlateau) {
        this.commandString = commandString;
        this.compassPoint = compassPoint;
        this.position = position;
        this.gridPlateau = gridPlateau;
    }

    @Override
    public RoverData execute() {
        RoverData roverData = new RoverData(compassPoint, position, Boolean.FALSE);
        for (Character c : commandString.toCharArray()) {
            if (c == 'L' )
                roverData = (new ChangeDirectionCommandLeft()).execute(roverData);
            else if ( c =='R' )
                roverData = (new ChangeDirectionCommandRight()).execute(roverData);
            else if (c == 'M') {
                roverData = (new MoveCommand(gridPlateau)).execute(roverData);
                if ( roverData.getIsObstructed())
                    break;
            }
        }

         return roverData;
    }

}
