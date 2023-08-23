//package rover.CommandPatterns.internalCommands;
//
//import common.Position;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import rover.CommandPatterns.TriFunction;
//import rover.CommandPatterns.userCommands.ChangeDirectionCommandLeft;
//import rover.CommandPatterns.userCommands.ChangeDirectionCommandRight;
//import rover.CommandPatterns.userCommands.MoveCommand;
//import rover.CompassPoint;
//import rover.GridPlateauFactory;
//import rover.RoverData;
//
//import java.util.ArrayList;
//import java.util.function.Function;
//
//public class ExecuteCommandsTest {
//
//    @Test
//    public void verifySignatureOfExecuteCommand() {
//        ExecuteCommands executeCommands = new ExecuteCommands("LLLLM", CompassPoint.N, new Position(0,0 ));
//        RoverData roverData = executeCommands.execute();
//
//        //TriFunction<String, CompassPoint, Position, RoverData>
//
//        Assertions.assertEquals(new Position(0,1), roverData.getPosition());
//        Assertions.assertEquals(CompassPoint.N, roverData.getCompassPoint());
//        Assertions.assertEquals(Boolean.FALSE, roverData.getIsObstructed());
//    }
//
//    @Test
//    public void executeCommands() {
//
//        ChangeDirectionCommandLeft  changeDirectionCommandLeft = new ChangeDirectionCommandLeft();
//        ChangeDirectionCommandRight changeDirectionCommandRight = new ChangeDirectionCommandRight();
//        MoveCommand moveCommand = new MoveCommand(GridPlateauFactory.create_standard_grid_with_no_obstructions());
//
//        ArrayList<Function<RoverData> functions = new ArrayList<>();
//        functions.add(changeDirectionCommandLeft::execute);
//        functions.add(changeDirectionCommandRight::execute);
//        functions.add(changeDirectionCommandLeft::execute);
//        functions.add(changeDirectionCommandRight::execute);
//        functions.add(moveCommand::execute);
//
//        RoverData roverData = new RoverData(CompassPoint.N, new Position(0,0), false);
//
//        ExecuteCommands executeCommands = new ExecuteCommands(String "LLLLM", CompassPoint.N, new Position(0,0));
//        roverData = executeCommands.executeCommands(roverData, functions);
//
//        Assertions.assertEquals(new Position(0,1), roverData.getPosition());
//        Assertions.assertEquals(CompassPoint.N, roverData.getCompassPoint());
//        Assertions.assertEquals(Boolean.FALSE, roverData.getIsObstructed());
//    }
//}
