import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Sweeper.Box;
import Sweeper.Coord;
import Sweeper.Game;
import Sweeper.Ranges;

//наследование от JFrame - чтоб прога была окошком
public class JavaSweeper extends JFrame {
    public static void main(String[] args) {
        new JavaSweeper().setVisible(true);
    }

    private Game game;

    private JPanel panel;
    private JLabel label;

    //константы
    private final int COLS = 15;        //кол-во столбцов
    private final int ROWS = 15;         //кол-во строк
    private final int BOMBS = 25;       //кол-во бомб
    private final int IMAGE_SIZE = 50;  //размер поля

    //конструктор по умолчанию
    private JavaSweeper () {
        //новый экземпляр игры
        game = new Game (COLS, ROWS, BOMBS);
        game.start();
        //класс Ranges статичный и можем бращаться не создавая экземпляр
        Ranges.setSize (new Coord (COLS, ROWS));

        //установка картинок
        setImages();

        //инициализация панелей и окна
        initLabel();
        initPanel();
        initFrame();
    }

    //метод отрисовки панели состояния игры
    private void initLabel() {
        label = new JLabel("!!WELCOME!!");
        add (label, BorderLayout.SOUTH);
    }

    //генерация сообщения в нижней панели
    private String getMessage() {
        switch (game.getState()) {
            case PLAYED: return "BE CAREFUL!";
            case BOMBED: return "Oh, now! BOMB!";
            case WINNER: return  "CONGRATULATIONS!";
            default: return "!!WELCOME!!";
        }
    }

    //метод отрисовывающий окно
    private void initFrame() {
        //задает минимальный размер окна для размещения всех элементов
        pack();
        //остановка программы с закрытием окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //заголовок
        setTitle("Java Sweeper");
        //размещаем по центру
        setLocationRelativeTo(null);
        //постоянный размер
        setResizable(false);
        //видимый
        setVisible(true);
        //иконка при запуске
        setIconImage(getImage("icon"));
    }

    //метод отрисовывающий панель
    public void initPanel() {
        //новый объект
        panel = new JPanel() {
            //отрисовка бомбы
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) {

                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x * IMAGE_SIZE,
                            coord.y * IMAGE_SIZE,
                            this );
                }


            }
        };

        //считывание нажатий мышки
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //координата x поля нажатия
                int x = e.getX() / IMAGE_SIZE;
                //координата y поля нажатия
                int y = e.getY() / IMAGE_SIZE;
                //объект класса Coord c координатами нажатия
                Coord coord = new Coord (x, y);

                //левая кнопка
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLefButton (coord);
                panel.repaint();

                //правая кнопка
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton (coord);
                panel.repaint();

                //средняя кнопка -> рестарт
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start ();
                label.setText(getMessage());
                panel.repaint();
            }
        });

        //задать размер
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        //добавляет панель на окно
        add (panel);
    }

    //метод подгружающий картинки
    public void setImages () {
        for (Box box : Box.values())
            box.image = getImage(box.name());
    }

    //метод загружающий картинки с ресурса
    protected Image getImage(String name) {
        String filename = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}
