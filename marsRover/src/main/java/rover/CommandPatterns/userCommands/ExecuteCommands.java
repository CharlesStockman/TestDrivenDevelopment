package rover.CommandPatterns.userCommands;


import common.Position;
import rover.CommandPatterns.TriFunction;
import rover.CompassPoint;
import rover.RoverData;

import java.util.List;

public class ExecuteCommands implements CommandInterface<RoverData> {


    @Override
    public RoverData execute(String command, CompassPoint compassPoint, Position position) {

         List<TriFunction<String, CompassPoint, Position, RoverData>> executeCommands;

         return new RoverData(compassPoint.N, new Position(0,1), Boolean.FALSE);
    }
}
