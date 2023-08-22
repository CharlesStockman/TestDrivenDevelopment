package rover;

import gridPlateau.GridPlateau;
import common.Position;
import rover.CommandPatterns.userCommands.ChangeDirectionCommandLeft;
import rover.CommandPatterns.userCommands.ChangeDirectionCommandRight;
import rover.CommandPatterns.userCommands.MoveCommand;
import rover.CommandPatterns.internalCommands.StartCommand;
import rover.CommandPatterns.internalCommands.ValidateCommand;

import static java.lang.Boolean.TRUE;

public class Rover {

    private GridPlateau gridPlateau;


    public Rover(GridPlateau gridPlateau) {
        this.gridPlateau = gridPlateau;
    }

    public Rover() {}

    public String displayCoordinatesAndDirection(CompassPoint compassPoint, Position position, Boolean isObstructured) {
        return String.format("%s%d:%d:%s", ((isObstructured == TRUE ) ? "O:" : ""),  position.getX(), position.getY(), compassPoint);
    }

    public String move(String input, CompassPoint compassPoint, Position position) {

        if (input == null)
            throw new NullPointerException("The input to the move function is null");

        (new StartCommand(Character.MIN_VALUE, CompassPoint.N, new Position(0, 0))).execute();
        input = (new ValidateCommand(input)).execute();

        RoverData roverData = new RoverData(compassPoint, position, Boolean.FALSE);
        for (Character c : input.toCharArray()) {
            if (c == 'L' ) {
                roverData = (new ChangeDirectionCommandLeft()).execute(roverData.getCompassPoint(), roverData.getPosition());
            }
            else if ( c =='R' ) {
                roverData = (new ChangeDirectionCommandRight()).execute(roverData.getCompassPoint(), roverData.getPosition());
            }
            else if (c == 'M') {
                roverData = (new MoveCommand(gridPlateau)).execute(roverData.getCompassPoint(), roverData.getPosition());
                if ( roverData.getIsObstructed())
                    break;
            }
        }

        return displayCoordinatesAndDirection(roverData.getCompassPoint(), roverData.getPosition(), roverData.getIsObstructed());
    }
}
