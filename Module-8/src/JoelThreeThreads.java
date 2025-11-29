//Joel Atkinson, November 28, 2025, CSD420 Advanced Java Programming Assignment 8.2
/* The purpose of this assignment is to create three threads that will display letters, numbers, and symbols and then
output them into a GUI to show the three threads running together until each one has printed 10,000 elements and prints
a message stating the thread is complete
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class JoelThreeThreads extends JFrame {

    private JTextArea textArea;
    private final Random random = new Random();

    // Character pools
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%&*";

    public JoelThreeThreads() {
        // Set up the GUI window
        setTitle("Joel Atkinson Assignment 8.2 Random Character Generator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();


        //Add scroll pane to scroll through GUI and thousands of outputs
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        // Start the three threads
        Thread letterThread = new Thread(new LetterPrinter(), "Letter-Thread");
        Thread numberThread = new Thread(new NumberPrinter(), "Number-Thread");
        Thread symbolThread = new Thread(new SymbolPrinter(), "Symbol-Thread");

        letterThread.start();
        numberThread.start();
        symbolThread.start();
    }

    // Runnable for printing letters
    class LetterPrinter implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                char c = LETTERS.charAt(random.nextInt(LETTERS.length()));
                appendToTextArea("Letter Thread: " + c + "\n");
                try {
                    Thread.sleep(1); // Small delay so GUI stays responsive
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            appendToTextArea("LETTER THREAD COMPLETE (10,000 LETTERS PRINTED)\n");
        }
    }

    // Runnable for printing numbers
    class NumberPrinter implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                char c = DIGITS.charAt(random.nextInt(DIGITS.length()));
                appendToTextArea("Number Thread: " + c + "\n");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            appendToTextArea("NUMBER THREAD COMPLETE (10,000 DIGITS PRINTED)\n");
        }
    }

    // Runnable for printing symbols
    class SymbolPrinter implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                char c = SYMBOLS.charAt(random.nextInt(SYMBOLS.length()));
                appendToTextArea("Symbol Thread: " + c + "\n");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            appendToTextArea("SYMBOL THREAD COMPLETE (10,000 SYMBOLS PRINTED)\n");
        }
    }

    /* Using invokeLater() to ensure that threads add to text output when there is room to do so preventing the system
    from crashing */
    private void appendToTextArea(String text) {
        SwingUtilities.invokeLater(() -> textArea.append(text));
    }

    // Main method to run the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JoelThreeThreads app = new JoelThreeThreads();
            app.setVisible(true);
        });
    }
}
