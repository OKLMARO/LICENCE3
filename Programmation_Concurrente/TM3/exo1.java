package Programmation_Concurrente.TM3;

public class exo1 implements Runnable{
    public static  int x = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            synchronized (this) {
               x++;
            }
            System.out.println("x = " + x);
        }
    }

    public static void main(String[] args) {
        exo1 ex = new exo1();
        Thread[] threads = new Thread[50];
        for (int i = 0; i < 50; i++) {
            threads[i] = new Thread(ex);
            threads[i].start();
        }
        try {
            for (int i = 0; i < 50; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("valeur finale = " + x);
    }
}