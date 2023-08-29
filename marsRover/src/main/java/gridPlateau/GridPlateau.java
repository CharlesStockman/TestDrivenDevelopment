package gridPlateau;

import utilities.Position;
import utilities.Terrain;
import lombok.Value;

import java.util.Objects;

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




}
