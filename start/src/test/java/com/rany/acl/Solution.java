package com.rany.acl;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/9/13 23:50
 * @email 18668485565163.com
 */
public class Solution {

    public static void main(String[] args) {
        new Solution().runThread();
    }

    private volatile int i = 0;
    private Thread thread1, thread2, thread3;
    private volatile int flag = 0;

    public void runThread() {
        thread1 = new Thread(new Thread1());
        thread2 = new Thread(new Thread2());
        thread3 = new Thread(new Thread3());
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public class Thread1 implements Runnable {
        public void run() {
            while (i <= 100) {
                if (flag == 0) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                    i++;
                    flag = 1;
                }
            }
        }
    }

    public class Thread2 implements Runnable {
        public void run() {
            while (i <= 100) {
                if (flag == 1) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                    i++;
                    flag = 2;
                }
            }
        }
    }

    public class Thread3 implements Runnable {
        public void run() {
            while (i <= 100) {
                if (flag == 2) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                    i++;
                    flag = 0;
                }
            }
        }
    }
}