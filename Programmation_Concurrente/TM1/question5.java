import static java.lang.Thread.sleep;

public class question5 {
    public static void main(String[] args) throws InterruptedException {
        Data d = new Data();

        Cons t1 = new Cons(d, "Michel");
        Cons t2 = new Cons(d, "Didier");
        Cons t3 = new Cons(d, "Jean");
        Cons t4 = new Cons(d, "Pierre");

        Prod c1 = new Prod(d);

        c1.start();

        t1.start();
        sleep(100);
        t2.start();
        sleep(200);
        t3.start();
        sleep(300);
        t4.start();

    }
}

class Cons extends Thread {
    private final Data d;
    private final String name;
    public Cons(Data d, String name) {
        this.d = d;
        this.name = name;
    }

    @Override
    public void run() throws RuntimeException{
        while (true) {
            if (d.flag) {
                System.out.println(name + " " + d.x);
                d.flag = false;
                try {
                    sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class Prod extends Thread {
    private final Data d;
    public Prod(Data d) {
        this.d = d;
    }

    @Override
    public void run() {
        while (true) {
            if (!d.flag) {
                d.x++;
                d.flag = true;
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Data {
    public int x = 0;
    public boolean flag = false;
}