package myRider;

import javax.swing.text.DefaultStyledDocument;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Sid927 on 19.09.2016.
 */
public class Model
{
    // Хранение базы словаря
    Map<String,String> map = new TreeMap<>();

    // добавляет слово
    public void addWord(String pair){
       String[] words = pair.split("-");
       map.put(words[0].trim(),words[1].trim());

    }

    public String getTranslate(String wrd){

        return map.get(wrd.trim());

    }


    // добавляет слова
    public void addWords(String text){
        map.clear();
        String[] pairs = text.split("\n");
        for (int i = 0; i < pairs.length; i++)
        {
            String[] words = pairs[i].split("-");
            map.put(words[0].trim(),words[1].trim());
        }
    }

    // существует слово
    public boolean isWordExist(String word)
    {
        if (map.get(word.trim()) != null)
            return true;
        else
            return false;
    }
    // удаляет слово
    public void remoteWord(){
        // не написано
    }

}
