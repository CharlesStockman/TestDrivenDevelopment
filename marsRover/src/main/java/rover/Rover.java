package rover;

import gridPlateau.GridPlateau;
import common.Position;
import rover.commandPattern.ChangeDirectionCommand;
import rover.commandPattern.MoveCommand;
import rover.commandPattern.StartCommand;
import rover.commandPattern.ValidateCommand;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rover {

    private Position position;
    private CompassPoint compassPoint;
    private GridPlateau gridPlateau;


    public Rover(GridPlateau gridPlateau) {
        this();
        this.gridPlateau = gridPlateau;

    }

    public Rover() {
        this.position = new Position(0, 0);
        this.compassPoint = CompassPoint.N;
    }

    public String displayCoordinatesAndDirection() {
        return String.format("%d:%d:%s", position.getX(), position.getY(), compassPoint);
    }

    public String displayObstructedCoordinatesAndDirection(CompassPoint compassPoint, Position position) {
        return String.format("O:%d:%d:%s", position.getX(), position.getY(), compassPoint);
    }

    public String move(String input) {

        Boolean isObstructive = false;
        Position previousPosition = null;

        if (input == null)
            throw new NullPointerException("The input to the move function is null");

        (new StartCommand(Character.MIN_VALUE, CompassPoint.N, new Position(0, 0))).execute();
        input = (new ValidateCommand(input)).execute();
         
        for (Character c : input.toCharArray()) {
            if (c == 'L' || c == 'R')
                this.compassPoint = (new ChangeDirectionCommand(c, compassPoint, position)).execute();
            else if (c == 'M') {
                previousPosition = position;
                this.position = (new MoveCommand(c, compassPoint, position)).execute();
                if (gridPlateau != null && gridPlateau.isCellObstructed(position)) {
                    isObstructive = true;
                    break;
                }
            }
        }

        String result = ( isObstructive ) ? displayObstructedCoordinatesAndDirection(compassPoint, previousPosition) : displayCoordinatesAndDirection();
        return result;

    }
}
