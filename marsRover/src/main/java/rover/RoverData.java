package rover;

import common.Position;
import gridPlateau.GridPlateau;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoverData {
    CompassPoint compassPoint;
    Position position;
    Boolean isObstructed;
    GridPlateau gridPlateau;
}
