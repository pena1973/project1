package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Server
{
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message)
    {
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet())
        {
            try
            {
                entry.getValue().send(message);
            }
            catch (IOException e)
            {
                ConsoleHelper.writeMessage("Пользователю " + entry.getKey() + " не могу отправить сообщение");
            }
        }
    }

    private static class Handler extends Thread
    {
        private Socket socket;

        public Handler(Socket socket)
        {
            this.socket = socket;
        }

//        Реализация метода должна:
//        8.1.	Сформировать и отправить команду запроса имени пользователя
//        8.2.	Получить ответ клиента
//        8.3.	Проверить, что получена команда с именем пользователя
//        8.4.	Достать из ответа имя, проверить, что оно не пустое и пользователь с таким именем
//        еще не подключен (используй connectionMap)
//        8.5.	Добавить нового пользователя и соединение с ним в connectionMap
//        8.6.	Отправить клиенту команду информирующую, что его имя принято
//        8.7.	Если какая-то проверка не прошла, заново запросить имя клиента
//        8.8.	Вернуть принятое имя в качестве возвращаемого значения

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException
        {
            while (true)
            {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();

                if (message.getType() == MessageType.USER_NAME)
                {
                    if (message.getData() != null && !message.getData().isEmpty())
                    {
                        if (connectionMap.get(message.getData()) == null)
                        {
                            connectionMap.put(message.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return message.getData();
                        }
                    }
                }
            }
        }

//        9.2.	 Пройтись по connectionMap
//        9.3.	 У каждого элемента из п.9.2 получить имя клиента, сформировать команду с типом
//        USER_ADDED и полученным именем
//        9.4.	 Отправить сформированную команду через connection
//        9.5.	 Команду с типом USER_ADDED и именем равным userName отправлять не нужно,
//        пользователь и так имеет информацию о себе

        private void sendListOfUsers(Connection connection, String userName) throws IOException
        {
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet())
            {
                String nameClient = entry.getKey();
                if (!nameClient.equals(userName))
                {
                    connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
                }
            }
        }

//        10.1.	 Принимать сообщение клиента
//        10.2.	 Если принятое сообщение – это текст (тип TEXT), то формировать новое
//        текстовое сообщение путем конкатенации: имени клиента, двоеточия, пробела и
//        текста сообщения. Например, если мы получили сообщение с текстом "привет чат" от
//        пользователя "Боб", то нужно сформировать сообщение "Боб: привет чат".
//            10.3.	 Отправлять сформированное сообщение всем клиентам с помощью метода
//        sendBroadcastMessage.
//        10.4.	 Если принятое сообщение не является текстом, вывести сообщение об ошибке
//        10.5.	 Организовать бесконечный цикл, внутрь которого перенести функционал
//        пунктов 10.1-10.4.

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message message = connection.receive();

                if (message.getType() == MessageType.TEXT)
                {
                    Message newMessage = new Message(MessageType.TEXT, userName.concat(": ").concat(message.getData()));
                    sendBroadcastMessage(newMessage);
                } else ConsoleHelper.writeMessage("Ошибка сообщение не текст");
            }
        }

//        Он должен:
//            11.1.	Выводить сообщение, что установлено новое соединение с удаленным
//        адресом, который можно получить с помощью метода getRemoteSocketAddress
//        11.2.	Создавать Connection, используя поле Socket
//        11.3.	Вызывать метод, реализующий рукопожатие с клиентом, сохраняя имя нового
//            клиента
//        11.4.	Рассылать всем участникам чата информацию об имени присоединившегося
//        участника (сообщение с типом USER_ADDED). Подумай, какой метод подойдет для
//        этого лучше всего.
//        11.5.	Сообщать новому участнику о существующих участниках
//        11.6.	Запускать главный цикл обработки сообщений сервером
//        11.7.	Обеспечить закрытие соединения при возникновении исключения
//        11.8.	Отловить все исключения типа IOException и ClassNotFoundException, вывести в
//        консоль информацию, что произошла ошибка при обмене данными с удаленным
//            адресом
//        11.9.	После того как все исключения обработаны, если п.11.3 отработал и возвратил
//        нам имя, мы должны удалить запись для этого имени из connectionMap и разослать
//        всем остальным участникам сообщение с типом USER_REMOVED и сохраненным
//        именем.
//        11.10.	Последнее, что нужно сделать в методе run() – вывести сообщение,
//        информирующее что соединение с удаленным адресом закрыто.
//        Наш сервер полностью готов. Попробуй его запустить.

        public void run(){

            ConsoleHelper.writeMessage("Установлено соединение с адресом " + socket.getRemoteSocketAddress());

            String userName = null;
            try (Connection connection = new Connection(socket))
            {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            }
            catch (IOException e)
            {
                ConsoleHelper.writeMessage("Ошибка обмена с адресом: " + socket.getRemoteSocketAddress());
            }
            catch (ClassNotFoundException e)
            {
                ConsoleHelper.writeMessage("Ошибка обмена с адресом: " + socket.getRemoteSocketAddress());
            }
            finally
            {
                if (userName != null)
                {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
            }
            ConsoleHelper.writeMessage("Закрыто соединение с удаленным адресом: " + socket.getRemoteSocketAddress());
        }
    }

    public static void main(String[] args)
    {
        ConsoleHelper.writeMessage("Введите порт сервера");
        int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(port);)
        {
            ConsoleHelper.writeMessage("Сервер запущен");
            while (true)
            {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("Ошибка сокета");
        }
    }
}

