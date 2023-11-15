package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class App {	
	static final int FRAMES_PER_SECOND = 5;	
	static final int SCALE = 10;

	public static void main(String[] args) {		
		Game game = new Game(50, 50, new Coordinate(40, 30));
		display(game);
		while (game.getSnake().isAlive()) {
			game.step();
			try {
				Thread.sleep(1000 / FRAMES_PER_SECOND);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void display(Game game) {		
		JFrame frame = new JFrame("Snake");
		JComponent component = new JComponent() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				SnakeObservable snake = game.getSnake();
				g.setColor(Color.GREEN);
				g.drawString("Score : " + snake.getEnergy(), 5, 10);
				g.setColor(Color.BLUE);
				for (Coordinate c : snake.getBody()) 								
					g.fillOval(c.getX() * SCALE, c.getY() * SCALE, SCALE, SCALE);
				game.getFruits().forEach(fruit -> {
					var c = fruit.getPosition();
					if (fruit.getSpecial() == true) {g.setColor(Color.RED);} else{g.setColor(Color.GREEN);}
					g.fillOval(c.getX() * SCALE, c.getY() * SCALE, SCALE, SCALE);
				});
			}				
		};
		game.getSnake().register(new SnakeObserver() {			
			@Override
			public void update() {
				component.repaint();			
			}
		});
		frame.setContentPane(component);
		frame.setSize(game.getWidth() * SCALE, game.getHeight() * SCALE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(new KeyAdapter() {
			SnakeObservable snake = game.getSnake();
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					snake.setDirection(Direction.Left);
					break;
				case KeyEvent.VK_RIGHT:
					snake.setDirection(Direction.Right);
					break;
				case KeyEvent.VK_UP:
					snake.setDirection(Direction.Up);
					break;
				case KeyEvent.VK_DOWN:
					snake.setDirection(Direction.Down);
					break;
				default:
					break;
				}
			}
		});
	}	
	

}
