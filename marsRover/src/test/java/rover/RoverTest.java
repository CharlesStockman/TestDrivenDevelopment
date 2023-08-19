package rover;

import gridPlateau.GridPlateau;
import common.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import rover.commandPattern.History;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RoverTest {

    @Test
    public void displayDefaultValues() {
        Assertions.assertEquals("0:0:N", (new Rover()).displayCoordinatesAndDirection());
    }

    @Test
    public void displayObstructedPosition() {
        Assertions.assertEquals("O:0:0:N", new Rover().displayObstructedCoordinatesAndDirection(CompassPoint.N, new Position(0,0)));
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

    @ParameterizedTest
    @CsvSource({"L, 0:0:W", "LL, 0:0:S", "LLL, 0:0:E", "LLLL, 0:0:N",
                "R, 0:0:E", "RR, 0:0:S", "RRR, 0:0:W", "RRRR, 0:0:N"})
    public void inputMoveCommandRotate(String command, String result) {
        Assertions.assertEquals(result, (new Rover()).move(command));
    }

    @ParameterizedTest
    @CsvSource({"M, 0:1:N", "RM, 1:0:E", "MLLM, 0:0:S", "RMLLM, 0:0:W"})
    public void inputMoveCommandMove(String command, String result) {
        Assertions.assertEquals(result, (new Rover()).move(command));
    }

    @ParameterizedTest
    @CsvSource({"MMMMMMMMMM, 0:0:N", "MMMMMMMMMM, 0:0:N", "LLM, 0:9:S", "LM, 9:0:W", "RMMMMMMMMMM, 0:0:E"})
    public void inputMoveCommandMoveNorthReachEndOfGrid(String command, String result) {
        Assertions.assertEquals(result, (new Rover()).move(command));
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
    public void gridWithWrap() {
        Rover rover = new Rover(GridPlateauFactory.create_standard_grid_with_no_obstructions());
        Assertions.assertEquals("0:0:N", rover.move("MMMMMMMMMM"));
    }

    @Test
    public void gridWithObstacle() {
        GridPlateau gridPlateau = GridPlateauFactory.create_standard_grid_with_obstructed_list(new Position(0,3));
        Assertions.assertEquals("O:0:2:N", (new Rover(gridPlateau)).move("MMMM"));
    }

    @Test
    public void gridDisplayHistoryOneMove() {
        GridPlateau gridPlateau = GridPlateauFactory.create_standard_grid_with_obstructed_list(new Position(0,3));
        Rover rover = new Rover(gridPlateau);
        Assertions.assertEquals("0:1:W", rover.move("ML"));

        List<History.Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(new History.Event( Character.MIN_VALUE, CompassPoint.N, new Position(0,0)));
        expectedEvents.add(new History.Event( 'M', CompassPoint.N, new Position(0,1)));
        expectedEvents.add(new History.Event( 'L', CompassPoint.E, new Position(0,1)));

        List<History.Event> actualEvents = History.getInstance().getHistory();

        for ( int index = 0; index < expectedEvents.size(); index++ ) {
            History.Event expected = expectedEvents.get(index);
            History.Event actual   = actualEvents.get(index);

            Assertions.assertEquals(expected.getCommand(), actual.getCommand());
            Assertions.assertEquals(expected.getPosition(), expected.getPosition());
            Assertions.assertEquals(expected.getDirection(), expected.getDirection());
        }
    }

    @Test
    public void moveArgumentLowerCase() {
        Rover rover = new Rover();
        Assertions.assertEquals("1:0:E", rover.move("rm"));
    }

    @Test
    void moveArgumentInvalidOneMistake() {
        Rover rover = new Rover();
        InvalidParameterException exception = Assertions.assertThrows(
                InvalidParameterException.class,  () -> (new Rover()).move("A"));
        Assertions.assertEquals(exception.getMessage(), "Command String : A\n0 base index(s) are 0\nIncorrect command(s) are A");
    }

    @Test
    void moveArgumentInvalidTwoMistakes() {
        Rover rover = new Rover();
        InvalidParameterException exception = Assertions.assertThrows(
                InvalidParameterException.class,  () -> (new Rover()).move("ALB"));
        Assertions.assertEquals(exception.getMessage(), "Command String : ALB\n0 base index(s) are 0,2\nIncorrect command(s) are A,B");
    }
}

