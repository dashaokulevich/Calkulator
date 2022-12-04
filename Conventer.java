package Сalculator;

import java.util.HashMap;
import java.util.TreeMap;

public class Conventer {
    HashMap<Character, Integer> romanString = new HashMap<>();
    TreeMap<Integer, String> arabianString = new TreeMap<>();


    public Conventer() {
        romanString.put('I',1);
        romanString.put('V',5);
        romanString.put('X',10);
        romanString.put('L',50);
        romanString.put('C',100);

        arabianString.put(100,"C");
        arabianString.put(90,"XC");
        arabianString.put(50,"L");
        arabianString.put(40,"XL");
        arabianString.put(10,"X");
        arabianString.put(9,"IX");
        arabianString.put(5,"V");
        arabianString.put(4,"IV");
        arabianString.put(1,"I");
    }


    public boolean isRoman(String number) {
        return romanString.containsKey(number.charAt(0));
    }
    public String intToRoman(int number) {
        String roman = "";
        do {
          int arabianKey = arabianString.floorKey(number);
            roman += arabianString.get(arabianKey);
            number -= arabianKey;
        }
        while (number != 0);
        return roman;
    }
    public  int romanToInt(String s) {
        int end = s.length() - 1;
        char[] arr = s.toCharArray();
        int result = romanString.get(arr[end]);
        for (int i = end - 1;i >= 0;i--){
              int arabian = romanString.get(arr[i]);
        if(arabian < romanString.get(arr[i + 1])){
                result -= arabian;
            }else
                result += arabian;
        }
        return result;
    }
}
