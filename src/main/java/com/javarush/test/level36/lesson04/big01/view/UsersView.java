package com.javarush.test.level36.lesson04.big01.view;

import com.javarush.test.level36.lesson04.big01.bean.User;
import com.javarush.test.level36.lesson04.big01.controller.Controller;
import com.javarush.test.level36.lesson04.big01.model.ModelData;

import java.util.List;

public class UsersView implements View
{
    private Controller controller;

    @Override
    public void refresh(ModelData modelData)
    {

        System.out.println(modelData.isDisplayDeletedUserList() ? "All deleted users:": "All users:");
        List<User> users =  modelData.getUsers();
        for (int i = 0; i < users.size(); i++)
        {
            System.out.println("\t"+users.get(i));
        }
        System.out.println("===================================================");
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void fireEventShowAllUsers(){
        controller.onShowAllUsers();
    }

    public void fireEventShowDeletedUsers() {

        controller.onShowAllDeletedUsers();
    }

    public void fireEventOpenUserEditForm(long id) {
        controller.onOpenUserEditForm(id);
    }

}
