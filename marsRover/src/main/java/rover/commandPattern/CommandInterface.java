package rover.commandPattern;

import common.Position;
import rover.CompassPoint;

public interface CommandInterface<T> {
    public T  execute();
}
