package gridPlateau;

import common.Position;
import common.Terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Create a factory capable of creating GridPlateau(s)
 */
public class GridPlateauFactory {

    private String[][] grid;

    /**
     * Handles basic creating a grid to allocate the 2D array and setting all cells to T
     * @param maximum_x  The maximum maximum_x-coordinate
     * @param maximum_y  The maximum maximum_y-coordinate
     */
    public GridPlateau initialize(int maximum_x, int maximum_y) {

        if ( maximum_x < 0 )
            throw new InvalidParameterException("create_grid() maximum_x parameter cannot be less then 0");

        if ( maximum_y < 0 )
            throw new InvalidParameterException("create_grid() maximum_y parameter cannot be less then 0");

        grid = new String[maximum_x][maximum_y];
        for ( int index = 0; index < maximum_x; index++ ) {
            Arrays.fill(grid[index], Terrain.Normal.name());
        }

        return new GridPlateau(grid);
    }

    /**
     * Handles create a terrain where a certain percent of the cells are Terrain and a certain percentage are Obstructed
     * @param maximum_x  The maximum maximum_x-coordinate
     * @param maximum_y  The maximum maximum_y-coordinate
     */
    public GridPlateau initialize(int maximum_x, int maximum_y, float percent_obstructed) {
        int  obstructedMax = (int)(( maximum_x * maximum_y ) * percent_obstructed );
        if ( percent_obstructed == 100 ) {
            throw new InvalidParameterException("Percentage of Obstacles can only be 99% or less.  One space is needed for the Rover");
        }

        initialize(maximum_x,maximum_y);
        List<Position> randomizedList = createRandomizedList(maximum_x, maximum_y);
        for ( int index = 0; index < obstructedMax; index++) {
            grid[randomizedList.get(index).getX()][randomizedList.get(index).getY()] = Terrain.Obstructed.name();
        }

        return new GridPlateau(grid);
    }

    /**
     * Handles create a terrain where a certain percent of the cells are Terrain and a certain percentage are Obstructed
     * @param maximum_x  The maximum maximum_x-coordinate
     * @param maximum_y  The maximum maximum_y-coordinate
     */
    public GridPlateau initialize(int maximum_x, int maximum_y , List<Position> obstaclesPositions ) {

        if ( obstaclesPositions == null ) {
            throw new NullPointerException("ObstaclePositions parameters must contain zero or more positions instances");
        }

        initialize(maximum_x,maximum_y);
        obstaclesPositions.forEach( (position) ->
        { grid[position.getX()][position.getY()] = Terrain.Obstructed.name(); });

        return new GridPlateau(grid);
    }

    private List<Position> createRandomizedList(int maximum_x,int maximum_y) {
        ArrayList<Position> randomList = new ArrayList<>();
        for ( int index_x = 0; index_x < maximum_x; index_x++ )
            for ( int index_y = 0; index_y < maximum_y; index_y++ )
                randomList.add( new Position(index_x, index_y));

        Collections.shuffle(randomList);
        return randomList;
    }
}