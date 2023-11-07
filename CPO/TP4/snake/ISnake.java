package snake;

import java.util.List;

public interface ISnake {

	Direction getDirection();

	void setDirection(Direction direction);

	List<Coordinate> getBody();

	boolean isAlive();
	
	void move();	

}