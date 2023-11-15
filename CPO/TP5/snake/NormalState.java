package snake;

public class NormalState extends SnakeState {

    protected ISnake snake;

    protected NormalState(Snake snake) {
        super(snake);
        this.snake = snake;
    }

    @Override
    public void move() {
		Coordinate current = snake.getBody().get(snake.getBody().size() - 1);
		Coordinate next = new Coordinate(current.getX() + snake.getDirection().getX(), current.getY() + snake.getDirection().getY());
		if(snake.getBody().contains(next) || snake.getGame().isOut(next))
			snake.setAlive(false);
        // ne bouge pas car je modifie une copie
		snake.getBody().add(next);
		snake.getBody().remove(0);
	}
    
}
