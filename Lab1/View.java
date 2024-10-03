package Lab1;

/** View class for Lab 1
 * @author Alexa Mercado
 *
 * 
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class View extends JFrame {

    JPanel displayWindow = new JPanel(new GridLayout(7, 2, 2, 2)); // primary window
    JPanel panel1 = new JPanel(new GridLayout(1, 2, 4, 4)); // panels or small sections in primary window
    JPanel panel2 = new JPanel(new GridLayout(1, 2, 4, 4));
    JPanel panel3 = new JPanel(new GridLayout(1, 2, 4, 4));
    JPanel panel4 = new JPanel(new GridLayout(1, 2, 4, 4));
    JPanel panel5 = new JPanel(new GridLayout(1, 2, 4, 4));
    JPanel panel6 = new JPanel(new GridLayout(1, 2, 4, 4));

    JLabel leftColumnTitle = new JLabel("     First Matrix Rows/Columns    "); // title for left column
    JLabel rightColumnTitle = new JLabel("    Second Matrix Rows/Columns  "); // title for right column
    JLabel emptySpace = new JLabel(""); // empty space
    JLabel fileName = new JLabel("File: ");
    JLabel fileName2 = new JLabel("File: ");
    JLabel fileName3 = new JLabel("File: ");
    JTextArea fileArea = new JTextArea();
    JTextArea fileArea2 = new JTextArea();
    JTextArea fileArea3 = new JTextArea();
    JButton createButton = new JButton("Create"); // create button
    JButton executeButton = new JButton("Execute"); // execute button
    JButton resetButton = new JButton("Reset"); // reset button
    JButton quitButton = new JButton("Quit"); // quit button
    JButton readButton1 = new JButton("Read");
    JButton readButton2 = new JButton("Read");
    JButton clearButton1 = new JButton("Clear");
    JButton clearButton2 = new JButton("Clear");
    JButton saveButton1 = new JButton("Save");
    JButton saveButton2 = new JButton("Save");
    JButton saveButton3 = new JButton("Save");
    JButton openButton1 = new JButton("Open");
    JButton openButton2 = new JButton("Open");
    JButton openButton3 = new JButton("Open");

    JTextField matrix1A = new JTextField(10); // textfield for matrix1 I
    JTextField matrix1B = new JTextField(10); // textfield for matrix1 J
    JTextField matrix2A = new JTextField(10); // textfield for matrix2 I
    JTextField matrix2B = new JTextField(10); // textfield for matrix2 J

    JComboBox comboBox = new JComboBox(); // dropdown menu

    JTextField remarks = new JTextField(100);

    int numData;
    boolean verb = true;

    int matrix1X;
    int matrix1Y;
    int matrix2X;
    int matrix2Y;
    JFrame vectorF1;
    JFrame vectorF2;
    JFrame vectorF3;
    JPanel matrixResults;
    JPanel matrixResultsBottom;
    JPanel matrix1;
    JPanel matrix1BottomPanel;
    JPanel matrix2;
    JPanel matrix2BottomPanel;

    public Color newColor = new Color(60, 45, 121);

    public static void main(String[] args) {
        new View();
    }

    /**
     * Constructor to create the view
     * 
     */
    public View() {

        // adding panels to displaywindow
        displayWindow.add(panel1);
        displayWindow.add(panel2);
        displayWindow.add(panel3);
        displayWindow.add(panel4);
        displayWindow.add(panel5);
        displayWindow.add(panel6);

        // panel 1 adding titles
        panel1.add(leftColumnTitle);
        panel1.add(rightColumnTitle);
        panel1.setBackground(newColor);
        leftColumnTitle.setForeground(Color.WHITE);
        rightColumnTitle.setForeground(Color.WHITE);

        // panel 2 adding matrix I textfields
        panel2.add(matrix1A);
        panel2.add(matrix2A);
        panel2.setBackground(newColor);

        // panel 3 adding matrix J textfields
        panel3.add(matrix1B);
        panel3.add(matrix2B);
        panel3.setBackground(newColor);

        // Use FlowLayout to center the createButton
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel4.add(createButton);
        panel4.setBackground(newColor);

        // Add comboBox and executeButton to panel5
        panel5.add(comboBox);
        panel5.add(executeButton);
        panel5.setBackground(newColor);

        // Add resetButton and quitButton to panel6
        panel6.add(resetButton);
        panel6.add(quitButton);
        panel6.setBackground(newColor);

        // Add remarks at the bottom of the frame
        add(remarks, BorderLayout.SOUTH);
        remarks.setEditable(false);
        remarks.setText(" Feedback to the user");

        // Add items to comboBox
        comboBox.addItem("Add");
        comboBox.addItem("Subtract");
        comboBox.addItem("Multiply");

        // Add displayWindow to the frame
        add(displayWindow);

        // Set background for the main window
        displayWindow.setBackground(newColor);

        // Set frame properties
        setTitle("Matrix Calculator");
        setBounds(500, 500, 500, 500);
        setVisible(true);
    }
}
