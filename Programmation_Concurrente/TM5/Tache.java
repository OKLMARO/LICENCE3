package Programmation_Concurrente.TM5;

import java.util.ArrayList;

public class Tache extends Thread{

	ArrayList<Runnable> commands;
	
	public Tache() {
		this.commands = new ArrayList<Runnable>();
	}
	
	public void AddCommands(Runnable command) {
		commands.add(command);
	}
	
	@Override
	public void run() {
		synchronized (this) {
			while(true) {
				if(commands.size() == 0) {
					try {wait();} 
					catch (InterruptedException e) {e.printStackTrace();}
				}
				commands.get(0).run();
				commands.remove(0);
			}
		}
	}

}
