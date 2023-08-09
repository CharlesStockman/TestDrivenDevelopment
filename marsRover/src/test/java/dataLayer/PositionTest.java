package dataLayer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    public void testCreatePosition() {
        Position position = new Position(3,5);
        Assertions.assertEquals(position.getX(), 3);
        Assertions.assertEquals(position.getY(), 5);
    }

    @Test
    public void testMovePositiveVertical() {
        Position position = new Position(3,5);
        position = position.moveVerticalUp();
        Assertions.assertEquals(position.getX(), 3);
        Assertions.assertEquals(position.getY(), 6);
    }

    @Test
    public void testMoveNegativeVertical() {
        Position position = new Position(3,5);
        position = position.moveVerticalDown();
        Assertions.assertEquals(position.getX(), 3);
        Assertions.assertEquals(position.getY(), 4);
    }

    @Test
    public void testMoveLeftHorizontal() {
        Position position = new Position(3,5);
        position = position.moveHorizontalLeft();
        Assertions.assertEquals(position.getX(), 2);
        Assertions.assertEquals(position.getY(), 5);
    }

    @Test
    public void testMoveRightHorizontal() {
        Position position = new Position(3,5);
        position = position.moveHorizontalRight();
        Assertions.assertEquals(position.getX(), 4);
        Assertions.assertEquals(position.getY(), 5);
    }

    @Test
    public void testWrapXOffLeftEdge() {
        Position position = new Position(-1, 5);
        position = position.wrap(10, 10 );
        Assertions.assertEquals(position.getX(), 10);
        Assertions.assertEquals(position.getY(), 5);
    }

    @Test
    public void testWrapXOffRightEdge() {
        Position position = new Position(11, 5);
        position = position.wrap(10, 10 );
        Assertions.assertEquals(position.getX(), 0);
        Assertions.assertEquals(position.getY(), 5);
    }

    @Test
    public void testWrapYOffTopEdge() {
        Position position = new Position(1, 11);
        position = position.wrap(10,10);
        Assertions.assertEquals(position.getX(), 1);
        Assertions.assertEquals(position.getY(), 0);
    }

    @Test
    public void testWrapYOffBottomEdge() {
        Position position = new Position(1, -1);
        position = position.wrap(10,10);
        Assertions.assertEquals(position.getX(), 1);
        Assertions.assertEquals(position.getY(), 10);
    }

}
