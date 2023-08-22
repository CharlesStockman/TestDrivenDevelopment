package rover.CommandPatterns.internalCommands;


import common.Position;
import rover.CommandPatterns.TriFunction;
import rover.CommandPatterns.internalCommands.CommandInterface;
import rover.CompassPoint;
import rover.RoverData;

import java.util.ArrayList;
import java.util.List;

public class ExecuteCommands implements CommandInterface<RoverData> {

    private String commandString;
    private Position position;
    private CompassPoint compassPoint;

    public ExecuteCommands(String commandString, CompassPoint compassPoint, Position position ) {
        this.commandString = commandString;
        this.compassPoint = compassPoint;
        this.position = position;
    }

    @Override
    public RoverData execute() {
         List<TriFunction<String, CompassPoint, Position, RoverData>> executeCommands;

         return new RoverData(CompassPoint.N, new Position(0,1), Boolean.FALSE);
    }

}
