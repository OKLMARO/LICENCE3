package snake;

public class Game {
	
	private int height;
	private int width;
	private SnakeObservable snake;

	public Game(int width, int height, Coordinate c) {
		this.height = height;
		this.width = width;
		snake = new SnakeObservable(this, c);
	}	

	public SnakeObservable getSnake() {
		return snake;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	 boolean isOut(Coordinate c) {
		if (c.getX() < 0 || c.getY() < 0)
			return true;
		if (c.getX() >= width || c.getY() >= height)
			return true;
		return false;
	}

	public void step() {
		snake.move();
	}


}
