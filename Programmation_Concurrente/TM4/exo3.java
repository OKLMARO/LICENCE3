import java.util.Random;

import static java.lang.Thread.sleep;

public class exo3 {
    public static void main(String[] args) {
        Pont pont = new Pont();
        Voiture[] v = new Voiture[10];
        Thread[] threads = new Thread[10];
        for (int i = 0; i < v.length; i++) {
            v[i] = new Voiture(new Random().nextBoolean(), pont);
            threads[i] = new Thread();
            threads[i].start();
        }
        while (true){
            
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
            pont.enter();
            System.out.println(Thread.currentThread().getId() + " passe sur le pont");
            try{
                Thread.sleep(new Random().nextInt(50));
            } catch(InterruptedException e){}
            pont.exit();
        }
    }
}

class Pont {
    boolean isUsed = false;
    boolean direction;

    public void enter(){
        this.isUsed = true;
    }

    public void exit(){
        this.isUsed = false;
    }
}