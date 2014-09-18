package uk.ac.standrews.cs2001.ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import java.io.*;
import uk.ac.standrews.cs2001.main.*;
import uk.ac.standrews.cs2001.parser.*;
/**
 * Graphical user interface used by SpellCheckerUI. It is another extension for this project.
 */
public class SpellCheckerGUI extends JFrame {
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private JTextArea errorConsole = new JTextArea();
    private JTextPane textPane = new JTextPane();
    private JButton checkButton = new JButton();
    private SpellCheckerParser parser = new SpellCheckerParser();
    private SpellChecker spellChecker = new SpellChecker();
    private StyledDocument doc = textPane.getStyledDocument();
    private Style style = textPane.addStyle("Style", null);

    public static void main(String[] args){
        SwingUtilities.invokeLater(SpellCheckerGUI::new);
    }

    /**
     * Default constructor
     */
    public SpellCheckerGUI(){
        this.redirectSystemStreams();
        this.setAppearance();
        this.addTextArea();
        this.addCheckButton();
        this.setVisible(true);
    }

    /**
     * Sets the appearance of the frame
     */
    public void setAppearance(){
        this.setTitle("Spell checker");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(550, 580);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    /**
     * Adds the text area to the frame
     */
    public void addTextArea(){
        this.textPane.setVisible(true);
        this.add(textPane, BorderLayout.CENTER);
    }

    /**
     * Adds the button that checks the text
     */
    public void addCheckButton(){
        this.checkButton.setText("Check my text");
        this.checkButton.setPreferredSize(new Dimension(520, 70));
        this.add(checkButton, BorderLayout.SOUTH);
        this.checkButton.setVisible(true);
        this.checkButton.addActionListener(e -> this.check());
    }

    /**
     * Used by the check button. It parses all the input text and
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
                SpellCheckerSuggestion[] tmp = spellChecker.findSuggestions(inputWords[i]);
                JOptionPane.showMessageDialog(null, "Did you mean: " + tmp[0].getWord() + " instead of " + inputWords[i]+"?");
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
     * Used to put error messages in the error text area
     * @param text text that contains errors in the program
     */
    public void updateTextArea(String text) {
        this.errorConsole.append(text);
    }

    /**
     * Overrides System.err and calls updateTextArea
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
