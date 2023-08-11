package dataLayer;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

public class GridPlateau {

    public GridPlateau() {}

    public String[][] initialize(int x, int y) {

        if ( x < 0 )
            throw new InvalidParameterException("create_grid() x parameter cannot be less then 0");

        if ( y < 0 )
            throw new InvalidParameterException("create_grid() y parameter cannot be less then 0");

        String[][] grid = new String[x][y];
        for ( int index = 0; index < x; index++ ) {
            Arrays.fill(grid[index], Terrian.Normal.name());
        }
        return grid;
    }

    public String[][] initialize(int x, int y, float percent_obstructed) {
        int  obstructured_max = (int)(( x * y ) * percent_obstructed );
        String[][] grid = initialize(x,y);

        if ( percent_obstructed == 100 ) {
            throw new InvalidParameterException("Percentage of Obstacles can only be 99% or less.  One space is needed for the Rover");
        }

        for ( int index = 0; index < 10; index++ )  {
            System.out.println(Arrays.toString(grid[index]));
        }

        Random random = new Random();
        for ( int index = 0; index < obstructured_max; index++) {
            //Integer next_x = x_coords.get(index);
            //Integer next_y = y_coords.get(index);

            boolean result = false;
            do {
                Integer next_x = random.nextInt(x);
                Integer next_y = random.nextInt(y);

                if ( grid[next_x][next_y] == Terrian.Normal.name()) {
                    grid[next_x][next_y] = Terrian.Obstructed.name();
                    result = true;
                }
            } while (result == false);
        }


        return grid;
    }

    public String[][] initialize_where_0_0_is_noraml(int x, int y, float percent_normal_terrian) {
        String grid[][] = this.initialize(x,y, percent_normal_terrian);
        if ( grid[0][0] == Terrian.Obstructed.name()) {
            for ( int index = 0; index < 10; index++) {
                for ( int index2 = 0; index < 10; index++ ) {
                    if ( grid[index][index2] == Terrian.Normal.name() ) {
                        grid[index][index2] = Terrian.Obstructed.name();
                        grid[0][0] = Terrian.Normal.name();
                        break;
                    }
                }
            }
        }

        return grid;
    }

}
