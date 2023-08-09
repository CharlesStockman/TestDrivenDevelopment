package dataLayer;

import java.security.InvalidParameterException;
import java.util.Arrays;

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

    public String[][] initialize(int x, int y, float percent_normal_terrian) {
        int  obstructured_max = (int)(( x * y ) * ( 1 - percent_normal_terrian) +1 );
        String[][] grid = initialize(x,y);

        for ( int index = 0; index < 10; index++ )  {
            System.out.println(Arrays.toString(grid[index]));
        }

        for ( int index_x = 0; index_x < x; index_x++) {
            for( int index_y = 0; index_y < y; index_y++ ) {
                grid[index_x][index_y] = Terrian.Obstructed.name();
                obstructured_max--;
                if ( obstructured_max == 0 ) return grid;
            }
        }

        return grid;
    }

}
