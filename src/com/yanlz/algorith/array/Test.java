package com.yanlz.algorith.array;


public class Test {
        public static void main(String[] args) {
            int size = 5;
            int halfSize = size / 2;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < Math.abs(halfSize - i); j++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < size - 2 * Math.abs(halfSize - i); j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }

}
