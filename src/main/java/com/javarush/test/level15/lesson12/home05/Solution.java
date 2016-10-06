package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {

    public Solution(){}
    //public Solution(int a){}
    public Solution(float a){}
    public Solution(double a){}

    protected Solution(Integer a){}
    protected Solution(Float a){}
    protected Solution(Double a){}

    private Solution(String a){}
    private Solution(boolean a){}
    private Solution(char a){}

    Solution(Object a){}
    Solution(Character a){}
    Solution(Boolean a){}

}

