package rover.CommandPatterns.userCommands;

import common.Position;
import gridPlateau.GridPlateau;
import lombok.Data;
import rover.CompassPoint;

/**
 * A command to move the rover one position in the direction that the rover is pointing in.
 */
public class MoveCommand implements CommandInterface<MoveCommand.PositionData> {

    private final GridPlateau gridPlateau;

    public MoveCommand(GridPlateau gridPlateau) {
        this.gridPlateau = gridPlateau;
    }

    @Override
    public PositionData execute(Character command , CompassPoint compassPoint, Position position) {

        Position newPosition = null;
        String otherInformation;

        if (compassPoint == CompassPoint.N)
            newPosition = position.moveVerticalUp();
        else if (compassPoint == CompassPoint.E)
            newPosition = position.moveHorizontalRight();
        else if (compassPoint == CompassPoint.S)
            newPosition = position.moveVerticalDown();
        else if (compassPoint == CompassPoint.W)
            newPosition = position.moveHorizontalLeft();

        newPosition = newPosition.wrap(10,10);

        PositionData positionData = new PositionData();
        if (gridPlateau != null && gridPlateau.isCellObstructed(newPosition)) {
            otherInformation = "obstructed:true";
            positionData.setObstructed(true);
            positionData.setPosition(position);
        } else {
            otherInformation = "obstructed:false";
            positionData.setObstructed(false);
            positionData.setPosition(newPosition);
        }

        addEventHistory('M',compassPoint, positionData.getPosition(), otherInformation);

        return positionData;
    }

    @Data
    public static class PositionData {
        private Position position;
        private boolean isObstructed;
    }
}
