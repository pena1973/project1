package com.javarush.test.level34.lesson15.big01.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader
{
    private Path levels;

    public LevelLoader(Path levels)
    {
        this.levels = levels;
    }
    public GameObjects getLevel(int level)
    {
        Set<Box> boxes = new HashSet<Box>();
        Set<Wall> walls = new HashSet<Wall>();
        Set<Home> homes = new HashSet<Home>();
        Player player = null;
        int x0 =Model.FIELD_SELL_SIZE/2;
        int y0 =Model.FIELD_SELL_SIZE/2;
        try
        {
            BufferedReader  reader = new BufferedReader(new FileReader(levels.toFile()));
            String line;

           while (level>60) level = level-60;

            while ((line = reader.readLine()) != null){
                if (line.equals("Maze: "+level)){
                    //дальше разбираем игровое поле
                    reader.readLine();//File offset: 148C, DS:00FC, table offset: 0000
                    int sizeX = Integer.parseInt(reader.readLine().replaceFirst("Size X: ",""));//Size X: 22
                    int sizeY = Integer.parseInt(reader.readLine().replaceFirst("Size Y: ",""));//Size Y: 11

                    reader.readLine();//End: 14BD
                    reader.readLine();//Length: 50
                    reader.readLine();//

                    for (int j = 0; j < sizeY; j++)
                    {
                        String lineField =  reader.readLine();
                        char[] chars =  lineField.toCharArray();

                        for (int i = 0; i <sizeX ; i++)
                        {
                            if (chars[i]=='X'){walls.add(new Wall(i*Model.FIELD_SELL_SIZE+x0,j*Model.FIELD_SELL_SIZE+y0));}
                            else if (chars[i]=='*'){boxes.add(new Box(i*Model.FIELD_SELL_SIZE+x0,j*Model.FIELD_SELL_SIZE+y0));}
                            else if (chars[i]=='.'){homes.add(new Home(i*Model.FIELD_SELL_SIZE+x0,j*Model.FIELD_SELL_SIZE+y0));}
                            else if (chars[i]=='&')
                            {homes.add(new Home(i*Model.FIELD_SELL_SIZE+x0,j*Model.FIELD_SELL_SIZE+y0));
                             boxes.add(new Box(i*Model.FIELD_SELL_SIZE+x0,j*Model.FIELD_SELL_SIZE+y0));}
                            else if (chars[i]=='@'){player = new Player(i*Model.FIELD_SELL_SIZE+x0,j*Model.FIELD_SELL_SIZE+y0);}
                        }

                    }
                     break; // дальше не ищем
                }

            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return new GameObjects(walls,boxes,homes,player);

    }
}
