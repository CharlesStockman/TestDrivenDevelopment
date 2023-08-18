package rover.commandPattern;

import common.Position;
import rover.CompassPoint;

public class StartCommand implements CommandInterface<Boolean> {

    Character command;
    CompassPoint compassPoint;
    Position position;

    public StartCommand(Character command, CompassPoint compassPoint, Position position) {
        this.command = command;
        this.compassPoint = compassPoint;
        this.position = position;
    }

    @Override
    public Boolean execute() {
        History.getInstance().clearHistory();
        addEventHistory(Character.MIN_VALUE, compassPoint, position, "");
        return true;
    }
}
