package rover.commandPattern;

import common.Position;
import gridPlateau.GridPlateau;
import rover.CompassPoint;

public class MoveCommand implements CommandInterface {

    private Character command;
    private CompassPoint compassPoint;

    private Position position;
    private GridPlateau gridPlateau;

    public MoveCommand(Character command, CompassPoint compassPoint, Position position) {
        this.command = command;
        this.compassPoint = compassPoint;
        this.position = position;
    }

    @Override
    public Position execute() {

        Position newPosition = null;

        if (command == 'M' && compassPoint == CompassPoint.N) {
            newPosition = position.moveVerticalUp();
            newPosition = newPosition.wrap(10,10);
        } else if (command == 'M' && compassPoint == CompassPoint.E) {
            newPosition = position.moveHorizontalRight();
            newPosition = newPosition.wrap(10,10);
        }  else if (command == 'M' && compassPoint == CompassPoint.S) {
            newPosition = position.moveVerticalDown();
            newPosition = newPosition.wrap(10, 10);
        } else if (command == 'M' && compassPoint == CompassPoint.W) {
            newPosition = position.moveHorizontalLeft();
            newPosition = newPosition.wrap(10,10);
        }

        addEventHistory('M',compassPoint, newPosition, "later");

        return newPosition;
    }
}
