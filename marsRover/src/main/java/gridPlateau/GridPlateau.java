package gridPlateau;

import common.Position;
import common.Terrian;

import java.security.InvalidParameterException;
import java.util.*;

public class GridPlateau {

    public GridPlateau() {}

    private String[][] grid;

    public void initialize(int x, int y) {

        if ( x < 0 )
            throw new InvalidParameterException("create_grid() x parameter cannot be less then 0");

        if ( y < 0 )
            throw new InvalidParameterException("create_grid() y parameter cannot be less then 0");

        grid = new String[x][y];
        for ( int index = 0; index < x; index++ ) {
            Arrays.fill(grid[index], Terrian.Normal.name());
        }
    }

    public void initialize(int x, int y, float percent_obstructed) {
        int  obstructured_max = (int)(( x * y ) * percent_obstructed );
        initialize(x,y);

        if ( percent_obstructed == 100 ) {
            throw new InvalidParameterException("Percentage of Obstacles can only be 99% or less.  One space is needed for the Rover");
        }

        for ( int index = 0; index < 10; index++ )  {
            System.out.println(Arrays.toString(grid[index]));
        }

        Random random = new Random();
        for ( int index = 0; index < obstructured_max; index++) {
            boolean result = false;
            do {
                int next_x = random.nextInt(x);
                int next_y = random.nextInt(y);

                if (Objects.equals(grid[next_x][next_y], Terrian.Normal.name())) {
                    grid[next_x][next_y] = Terrian.Obstructed.name();
                    result = true;
                }
            } while (!result);
        }
    }

    public void initializeCustom(int x, int y , List<Position> obstaclesPositions ) {

        if ( obstaclesPositions == null ) {
            throw new NullPointerException("ObstaclePositions parameters must contain zero or more positions instances");
        }

        initialize(x,y);
        obstaclesPositions.forEach( (position) ->
            { grid[position.getX()][position.getY()] = Terrian.Obstructed.name(); });

    }

    public Boolean isCellObstructed(Position position ) {
        return Objects.equals(grid[position.getX()][position.getY()], Terrian.Obstructed.name());
    }

    public Integer getLength() {
        return ( grid == null ) ?  0 : grid.length;
    }

    public Integer getWidth() {
        return ( grid == null ) ? 0 : grid[0].length;
    }

    public void setTile(Position position, String terrian ) {
        grid[position.getX()][position.getY()] = terrian;
    }


}
