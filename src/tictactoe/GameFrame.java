
package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameFrame {
    static JButton [] button = new JButton [9];
    static JButton newGameButton = new JButton("New Game");
    static JPanel centerPanel,p1,p2;
    static JFrame frame = new JFrame("My game");
    static JLabel label = new JLabel ("score");
    static JLabel win = new JLabel("WIN :");
    static JLabel lost = new JLabel ("LOST :");
    Listener lis = new Listener(this);
    
    public GameFrame() {
        centerPanel = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        newGameButton.addActionListener(lis);
        centerPanel.setLayout(new BorderLayout());
        
        p1.setLayout(new BorderLayout());
        p1.add(BorderLayout.LINE_START, win);
        p1.add(BorderLayout.LINE_END,lost);
        p1.add(BorderLayout.SOUTH, newGameButton);
        centerPanel.add(BorderLayout.NORTH,p1);
        
        p2.setLayout(new BorderLayout());
        p2.setLayout(new GridLayout(3,3));
        for (int i = 0; i < button.length; i++){
            button [i] = new JButton("");
            button[i].setBackground(Color.orange);
            button[i].addActionListener(lis);
            p2.add(BorderLayout.CENTER, button[i]);
        }
        
        JPanel p3 = new JPanel(new BorderLayout());
        p3.add(BorderLayout.CENTER, label);
        centerPanel.add(BorderLayout.SOUTH, p3);
        centerPanel.add(BorderLayout.CENTER, p2);
        
        frame.add(centerPanel);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }  
}
