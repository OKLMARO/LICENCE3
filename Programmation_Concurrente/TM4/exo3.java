package Programmation_Concurrente.TM4;

import java.util.Random;

import static java.lang.Thread.sleep;

public class exo3 {
    public static void main(String[] args) {
        Pont pont = new Pont();
        Voiture[] v = new Voiture[10];
        Thread[] threads = new Thread[10];
        for (int i = 0; i < v.length; i++) {
            v[i] = new Voiture(new Random().nextBoolean(), pont);
            threads[i] = new Thread(v[i]);
            threads[i].start();
        }
    }
}

class Voiture implements Runnable {
    boolean direction;

    Pont pont;

    public Voiture(boolean direction, Pont pont) {
        this.direction = direction;
        this.pont = pont;
    }

    @Override
    public void run() {
        while (true) {
            
            pont.enter(this);
            
            try{
                Thread.sleep(new Random().nextInt(50));
            } catch(InterruptedException e){}

            System.out.println("Direction : " + pont.direction);
            
            pont.exit();
            
            try{
                Thread.sleep(new Random().nextInt(50));
            } catch(InterruptedException e){}
        }
    }
}

class Pont {
    boolean isUsed = false;
    boolean direction;
    int passage_pont = 0;

    public synchronized void enter(Voiture v){
        if(this.isUsed == true && this.direction != v.direction){
            try{    
                this.wait();
            } catch(InterruptedException e){}
        }
        
        try{
            Thread.sleep(new Random().nextInt(100));
        } catch(InterruptedException e){}

        this.isUsed = true;
        this.passage_pont = this.passage_pont + 1;

        System.out.println("La voiture : " + Thread.currentThread().getId() + " passe sur le pont");
    }

    public synchronized void exit(){
        if(this.passage_pont == 0){
            this.isUsed = false;
            this.notify();
        }

        else if(this.passage_pont == 4){
            this.passage_pont = 0;
            
            if(this.direction == true){
                this.direction = false;
            } else{
                this.direction = true;
            }
            
            this.notifyAll();
        }

        System.out.println("La voiture : " + Thread.currentThread().getId() + " sors du pont");
    }
}