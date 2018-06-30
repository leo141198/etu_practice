package Graphics;

import javax.swing.*;
import java.awt.*;

public class Form extends JFrame {
    public JPanel formView;
    private JButton button1;
    public JPanel map;

    public Form() {
        setContentPane(formView);
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 300));
    }

    private void createUIComponents() {
        map = new Map();
    }
}