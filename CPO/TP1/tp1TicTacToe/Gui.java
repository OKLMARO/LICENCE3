import javax.swing.JFrame;

public class Gui {
    private JFrame frame = new JFrame("Tic Tac Toe");
    private TicTacToe game = new TicTacToe();
    public Gui() {
        frame.setContentPane(game);
        frame.setSize(game.getSize());
        frame.setLocation(100, 100);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}