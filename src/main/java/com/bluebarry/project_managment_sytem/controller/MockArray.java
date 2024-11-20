package com.bluebarry.project_managment_sytem.controller;

public class MockArray {
    public static void main(String[] args) {
//        int[] id= {1,2,3,5,7,9};
////
//                                                     //print all array elements
//        for(int a:id){
//            System.out.print(a+" ");
//        }


//        int[] numbers={10,20,30,40,50};
//        int sum=0;
//        for (int a:numbers){
//            sum+=a;                                  //adding elements in array
//
//        }
//        System.out.println(sum);


//        int[] numbers={10,20,30,40,50};
//        int max=numbers[0];
//        for (int i = 0; i < numbers.length; i++) {
//            if (numbers[i]>max){                    // max element in array
//                max=numbers[i];
//            }
//
//        }
//        System.out.println(max);

//int[] numbers={2,4,6,14,10,12};
//int max=numbers[0];
//for (int num=0;num<numbers.length;num++){
//    if (numbers[num]>max){
//        max=numbers[num];
//    }
//}
//        System.out.println(max);




int[] number={1,2,3,4,5};
int start=0;
int end=number.length-1;
while (start<end){
    int temp=number[start];
    number[start]=number[end];
    number[end]=temp;
    start++;
    end--;

}
for (int num:number){
    System.out.println(num);
}


    }
}
