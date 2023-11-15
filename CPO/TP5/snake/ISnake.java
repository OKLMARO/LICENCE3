package snake;

import java.util.List;

public interface ISnake {

	Direction getDirection();

	void setDirection(Direction direction);

	List<Coordinate> getBody();

	boolean isAlive();
	
	void move();	
	
	default public Coordinate getHead() {
		var body = getBody();
		return body.get(body.size()-1);
	}

	void addEnergy(int energy);

	int getEnergy();

	Game getGame();

	void setAlive(boolean alive);
}