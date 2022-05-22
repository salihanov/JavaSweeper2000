package Sweeper;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class FileWrite {
    //путь к файлу
    private String path_to_file;
    //флаг запись(true) в файл
    private boolean append = false;

    //конструктор по умолчанию
    public FileWrite (String path) {
        path_to_file = path;
    }

    //конструктор
    public FileWrite (String path, boolean value) {
        path_to_file = path;
        append = value;
    }

    //запись текста text в файл
    public void TextWrite (String text) throws IOException {
        FileWriter write_to_file = new FileWriter(path_to_file, append);
        PrintWriter print = new PrintWriter(write_to_file);
        print.printf("%s" + "%n", text);
        print.close();
    }
}
