package Sweeper;

import java.io.IOException;

/*по сути контроллер - управляет размерами поля,
 кол-вом бомб, обработкой нажатий и тд */
public class Game {

    private Bomb bomb;
    private Flag flag;
    private GameState state;

    //гетер статуса игры
    public GameState getState() {
        return state;
    }

    //конструктор
    public Game (int cols, int rows, int bombs) {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb (bombs);
        flag = new Flag();
        state = GameState.PLAYED;
    }

    //карта расстановки
    public void start () {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }

    //ф-ия говорит что где разместить на экране
    public Box getBox (Coord coord) {
        if (flag.get(coord) == Box.OPENED)
            return bomb.get(coord);
        else
            return flag.get(coord);
    }

    //нажатие левой кнопки мыши
    public void pressLefButton(Coord coord){
        openBox(coord);
        checkWinner();
    }

    //проверка на победу
    private void checkWinner () {
        if (state == GameState.PLAYED)
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs()) {
                state = GameState.WINNER;
                //new code
                EndGame.endGameAction();
            }
    }

    //то что происходит при нажатии левой кнопки
    private void openBox (Coord coord) {
        switch (flag.get(coord)) {
            case OPENED: setOpenedToClosedBoxesAroundNumber(coord); return;
            case FLAGED: return;
            case CLOSED:
                switch (bomb.get (coord)) {
                    case ZERO: openBoxesAround(coord); return;
                    case BOMB: openBombs(coord); return;
                    default  : flag.setOpenedToBox(coord); return;
                }
        }
    }

    //нажимая на цифру открываем клетки вокруг
    void setOpenedToClosedBoxesAroundNumber (Coord coord) {
        if(bomb.get (coord) != Box.BOMB)
            if(flag.getCountOfFlagedBoxesAround(coord) == bomb.get(coord).getNumber())
                for (Coord around : Ranges.getCoordsAround(coord))
                    if (flag.get (around) == Box.CLOSED)
                        openBox(around);

    }

    //при проигрыше
    private void openBombs (Coord bombed) {
        state = GameState.BOMBED;
        flag.setBombedToBox (bombed);
        for (Coord coord : Ranges.getAllCoords())
            if (bomb.get (coord) == Box.BOMB)
                flag.setOpenedToClosedBombBox (coord);
            else
                flag.setNobombToFlagedSafeBox (coord);
    }

    //рекурсивное открытие клеток
    private  void openBoxesAround(Coord coord) {
        flag.setOpenedToBox(coord);
        for (Coord around : Ranges.getCoordsAround(coord))
            openBox(around);
    }

    //нажатие левой кнопки мыши
    public void pressRightButton(Coord coord) {
        if(gameOver ()) return;
            flag.toggleFlagedToBox (coord);
    }

    //маркер конца игры
    private boolean gameOver() {
        if (state == GameState.PLAYED)
            return false;
        start ();
        return true;
    }


}
