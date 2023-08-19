package rover.CommandPattern.commands.commands;

import common.Position;
import gridPlateau.GridPlateau;
import lombok.Data;
import rover.CompassPoint;

public class MoveCommand implements CommandInterface {

    private Character command;
    private CompassPoint compassPoint;

    private Position position;
    private GridPlateau gridPlateau;

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
            newPosition = position;
            otherInformation = "obstructed:true";
            positionData.setObstructed(true);
            positionData.setPosition(position);

        } else {
            otherInformation = "obstructed:false";
            positionData.setObstructed(false);
            positionData.setPosition(newPosition);
        }

        addEventHistory('M',compassPoint, newPosition, otherInformation);

        return positionData;
    }

    @Data
    public static class PositionData {
        private Position position;
        private boolean isObstructed;
    }
}
