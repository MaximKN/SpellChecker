package uk.ac.standrews.cs2001;

import javax.swing.*;
import java.awt.*;

/**
 * This class is a graphical user interface used by SpellCheckerUI. It is another extension for this project.
 */
public class SpellCheckerGUI extends JFrame {
    private Dimension dim;
    private JTextArea textArea = new JTextArea();
    private JButton checkButton = new JButton();

    public SpellCheckerGUI(){
        this.setAppearance();
        this.addTextArea();
        this.addCheckButton();
        this.setVisible(true);
    }

    /**
     * This method sets all settings for the spell checker.
     */
    public void setAppearance(){
        this.setTitle("Spell checker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(550, 580);
        this.dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    public void addTextArea(){
        this.textArea.setVisible(true);
        this.add(textArea, BorderLayout.CENTER);
    }

    public void addCheckButton(){
        this.checkButton.setText("Check my text!");
        this.checkButton.setPreferredSize(new Dimension(520, 70));
        this.add(checkButton, BorderLayout.SOUTH);
        this.checkButton.setVisible(true);
        /* New lambda function */
        this.checkButton.addActionListener(ae -> {
            this.textArea.getText();
        });
    }

}
