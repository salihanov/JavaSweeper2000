package Sweeper;

import java.io.*;
import java.net.Socket;

public class NetClient {
    //сокет для общения
    private static Socket clientSocket;
    // нам нужен ридер читающий с консоли, иначе как
    private static BufferedReader reader;


    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void ServerMessage(String text) {
        try {
            try {
                //адрес - локальный хост, порт - 4004, такой же как у сервера
                clientSocket = new Socket("localhost", 4004);
                //этой строкой мы запрашиваем у сервера доступ на соединение
                reader = new BufferedReader(new InputStreamReader(System.in));
                //читать соообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //писать туда же
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));


                //отправляем сообщение на сервер
                out.write(text + "\n");
                out.flush();
                //ждём, что скажет сервер
                String serverWord = in.readLine();
                //получив - выводим на экран
                System.out.println(serverWord);
            }
            finally {
                // в любом случае необходимо закрыть сокет и потоки
                System.out.println("NetClient was closed...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}
