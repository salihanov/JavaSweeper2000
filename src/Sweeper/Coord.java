package Sweeper;

//класс координат поля
public class Coord {
    public int x;
    public int y;

    //конструктор
    public Coord (int x, int y) {
        this.x = x;
        this.y = y;
    }

    //равенство координат
    @Override
    public boolean equals(Object o) {
        if (o instanceof Coord) {
            Coord to = (Coord)o;
            return to.x == x && to.y== y;
        }
        return super.equals(o);
    }
}

