package Sweeper;

import javax.swing.*;
import java.io.IOException;

public class EndGame {
    //новое окно для записи никнейма
    static JFrame enterFrame = new JFrame();

    public static void endGameAction() {
        //вывод окна записи имени
        String getName = JOptionPane.showInputDialog(enterFrame, "Enter your name");
        //объект FileWrite, с адресом файла записи
        FileWrite line = new FileWrite("C:\\Users\\Acer\\IdeaProjects\\Text\\out.txt", true);
        //запись имени в файл (обернута в try/catch)
        try {line.TextWrite(getName);} catch (IOException e) {e.printStackTrace();}
        //отправка сообщения на сервер
        NetClient.ServerMessage(getName);
        //окно успешного добавления в таблицу победителей
        JOptionPane.showMessageDialog(enterFrame, "My congratulations, " + getName + "!");
    }
}
