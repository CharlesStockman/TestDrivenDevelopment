package rover.CommandPatterns;

import gridPlateau.GridPlateau;
import rover.RoverData;
import utilities.Position;
import lombok.Data;
import rover.CompassPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Adds history to the command design pattern
 * From an older solution that I rejected.  Code is no longer being used, but since it is practice
 */
public class History {

    private final List<Event> eventList;
    private static History history;

    private History() {
        eventList = new ArrayList<>();
    }

    public static History getInstance() {
        history = ( history == null ) ? new History() : history;
        return history;
    }

    /**
     * One event from the history list
     *
     * @param command               The command for the event being logged
     * @param compassPoint          The final direction of the rover for the event being logged.
     * @param position              The final position of the rove from the @Link { gridPlateau.GridPlateau }
     */
    public void addEvent(String command, CompassPoint compassPoint, Position position) {
        Event event = new Event(command, compassPoint, position);
        eventList.add(event);
    }

    public void clearHistory() {
        history = null;
    }

    public List<History.Event> getHistory() {
        return eventList;
    }

    @Data
    public static class Event {
        String command;

        RoverData roverData;

        public Event(String command, CompassPoint direction, Position position) {
            this.command = ( command.charAt(0) == Character.MIN_VALUE ) ? "Start Data" : command;
            roverData = new RoverData(direction, position, false , null);
        }
    }
}
