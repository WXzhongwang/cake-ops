package com.rany.ops;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/9/13 23:34
 * @email 18668485565163.com
 */
public class PrintNumThread extends Thread {

    private static int num = 0;
    private static final Object object = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (object) {
                //唤醒等待的线程 必须在同步代码块中
                object.notify();
                if (num < 100) {
                    num++;
                    System.out.println(Thread.currentThread().getName() + ":" + num);
                } else {
                    break;
                }
                try {
                    /*
                    wait()  必须在同步代码块中使用，
                            必须是使用同步锁调用wait()
                            wait()调用后，锁会释放
                            必须要通过其他线程来唤醒
                     */
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        PrintNumThread p1 = new PrintNumThread();
        PrintNumThread p2 = new PrintNumThread();
        p1.setName("A");
        p1.start();
        p2.setName("B");
        p2.start();
    }
}