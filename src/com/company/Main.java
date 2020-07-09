package com.company;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        for(long i=2;i< Long.MAX_VALUE;i ++){
            String result = convertTenTo93(i);
            System.out.println(result+" "+ (convert93ToTen(result)));
        }
    }
    public static String convertTenTo93(long num){

        String result = new String();


        if(num == 0)
            return "0";
        long copy = num;
        boolean isNeg = copy<0;
        if(isNeg) {
            copy = Math.abs(num);
            System.out.println(copy);
        }

        while(copy>0){
            result = (convertDigit((short)((copy)%93)))+result;
            copy /=93;
        }
        if(isNeg)
            result = "-"+result;
        return result.toString();
    }
    public static long convert93ToTen(String num){
        int index =0;
        long sum =0;
        boolean isNeg = num.charAt(0) == '-';
        if(isNeg)
            num = num.substring(1);
        for(int i=num.length()-1; i>= 0;i--){

            sum += convertBackChar(num.charAt(i))*Math.pow(94,index);
            index++;
        }
        if(isNeg)
            sum = -sum;
        return sum;
    }
    private static long convertBackChar(char c){

        if(Character.isDigit(c)) {
            return c - 48;
        }
        if(Character.isUpperCase(c))
            return (c-64)+9;
        if(Character.isLowerCase(c))
            return (c-97)+35;
        return convertBack(c);
    }
    private static long convertBack(char c){
        long sum = (c-33);
        if(c-33 >= 45)
            sum--;
        if(c-33 >=15)
            sum -= 9;
        if(c-33>= 32)
            sum -= 26;
        if(c-33>= 64)
            sum -= 26;

        return sum +60;
    }

    private static char convertDigit(short num){
        if(num <0)
            throw new InvalidParameterException("Number can not be negitive");

        if(num<=9)
            return (char)(num+48);
        else if(num<36)
            return (char)(65+(num-10));
        else if(num < 61)
            return (char)(97+(num-35));
        return convertToIrregular( num);
    }
    private static char convertToIrregular(short num){

        int index =33+(num-61);
        if(index>=45)
            index++;
        if(index>=48)
            index += 10;
        if(index>=65) {
            index +=26;
        }
        if(index >= 97)
            index+=26;
        return (char)index;

    }



}
