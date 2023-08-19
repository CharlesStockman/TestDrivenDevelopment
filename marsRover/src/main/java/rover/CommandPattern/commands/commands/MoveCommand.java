package rover.CommandPattern.commands.commands;

import common.Position;
import gridPlateau.GridPlateau;
import rover.CompassPoint;

public class MoveCommand implements CommandInterface {

    private Character command;
    private CompassPoint compassPoint;

    private Position position;
    private GridPlateau gridPlateau;

    public MoveCommand() {
    }

    @Override
    public Position execute(Character command , CompassPoint compassPoint, Position position) {

        Position newPosition = null;

        if (compassPoint == CompassPoint.N)
            newPosition = position.moveVerticalUp();
        else if (compassPoint == CompassPoint.E)
            newPosition = position.moveHorizontalRight();
        else if (compassPoint == CompassPoint.S)
            newPosition = position.moveVerticalDown();
        else if (compassPoint == CompassPoint.W)
            newPosition = position.moveHorizontalLeft();

        newPosition = newPosition.wrap(10,10);

        addEventHistory('M',compassPoint, newPosition, "later");

        return newPosition;
    }
}
