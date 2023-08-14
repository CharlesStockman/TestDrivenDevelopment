package rover;

import common.Position;
import gridPlateau.GridPlateau;

import java.util.Arrays;
import java.util.List;

public class GridPlateauFactory {

    public static  GridPlateau create_standard_grid_with_obstructed_list(Position... positions) {
        GridPlateau gridPlateau = new GridPlateau();
        List<Position> positionList =  Arrays.asList(positions);
        gridPlateau.initialize(10,10, positionList);
        return gridPlateau;
    }

    public static GridPlateau create_standard_grid_with_no_obstructions() {
        GridPlateau gridPlateau = new GridPlateau();
        gridPlateau.initialize(10, 10, 0 );
        return gridPlateau;
    }

    public static GridPlateau create_standard_grid_with_obstructed_percentage(Float percentage_obstructed) {
        GridPlateau gridPlateau = new GridPlateau();
        gridPlateau.initialize(10,10, percentage_obstructed);
        return gridPlateau;
    }
}
