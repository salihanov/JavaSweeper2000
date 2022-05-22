package Sweeper;

class Matrix {
    private Box [] [] matrix;

    //конструктор
    Matrix (Box defaultBox) {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coord coord : Ranges.getAllCoords())
            matrix [coord.x][coord.y] = defaultBox;
    }

    //гетер
    Box get (Coord coord) {
        if (Ranges.inRange (coord))
            return matrix [coord.x][coord.y];
        return null;
    }

    //сетер
    void set (Coord coord, Box box) {
        if (Ranges.inRange (coord))
            matrix [coord.x][coord.y] = box;
    }


}
