package rover;

import gridPlateau.GridPlateau;
import common.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoverTest {

    @Test
    public void displayDefaultValues() {
        Assertions.assertEquals("0:0:N", (new Rover()).displayCoordinatesAndDirection());
    }

    @Test
    public void inputMovePrintCellForNextObstacle() {
        GridPlateau gridPlateau = GridPlateauFactory.create_standard_grid_with_obstructed_list(new Position(0,2));
        Assertions.assertEquals("O:0:1:N", (new Rover(gridPlateau)).move("MM"));
    }

    @Test
    public void inputMoveCommandsIsEmptyString() {
        Assertions.assertEquals("0:0:N", (new Rover()).move(""));
    }

    @Test
    public void inputMoveCommandsIsNullString() {
        NullPointerException exception =
                Assertions.assertThrows(NullPointerException.class, () -> (new Rover()).move(null));
        Assertions.assertEquals(exception.getMessage(), "The input to the move function is null");
    }

    @Test
    public void inputMoveCommandLeftRotateOnce() {
        Assertions.assertEquals("0:0:W", (new Rover()).move("L"));
    }

    @Test
    public void inputMoveCommandLeftRotateTwice() {
        Assertions.assertEquals("0:0:S", (new Rover()).move("LL"));
    }

    @Test
    public void inputMoveCommandLeftRotateThree() {
        Assertions.assertEquals("0:0:E", (new Rover()).move("LLL"));
    }

    @Test
    public void inputMoveCommandLeftRotateFour() {
        Assertions.assertEquals("0:0:N", (new Rover()).move("LLLL"));
    }

    @Test
    public void inputMoveCommandRightRotateOne() {
        Assertions.assertEquals("0:0:E", (new Rover()).move("R"));
    }

    @Test
    public void inputMoveCommandRightRotateTwo() {
        Assertions.assertEquals("0:0:S", (new Rover()).move("RR"));
    }

    @Test
    public void inputMoveCommandRightRotateThree() {
        Assertions.assertEquals("0:0:W", (new Rover()).move("RRR"));
    }

    @Test
    public void inputMoveCommandRightRotateFour() {
        Assertions.assertEquals("0:0:N", (new Rover()).move("RRRR"));
    }

    @Test
    public void inputMoveCommandMoveNorth() {
        Assertions.assertEquals("0:1:N", (new Rover()).move("M"));
    }

    @Test
    public void inputMoveCommandMoveEast() {
        Assertions.assertEquals("1:0:E", (new Rover()).move("RM"));
    }

    @Test
    public void inputMoveCommandMoveSouth() {
        Assertions.assertEquals("0:0:S", (new Rover()).move("MLLM"));
    }

    @Test
    public void inputMoveCommandMoveWest() {
        Assertions.assertEquals("0:0:W", (new Rover()).move("RMLLM"));
    }

    @Test
    public void inputMoveCommandMoveNorthReachEndOfGrid() {
        Assertions.assertEquals("0:0:N", (new Rover()).move("MMMMMMMMMM"));
    }

    @Test
    public void inputMoveCommandMoveSouthReachEndOfGrid() {
        Assertions.assertEquals("0:9:S", (new Rover()).move("LLM"));
    }

    @Test
    public void inputMoveCommandMoveWestEndOfGrid() {
        Assertions.assertEquals("9:0:W", (new Rover()).move("LM"));
    }

    @Test
    public void inputMoveCommandEastEndOfGrid() {
        Assertions.assertEquals("0:0:E", (new Rover()).move("RMMMMMMMMMM"));
    }

    //
    // Test from the Specifications
    //
    @Test
    public void gridWithNoObstacles() {
        Rover rover = new Rover(GridPlateauFactory.create_standard_grid_with_no_obstructions());
        Assertions.assertEquals("2:3:N", rover.move("MMRMMLM"));
    }

    @Test
    public void gridWihWrap() {
        Rover rover = new Rover(GridPlateauFactory.create_standard_grid_with_no_obstructions());
        Assertions.assertEquals("0:0:N", rover.move("MMMMMMMMMM"));
    }

    @Test
    public void gridWithObstacle() {
        GridPlateau gridPlateau = GridPlateauFactory.create_standard_grid_with_obstructed_list(new Position(0,3));
        Assertions.assertEquals("O:0:2:N", (new Rover(gridPlateau)).move("MMMM"));
    }
}
