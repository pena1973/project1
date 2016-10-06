package com.javarush.test.level40.lesson08.home01;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/* Сокетный сервер и клиент
Есть сервер, он принимает входящиие сообщения от клиентов и отвечает им echo.
Есть клиенты, они считывают сообщения с клавиатуры и отправляют их серверу.
Программа запускается, но не работает.
Разберись в чем проблема, внеси минимальные изменения в код, чтобы все заработало.
*/

public class Connection implements Closeable {
    private final Socket socket;
   // private final ObjectInputStream in;
   // private final ObjectOutputStream out;

    public Connection(Socket socket) throws Exception {
        this.socket = socket;
        //this.in = new ObjectInputStream(socket.getInputStream());
        //this.out = new ObjectOutputStream(socket.getOutputStream());
    }

    public void send(String message) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(message);
    }

    public String receive() throws Exception {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        return (String) in.readObject();
    }

    @Override
    public void close() throws IOException {
      //  in.close();
      //  out.close();
        socket.getOutputStream().close();
        socket.getInputStream().close();
        socket.close();
    }
}
