package dataLayer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class GridPlateauTest {

    @Test
    public void create_grid_test() {
        GridPlateau gridPlateau = new GridPlateau();
        int x=10;
        int y=10;

        Object[][] grid = gridPlateau.initialize(10,10);

        var result = 0;
        for(int index = 0; index < x; index++) {
            result = result +  grid[index].length;
        }

        Assertions.assertEquals(x*y, result);
    }

    @Test
    public void create_grid_with_invalid_x() {
        InvalidParameterException exception = Assertions.assertThrows(
                InvalidParameterException.class,
                () -> (new GridPlateau()).initialize(-1, 5));
        Assertions.assertEquals(exception.getMessage(), "create_grid() x parameter cannot be less then 0");
    }

    @Test
    public void create_grid_with_invalid_y() {
        InvalidParameterException exception = Assertions.assertThrows(
                InvalidParameterException.class,
                () -> (new GridPlateau()).initialize(5, -1));
        Assertions.assertEquals(exception.getMessage(), "create_grid() y parameter cannot be less then 0");
    }

    @Test
    public void create_grid_with_normal_terrian() {
        GridPlateau gridPlateau = new GridPlateau();
        String[][] grid = gridPlateau.initialize(10,10);
        Assertions.assertEquals(grid[0][0].getClass(), String.class);
        Assertions.assertEquals(grid[0][0], "Normal");
    }

    @Test
    public void create_grid_with_normal_terrain_and_obstacle() {

        GridPlateau gridPlateau = new GridPlateau();
        String[][] grid = gridPlateau.initialize(10,10, .2f );

        for ( int index = 0; index < 10; index++ )  {
            System.out.println(Arrays.toString(grid[index]));
        }

        long countNormal = Arrays.stream(grid).
                flatMap(Arrays::stream).
                filter(action -> action.contentEquals("Normal")).
                count();

        Assertions.assertEquals(80, countNormal);

        long countObstructed = Arrays.stream(grid).
                flatMap(Arrays::stream).
                filter(action -> action.contentEquals("Obstructed")).
                count();

        Assertions.assertEquals(20, countObstructed);

    }

    @Test
    public void create_grid_throw_exception_when_grid_is_all_obstacle() {
        GridPlateau gridPlateau = new GridPlateau();
        InvalidParameterException exception = Assertions.assertThrows(InvalidParameterException.class,
                () -> gridPlateau.initialize(10,10,100));
        Assertions.assertEquals(exception.getMessage(),
                "Percentage of Obstacles can only be 99% or less.  One space is needed for the Rover");
    }

    @Test
    public void create_grid_where_0_0_is_Not_Obstructed() {
        GridPlateau gridPlateau = new GridPlateau();
        String[][] grid = gridPlateau.initialize_where_0_0_is_noraml(10,10, .8f );
        Assertions.assertEquals(grid[0][0], Terrian.Normal.name());
    }
}
