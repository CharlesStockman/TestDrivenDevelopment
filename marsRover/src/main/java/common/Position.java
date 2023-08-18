package common;

import lombok.Data;

/*
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

    public Position moveVerticalUp() {
        return new Position(x, y + 1);
    }

    public Position moveVerticalDown() {
        return new Position(x , y - 1);
    }

    public Position moveHorizontalLeft() {
        return new Position(x - 1, y);
    }

    public Position moveHorizontalRight() {
        return new Position(x + 1, y);
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
