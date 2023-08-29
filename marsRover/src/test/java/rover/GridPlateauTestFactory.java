package rover;

import utilities.Position;
import gridPlateau.GridPlateau;
import gridPlateau.GridPlateauFactory;

import java.util.Arrays;
import java.util.List;

public class GridPlateauTestFactory {

    public static  GridPlateau create_standard_grid_with_obstructed_list(Position... positions) {
        GridPlateauFactory gridPlateauFactory = new GridPlateauFactory();
        List<Position> positionList =  Arrays.asList(positions);
        return gridPlateauFactory.initialize(10,10, positionList);
    }

    public static GridPlateau create_standard_grid_with_no_obstructions() {
        GridPlateauFactory gridPlateauFactory = new GridPlateauFactory();
        return gridPlateauFactory.initialize(10, 10, 0 );
    }

    public static GridPlateau create_standard_grid_with_obstructed_percentage(Float percentage_obstructed) {
        GridPlateauFactory gridPlateauFactory = new GridPlateauFactory();
        return gridPlateauFactory.initialize(10,10, percentage_obstructed);
    }
}
