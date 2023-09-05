package rover;

import utilities.Position;
import gridPlateau.GridPlateau;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * An immutable class containing the current state of the rover
 */
@Value
@AllArgsConstructor
public class RoverData {

    // The direction the rover is facing
    CompassPoint compassPoint;

    // The x,y coordinate the rover inhabits
    Position position;

    // Is the next tile thr rover moves to obstructed
    Boolean isObstructed;

    // The Grid that the rover transverses
    GridPlateau gridPlateau;

    public RoverData(CompassPoint compassPoint, Position position, GridPlateau gridPlateau) {
        this.compassPoint = compassPoint;
        this.position = position;
        this.gridPlateau = gridPlateau;
        this.isObstructed = false;
    }
}
