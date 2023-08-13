package rover;

import gridPlateau.GridPlateau;
import common.Position;

public class Rover {

    private Position position;
    private Enum<CompassPoint> compassPoint;
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

            if (c == 'L' && compassPoint == CompassPoint.N)
                this.compassPoint = CompassPoint.W;
            else if (c == 'L' && compassPoint == CompassPoint.W)
                this.compassPoint = CompassPoint.S;
            else if (c == 'L' && compassPoint == CompassPoint.S)
                this.compassPoint = CompassPoint.E;
            else if (c == 'L' && compassPoint == CompassPoint.E)
                this.compassPoint = CompassPoint.N;
            else if (c == 'R' && compassPoint == CompassPoint.N)
                this.compassPoint = CompassPoint.E;
            else if (c == 'R' && compassPoint == CompassPoint.E)
                this.compassPoint = CompassPoint.S;
            else if (c == 'R' && compassPoint == CompassPoint.S)
                this.compassPoint = CompassPoint.W;
            else if (c == 'R' && compassPoint == CompassPoint.W)
                this.compassPoint = CompassPoint.N;

            if (c == 'M' && compassPoint == CompassPoint.N) {
                position = position.moveVerticalUp();
                position = position.wrap(10,10);
            } else if (c == 'M' && compassPoint == CompassPoint.E) {
                position = position.moveHorizontalRight();
                position = position.wrap(10,10);
            } else if (c == 'M' && compassPoint == CompassPoint.S) {
                position = position.moveVerticalDown();
                position = position.wrap(10,10);
            } else if (c == 'M' && compassPoint == CompassPoint.W) {
                position = position.moveHorizontalLeft();
                position = position.wrap(10,10);
            }

            if ( gridPlateau != null && gridPlateau.isCellObstructed(position)) {
                result =  "O:" + previous_position;
                System.out.println( "Currently in break code with " + previous_position);
                break;
            }
        }

        if ( result == null ) {
            result = displayCoordinatesAndDirection();
        }

        return result;
    }


}
