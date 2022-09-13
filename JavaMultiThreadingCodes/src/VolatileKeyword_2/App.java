package VolatileKeyword_2;

/**
 * Volatile Keyword, <em>“… the volatile modifier guarantees that any thread that
 * reads a field will see the most recently written value.”</em> - Josh Bloch
 * <br><br>
 * Codes with minor comments are from
 * <a href="http://www.caveofprogramming.com/youtube/">
 * <em>http://www.caveofprogramming.com/youtube/</em>
 * </a>
 * <br>
 * also freely available at
 * <a href="https://www.udemy.com/java-multithreading/?couponCode=FREE">
 * <em>https://www.udemy.com/java-multithreading/?couponCode=FREE</em>
 * </a>
 *
 * @author Z.B. Celik <celik.berkay@gmail.com>
 */

import java.util.Scanner;

class Processor extends Thread {

    private volatile boolean running = true;
    // volatile make sure that below two thread always read the most latest value in  the RAM
    // Hert two thread main and Proccessor are working on 'running' shared variable
    // in some system or JVM   line /// while (running) {//  can optimized or chache to thead
    // will not shutdown to make sure this behaves always correctly use volatile in running variable.


    public void run() {
        while (running) {
            System.out.println("Running");


            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}

public class App {

    public static void main(String[] args) {
        Processor pro = new Processor();
        pro.start();
        // Wait for the enter key
        System.out.println("Enter something to stop the thread,\nVolatile variable running will be forced to true :");
        new Scanner(System.in).nextLine();
        pro.shutdown();
    }
}
