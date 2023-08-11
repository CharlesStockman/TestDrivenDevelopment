package dataLayer;

public class Position {

    private Integer x;
    private Integer y;

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
        Position position = new Position(x, y + 1);
        return position;
    }

    public Position moveVerticalDown() {
        Position position = new Position(x , y - 1);
        return position;
    }

    public Position moveHorizontalLeft() {
        Position position = new Position(x - 1, y);
        return position;
    }

    public Position moveHorizontalRight() {
        Position position = new Position(x + 1, y);
        return position;
    }

    public Position wrap(Integer maxX, Integer maxY) {
        Integer tmp_x = null;
        if ( x == -1 )
            tmp_x = maxX -1;
        else if ( x == maxX )
            tmp_x = 0;
        else
            tmp_x = x;

        Integer tmp_y = null;
        if ( y == -1 )
            tmp_y = maxY - 1;
        else if ( y == maxY )
            tmp_y = 0;
        else
            tmp_y = y;

        return new Position(tmp_x, tmp_y);
    }


}
