package com.bluebarry.project_managment_sytem.controller;

import java.util.Scanner;

public class StringGame {
    public static void main(String[] args) {
//        String name="dirsj";
//        String na=new String("dirsj");
//        boolean a=name.equals(na);
//        System.out.println(a);


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String input = scanner.nextLine();

        String reversed = ""; // To store the reversed string
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i); // Add characters in reverse order
        }

        System.out.println("Reversed string: " + reversed);

    }
}
