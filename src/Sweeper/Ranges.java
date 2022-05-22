package Sweeper;

import java.util.ArrayList;
import java.util.Random;

//класс поля
public class Ranges {
    //размер поля
    private static Coord size;
    //итератор (массив всех координат)
    private static ArrayList<Coord> allCoords;
    //генератор слу чисел для разиещ бомб
    private static Random random = new Random();


    //сетер
    public static void setSize(Coord _size) {
        size = _size;
        allCoords = new ArrayList<Coord>();
        for (int y = 0; y < size.y; y++)
            for (int x = 0; x < size.x; x++)
                allCoords.add(new Coord (x, y));
    }

    //гетер
    public static Coord getSize() {
        return size;
    }

    //возвращает список всех координат
    public static ArrayList<Coord> getAllCoords () {
        return allCoords;
    }

    //проверка нахождения в границах поля
    static boolean inRange (Coord coord) {
        return coord.x >= 0 && coord.x < size.x &&
                coord.y >= 0 && coord.y < size.y;
    }

    //случайная клетка в границах поля
    static Coord getRnadomCoord () {
        return new Coord (random.nextInt(size.x),
                          random.nextInt(size.y));
    }

    //все клетки вокруг данной
    static ArrayList<Coord> getCoordsAround (Coord coord) {
        Coord around;
        ArrayList<Coord> list = new ArrayList<Coord>();
        for(int x = coord.x - 1; x <= coord.x + 1; x++)
            for(int y = coord.y - 1; y <= coord.y + 1; y++)
                if (inRange(around = new Coord (x, y)))
                    if (!around.equals(coord))
                        list.add (around);
        return list;
    }
}
