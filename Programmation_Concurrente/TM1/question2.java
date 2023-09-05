public class question2 extends Thread{
    @Override
    public void run(){
        for(int i = 0;i<1000;i++){
            System.out.println(Thread.currentThread().getId() + " " + i);
        }
        for(int j = 0;j<1000;j++){
            System.out.println(Thread.currentThread().getId() + " " + j);
        }
    }
}
