import java.util.Random;

import static java.lang.Thread.sleep;

public class exo3 {
    public static void main(String[] args) {
        while (true){
            Pont pont = new Pont();
            Voiture v = new Voiture(new Random().nextBoolean(), pont);
            v.run();
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
            try {
                sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            pont.take();
            pont.direction = this.direction;
            System.out.println("La voiture en direction de : " + this.direction + " passe sur le pont");

            try {
                sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("La voiture en direction de : " + direction + " est passe sur le pont ");
            pont.release();
        }
    }
}

class Pont {
    boolean isUsed = false;
    boolean direction;

    public synchronized void take() {
        while (isUsed && direction != this.direction) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        isUsed = true;
    }

    public synchronized void release() {
        isUsed = false;
        this.notify();
    }
}