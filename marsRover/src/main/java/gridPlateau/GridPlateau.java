package gridPlateau;

import common.Position;
import common.Terrain;

import java.security.InvalidParameterException;
import java.util.*;

/**
 * Represent part of martian plateau as a 2 Dimension Array where each cell has a @Link { gridPlateau.GridPlateau }
 */
public class GridPlateau {

    public GridPlateau() {}

    private String[][] grid;

    /**
     * Handles basic creating a grid to allocate the 2D array and setting all cells to T
     * @param maximum_x  The maximum maximum_x-coordinate
     * @param maximum_y  The maximum maximum_y-coordinate
     */
    public void initialize(int maximum_x, int maximum_y) {

        if ( maximum_x < 0 )
            throw new InvalidParameterException("create_grid() maximum_x parameter cannot be less then 0");

        if ( maximum_y < 0 )
            throw new InvalidParameterException("create_grid() maximum_y parameter cannot be less then 0");

        grid = new String[maximum_x][maximum_y];
        for ( int index = 0; index < maximum_x; index++ ) {
            Arrays.fill(grid[index], Terrain.Normal.name());
        }
    }

    /**
     * Handles create a terrain where a certain percent of the cells are Terrain and a certain percentage are Obstructed
     * @param maximum_x  The maximum maximum_x-coordinate
     * @param maximum_y  The maximum maximum_y-coordinate
     */
    public void initialize(int maximum_x, int maximum_y, float percent_obstructed) {
        Random random = new Random();

        int  obstructedMax = (int)(( maximum_x * maximum_y ) * percent_obstructed );
        if ( percent_obstructed == 100 ) {
            throw new InvalidParameterException("Percentage of Obstacles can only be 99% or less.  One space is needed for the Rover");
        }

        initialize(maximum_x,maximum_y);

        for ( int index = 0; index < obstructedMax; index++) {
            // Continue to find a cell that is currently Terrain to be changed to Obstructive
            // Handles the issue where you select a cello that is already obstructive
            boolean result = false;
            do {
                int next_x = random.nextInt(maximum_x);
                int next_y = random.nextInt(maximum_y);

                if (Objects.equals(grid[next_x][next_y], Terrain.Normal.name())) {
                    grid[next_x][next_y] = Terrain.Obstructed.name();
                    result = true;
                }
            } while (!result);
        }
    }

    /**
     * Handles create a terrain where a certain percent of the cells are Terrain and a certain percentage are Obstructed
     * @param maximum_x  The maximum maximum_x-coordinate
     * @param maximum_y  The maximum maximum_y-coordinate
     */
    public void initialize(int maximum_x, int maximum_y , List<Position> obstaclesPositions ) {

        if ( obstaclesPositions == null ) {
            throw new NullPointerException("ObstaclePositions parameters must contain zero or more positions instances");
        }

        initialize(maximum_x,maximum_y);
        obstaclesPositions.forEach( (position) ->
            { grid[position.getX()][position.getY()] = Terrain.Obstructed.name(); });
    }

    public Boolean isCellObstructed(Position position ) {
        return Objects.equals(grid[position.getX()][position.getY()], Terrain.Obstructed.name());
    }

    public Integer getLength() {
        return ( grid == null ) ?  0 : grid.length;
    }

    public Integer getWidth() {
        return ( grid == null ) ? 0 : grid[0].length;
    }

    public void setTile(Position position, String terrain ) {
        grid[position.getX()][position.getY()] = terrain;
    }




}
