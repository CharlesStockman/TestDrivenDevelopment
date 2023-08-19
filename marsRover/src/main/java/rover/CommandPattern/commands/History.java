package rover.CommandPattern.commands;

import common.Position;
import lombok.Data;
import rover.CompassPoint;

import java.util.ArrayList;
import java.util.List;

public class History {

    private List<Event> eventList = null;
    private static History history;


    private History() {
        eventList =  ( eventList == null ) ? new ArrayList<Event>() : eventList;
    };

    public static History getInstance() {
        history = ( history == null ) ? new History() : history;
        return history;
    }

    public void addEvent(Character command, CompassPoint compassPoint, Position position) {
        Event event = new Event(command, compassPoint, position);
        eventList.add(event);
    }

    public void clearHistory() {
        history = null;
    }

    public List<Event> getHistory() {
        return eventList;
    }

    @Data
    public static class Event {
        Character command;
        CompassPoint direction;
        Position position;
        //String finalPositionAndDirection;

        public Event(Character command, CompassPoint direction, Position position) {
            this.command = command;
            this.direction = direction;
            this.position = position;
            //this.finalPositionAndDirection = displayCoordinatesAndDirection(position, direction);
        }

        public String displayCoordinatesAndDirection(Position position, CompassPoint compassPoint) {
            return String.format("%d:%d:%s", position.getX() , position.getY() , compassPoint);
        }

    }
}
