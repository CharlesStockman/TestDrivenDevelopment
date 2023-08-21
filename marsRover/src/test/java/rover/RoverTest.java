package rover;

import gridPlateau.GridPlateau;
import common.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import rover.CommandPatterns.History;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class RoverTest {

    private final CompassPoint startingCompassDirection = CompassPoint.N;
    private final Position  startingPosition = new Position(0,0);

    @Test
    public void displayDefaultValues() {
        Assertions.assertEquals("0:0:N", (new Rover()).displayCoordinatesAndDirection(CompassPoint.N, new Position(0,0), false));
    }

    @Test
    public void displayObstructedPosition() {
        Assertions.assertEquals("O:0:0:N", new Rover().displayCoordinatesAndDirection(CompassPoint.N, new Position(0,0), true ));
    }

    @Test
    public void inputMoveForObstacle() {
        GridPlateau gridPlateau = GridPlateauFactory.create_standard_grid_with_obstructed_list(new Position(0,2));
        Assertions.assertEquals("O:0:1:N", (new Rover(gridPlateau)).move("MM", startingCompassDirection, startingPosition));
    }

    @Test
    public void inputMoveCommandsIsEmptyString() {
        Assertions.assertEquals("0:0:N", (new Rover()).move("", startingCompassDirection, startingPosition));
    }

    @Test
    public void inputMoveCommandsIsNullString() {
        NullPointerException exception =
                Assertions.assertThrows(NullPointerException.class, () -> (new Rover()).move(null, startingCompassDirection, startingPosition));
        Assertions.assertEquals(exception.getMessage(), "The input to the move function is null");
    }

    @ParameterizedTest
    @CsvSource({"L, 0:0:W", "LL, 0:0:S", "LLL, 0:0:E", "LLLL, 0:0:N",
                "R, 0:0:E", "RR, 0:0:S", "RRR, 0:0:W", "RRRR, 0:0:N"})
    public void inputMoveCommandRotate(String command, String result) {
        Assertions.assertEquals(result, (new Rover()).move(command, startingCompassDirection, startingPosition));
    }

    @ParameterizedTest
    @CsvSource({"M, 0:1:N", "RM, 1:0:E", "MLLM, 0:0:S", "RMLLM, 0:0:W"})
    public void inputMoveCommandMove(String command, String result) {
        Assertions.assertEquals(result, (new Rover()).move(command, startingCompassDirection, startingPosition));
    }

    @ParameterizedTest
    @CsvSource({"MMMMMMMMMM, 0:0:N", "MMMMMMMMMM, 0:0:N", "LLM, 0:9:S", "LM, 9:0:W", "RMMMMMMMMMM, 0:0:E"})
    public void inputMoveCommandMoveGoesOffGrid(String command, String result) {
        Assertions.assertEquals(result, (new Rover()).move(command, startingCompassDirection, startingPosition));
    }

    //
    // Test from the Specifications
    //
    @Test
    public void gridWithNoObstacles() {
        Rover rover = new Rover(GridPlateauFactory.create_standard_grid_with_no_obstructions());
        Assertions.assertEquals("2:3:N", rover.move("MMRMMLM", startingCompassDirection, startingPosition));
    }

    @Test
    public void gridWithWrap() {
        Rover rover = new Rover(GridPlateauFactory.create_standard_grid_with_no_obstructions());
        Assertions.assertEquals("0:0:N", rover.move("MMMMMMMMMM", startingCompassDirection, startingPosition));
    }

    @Test
    public void gridWithObstacle() {
        GridPlateau gridPlateau = GridPlateauFactory.create_standard_grid_with_obstructed_list(new Position(0,3));
        Assertions.assertEquals("O:0:2:N", (new Rover(gridPlateau)).move("MMMM", startingCompassDirection, startingPosition));
    }

    /// End of sample examples

    @Test
    public void gridDisplayHistoryUptoThreeMoves() {
        GridPlateau gridPlateau = GridPlateauFactory.create_standard_grid_with_obstructed_list(new Position(0,3));
        Rover rover = new Rover(gridPlateau);
        Assertions.assertEquals("0:1:W", rover.move("ML", startingCompassDirection, startingPosition));

        List<History.Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(new History.Event( String.valueOf(Character.MIN_VALUE), CompassPoint.N, new Position(0,0)));
        expectedEvents.add(new History.Event( "M", CompassPoint.N, new Position(0,1)));
        expectedEvents.add(new History.Event( "L", CompassPoint.E, new Position(0,1)));

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
    public void moveArgumentInvalidLowerCase() {
        Rover rover = new Rover();
        Assertions.assertEquals("1:0:E", rover.move("rm", startingCompassDirection, startingPosition));
    }

    @Test
    void moveArgumentInvalidOneMistake() {
        InvalidParameterException exception = Assertions.assertThrows(
                InvalidParameterException.class,  () -> (new Rover()).move("A", startingCompassDirection, startingPosition));
        Assertions.assertEquals(exception.getMessage(), "Command String : A\n0 base index(s) are 0\nIncorrect command(s) are A");
    }

    @Test
    void moveArgumentInvalidTwoMistakes() {
        InvalidParameterException exception = Assertions.assertThrows(
                InvalidParameterException.class,  () -> (new Rover()).move("ALB", startingCompassDirection, startingPosition));
        Assertions.assertEquals(exception.getMessage(), "Command String : ALB\n0 base index(s) are 0,2\nIncorrect command(s) are A,B");
    }
}

