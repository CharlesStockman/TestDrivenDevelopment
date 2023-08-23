package rover.CommandPatterns.internalCommands;


import common.Position;
import gridPlateau.GridPlateau;
import rover.CommandPatterns.TriFunction;
import rover.CommandPatterns.internalCommands.CommandInterface;
import rover.CommandPatterns.userCommands.ChangeDirectionCommandLeft;
import rover.CommandPatterns.userCommands.ChangeDirectionCommandRight;
import rover.CommandPatterns.userCommands.MoveCommand;
import rover.CompassPoint;
import rover.RoverData;

import java.util.ArrayList;
import java.util.List;

public class ExecuteCommands implements CommandInterface<RoverData> {

    private String commandString;
    private Position position;
    private CompassPoint compassPoint;
    private GridPlateau gridPlateau;

    public ExecuteCommands(String commandString, CompassPoint compassPoint, Position position, GridPlateau gridPlateau) {
        this.commandString = commandString;
        this.compassPoint = compassPoint;
        this.position = position;
        this.gridPlateau = gridPlateau;
    }

    @Override
    public RoverData execute() {
         List<TriFunction<String, CompassPoint, Position, RoverData>> executeCommands;

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
