package Programmation_Concurrente.TM2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class question1 extends Thread{
    
    Valeur valeur;
    Lock l = new ReentrantLock();

    public question1(Valeur valeur){
        valeur = this.valeur;
    }

    public void run(){
        for (int i = 0; i < 1000; i = i + 1){
            l.lock();
            valeur.x = valeur.x + 1;
            System.out.println(Thread.currentThread().getId() + " " + valeur.x);    
            l.unlock();
        }
    }

    public static void main(String[] args) {
        Valeur v =  new Valeur();
        Thread t1 = new question1(v);
        Thread t2 = new question1(v);

        t1.start();
        t2.start();

        System.out.println(v.x);
    }    
}