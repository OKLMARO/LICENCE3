package Programmation_Concurrente.TM2;

import javax.swing.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class question4 implements Runnable {
    Valeur valeur;
    Lock l;
    private int nbr_manger;

    public question4(Lock l, Valeur valeur) {
        this.valeur = valeur;
        this.l = l;
        this.nbr_manger = 0;
    }

    public void run() { 
        for (int i = 0; i < 1000; i++) {
            l.lock();
            valeur.x = valeur.x + 1;
            this.nbr_manger = this.nbr_manger + 1;
            System.out.println(Thread.currentThread().getId() + " à manger pour la " + nbr_manger + " fois");
            l.unlock();
        }
    }

    private static class run{
        public static void main(String[] args) {
            Valeur valeur = new Valeur();
            Lock l = new ReentrantLock();

            Thread[] threads = new Thread[5];

            for(int i = 0; i < threads.length; i = i + 1) {
                threads[i] = new Thread(new question4(l, valeur));
                threads[i].start();
            }

            for(int i = 0; i < threads.length; i = i + 1) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }
        }
    }
}
