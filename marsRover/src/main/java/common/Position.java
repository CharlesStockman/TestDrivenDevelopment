package common;

import lombok.Data;

/*
 * To model a cell from the @Link { gridPlateau.GridPlateau }
 *
 * Note need @Data for the equals() member function so JUnit equal will not compare the references, but the data.
 */
@Data
public class Position {

    private final Integer x;
    private final Integer y;

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public static Position  moveVerticalUp(Position position) {
        return new Position(position.getX(), position.getY() + 1);
    }

    public static Position moveVerticalDown(Position position) {
        return new Position(position.getX() , position.getY() - 1);
    }

    public static Position moveHorizontalLeft(Position position) {
        return new Position(position.getX() - 1, position.getY());
    }

    public static Position moveHorizontalRight(Position position) {
        return new Position(position.getX() + 1, position.getY());
    }

    public Position wrap(Integer maxX, Integer maxY) {
        int tmp_x;
        if ( x == -1 )
            tmp_x = maxX -1;
        else if (x.equals(maxX))
            tmp_x = 0;
        else
            tmp_x = x;

        int tmp_y;
        if ( y == -1 )
            tmp_y = maxY - 1;
        else if (y.equals(maxY))
            tmp_y = 0;
        else
            tmp_y = y;

        return new Position(tmp_x, tmp_y);
    }
}
