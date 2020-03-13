package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String fio;
    private static final String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static long p, q, n, m, d, e;

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите Фамилию:");
            fio = scanner.nextLine().toLowerCase();
            System.out.println("Введите P:");
            p = scanner.nextInt();
            System.out.println("Введите Q:");
            q = scanner.nextInt();
            scanner.close();
        }catch (Exception ex){System.out.println(ex);}

        check();

        if (isTheNumberSimple(p) && isTheNumberSimple(q)) {

            n = p * q;
            m = (p - 1) * (q - 1);
            e = calculateE(m);
            d = calculateD(e, m);

            List<String> result = rsaEncode(fio, e, n);

            showResult(result);

        } else {
            System.out.println("p или q - не простые числа!");
        }
    }


    private static List<String> rsaEncode(String s, long e, long n){
        ArrayList<String> result = new ArrayList<>();

        long bi;

        for (int i = 0; i < s.length(); i++)
        {
            int index = alphabet.indexOf(s.charAt(i)) + 1;

            bi = index;
            bi = (long)Math.pow(bi, (int)e);

            bi = bi % n;

            result.add(String.valueOf(bi));
        }

        return result;
    }

    private static void check(){
        for (int i = 0; i < fio.length(); i++) {
            if (!alphabet.contains(String.valueOf(fio.charAt(i)))) {
                System.out.println("Некорректный ввод!!!");
                System.exit(0);
            }
        }

    }

    private static boolean isTheNumberSimple(long n) {
        if (n < 2)
            return false;

        if (n == 2)
            return true;

        for (long i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    private static long calculateE(long m){
        long e = 0;
        for (e = 2; e <= m; e++) {
            if ( m % e != 0 ) {
                return e;
            }
        }
        return e;
    }

    private static long calculateD(long e, long m){

        int d = 0;
        while (true)
        {
            if ((e * d) % m == 1)
                break;
            else
                d++;
        }

        return d;
    }

    private static void showResult(List<String> result){

        System.out.println("p= " + p + " q= " + q + " n= " + n + " m= " + m + " d= " + d + " e= " + e);

        System.out.println("Зашифрованное сообщение:");
        for (String s: result) {
            System.out.print(s + "\t");
        }
    }
}
