package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import java.nio.file.Paths;
import java.util.Set;

public class Model
{
    public static final int  FIELD_SELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("src/com/javarush/test/level34/lesson15/big01/res/levels.txt"));

    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects(){
        return gameObjects;
    }

    public void restartLevel(int level){
        gameObjects = levelLoader.getLevel(level);
    }
    public void restart(){
        restartLevel(currentLevel);
    }
    public void startNextLevel(){
        restartLevel(++currentLevel);
    }

    public void move(Direction direction){
        Player player = gameObjects.getPlayer();

        if (checkWallCollision(player,direction)) return;

        if (checkBoxCollision(direction))return;

        int newX = 0, newY = 0;

        switch (direction) {
            case LEFT:
                newX -= Model.FIELD_SELL_SIZE;
                break;
            case RIGHT:
                newX += Model.FIELD_SELL_SIZE;
                break;
            case UP:
                newY -= Model.FIELD_SELL_SIZE;
                break;
            case DOWN:
                newY += Model.FIELD_SELL_SIZE;
                break;
        }
        player.move(newX,newY);
        checkCompletion();

    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction){
        Set<Wall> walls = gameObjects.getWalls();
        for (Wall wall:walls) {if (gameObject.isCollision(wall, direction)) return true;}
        return false;
    }

    public boolean checkBoxCollision1(Direction direction){ // мой вариант
//        Player player = gameObjects.getPlayer();
//        int newX = 0, newY = 0;
//        // если стена
//         if (checkWallCollision(player,direction)) return true;
//
//        // если ящик
//        Set<Box> boxes = gameObjects.getBoxes(); // первый ящик
//
//
//        for (Box box:boxes){
//            if (player.isCollision(box, direction)){
//                // если за ним стена
//                if (checkWallCollision(box,direction)) return true;
//                // если за ним ящик
//                Set<Box> boxesBack = gameObjects.getBoxes(); // ящики за ящиком
//
//                for (Box boxBack :boxesBack)
//                {if (box.isCollision(boxBack, direction))
//                    return true;}
//
//                // если  столкновение произошло но сзади ничего нет двигаем
//                switch (direction) {
//                    case LEFT:
//                        newX -= Model.FIELD_SELL_SIZE;
//                        break;
//                    case RIGHT:
//                        newX += Model.FIELD_SELL_SIZE;
//                        break;
//                    case UP:
//                        newY -= Model.FIELD_SELL_SIZE;
//                        break;
//                    case DOWN:
//                        newY += Model.FIELD_SELL_SIZE;
//                        break;
//                }
//                box.move(newX,newY);
//                return false;
//            }
//        }
        return false;
    }


    public boolean checkBoxCollision(Direction direction){
        boolean res = false;
        Player player = gameObjects.getPlayer();
        int x1 = player.getX();
        int y1 = player.getY();
        int count = FIELD_SELL_SIZE;
        switch (direction){
            case LEFT:
                x1 = x1-count;
                break;
            case RIGHT:
                x1 = x1+count;
                break;
            case UP:
                y1 = y1-count;
                break;
            case DOWN:
                y1 = y1+count;
        }
        // найдем во что уперся плеяр
        GameObject  stoped = null;
        for (GameObject gameObject: gameObjects.getAll()){
            if (((x1 == gameObject.getX()) &&(y1 == gameObject.getY())) && !(gameObject instanceof Player)&& !(gameObject instanceof Home)){
                stoped = gameObject;
            }
        }
        //свободное место или дом
        if ((stoped == null)){
            return false;
        }
        if (stoped instanceof Box){
            Box stopedBox = (Box)stoped;
            if (checkWallCollision(stopedBox,direction)){
                return true;
            }
            for (Box box : gameObjects.getBoxes()){
                if(stopedBox.isCollision(box,direction)){
                    return true;
                }
            }
            switch (direction)
            {
                case LEFT:
                    stopedBox.move(-count, 0);
                    break;
                case RIGHT:
                    stopedBox.move(count, 0);
                    break;
                case UP:
                    stopedBox.move(0, -count);
                    break;
                case DOWN:
                    stopedBox.move(0, count);
            }
        }
        return false;
    }


    public void checkCompletion(){
        Set<Home> homes = gameObjects.getHomes();
        Set<Box> boxes = gameObjects.getBoxes();
        int countHomes = homes.size();
        int countBoxes = 0;

        for (Home home:homes) {
            for (Box box:boxes) {
            if (home.getX()==box.getX() && home.getY()==box.getY())
                countBoxes++;
            }
            if (countHomes==countBoxes) eventListener.levelCompleted(currentLevel);

        }

    }

}

