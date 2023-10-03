package Programmation_Concurrente.TM3;

import java.util.Random;

public class Philosophe implements Runnable{
    public static void main(String[] args) {
        Baguette b1 = new Baguette();
        Baguette b2 = new Baguette();
        Baguette b3 = new Baguette();
        Baguette b4 = new Baguette();
        Baguette b5 = new Baguette();

        Philosophe p1 = new Philosophe(b1, b2);
        Philosophe p2 = new Philosophe(b3, b2);
        Philosophe p3 = new Philosophe(b3, b4);
        Philosophe p4 = new Philosophe(b4, b5);
        Philosophe p5 = new Philosophe(b5, b1);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);
        Thread t4 = new Thread(p4);
        Thread t5 = new Thread(p5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }

    Baguette b1;
    Baguette b2;

    public Philosophe(Baguette b1, Baguette b2) {
        this.b1 = b1;
        this.b2 = b2;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (b1) {
                synchronized (b2) {
                    System.out.println(Thread.currentThread().getName() + " mange");
                }
            }
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Baguette {
    boolean onTable = true;
}
