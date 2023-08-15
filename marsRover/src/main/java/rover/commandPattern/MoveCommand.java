package rover.commandPattern;

import common.Position;
import rover.CompassPoint;

public class MoveCommand implements CommandInterface {

    private Character command;
    private CompassPoint compassPoint;

    private Position position;

    public MoveCommand(Character command, CompassPoint compassPoint, Position position) {
        this.command = command;
        this.compassPoint = compassPoint;
        this.position = position;
    }

    @Override
    public Position execute() {
        if (command == 'M' && compassPoint == CompassPoint.N) {
            position = position.moveVerticalUp();
            position = position.wrap(10,10);
        } else if (command == 'M' && compassPoint == CompassPoint.E) {
            position = position.moveHorizontalRight();
            position = position.wrap(10,10);
        }  else if (command == 'M' && compassPoint == CompassPoint.S) {
            position = position.moveVerticalDown();
            position = position.wrap(10, 10);
        } else if (command == 'M' && compassPoint == CompassPoint.W) {
            position = position.moveHorizontalLeft();
            position = position.wrap(10,10);
        }

        return position;
    }
}
