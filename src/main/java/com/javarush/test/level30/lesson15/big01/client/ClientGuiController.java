package com.javarush.test.level30.lesson15.big01.client;

public class ClientGuiController extends Client
{
    private ClientGuiModel model = new ClientGuiModel();
    private ClientGuiView view = new ClientGuiView(this);

    public class GuiSocketThread extends SocketThread
    {
//        21.4.1.	void processIncomingMessage(String message) – должен устанавливать новое
//        сообщение у модели и вызывать обновление вывода сообщений у
//        представления.
        @Override
        protected void processIncomingMessage(String message)
        {
            model.setNewMessage(message);
            view.refreshMessages();
        }

//        21.4.2.	void informAboutAddingNewUser(String userName) – должен добавлять нового
//        пользователя в модель и вызывать обновление вывода пользователей у
//        отображения.

        @Override
        protected void informAboutAddingNewUser(String userName)
        {
            model.addUser(userName);
            view.refreshUsers();
        }

//        21.4.3.	void informAboutDeletingNewUser(String userName) – должен удалять
//        пользователя из модели и вызывать обновление вывода пользователей у
//        отображения.

        @Override
        protected void informAboutDeletingNewUser(String userName)
        {
            model.deleteUser(userName);
            view.refreshUsers();
        }

//        21.4.4.	void notifyConnectionStatusChanged(boolean clientConnected) – должен вызывать
//        аналогичный метод у представления.


        @Override
        protected void notifyConnectionStatusChanged(boolean clientConnected)
        {
            view.notifyConnectionStatusChanged(clientConnected);
        }
    }

//    21.5.1.	SocketThread getSocketThread() – должен создавать и возвращать объект типа
//    GuiSocketThread.
    @Override
    protected SocketThread getSocketThread()
    {
        return new GuiSocketThread();
    }

//    21.5.2.	void run() – должен получать объект SocketThread через метод getSocketThread()
//    и вызывать у него метод run(). Разберись, почему нет необходимости вызывать
//    метод run в отдельном потоке, как мы это делали для консольного клиента.

    @Override
    public void run()
    {
        getSocketThread().run();
    }

//    21.5.3.	getServerAddress(), getServerPort(),getUserName(). Они должны вызывать
//    одноименные методы из представления (view).

    @Override
    protected String getServerAddress()
    {
        return view.getServerAddress();
    }

    @Override
    protected int getServerPort()
    {
        return view.getServerPort();
    }

    @Override
    protected String getUserName()
    {
        return view.getUserName();
    }
    //   21.6.	Объяви метод ClientGuiModel getModel(), который должен возвращать модель.

    public ClientGuiModel getModel()
    {
        return model;
    }

//    21.7.	Объяви метод main(), который должен создавать новый объект
//    ClientGuiController и вызывать у него метод run().
    public static void main(String[] args)
    {
        ClientGuiController clientGuiController = new ClientGuiController();
        clientGuiController.run();
    }
}
