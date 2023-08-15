package rover;

import gridPlateau.GridPlateau;
import common.Position;
import rover.commandPattern.ChangeDirectionCommand;
import rover.commandPattern.MoveCommand;

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
        return String.format("%d:%d:%s", position.getX() , position.getY() , compassPoint);
    }

    public String move(String input) {

        String result = null;
        String previous_position = null;

        if ( input == null )
            throw new NullPointerException("The input to the move function is null");
        else if ( input.length() == 0 )
             result = displayCoordinatesAndDirection();

        for ( Character c : input.toCharArray()) {

            previous_position = displayCoordinatesAndDirection();

            if ( c == 'L' || c == 'R')
                this.compassPoint = (new ChangeDirectionCommand(c, compassPoint)).execute();
            else if ( c == 'M')
                this.position = ( new MoveCommand(c, compassPoint, position)).execute();

            if ( gridPlateau != null && gridPlateau.isCellObstructed(position)) {
                result =  "O:" + previous_position;
                break;
            }
        }

        if ( result == null ) {
            result = displayCoordinatesAndDirection();
        }

        return result;
    }


}
