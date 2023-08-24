package gridPlateau;

import common.Position;
import common.Terrain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GridPlateauTest {

    @Test
    public void create_grid_test() {
        GridPlateauFactory gridFactory = new GridPlateauFactory();
        GridPlateau gridPlateau = gridFactory.initialize(10,10, 0 );
        assertEquals(gridPlateau.getLength() * gridPlateau.getWidth(), 100);
    }

    @Test
    public void create_grid_with_invalid_x() {
        InvalidParameterException exception = assertThrows(
                InvalidParameterException.class,
                () -> (new GridPlateauFactory()).initialize(-1, 5));
        assertEquals(exception.getMessage(), "create_grid() maximum_x parameter cannot be less then 0");
    }

    @Test
    public void create_grid_with_invalid_y() {
        InvalidParameterException exception = assertThrows(
                InvalidParameterException.class,
                () -> (new GridPlateauFactory()).initialize(5, -1));
        assertEquals(exception.getMessage(), "create_grid() maximum_y parameter cannot be less then 0");
    }

    @Test
    public void create_grid_with_normal_terrian() {
        GridPlateauFactory gridPlateauFactory = new GridPlateauFactory();
        GridPlateau gridPlateau = gridPlateauFactory.initialize(10,10);
        for ( int indexX = 0; indexX < 10; indexX++)
            for(int indexY = 0; indexY < 10; indexY++ ) {
                Position position = new Position(indexX, indexY);
                if (gridPlateau.isCellObstructed(position))
                    fail(String.format("Tile is %s instead of %s for position (%d,%d",
                            Terrain.Obstructed.name(), Terrain.Normal, position.getX(), position.getY()));
            }
    }

    @Test
    public void create_grid_with_normal_terrain_and_obstacle() {

        GridPlateauFactory gridPlateauFactory = new GridPlateauFactory();
        GridPlateau gridPlateau = gridPlateauFactory.initialize(10, 10, .2f);

        int normal_tile_counter = 0;
        int obstacle_tile_counter = 0;
        for (int indexX = 0; indexX < 10; indexX++)
            for (int indexY = 0; indexY < 10; indexY++) {
                if (gridPlateau.isCellObstructed(new Position(indexX, indexY)))
                    obstacle_tile_counter++;
                else
                    normal_tile_counter++;
            }

        assertEquals(80, normal_tile_counter);
        assertEquals(20, obstacle_tile_counter);

    }

    @Test
    public void create_grid_throw_exception_when_grid_is_all_obstacle() {
        GridPlateauFactory gridPlateauFactory = new GridPlateauFactory();
        InvalidParameterException exception = assertThrows(InvalidParameterException.class,
                () -> gridPlateauFactory.initialize(10,10,100));
        assertEquals(exception.getMessage(),
                "Percentage of Obstacles can only be 99% or less.  One space is needed for the Rover");
    }

    @Test
    public void gridTestCustomGridObstructedListNull() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class, () -> (new GridPlateauFactory()).initialize(10,10, null));
        Assertions.assertEquals(
                exception.getMessage(), "ObstaclePositions parameters must contain zero or more positions instances");
    }

    @Test void gridTestCustomerObstructedList() {
        ArrayList<Position> obstructedPositions = new ArrayList<>();
        Position obstructedPosition = new Position(3,5);
        obstructedPositions.add(obstructedPosition);

        GridPlateauFactory gridPlateauFactory = new GridPlateauFactory();
        GridPlateau gridPlateau = gridPlateauFactory.initialize(10, 10, obstructedPositions);

        int count_normal_tiles = 0;
        int count_obstructed_tiles = 0;
        for ( int indexX = 0; indexX < 10; indexX++) {
            for ( int indexY = 0; indexY < 10; indexY++ ) {
                Position position = new Position(indexX, indexY);
                if ( gridPlateau.isCellObstructed(position))
                    count_obstructed_tiles++;
                else
                    count_normal_tiles++;
            }
        }

        Assertions.assertEquals(99, count_normal_tiles);
        Assertions.assertEquals(1, count_obstructed_tiles);
    }

    @Test
    public void gridTestIfTileIsNotObstructed() {
        GridPlateauFactory gridPlateauFactory = new GridPlateauFactory();
        GridPlateau gridPlateau = gridPlateauFactory.initialize(10,10);
        Position position = new Position(3,5);
        assertEquals(false, gridPlateau.isCellObstructed(position));

    }

    @Test
    public void gridTestIfTileIsObstructed() {
        GridPlateauFactory gridPlateauFactory = new GridPlateauFactory();
        GridPlateau gridPlateau = gridPlateauFactory.initialize(10,10);
        gridPlateau.setTile(new Position(4,5), Terrain.Obstructed.name());
        assertEquals(true, gridPlateau.isCellObstructed(new Position(4,5)));
    }

    @Test
    public void gridTestGetCorrectLength() {
        GridPlateauFactory gridPlateauFactory = new GridPlateauFactory();
        GridPlateau gridPlateau = gridPlateauFactory.initialize(10,10);
        assertEquals(10, gridPlateau.getLength());
    }

    @Test
    public void gridTestGetCorrectLengthWhereGridIsNotCreated() {
        GridPlateauFactory gridPlateauFactory = new GridPlateauFactory();
        GridPlateau gridPlateau = new GridPlateau(null);
        assertEquals(0, gridPlateau.getLength());
    }

    @Test
    public void gridTestGetCorrectWidth() {
        GridPlateauFactory gridPlateauFactory = new GridPlateauFactory();
        GridPlateau gridPlateau = gridPlateauFactory.initialize(10,10);
        assertEquals(10, gridPlateau.getWidth());
    }

    @Test
    public void gridTestGetCorrectWidthWhereGridIsNotCreated() {
        GridPlateau gridPlateau = new GridPlateau(null);
        assertEquals(0, gridPlateau.getWidth());
    }

    @Test
    public void gridSetTileWithNoInitializedGrid() {
        GridPlateau gridPlateau = new GridPlateau(null);
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> gridPlateau.setTile(new Position(3,5 ), Terrain.Obstructed.name()));
        assertEquals(exception.getMessage(), "Cannot load from object array because \"this.grid\" is null");

        // Assertions.assertEquals(true, gridPlateau.isCellObstructed(new Position(3,5 )));
    }

    @Test
    public void gridSetTile() {
        GridPlateauFactory gridPlateauFactory = new GridPlateauFactory();
        GridPlateau gridPlateau = gridPlateauFactory.initialize(10,10);
        gridPlateau.setTile(new Position(3,5), Terrain.Obstructed.name());
        assertEquals(true, gridPlateau.isCellObstructed(new Position(3,5)));
    }
}
