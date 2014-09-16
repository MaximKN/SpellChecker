package uk.ac.standrews.cs2001;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
/**
 * This class is a graphical user interface used by SpellCheckerUI. It is another extension for this project.
 */
public class SpellCheckerGUI extends JFrame {
    private Dimension dim;
    private JTextPane textPane = new JTextPane();
    private JButton checkButton = new JButton();
    private SpellCheckerParser parser = new SpellCheckerParser();
    private SpellChecker spellChecker = new SpellChecker();
    private StyledDocument doc = textPane.getStyledDocument();
    private Style style = textPane.addStyle("Style", null);

    public SpellCheckerGUI(){
        this.setAppearance();
        this.addTextArea();
        this.addCheckButton();
        this.setVisible(true);
    }

    public static void main(String[] args){
        new SpellCheckerGUI();
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
        this.textPane.setVisible(true);
        this.add(textPane, BorderLayout.CENTER);
    }

    public void addCheckButton(){
        this.checkButton.setText("Check my text");
        this.checkButton.setPreferredSize(new Dimension(520, 70));
        this.add(checkButton, BorderLayout.SOUTH);
        this.checkButton.setVisible(true);
        /* New lambda function */
        this.checkButton.addActionListener(de -> {
            String[] inputWords = parser.parse(this.textPane.getText());
            SpellCheckResult[] scResults = new SpellCheckResult[inputWords.length];
            this.textPane.setText(""); // Clear the text area
            for (int i = 0; i < inputWords.length; i++) {
                scResults[i] = spellChecker.bsCheck(inputWords[i]);
                if (!scResults[i].isCorrect()){
                    StyleConstants.setForeground(style, Color.red);

                    try { doc.insertString(doc.getLength(), inputWords[i] + " ",style); }
                    catch (BadLocationException e){
                        e.printStackTrace();
                    }
                }
                else{
                    StyleConstants.setForeground(style, Color.blue);

                    try { doc.insertString(doc.getLength(), inputWords[i] + " ",style); }
                    catch (BadLocationException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
