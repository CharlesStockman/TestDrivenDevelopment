package service;

import dataLayer.Position;
import dataLayer.Terrian;
import org.example.CompassPoint;

import java.util.Stack;

public class Rover {

    Integer x;
    Integer y;
    Enum<CompassPoint> compassPoint;
    String[][] grid;

    private String previous_position;

    public Rover(String[][] grid) {
        this();
        this.grid = grid;
    }

    public Rover() {
        this.x = 0;
        this.y = 0;
        this.compassPoint = CompassPoint.N;
        this.grid = null;
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

            System.out.println("The next position is " + displayCoordinatesAndDirection());

            if ( grid != null && grid[x][y] == Terrian.Obstructed.name()) {
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
