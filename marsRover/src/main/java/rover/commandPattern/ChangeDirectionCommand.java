package rover.commandPattern;

import rover.CompassPoint;

public class ChangeDirectionCommand implements CommandInterface {

    private Character command;
    private CompassPoint compassPoint;

    public ChangeDirectionCommand(Character command, CompassPoint compassPoint) {
        this.command = command;
        this.compassPoint = compassPoint;
    }

    @Override
    public CompassPoint execute() {
        CompassPoint result = null;
        if (command == 'L' && compassPoint == CompassPoint.N)
            result = CompassPoint.W;
        else if (command == 'L' && compassPoint == CompassPoint.W)
            result = CompassPoint.S;
        else if (command == 'L' && compassPoint == CompassPoint.S)
            result = CompassPoint.E;
        else if (command == 'L' && compassPoint == CompassPoint.E)
            result = CompassPoint.N;
        else if (command == 'R' && compassPoint == CompassPoint.N)
            result = CompassPoint.E;
        else if (command == 'R' && compassPoint == CompassPoint.E)
            result = CompassPoint.S;
        else if (command == 'R' && compassPoint == CompassPoint.S)
            result = CompassPoint.W;
        else if (command == 'R' && compassPoint == CompassPoint.W)
            result = CompassPoint.N;
        return result;
    }
}
