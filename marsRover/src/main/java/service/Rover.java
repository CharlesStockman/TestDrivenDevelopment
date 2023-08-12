package service;

import dataLayer.GridPlateau;
import dataLayer.Position;
import dataLayer.Terrian;
import org.example.CompassPoint;

import java.util.Stack;

public class Rover {

    private Integer x;
    private Integer y;
    private Enum<CompassPoint> compassPoint;
    private GridPlateau gridPlateau;



    private String previous_position;

    public Rover(GridPlateau gridPlateau) {
        this();
        this.gridPlateau = gridPlateau;

    }

    public Rover() {
        this.x = 0;
        this.y = 0;
        this.compassPoint = CompassPoint.N;
    }

    public String displayCoordinatesAndDirection() {
        return String.format("%d:%d:%s", x , y , compassPoint);
    }

    public String move(String input) {
        String result = null;

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
                Position position = new Position(x, y);
                position = position.moveVerticalUp();
                position = position.wrap(10,10);
                x = position.getX();
                y = position.getY();
            } else if (c == 'M' && compassPoint == CompassPoint.E) {

                Position position = new Position(x, y);
                position = position.moveHorizontalRight();
                position = position.wrap(10,10);
                x = position.getX();
                y = position.getY();
            } else if (c == 'M' && compassPoint == CompassPoint.S) {
                Position position = new Position(x, y);
                position = position.moveVerticalDown();
                position = position.wrap(10,10);
                x = position.getX();
                y = position.getY();
            } else if (c == 'M' && compassPoint == CompassPoint.W) {
                Position position = new Position(x, y);
                position = position.moveHorizontalLeft();
                position = position.wrap(10,10);
                x = position.getX();
                y = position.getY();
            }

            Position position = new Position(x,y);
            if ( gridPlateau != null && gridPlateau.isCellObstructed(position)) {
                result =  "O:" +previous_position;
                break;
            }
        }

        if ( result == null ) {
            result = displayCoordinatesAndDirection();
        }

        return result;


    }


}
