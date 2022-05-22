package Sweeper;

class Flag {
    //внешняя матрица
    private Matrix flagMap;
    //кол-во закрытых клеток
    private int countOfClosedBoxes;

    void start () {
        flagMap = new Matrix(Box.CLOSED);
        //кол-во закрытых клеток = x*y
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get (Coord coord) {
        return flagMap.get (coord);
    }

    //открыть клетку (прозрачный верхний слой)
    public void setOpenedToBox(Coord coord) {
        flagMap.set (coord, Box.OPENED);
        countOfClosedBoxes--;
    }

    //при нажатии правой кнопки мыши: флаг <-> закрыто
    public void toggleFlagedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGED : setClosedToBox (coord); break;
            case CLOSED : setFlagedToBox (coord); break;
        }
    }

    //начальное размещение закрывающих полей
    private void setClosedToBox(Coord coord) {
        flagMap.set (coord, Box.CLOSED);
    }

    //разместить флаг на клетку
    private void setFlagedToBox(Coord coord) {
        flagMap.set (coord, Box.FLAGED);
    }

    //сосчитать закрытые клетки (проверка на выигрыш)
    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    //разместить изображения подрыва
    void setBombedToBox(Coord coord) {
        flagMap.set (coord, Box.BOMBED);
    }

    //раскрыть ненайденные бомбы
    void setOpenedToClosedBombBox(Coord coord) {
        if (flagMap.get (coord) == Box.CLOSED)
            flagMap.set (coord, Box.OPENED);
    }

    //разместить изображение "нет бомбы" на неверно расставленные флаги
    void setNobombToFlagedSafeBox(Coord coord) {
        if (flagMap.get (coord) == Box.FLAGED)
            flagMap.set (coord, Box.NOBOMB);
    }

    //подсчет помеченных клеток вокруг
    int getCountOfFlagedBoxesAround(Coord coord) {
        int count = 0;
        for (Coord around : Ranges.getCoordsAround(coord))
            if (flagMap.get(around) == Box.FLAGED)
                count++;
        return count;
    }
}
