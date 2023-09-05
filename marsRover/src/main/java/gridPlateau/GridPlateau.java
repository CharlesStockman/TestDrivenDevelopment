package gridPlateau;

import utilities.Position;
import utilities.Terrain;
import lombok.Value;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represent part of martian plateau as a 2 Dimension Array where each cell has a @Link { gridPlateau.GridPlateau }
 * The rover travels the martian plateau
 */
@Value
public class GridPlateau {

    public GridPlateau(String[][] grid) {
        this.grid = grid;
    }

    String[][] grid;

    public Boolean isCellObstructed(Position position ) {
        return Objects.equals(grid[position.getX()][position.getY()], Terrain.Obstructed.name());
    }

    public Integer getLength() {
        return ( grid == null ) ?  0 : grid.length;
    }

    public Integer getWidth() {
        return ( grid == null ) ? 0 : grid[0].length;
    }

    public void setTile(Position position, String terrain ) {
        grid[position.getX()][position.getY()] = terrain;
    }

    public String toString() {

        Map<String, Long> counts =  Arrays.stream(grid).flatMap(
                string -> Stream.of(string)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for ( Terrain terrain : Terrain.values() ) {
            if ( counts.containsKey(terrain.name()) == false ) {
                counts.put(terrain.name(), 0L);
            }
        }

        long normal = counts.get(Terrain.Normal.name());
        long obstructed = counts.get(Terrain.Obstructed.name());

        String str = new String("");
        str = String.format("Gradient Plateau : shape(%dx%d) ", this.getWidth(), this.getLength());
        str = str + String.format("totals Normal:%d and Obstructed:%d", normal, obstructed);
        return str;
    }




}
