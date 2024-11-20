package com.bluebarry.project_managment_sytem.controller;

public class Pattern {
    public static void main(String[] args) {
//        for (int i = 1; i <=3; i++) {
//            for (int j=1; j<=3; j++){
//
//                System.out.print("*");
//            }
//            System.out.println();
//
//        }
//***
//***
//***



//*
//* *
//* * *
//* * * *
//* * * * *
//        for (int i = 1; i <=5; i++) {
//            for (int j=1; j<=i;j++){
//                System.out.print("*");
//            }
//            System.out.println(" ");
//
//        }

//        $$$$1
//        $$$12
//        $$123
//        $1234
//        12345

//        int n = 5;
//        for (int i = 1; i <= n; i++) {
//            // Print dollar signs in decreasing order
//            for (int j = i; j < n; j++) {
//                System.out.print("$");
//            }
//            // Print increasing numbers starting from 1 up to the current row number
//            for (int j = 1; j <= i; j++) {
//                System.out.print(j);
//            }
//            System.out.println();
//        }

//        $
//        $ $
//        $ $ $
//        $ $ $ $
//int n=4;
//        for (int i = 1; i <=n; i++) {
//            for (int j=1; j<=i; j++){
//                System.out.print("$");
//            }
//            System.out.println();
//        }
//        1
//        2 2
//        3 3 3
//        4 4 4 4
//        5 5 5 5 5
//        for (int i = 1; i <=5 ; i++) {
//            for (int j=1; j<=i; j++){
//                System.out.print(i+"");
//            }
//            System.out.println();
//
//        }



//* * * * *
//*       *
//*       *
//*       *
//* * * * *
//        int n=5;
//        for (int i = 1; i<=n ; i++) {
//            for (int j=1;j<=n;j++){
//                if (i==1||i==n||j==1||j==n){
//                    System.out.print("*");
//                }else{
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//        }


//        1
//        1 2
//        1 2 3
//        1 2 3 4
//        1 2 3 4 5

           int n=5;
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <=i; j++) {
                System.out.print(j+" ");

            }
            System.out.println();

        }

//        1 2 3 4 5
//        1 2 3 4
//        1 2 3
//        1 2
//        1

//        int n=5;
//        for (int i = n; i >=1 ; i--) {
//            for (int j=1;j<=i;j++){
//                System.out.print(j+" ");
//            }
//            System.out.println();
//
//        }

    }
}
