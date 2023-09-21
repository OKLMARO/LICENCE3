package Programmation_Concurrente.TM2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class question2 implements Runnable{
    Valeur valeur;
    Lock l;
    
    public question2(Lock l, Valeur valeur){
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
}

class run {
    public static void main(String[] args) {
        Valeur valeur = new Valeur();
        Lock l = new ReentrantLock();

        Thread[] threads = new Thread[50];
        
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new question2(l, valeur));
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }

        System.out.println("x = " + valeur.x);
    }
}