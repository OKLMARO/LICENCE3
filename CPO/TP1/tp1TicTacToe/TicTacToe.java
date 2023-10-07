import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class TicTacToe extends JComponent {
    public final static int WIDTH = 300;
    public final static int HEIGHT = 300;
    private int turn;
    private final Player[][] board = new Player[3][3];

    public TicTacToe() {
        super();
        for (Player[] line: board)  Arrays.fill(line, Player.EMPTY);
        setOpaque(true);
        setSize(WIDTH, HEIGHT);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                incTurn();
                int x = e.getX();
                int y = e.getY();
                if (getTurn() % 2 == 0)
                    board[position(x, TicTacToe.WIDTH)][position(y, TicTacToe.HEIGHT)] = Player.TWO;
                else
                    board[position(x, TicTacToe.WIDTH)][position(y, TicTacToe.HEIGHT)] = Player.ONE;
                repaint();
            }
        });
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        int width = getSize().width;
        int height = getSize().height;
        g.setColor(Color.BLACK);
        drawVLines(g, width, height);
        drawHLines(g, width, height);
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == Player.ONE) {
                    g.setColor(Color.RED);
                    drawMove(g, i, j);
                } else if (board[i][j] == Player.TWO) {
                    g.setColor(Color.BLUE);
                    drawMove(g, i, j);
                }
            }
    }
    private void drawVLines(Graphics g, int width, int height) {
        g.drawLine(width / 3, 0, width / 3, height);
        g.drawLine(width * 2 / 3, 0, width * 2 / 3, height);
    }
    private void drawHLines(Graphics g, int width, int height) {
        g.drawLine(0, height / 3, width, height / 3);
        g.drawLine(0, height * 2 / 3, width, height * 2 / 3);
    }
    private void drawMove(Graphics g, int i, int j) {
        var x = getSize().width * i / 3;
        var xWidth = getSize().width / 3;
        var y = getSize().height * j / 3;
        var yWidth = getSize().height / 3;
        g.fillRect(x, y, xWidth, yWidth);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, xWidth, yWidth);
    }
    private int position(int pos, int size) {
        return pos / (size / 3);
    }
    //TODO
    public Player winner() {
        return Player.EMPTY;
    }
    void incTurn() {
        turn++;
    }
    public int getTurn() {
        return turn;
    }
}
