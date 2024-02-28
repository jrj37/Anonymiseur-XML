package Graphics;

import javax.swing.*;
import java.awt.*;

public class Gif extends JFrame {
    public void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("LICORNE");

        ImageIcon imageIcon = new ImageIcon("data/licornedanse.gif");
        JLabel label = new JLabel(imageIcon);
        add(label, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

