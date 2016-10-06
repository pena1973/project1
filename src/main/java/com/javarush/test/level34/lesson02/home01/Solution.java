package com.javarush.test.level34.lesson02.home01;

import java.lang.annotation.Documented;
import java.text.DecimalFormat;

/* Рекурсия для мат.выражения
На вход подается строка - математическое выражение.
Выражение включает целые и дробные числа, скобки (), пробелы, знак отрицания -, возведение в степень ^, sin(x), cos(x), tan(x)
Для sin(x), cos(x), tan(x) выражение внутри скобок считать градусами, например, cos(3 + 19*3)=0.5
Степень задается так: a^(1+3) и так a^4, что эквивалентно a*a*a*a.
С помощью рекурсии вычислить выражение и количество математических операций. Вывести через пробел результат в консоль.
Результат выводить с точностью до двух знаков, для 0.33333 вывести 0.33, использовать стандартный принцип округления.
Не создавайте статические переменные и поля класса.
Не пишите косвенную рекурсию.
Пример, состоящий из операций sin * - + * +:
sin(2*(-5+1.5*4)+28)
Результат:
0.5 6
*/
public class Solution
{
    public static void main(String[] args)
    {
        Solution solution = new Solution();
        solution.recursion("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6

        String s = "sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output 0.5 6 actually ");
        solution.recursion(s, 0);
        s = "tan(45)";
        System.out.print(s + " expected output 1 1 actually ");
        solution.recursion(s, 0);
        s = "tan(-45)";
        System.out.print(s + " expected output -1 2 actually ");
        solution.recursion(s, 0);
        s = "0.305";
        System.out.print(s + " expected output 0.3 0 actually ");
        solution.recursion(s, 0);
        s = "0.3051";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);
        s = "(0.3051)";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);
        s = "1+(1+(1+1)*(1+1))*(1+1)+1";
        System.out.print(s + " expected output 12 8 actually ");
        solution.recursion(s, 0);
        s = "tan(44+sin(89-cos(180)^2))";
        System.out.print(s + " expected output 1 6 actually ");
        solution.recursion(s, 0);
        s = "-2+(-2+(-2)-2*(2+2))";
        System.out.print(s + " expected output -14 8 actually ");
        solution.recursion(s, 0);
        s = "sin(80+(2+(1+1))*(1+1)+2)";
        System.out.print(s + " expected output 1 7 actually ");
        solution.recursion(s, 0);
        s = "1+4/2/2+2^2+2*2-2^(2-1+1)";
        System.out.print(s + " expected output 6 11 actually ");
        solution.recursion(s, 0);
        s = "2^10+2^(5+5)";
        System.out.print(s + " expected output 2048 4 actually ");
        solution.recursion(s, 0);
        s = "1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1";
        System.out.print(s + " expected output 72.96 8 actually ");
        solution.recursion(s, 0);  // !!!!
        s = "0.000025+0.000012";
        System.out.print(s + " expected output 0 1 actually ");
        solution.recursion(s, 0);
        s = "-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)";
        System.out.print(s + " expected output -3 16 actually ");
        solution.recursion(s, 0);
        s = "cos(3 + 19*3)";
        System.out.print(s + " expected output 0.5 3 actually ");
        solution.recursion(s, 0);
        s = "2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)"; //!!
        System.out.print(s + " expected output 8302231.36 14 actually ");
        solution.recursion(s, 0);
        s = "(-1 + (-2))";
        System.out.print(s + " expected output -3 3 actually ");
        solution.recursion(s, 0);
        s = "-sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output -0.5 7 actually ");
        solution.recursion(s, 0);
        s = "sin(100)-sin(100)";
        System.out.print(s + " expected output 0 3 actually ");
        solution.recursion(s, 0);
        s = "-(-22+22*2)";
        System.out.print(s + " expected output -22 4 actually ");
        solution.recursion(s, 0);
        s = "-2^(-2)";
        System.out.print(s + " expected output -0.25 3 actually ");
        solution.recursion(s, 0);
        s = "-(-2^(-2))+2+(-(-2^(-2)))";
        System.out.print(s + " expected output 2.5 10 actually ");
        solution.recursion(s, 0);
        s = "(-2)*(-2)";
        System.out.print(s + " expected output 4 3 actually ");
        solution.recursion(s, 0);
        s = "(-2)/(-2)";
        System.out.print(s + " expected output 1 3 actually ");
        solution.recursion(s, 0);
        s = "sin(-30)";
        System.out.print(s + " expected output -0.5 2 actually ");
        solution.recursion(s, 0);
        s = "cos(-30)";
        System.out.print(s + " expected output 0.87 2 actually ");
        solution.recursion(s, 0);
        s = "tan(-30)";
        System.out.print(s + " expected output -0.58 2 actually ");
        solution.recursion(s, 0);
        s = "2+8*(9/4-1.5)^(1+1)";
        System.out.print(s + " expected output 6.5 6 actually ");
        solution.recursion(s, 0);
        s = "0.005 ";
        System.out.print(s + " expected output 0.01 0 actually ");
        solution.recursion(s, 0);
        s = "0.0049 ";
        System.out.print(s + " expected output 0 0 actually ");
        solution.recursion(s, 0);
        s = "0+0.304";
        System.out.print(s + " expected output 0.3 1 actually ");
        solution.recursion(s, 0);
    }

    public void recursion(final String expression, int countOperation)
    {

        String expressionNew = expression.replaceAll(" ", "")+"     ";
        // считаю  операции
        if (countOperation == 0)
        {
            int ind = 0;
            String s = "";
            while (ind < expressionNew.length()-3)
            {
                s = expressionNew.substring(ind, ind + 1);
                if (s.matches("\\-|\\+|\\*|\\/|\\^")) countOperation++;
                s = expressionNew.substring(ind, ind + 3);
                if (s.equals("tan"))
                {
                    expressionNew = expressionNew.replaceFirst("tan", "");
                    countOperation++;
                }
                if (s.equals("cos"))
                {
                    expressionNew = expressionNew.replaceFirst("cos", "");
                    countOperation++;
                }
                if (s.equals("sin"))
                {
                    expressionNew = expressionNew.replaceFirst("sin", "");
                    countOperation++;
                }
                ind++;
            }
       }

        expressionNew = " " + expression.replaceAll(" ", "") + " ";

        // ищу конструкции типа (число) и очищаю от скобок
        expressionNew = didgeIs(expressionNew);

        expressionNew = expressionNew.replaceAll(" ", "");
        //Если уже просто число на входе
        if (expressionNew.matches("^-\\d+(\\.\\d+)?$") | (expressionNew.matches("\\d+(\\.\\d+)?$")))
        {
            double result = Double.parseDouble(expressionNew);
            System.out.print(new DecimalFormat("#.##").format(result).toString().replaceAll("\\,","."));
            System.out.println(" " + countOperation);
            return;

        }

        expressionNew = " " + expressionNew.replaceAll(" ", "") + " ";
        //ищем степень двух простых цифр
        expressionNew = stepenIs(expressionNew, countOperation);

        //ищем умножение деление двух простых цифр
        expressionNew = multiplyIs(expressionNew, countOperation);

        //ищем сложение вычитание двух простых цифр
        expressionNew = addIs(expressionNew, countOperation);

        //ищем функцию двух простых цифр
        funkIs(expressionNew, countOperation);

        //  System.out.println(expressionNew);
    }

    private double gradInRag(double a){
        final double  PI= 3.141592653589793;
        double  rad = (a*PI)/180;
        return rad;       }

    // ищу конструкции типа (число) и очищаю от скобок
    private String didgeIs(String expression)
    {
        String expressionNew = expression;
        int ind = 0; // индекс цифры
        String cifra = "";
        int lenght = 0; // длинна цифры
        //ищем число
        while (ind < expressionNew.length() - 1)
        {
            ind++;
            while (expressionNew.substring(ind, ind + 1).matches("\\d|\\."))
            {
                cifra = cifra + expressionNew.substring(ind, ind + 1);
                lenght++;
                ind++;
            }

            if (!cifra.equals(""))
            {
                // если цифра отрицательная
                if (expressionNew.substring(ind - lenght - 1, ind - lenght).equals("-"))
                {
                    cifra = "-" + cifra;
                    lenght++;
                }

                //   анализирую что вокруг цифры, если скобки  -  убираю
                if (expressionNew.substring(ind - lenght - 1, ind - lenght).equals("(")
                        && expressionNew.substring(ind, ind + 1).equals(")")
                        && !expressionNew.substring(ind - lenght - 2, ind - lenght - 1).matches("\\w"))
                {
                    expressionNew = expressionNew.replaceFirst("\\(" + cifra + "\\)", cifra);
                    ind = 0;
                }

                cifra = "";
                lenght = 0;

            }
        }
        expressionNew = expressionNew.replaceFirst("\\-\\-","+");
        expressionNew = expressionNew.replaceFirst("\\+\\-","-");

        return expressionNew;
    }

    private String multiplyIs(String expression, int countOperation)
    {
        String expressionNew = " " + expression.replaceAll(" ", "") + " ";
        String cifra1 = ""; // цифра
        String cifra2 = ""; // цифра
        String znak = ""; //

        //ищем умножение или деление
        while ((expressionNew.indexOf("*") > 0) || (expressionNew.indexOf("/") > 0))
        {  int ind1=0;
         // если две операции подряд беру ту которая первая
            if (expressionNew.indexOf("*")> 0 && (expressionNew.indexOf("/")>0))
            {//Ищем знак умножение или деление
                if (expressionNew.indexOf("*") < (expressionNew.indexOf("/"))) {ind1 = expressionNew.indexOf("*");znak = "*";}
                else {ind1 = expressionNew.indexOf("/");znak = "/";}
            }
            else if (expressionNew.indexOf("*")> 0 && (expressionNew.indexOf("/")<0)){ind1 = expressionNew.indexOf("*"); znak = "*";}
            else if (expressionNew.indexOf("*")< 0 && (expressionNew.indexOf("/")>0)){ind1 = expressionNew.indexOf("/"); znak = "/";}

            int ind2 = expressionNew.indexOf(znak) + 1;

            //ищу первую цифру
            while (expressionNew.substring(ind1 - 1, ind1).matches("\\d|\\."))
            {
                cifra1 = expressionNew.substring(ind1 - 1, ind1) + cifra1;
                ind1--;
            }
            // Ищу знак первой цифры (минус если есть)
            if (expressionNew.substring(ind1 - 1, ind1).matches("\\-"))
                cifra1 = expressionNew.substring(ind1 - 1, ind1) + cifra1;

            // Ищу знак второй цифры (минус если есть)
            if (expressionNew.substring(ind2, ind2 + 1).matches("\\-"))
            {
                cifra2 = cifra2 + expressionNew.substring(ind2, ind2 + 1);
                ind2++;
            }

            //ищу вторую цифру
            while (expressionNew.substring(ind2, ind2 + 1).matches("\\d|\\."))
            {
                cifra2 = cifra2 + expressionNew.substring(ind2, ind2 + 1);
                ind2++;
            }

            // если обе цифры найдены заменяю кусок выражения результатом
            if (!cifra1.equals("") & !cifra2.equals(""))
            {
                // деление на 0 не проверяю
                Double result = znak.equals("*") ? Double.parseDouble(cifra1) * Double.parseDouble(cifra2) : Double.parseDouble(cifra1) / Double.parseDouble(cifra2);

                expressionNew = expressionNew.replaceFirst(cifra1 + "\\" + znak + cifra2, new DecimalFormat("#.##").format(result).toString().replaceAll("\\,", "."));

                if (znak.equals("*")) expressionNew = expressionNew.replaceFirst("\\_", "*");
                else if (znak.equals("/")) expressionNew = expressionNew.replaceFirst("\\#", "/");

                expressionNew = expressionNew.replaceAll(" ", "");

                recursion(expressionNew, countOperation); // передаю выражение дальше увеличивая счетчик на единицу
                return "";
            } else  // если цифры не найдены заменяю знак на безобидный
            {
                if (znak.equals("*")) expressionNew = expressionNew.replaceFirst("\\*", "_");
                else if (znak.equals("/")) expressionNew = expressionNew.replaceFirst("\\/", "#");

                cifra1 = ""; // цифра
                cifra2 = ""; // цифра
                znak = ""; //
            }
        }
        //если ничего не нашли верну все замены обратно
        expressionNew = expressionNew.replaceAll("\\_", "*");
        expressionNew = expressionNew.replaceAll("\\#", "/");
        return expressionNew;
    }

    //ищем степень двух простых цифр цифр
    private String stepenIs(String expression, int countOperation)
    {
        String expressionNew = " " + expression.replaceAll(" ", "") + " ";
        String cifra1 = ""; // цифра
        String cifra2 = ""; // цифра

        int indznak = expressionNew.indexOf("^");
        if (indznak > 0)

        {
            int ind1 = expressionNew.indexOf("^");
            int ind2 = expressionNew.indexOf("^") + 1;

            //ищу первую цифру
            while (expressionNew.substring(ind1 - 1, ind1).matches("\\d|\\."))
            {
                cifra1 = expressionNew.substring(ind1 - 1, ind1) + cifra1;
                ind1--;
            }

            // Ищу знак второй цифры (минус если есть)
            if (expressionNew.substring(ind2, ind2 + 1).matches("\\-"))
            {
                cifra2 = cifra2 + expressionNew.substring(ind2, ind2 + 1);
                ind2++;
            }

            //ищу вторую цифру
            while (expressionNew.substring(ind2, ind2 + 1).matches("\\d|\\."))
            {
                cifra2 = cifra2 + expressionNew.substring(ind2, ind2 + 1);
                ind2++;
            }

            if (!cifra1.equals("") & !cifra2.equals(""))
            {

                Double result = Math.pow(Double.parseDouble(cifra1), Double.parseDouble(cifra2));

                expressionNew = expressionNew.replaceFirst(cifra1 + "\\^" + cifra2, new DecimalFormat("#.##").format(result).toString().replaceAll("\\,", "."));
                expressionNew = expressionNew.replaceAll(" ", "");
                recursion(expressionNew, countOperation);
                return "";
            }
        }
        return expressionNew;
    }

    // Сложение и вычитание
    private String addIs(String expression, int countOperation)
    {
        String expressionNew = " " + expression.replaceAll(" ", "") + " ";

        // если все выражение начинается на минус поставлю поставлю вместо минуса @ чтобы его не считать за знак операции
        if (expression.replaceAll(" ", "").indexOf("-") == 0) expressionNew = expressionNew.replaceFirst("\\-", "#");

        String cifra1 = ""; // цифра
        String cifra2 = ""; // цифра
        String znak = ""; //

        //ищем сложение и вычитание
        while ((expressionNew.indexOf("+") > 0) || (expressionNew.indexOf("-") > 0))
        {
            //Ищем знак
            int ind1 = expressionNew.indexOf("+");
            if (ind1 > 0)
            {
                znak = "+";
            } else
            {
                ind1 = expressionNew.indexOf("-");

                if (ind1 > 0)
                {
                    znak = "-";
                }
            }
            int ind2 = expressionNew.indexOf(znak) + 1;

            //ищу первую цифру
            while (expressionNew.substring(ind1 - 1, ind1).matches("\\d|\\."))
            {
                cifra1 = expressionNew.substring(ind1 - 1, ind1) + cifra1;
                ind1--;
            }
            // Ищу знак первой цифры (минус если есть)

            if (expressionNew.substring(ind1 - 1, ind1).matches("\\-|\\#")) cifra1 = "-" + cifra1;

            //ищу вторую цифру
            while (expressionNew.substring(ind2, ind2 + 1).matches("\\d|\\."))
            {
                cifra2 = cifra2 + expressionNew.substring(ind2, ind2 + 1);
                ind2++;
            }
            // проверяю знак после цифры что это не умножение и не деление и не степень и
            // если обе цифры найдены заменяю кусок выражения результатом
            if (!cifra1.equals("") & !cifra2.equals("") & (!expressionNew.substring(ind2, ind2 + 1).matches("\\*|\\/|\\^")))
            {

                Double result = znak.equals("+") ? Double.parseDouble(cifra1) + Double.parseDouble(cifra2) : Double.parseDouble(cifra1) - Double.parseDouble(cifra2);

                expressionNew = expressionNew.replaceFirst(cifra1 + "\\" + znak + cifra2, "+" + new DecimalFormat("#.##").format(result).toString().replaceAll("\\,","."));


                cifra1 = cifra1.replaceFirst("\\-", "#");
                expressionNew = expressionNew.replaceFirst(cifra1 + "\\" + znak + cifra2, result.toString());

                expressionNew = expressionNew.replaceAll("\\_", "+");
                expressionNew = expressionNew.replaceAll("\\#", "-");

                expressionNew = expressionNew.replaceAll("\\+\\+", "+");
                expressionNew = expressionNew.replaceAll("\\-\\+", "-");
                expressionNew = expressionNew.replaceAll("\\+\\-", "-");
                expressionNew = expressionNew.replaceAll("\\-\\-", "+");
                expressionNew = expressionNew.replaceAll("\\(\\+", "(");
                expressionNew = expressionNew.replaceAll("\\ \\+", " ");

                expressionNew = expressionNew.replaceAll(" ", "");
                // System.out.println(expressionNew.toString());
                recursion(expressionNew, countOperation); // передаю выражение дальше увеличивая счетчик на единицу
                return "";
                //break;
            } else  // если цифры не найдены заменяю знак на безобидный
            {
                if (znak.equals("+")) expressionNew = expressionNew.replaceFirst("\\+", "_");
                else if (znak.equals("-")) expressionNew = expressionNew.replaceFirst("\\-", "#");

                cifra1 = ""; // цифра
                cifra2 = ""; // цифра
                znak = ""; //
            }
        }
        //если ничего не нашли верну все замены обратно
        expressionNew = expressionNew.replaceFirst("\\_", "+");
        expressionNew = expressionNew.replaceFirst("\\#", "-");
        return expressionNew;
    }

    //ищем sin(x), cos(x), tan(x)
    private String funkIs(String expression, int countOperation)
    {

        String expressionNew = " " + expression.replaceAll(" ", "") + " ";
        String cifra2 = ""; // цифра
        String znak = ""; //


        int indznak = expressionNew.indexOf("sin");
        if (indznak < 0) indznak = expressionNew.indexOf("cos");
        if (indznak < 0) indznak = expressionNew.indexOf("tan");

        while (indznak > 0)

        {

            //ищу знак
            znak = expressionNew.substring(indznak, indznak + 3);

            int ind = expressionNew.indexOf(znak) + 4; // пропускаю первую скобку

            //ищу содержимое (может быть отрицательным)
            if (expressionNew.substring(ind, ind + 1).equals("-"))
            {
                cifra2 = "-";

                ind++;
            }
            // собираю цифру
            while (expressionNew.substring(ind, ind + 1).matches("\\d|\\."))
            {
                cifra2 = cifra2 + expressionNew.substring(ind, ind + 1);
                ind++;
            }

            // следующая буква должна быть скобка если нет то Заменяю выражение и ищу заново
            if (!expressionNew.substring(ind, ind + 1).equals(")"))
            {
                switch (znak)
                {
                    case "sin":
                        expressionNew = expressionNew.replaceFirst("sin","sss");
                        break;
                    case "cos":
                        expressionNew = expressionNew.replaceFirst("cos","ccc");
                        break;
                    case "tan":
                        expressionNew = expressionNew.replaceFirst("tan","ttt");
                        break;
                }
                indznak = expressionNew.indexOf("sin");
                if (indznak < 0) indznak = expressionNew.indexOf("cos");
                if (indznak < 0) indznak = expressionNew.indexOf("tan");
                cifra2 = "";
                continue;
            }

            Double result = 0d;

            if (!cifra2.equals(""))
            {

                switch (znak)
                {
                    case "sin":
                        result = (double) Math.sin(gradInRag(Double.parseDouble(cifra2)));
                        break;
                    case "cos":
                        result = (double) Math.cos(gradInRag(Double.parseDouble(cifra2)));
                        break;
                    case "tan":
                        result = (double) Math.tan(gradInRag(Double.parseDouble(cifra2)));
                        break;
                }

                expressionNew = expressionNew.replaceFirst("sss","sin");
                expressionNew = expressionNew.replaceFirst("ccc","cos");
                expressionNew = expressionNew.replaceFirst("ttt","tan");

                expressionNew = expressionNew.replaceFirst(znak + "\\(" + cifra2 + "\\)", new DecimalFormat("#.##").format(result).toString().replaceAll("\\,","."));
                expressionNew = expressionNew.replaceFirst("\\-\\-","+");
                expressionNew = expressionNew.replaceFirst("\\+\\-","-");
                expressionNew = expressionNew.replaceAll(" ", "");

                recursion(expressionNew, countOperation);
                return "";
            }
        }

        return "";
    }

    public Solution()
    {
        //don't delete
    }
}
