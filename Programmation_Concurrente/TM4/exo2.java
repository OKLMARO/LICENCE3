import java.util.Random;

import static java.lang.Thread.sleep;

public class exo2 {
    public static void main(String[] args) {
        Philosophe[] philosophes = new Philosophe[5];
        Fourchette[] fourchettes = new Fourchette[5];
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            fourchettes[i] = new Fourchette(i);
        }
        for (int i = 0; i < 5; i++) {
            philosophes[i] = new Philosophe(i, fourchettes[i], fourchettes[(i + 1) % 5]);
            threads[i] = new Thread(philosophes[i]);
            threads[i].start();
        }
    }
}

class Philosophe implements Runnable {
    int id;
    Fourchette fourchetteGauche;
    Fourchette fourchetteDroite;

    public Philosophe(int id, Fourchette fourchetteGauche, Fourchette fourchetteDroite) {
        this.id = id;
        this.fourchetteGauche = fourchetteGauche;
        this.fourchetteDroite = fourchetteDroite;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            fourchetteGauche.take();
            System.out.println("Philosophe " + id + " prend fourchette gauche " + fourchetteGauche.id);

            fourchetteDroite.take();
            System.out.println("Philosophe " + id + " prend fourchette droite " + fourchetteDroite.id);

            System.out.println("Philosophe " + id + " mange");
            try {
                sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Philosophe " + id + " relache fourchette droite " + fourchetteDroite.id);
            fourchetteDroite.release();
            System.out.println("Philosophe " + id + " relache fourchette gauche " + fourchetteGauche.id);
            fourchetteGauche.release();
        }

    }
}

class Fourchette {
    boolean isUsed = false;
    int id;

    public Fourchette(int id) {
        this.id = id;
    }

    public synchronized void take() {
        while (isUsed) {
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