package Сalculator;

import java.util.Scanner;

public class Сalculator {
    public static void main(String[] args) throws Exception {
        System.out.println("Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами");
        System.out.println("Калькулятор  принимает на вход числа от 1 до 10 включительно, не более.");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String arithmeticExp = scanner.nextLine();
            System.out.println(Solution.calc(arithmeticExp));
        }
    }

   static class Solution  {
         static String[] action = {"+", "-", "/", "*"};
         static String[] regaxAction = {"\\+", "-", "/", "\\*"};
         static int fact = -1;
         static int a, b;
         static int result;


        public static String calc(String input) throws Exception {
            Conventer conventer = new Conventer();
            for (int i = 0; i < action.length; i++) {
                if (input.contains(action[i])) {
                    fact = i;
                    break;
                }
            }
            if (fact == -1) {
                throw new Exception("Не является арифметической операцией");
            }
            String exp = input.replace(" ", "");
            String[] data = exp.split(regaxAction[fact]);
            if (data.length > 2 || data.length == 1) {
                throw new Exception("Формат математической операции не удовлетворяет заданию - два числовых операнда и один оператор (+, -, /, *)");
            }

            boolean roman1 = conventer.isRoman(data[0]);
            boolean roman2 = conventer.isRoman(data[1]);

            if (roman1 && roman2) {
                try {
                    a = conventer.romanToInt(data[0]);
                    b = conventer.romanToInt(data[1]);
                }catch (Exception e){
                    throw new Exception("Формат математической операции не удовлетворяет заданию - два числовых операнда и один оператор (+, -, /, *)");
                }
            } else if (!roman1 && !roman2) {
                try {
                    a = Integer.parseInt(data[0]);
                    b = Integer.parseInt(data[1]);
                }catch (Exception e){
                    throw new Exception("Формат математической операции не удовлетворяет заданию - два числовых операнда и один оператор (+, -, /, *)");
                }
            } else
                throw new Exception("Используются одновременно разные системы счисления");


            if (a > 10 || b > 10) {
                throw new Exception("Калькулятор принимает на вход числа от 1 до 10 включительно");

            }

            switch (regaxAction[fact]) {
                case "\\+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "/":
                    if(b==0){
                        throw new Exception("Ошибка,на ноль делить нельзя");
                    }
                    result = a / b;
                    break;
                case "\\*":
                    result = a * b;
                    break;

            }

            if (roman1) {
                if (result > 0) {
                    return conventer.intToRoman(result);
                } else {
                    throw new Exception("Результатом работы калькулятора с римскими числами могут быть только положительные числа");
                }
            } else
                return String.valueOf(result);
        }
    }
}
