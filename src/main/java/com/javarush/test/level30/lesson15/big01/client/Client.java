package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.Connection;
import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client
{
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public class SocketThread extends Thread
    {
            protected void processIncomingMessage(String message){
                ConsoleHelper.writeMessage(message);
            }
            protected void informAboutAddingNewUser(String userName){
                ConsoleHelper.writeMessage("Участник с именем " + userName + " присоединился к чату");
            }
            protected void informAboutDeletingNewUser(String userName){
                ConsoleHelper.writeMessage("Участник с именем " + userName + " покинул чат");
            }

            protected void notifyConnectionStatusChanged(boolean clientConnected){
                Client.this.clientConnected = clientConnected;

                synchronized (Client.this) {
                    Client.this.notify();
                }
            }
            protected void clientHandshake() throws IOException, ClassNotFoundException{
                while (true) {
                    Message message = connection.receive();
                    if (message.getType() == MessageType.NAME_REQUEST) {
                        String userName = getUserName();
                        connection.send(new Message(MessageType.USER_NAME, userName));
                    }
                    else if (message.getType() == MessageType.NAME_ACCEPTED) {
                        notifyConnectionStatusChanged(true);
                        return;
                    }
                    else {
                        throw new IOException("Unexpected MessageType");
                    }
                }
            }


        protected void clientMainLoop() throws IOException, ClassNotFoundException {

            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    processIncomingMessage(message.getData());
                }
                else if (message.getType() == MessageType.USER_ADDED) {
                    informAboutAddingNewUser(message.getData());
                }
                else if (message.getType() == MessageType.USER_REMOVED) {
                    informAboutDeletingNewUser(message.getData());
                }
                else {
                    throw new IOException("Unexpected MessageType");
                }
            }

        }

        public void run(){
        String adress = getServerAddress();
        int port = getServerPort();

            try
            {
                Socket socket =  new Socket(adress,port);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }
            catch (IOException e)
            {
                notifyConnectionStatusChanged(false);
            }
            catch (ClassNotFoundException e)
            {
                notifyConnectionStatusChanged(false);
            }


        }
    }
    protected String getServerAddress()
    {
        ConsoleHelper.writeMessage("Please write IP");
        return ConsoleHelper.readString();
    }

    protected int getServerPort()
    {
        ConsoleHelper.writeMessage("Please write port");
        return ConsoleHelper.readInt();
    }

    protected String getUserName()
    {
        ConsoleHelper.writeMessage("Please write your name");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSentTextFromConsole()
    {
        return true;
    }

    protected SocketThread getSocketThread()
    {
        return new SocketThread();
    }

    protected void sendTextMessage(String text)
    {
        try
        {
            connection.send(new Message(MessageType.TEXT, text));
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("Connection error");
            clientConnected = false;
        }
    }

    public void run()
    {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try
        {
            synchronized (this)
            {
                this.wait();
            }
        }
        catch (InterruptedException e)
        {
            ConsoleHelper.writeMessage("Wait error");
            return;
        }

        if (clientConnected)
        {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        } else
        {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }

        String message;
        while (clientConnected)
        {
            if (!(message = ConsoleHelper.readString()).equalsIgnoreCase("exit"))
            {
                if (shouldSentTextFromConsole())
                    sendTextMessage(message);
            }
            else break;
        }
    }
    public static void main(String[] args)
    {
        Client client = new Client();
        client.run();
    }

}