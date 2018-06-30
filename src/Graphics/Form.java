package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JFrame {
    public JPanel formView;
    private JButton button1;
    public JPanel map;
    private JComboBox comboBox1;

    private Map m;

    public Form() {
        setContentPane(formView);
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 300));

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //m.timer.start();
            }
        });
    }

    private void createUIComponents() {
        m = new Map();
        map = m;
    }
}