package rover;

import common.Position;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoverData {
    CompassPoint compassPoint;
    Position position;
    Boolean isObstructed;
}
