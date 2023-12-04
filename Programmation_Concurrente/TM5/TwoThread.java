package Programmation_Concurrente.TM5;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class TwoThread implements Executor{

	Tache2 t1;
	Tache2 t2;
	ArrayList<Runnable> commands;
	
	
	
	public TwoThread() {
		commands = new ArrayList<Runnable>();
		t1 = new Tache2(this);
		t2 = new Tache2(this);
		t1.start();
		t2.start();
	}
	
	@Override
	public void execute(Runnable command) {
		if(t1.cmd == null) {
			synchronized (t1) {
					t1.cmd = command;
					t1.notify();
			}
		}else if(t2.cmd == null) {
			synchronized (t2) {
					t2.cmd = command;
					t2.notify();
			}
		}else {
			commands.add(command);
		}
	}
}
