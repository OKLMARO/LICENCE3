package Programmation_Concurrente.TM2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class question1 implements Runnable{
    Valeur valeur;
    Lock l;
    
    public question1(Lock l, Valeur valeur){
        this.l = l;
        this.valeur = valeur;
    }

    public void run(){
        for (int i = 0; i < 1000; i++) {
            l.lock();
            valeur.x = valeur.x + 1;
            l.unlock();
        }
        System.out.println(valeur.x);
    }

    private static class run {
        public static void main(String[] args) {
            Valeur valeur = new Valeur();
            Lock l = new ReentrantLock();

            question1 q1 = new question1(l, valeur);
            question1 q2 = new question1(l, valeur);

            Thread t1 = new Thread(q1);
            Thread t2 = new Thread(q2);

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }

            System.out.println(Thread.currentThread().getId() + " " + valeur.x);
        }
    }
}