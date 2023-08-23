package rover.CommandPatterns.userCommands;

import common.Position;
import gridPlateau.GridPlateau;
import lombok.Data;
import rover.CompassPoint;
import rover.RoverData;

/**
 * A command to move the rover one position in the direction that the rover is pointing in.
 */
public class MoveCommand implements CommandInterface<RoverData> {

    private final String commandName = "M";

    private final GridPlateau gridPlateau;

    public MoveCommand(GridPlateau gridPlateau) {
        this.gridPlateau = gridPlateau;
    }

    @Override
    public RoverData execute(RoverData roverData) {

        Position newPosition = null;
        String otherInformation;

        if (roverData.getCompassPoint() == CompassPoint.N)
            newPosition = roverData.getPosition().moveVerticalUp();
        else if (roverData.getCompassPoint() == CompassPoint.E)
            newPosition = roverData.getPosition().moveHorizontalRight();
        else if (roverData.getCompassPoint() == CompassPoint.S)
            newPosition = roverData.getPosition().moveVerticalDown();
        else if (roverData.getCompassPoint() == CompassPoint.W)
            newPosition = roverData.getPosition().moveHorizontalLeft();

        newPosition = newPosition.wrap(10,10);

        RoverData newRoverData = new RoverData();
        newRoverData.setCompassPoint(roverData.getCompassPoint());

        if (gridPlateau != null && gridPlateau.isCellObstructed(newPosition)) {
            otherInformation = "obstructed:true";
            newRoverData.setIsObstructed(true);
            newRoverData.setPosition(roverData.getPosition());
        } else {
            otherInformation = "obstructed:false";
            newRoverData.setIsObstructed(false);
            newRoverData.setPosition(newPosition);
        }

        addEventHistory( commandName, newRoverData.getCompassPoint(), newRoverData.getPosition(), otherInformation);
        return create(newRoverData.getCompassPoint(), newRoverData.getPosition(), newRoverData.getIsObstructed());
    }
}
