package CPO.tp1exo1;

import java.awt.Dimension;

import javax.swing.JFrame;

public class App {
    private static void createWindow(){
        JFrame frame = new JFrame("Ma Fenêtre");
        frame.setPreferredSize(new Dimension(300, 300));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        createWindow();
    }
}
