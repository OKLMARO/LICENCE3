package snake;

public class Fruit {
	
	static final int TIME_OUT = 25;	
	private int countdown;
	private Coordinate position;
	private boolean alive;
	private boolean special;
	
	public Fruit(Coordinate position) {
		this(position, false);
	}
	
	public Fruit(Coordinate position, boolean special) {
		countdown = TIME_OUT;
		this.position = position;
		alive = true;
		this.special = special;
	}
		
	public void step(ISnake snake) {
		if (alive) {
			countdown--;
			if(countdown == 0 || snake.getHead().equals(position)) {
				if (snake.getHead().equals(position)) {
					if(this.special){
						snake.addEnergy(5);
					} else{
						snake.addEnergy(1);
					}
				}
				alive = false;
			}			
		}
		
	}

	public boolean isAlive() {		
		return alive;
	}

	public Coordinate getPosition() {
		return position;
	}

	public boolean getSpecial(){
		return this.special;
	}
}
