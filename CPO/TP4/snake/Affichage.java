package snake;

import javax.swing.*;
import java.awt.*;

public class Affichage extends JFrame {
    Game game;

    public Affichage(Game game) {
        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.game = game;
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 500, 500);
        g.setColor(Color.BLUE);
        for (Coordinate coordinate : this.game.getSnake().getBody()) {
            g.drawRect(coordinate.getX(), coordinate.getY(), 20, 20);
        }
    }
}
