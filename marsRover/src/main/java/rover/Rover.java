package rover;

import gridPlateau.GridPlateau;
import common.Position;
import rover.CommandPattern.commands.commands.ChangeDirectionCommand;
import rover.CommandPattern.commands.commands.MoveCommand;
import rover.CommandPattern.commands.internalCommands.StartCommand;
import rover.CommandPattern.commands.internalCommands.ValidateCommand;

import javax.print.attribute.standard.NumberOfInterveningJobs;

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

        Position previousPosition = null;

        if (input == null)
            throw new NullPointerException("The input to the move function is null");

        (new StartCommand(Character.MIN_VALUE, CompassPoint.N, new Position(0, 0))).execute();
        input = (new ValidateCommand(input)).execute();

        MoveCommand.PositionData positionData = null;
        for (Character c : input.toCharArray()) {
            if (c == 'L' || c == 'R')
                this.compassPoint = (new ChangeDirectionCommand()).execute(c, compassPoint, position);
            else if (c == 'M') {
                positionData = (new MoveCommand(gridPlateau)).execute(c, compassPoint, position);
                if ( positionData.isObstructed())
                    break;
                else
                    this.position = positionData.getPosition();
            }
        }

        String result = (positionData != null && positionData.isObstructed() ) ? displayObstructedCoordinatesAndDirection(compassPoint, position) : displayCoordinatesAndDirection();
        return result;

    }
}
