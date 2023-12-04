package Programmation_Concurrente.TM5;

public class Tache2 extends Thread{

	Runnable cmd;
	TwoThread t;
	
	public Tache2(TwoThread t) {
		this.t = t;
	}

	@Override
	public void run() {
		super.run();
		while(true) {
			synchronized (this) {
				if(cmd == null) {
					try {wait();} 
					catch (InterruptedException e) {e.printStackTrace();}
				}
				cmd.run();
				synchronized (t) {
					if(t.commands.size() > 0) {
						cmd = t.commands.get(0);
						t.commands.remove(0);
					}else cmd = null;
				}
			}
		}	
	}
}
