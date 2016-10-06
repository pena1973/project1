package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endX) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution
{
    public static void main(String[] args)
    {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}


        };
        //detectAllWords(crossword, "home", "same");
        List<Word> rezList = detectAllWords(crossword, "home", "same", "emoh", "emas", "fderlk", "klredf", "fulmp", "poeejj", "jjeeop",
                "pmluf", "kovhj", "jhvok", "lprr", "rrpl", "lprr", "voel", "lock", "r", "re", "eo", "oe", null, "", " ");
        //  detectAllWords(crossword, "", null);
        /*
        Ожидаемый результат
        home - (5, 3) - (2, 0)
        same - (1, 1) - (4, 1)
         */
        for (int i = 0; i < rezList.size(); i++)
        {
            System.out.println(rezList.get(i));
        }
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words)
    {
        List<Word> rezList = new ArrayList<>(); // здесь храним результат
        Word w;// готовим переменную

        // считываем массив искомых слов

        for (String word0 : words)
        {
            //  listWord.add(word0);
            if (word0 == null) continue;

            // берем слово и ищем первую букву в матрице

            char[] arrWord = word0.toCharArray();

            if (arrWord.length == 0) continue;

            // int count = 0;
            for (int i = 0; i < crossword.length; i++)
            {
                for (int j = 0; j < crossword[i].length; j++)
                {
                    if (crossword[i][j] == arrWord[0]) // нашли первую букву
                    {
                        // берем вторую букву и ищем направление, если совпало
                        // считываем слово в направлении, сравниваем со словом - если совпало пишем в массив

                        //вверх
                        if ((w = searchWord(crossword, arrWord, i, j, i - 1, j)) != null)
                        {
                            rezList.add(w);
                        }
                        //вниз
                        else if ((w = searchWord(crossword, arrWord, i, j, i + 1, j)) != null)
                        {
                            rezList.add(w);
                        }
                        //влево
                        else if ((w = searchWord(crossword, arrWord, i, j, i, j - 1)) != null)
                        {
                            rezList.add(w);
                        }
                        //вправо
                        else if ((w = searchWord(crossword, arrWord, i, j, i, j + 1)) != null)
                        {
                            rezList.add(w);
                        }
                        // по диагонали вверх влево
                        else if ((w = searchWord(crossword, arrWord, i, j, i - 1, j - 1)) != null)
                        {
                            rezList.add(w);
                        }
                        // по диагонали вверх вправо
                        else if ((w = searchWord(crossword, arrWord, i, j, i - 1, j + 1)) != null)
                        {
                            rezList.add(w);
                        }
                        // по диагонали вниз влево
                        else if ((w = searchWord(crossword, arrWord, i, j, i + 1, j - 1)) != null)
                        {
                            rezList.add(w);
                        }
                        // по диагонали вниз вправо
                        else if ((w = searchWord(crossword, arrWord, i, j, i + 1, j + 1)) != null)
                        {
                            rezList.add(w);
                        }

                    }
                }
            }
        }
        return rezList;
    }

    private static Word searchWord(int[][] crossword, char[] arrWord, int i1, int j1, int i2, int j2)
    {
        // определяю правильное ли направление и если нет возвращаю null

        // нарушена граница массива
        if ((crossword.length - 1) < i2) return null;
        if ((crossword[i1].length - 1) < j2) return null;
        if (i2 < 0) return null;
        if (j2 < 0) return null;
        // проверяю может слово из одной буквы тогда ее же и возвращаю
        if (arrWord.length == 1)
        {
            String wordStr = "" + arrWord[0];

            Word word = new Word(wordStr);
            word.setStartPoint(j1, i1);
            word.setEndPoint(j1, i1);
            return word;
        }
        // не совпадает вторая буква
        if (crossword[i2][j2] != arrWord[1]) return null;

        //если прошло проверку на направление проверяю все слово
        // здесь я ищу слово в нужном направлении и если нацйдено создаю его и возвращаю
        int count = 0;   // Счетчик символов в слове
        int length = arrWord.length - 1; // длина искомого слова
        int finishI = i2; // конечная точка по вертикали
        int finishJ = j2; // конечная точка по горизонтали
        int startI = i1; // конечная точка по вертикали
        int startJ = j1; // конечная точка по горизонтали
        boolean wordFound = true;

        // сдвиги координат при прохождении цикла
        int shiftI = 0;
        int shiftJ = 0;
        // плюсовое направление
        if (i1 < i2)
        {
            finishI = i1 + length;
            shiftI = 1;
        } // если сдвиг направления есть определяем счетчик

        if (j1 < j2)
        {
            finishJ = j1 + length;
            shiftJ = 1;
        }// если сдвиг направления есть определяем счетчик

        // минусовое направление
        if (i1 > i2)
        {
            finishI = i1 - length;
            shiftI = -1;
            startI = i1;
        } // если сдвиг направления есть определяем счетчик

        if (j1 > j2)
        {
            finishJ = j1 - length;
            shiftJ = -1;
            startJ = j1;
        } // если сдвиг направления есть определяем счетчик

        //проверяем не выходят ли координаты за кросворд
        // есди выходят - не то направление
        if (finishI >= crossword.length) return null;
        if (finishI < 0) return null;
        if (finishJ >= crossword[finishI].length) return null;
        if (finishJ < 0) return null;
        if (startI >= crossword.length) return null;
        if (startI < 0) return null;
        if (startJ >= crossword[startI].length) return null;
        if (startJ < 0) return null;

        // читаю слово если все буквы совпадают
        for (int i = 0; i < arrWord.length; i++)
        {
            if (arrWord[i] != crossword[startI + i * shiftI][startJ + i * shiftJ])
            {
                wordFound = false;
            }
        }

        if (wordFound)
        {
            String wordStr = "";
            for (int i = 0; i < arrWord.length; i++) wordStr = wordStr + arrWord[i];


            Word word = new Word(wordStr);
            word.setStartPoint(startJ, startI);
            word.setEndPoint(finishJ, finishI);
            return word;
        } else return null;
    }

    public static class Word
    {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text)
        {
            this.text = text;
        }

        public void setStartPoint(int i, int j)
        {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j)
        {
            endX = i;
            endY = j;
        }

        @Override
        public String toString()
        {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
