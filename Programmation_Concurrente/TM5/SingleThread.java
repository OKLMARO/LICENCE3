package Programmation_Concurrente.TM5;

import java.util.concurrent.Executor;

public class SingleThread implements Executor{

	Tache t;
	
	public SingleThread() {
		t = new Tache();
		t.start();
	}
	
	@Override
	public void execute(Runnable command) {
		synchronized (t) {
			t.AddCommands(command);;
			t.notify();
		}
	}

}
