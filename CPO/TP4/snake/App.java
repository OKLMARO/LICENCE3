package snake;

import java.util.List;

public class App {

	
	static final int FRAMES_PER_SECOND = 3;
	
	public static void main(String[] args) {		
		Game game = new Game(50, 50, new Coordinate(40, 30));
		game.getSnake().register(new SnakeObserver() {			
			@Override
			public void update() {
				List<Coordinate> body = game.getSnake().getBody();
				Coordinate coordinate = body.get(body.size()-1);
				System.out.println("" + coordinate.getX() + " " + coordinate.getY());				
			}
		});

		game.getSnake().register(new SnakeObserver() {
			Affichage affichage = new Affichage(game);
			@Override
			public void update() {
				affichage.repaint();
			}
		});
		
		while (game.getSnake().isAlive()) {
			game.step();
			try {
				Thread.sleep(1000 / FRAMES_PER_SECOND);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		

	}

}
