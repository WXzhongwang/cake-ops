package com.rany.ops;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/9/13 23:40
 * @email 18668485565163.com
 */
public class OneToHundredPrinter {
    static volatile int flag = 0;

    public static void main(String[] args) {
        new Thread(new Task1(), "A").start();
        new Thread(new Task2(), "B").start();
    }

    static class Task1 implements Runnable {
        @Override
        public void run() {
            int i = -1;
            while (i <= 98) {
                if (OneToHundredPrinter.flag == 0) {
                    i += 2;
                    System.out.println("a:" + i);
                    OneToHundredPrinter.flag = 1;
                }
            }
        }
    }

    static class Task2 implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (i <= 98) {
                if (OneToHundredPrinter.flag == 1) {
                    i += 2;
                    System.out.println("b:" + i);
                    OneToHundredPrinter.flag = 0;
                }
            }
        }
    }
}
