package Sweeper;

//класс нижней матрицы бомб и чисел
class Bomb {
    //матрица
    private Matrix bombMap;
    //общее кол-во бомб
    private int totalBombs;

    //конструктор
    Bomb (int totalBombs){
        this.totalBombs = totalBombs;
        fixBombCount();
    }

    //новое поле
    void start () {
        bombMap = new Matrix(Box.ZERO);
        for(int j =0; j < totalBombs; j++)
            placeBomb ();
    }

    //гетер объекта на матрице
    Box get (Coord coord) {
        return bombMap.get (coord);
    }

    //бомб не более половины от всех клеток
    private void fixBombCount () {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs)
            totalBombs = maxBombs;
    }

    //размещение бомб в матрице
    private void placeBomb (){
        while (true) {
            Coord coord = Ranges.getRnadomCoord();
            if (Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set (coord, Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }
    }

    //при размещении бомбы +1 к клеткам вокруг
    private void incNumbersAroundBomb (Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord))
            if (Box.BOMB != bombMap.get (around))
                bombMap.set (around, bombMap.get(around).getNextNumberBox());
    }

    //гетер общего кол-ва бомб
    int getTotalBombs() {
        return totalBombs;
    }
}
