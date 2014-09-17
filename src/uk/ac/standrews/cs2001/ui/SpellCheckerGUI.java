package uk.ac.standrews.cs2001.ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import java.io.*;
import uk.ac.standrews.cs2001.main.*;
import uk.ac.standrews.cs2001.parser.*;
/**
 * This class is a graphical user interface used by SpellCheckerUI. It is another extension for this project.
 */
public class SpellCheckerGUI extends JFrame {
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private final JTextArea errorConsole = new JTextArea();
    private final JTextPane textPane = new JTextPane();
    private final JButton checkButton = new JButton();
    private final SpellCheckerParser parser = new SpellCheckerParser();
    private final SpellChecker spellChecker = new SpellChecker();
    private final StyledDocument doc = textPane.getStyledDocument();
    private final Style style = textPane.addStyle("Style", null);

    public static void main(String[] args){
        SwingUtilities.invokeLater(SpellCheckerGUI::new);
    }

    public SpellCheckerGUI(){
        this.redirectSystemStreams();
        this.setAppearance();
        this.addTextArea();
        this.addCheckButton();
        this.setVisible(true);
    }
    public void setAppearance(){
        this.setTitle("Spell checker");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(550, 580);
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
        this.checkButton.addActionListener(e -> this.check());
    }

    /**
     * This method is used by the check button. It parses all the input text and
     * then uses binary search algorithm to find their existence in the dictionary
     */
    public void check(){
        String[] inputWords = parser.parse(this.textPane.getText());
        if (inputWords == null){
            JOptionPane.showMessageDialog(new JFrame(), this.errorConsole.getText(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SpellCheckResult[] scResults = new SpellCheckResult[inputWords.length];
        this.textPane.setText(""); // Clear the text area

        for (int i = 0; i < inputWords.length; i++) {
            scResults[i] = spellChecker.bsCheck(inputWords[i]);
            if (!scResults[i].isCorrect()){
                StyleConstants.setForeground(style, Color.red);
                // Change color and add to the text area
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
        StyleConstants.setForeground(style, Color.black);
        try { doc.insertString(doc.getLength(), " ",style); }
        catch (BadLocationException e){
            e.printStackTrace();
        }
    }

    /**
     * This method is used to put error messages in the error text area
     * @param text text that contains errors in the program
     */
    public void updateTextArea(String text) {
        this.errorConsole.append(text);
    }

    /**
     * This method overrides System.err and calls updateTextArea
     */
    public void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                updateTextArea(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateTextArea(new String(b, off, len));
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };
        System.setErr(new PrintStream(out, true));
    }
}
