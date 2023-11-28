package com.devincompany.app;
import java.util.Scanner;

public class LoopsII {
    public static void main(String[] arg) {
        Scanner newScan = new Scanner(System.in);
        int newInt = newScan.nextInt();
        for (int i = 0; i < newInt; i++){
            int a = newScan.nextInt();
            int b = newScan.nextInt();
            int n = newScan.nextInt();
            for (int j = 0; j < n; j++){
                int g = (int)Math.round(Math.pow(i,2))*b;
                a += g;
                System.out.print(a);
            }
            System.out.println("");
        }
        newScan.close();
    }
}
