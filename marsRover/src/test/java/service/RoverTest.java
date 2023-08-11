package service;

import dataLayer.GridPlateau;
import dataLayer.Terrian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoverTest {

    @Test
    public void displayDefaultValues() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:0:N", rover.displayCoordinatesAndDirection());
    }

    @Test
    public void inputMovePrintCellForNextObstacle() {
        String[][] testGrid = (new GridPlateau()).initialize(10, 10, 0);
        testGrid[0][2] = Terrian.Obstructed.name();
        Rover rover = new Rover(testGrid);
        Assertions.assertEquals("O:0:1:N", rover.move("NMM"));
    }

    @Test
    public void inputMoveCommandsIsEmptyString() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:0:N", rover.move(""));
    }

    @Test
    public void inputMoveCommandsIsNullString() {
        Rover rover = new Rover();
        NullPointerException exception =
                Assertions.assertThrows(NullPointerException.class, () -> (new Rover()).move(null));
        Assertions.assertEquals(exception.getMessage(), "The input to the move function is null");
    }

    @Test
    public void inputMoveCommandLeftRotateOnce() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:0:W", rover.move("L"));
    }

    @Test
    public void inputMoveCommandLeftRotateTwice() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:0:S", rover.move("LL"));
    }

    @Test
    public void inputMoveCommandLeftRotateThree() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:0:E", rover.move("LLL"));
    }

    @Test
    public void inputMoveCommandLeftRotateFour() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:0:N", rover.move("LLLL"));
    }

    @Test
    public void inputMoveCommandRightRotateOne() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:0:E", rover.move("R"));
    }

    @Test
    public void inputMoveCommandRightRotateTwo() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:0:S", rover.move("RR"));
    }

    @Test
    public void inputMoveCommandRightRotateThree() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:0:W", rover.move("RRR"));
    }

    @Test
    public void inputMoveCommandRightRotateFour() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:0:N", rover.move("RRRR"));
    }

    @Test
    public void inputMoveCommandMoveNorth() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:1:N", rover.move("M"));
    }

    @Test
    public void inputMoveCommandMoveEast() {
        Rover rover = new Rover();
        Assertions.assertEquals("1:0:E", rover.move("RM"));
    }

    @Test
    public void inputMoveCommandMoveSouth() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:0:S", rover.move("MLLM"));
    }

    @Test
    public void inputMoveCommandMoveWest() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:0:W", rover.move("RMLLM"));
    }

    @Test
    public void inputMoveCommandMoveNorthReachEndOfGrid() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:0:N", rover.move("MMMMMMMMMM"));
    }

    @Test
    public void inputMoveCommandMoveSouthReachEndOfGrid() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:9:S", rover.move("LLM"));
    }

    @Test
    public void inputMoveCommandMoveWestEndOfGrid() {
        Rover rover = new Rover();
        Assertions.assertEquals("9:0:W", rover.move("LM"));
    }

    @Test
    public void inputMoveCommandEastEndOfGrid() {
        Rover rover = new Rover();
        Assertions.assertEquals("0:0:E", rover.move("RMMMMMMMMMM"));
    }

    //
    // Test from the Specifications
    //
    @Test
    public void gridWithNoObstacles() {
        String[][] grid = new GridPlateau().initialize(10,10);
        Rover rover = new Rover();
        Assertions.assertEquals("2:3:N", rover.move("MMRMMLM"));
    }

    @Test
    public void gridWihWrap() {
        String[][] grid = new GridPlateau().initialize(10,10);
        Rover rover = new Rover();
        Assertions.assertEquals("0:0:N", rover.move("MMMMMMMMMM"));
    }

    @Test
    public void gridWithObstacle() {
        String[][] grid = new GridPlateau().initialize(10,10, .1f);
        grid[0][3] = Terrian.Obstructed.name();
        Assertions.assertEquals("O:0:2:N", (new Rover(grid)).move("MMMM"));

    }



}
