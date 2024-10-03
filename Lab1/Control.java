package Lab1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.io.*;

/**
 * Control class for Lab 1
 * 
 * @author Alexa Mercado
 * 
 * 
 */

public class Control extends View implements ActionListener, Serializable {
    JTextField[][] array1;
    JTextField[][] array2;
    JTextField[][] arrayResult;
    double[][] data1;
    double[][] data2;
    double[][] dataResults;
    Model m;

    public static void main(String[] args) {
        new Control();
    }

    /**
     * Constructor extends the view and add actionlisteners to buttons
     * 
     */
    public Control() {
        m = new Model(this);
        createButton.addActionListener(this);
        executeButton.addActionListener(this);
        resetButton.addActionListener(this);
        quitButton.addActionListener(this);
        readButton1.addActionListener(this);
        readButton2.addActionListener(this);
        clearButton1.addActionListener(this);
        clearButton2.addActionListener(this);
        openButton1.addActionListener(this);
        openButton2.addActionListener(this);
        openButton3.addActionListener(this);
        saveButton1.addActionListener(this);
        saveButton2.addActionListener(this);
        saveButton3.addActionListener(this);

    }

    /**
     * Grabs action associated with button
     * 
     */
    public void actionPerformed(ActionEvent action) {
      
        if (action.getActionCommand().equals("Create")) {
            addMatrixPanel();
        }
        if (action.getActionCommand().equals("Reset")) {
            resetAll();
        }
        if (action.getActionCommand().equals("Quit")) {
            quitApp();
        }
        String selection = (String) comboBox.getSelectedItem();
        if (action.getActionCommand().equals("Execute") && selection.equals("Add")) {
            execution("Add");
        }
        if (action.getActionCommand().equals("Execute") && selection.equals("Subtract")) {
            execution("Subtract");
        }
        if (action.getActionCommand().equals("Execute") && selection.equals("Multiply")) {
            execution("Multiply");
        }

        if (action.getSource().equals(readButton1)) {
            ReadV(1);
        }
        if (action.getSource().equals(readButton2)) {
            ReadV(2);
        }
        if (action.getSource().equals(clearButton1)) {
            clearAll(array1, data1);
        }
        if (action.getSource().equals(clearButton2)) {
            clearAll(array2, data2);
        }
        if (action.getSource().equals(saveButton1)) {
            saveFile(fileArea.getText(), data1);
        }
        if (action.getSource().equals(saveButton2)) {
            saveFile(fileArea2.getText(), data2);
        }
        if (action.getSource().equals(saveButton3)) {
            saveFile(fileArea3.getText(), dataResults);
        }
        if (action.getSource().equals(openButton1)) {
            openFileDisplay(openFile(fileArea.getText()), matrix1, vectorF1, 1);
        }
        if (action.getSource().equals(openButton2)) {
            openFileDisplay(openFile(fileArea2.getText()), matrix2, vectorF2, 2);
        }
        if (action.getSource().equals(openButton3)) {
            openFileDisplay(openFile(fileArea3.getText()), matrixResults, vectorF3, 3);
        }

        validate();
        repaint();
    }

    /**
     * Creates matrix1 and matrix2 panels
     * 
     */
    public void addMatrixPanel() {
        if (validateInteger(matrix1A) && validateInteger(matrix1B) && validateInteger(matrix2A) &&
                validateInteger(matrix2B)) {

            matrix1X = Integer.parseInt(matrix1A.getText());
            matrix1Y = Integer.parseInt(matrix1B.getText());
            matrix2X = Integer.parseInt(matrix2A.getText());
            matrix2Y = Integer.parseInt(matrix2B.getText());

            vectorF1 = new JFrame("Matrix 1");
            vectorF2 = new JFrame("Matrix 2");
            vectorF3 = new JFrame("Results");
            matrix1 = new JPanel(new GridLayout(matrix1X, matrix1Y, 4, 4));
            matrix1BottomPanel = new JPanel(new GridLayout(1, 6));
            matrix2 = new JPanel(new GridLayout(matrix2X, matrix2Y, 4, 4));
            matrix2BottomPanel = new JPanel(new GridLayout(1, 6));
            matrixResultsBottom = new JPanel(new GridLayout(1, 6));

            array1 = new JTextField[matrix1X][matrix1Y];
            array2 = new JTextField[matrix2X][matrix2Y];

            matrix1.setBackground(Color.MAGENTA);
            matrix2.setBackground(Color.PINK);

            vectorF1.add(matrix1);
            vectorF1.add(matrix1BottomPanel, BorderLayout.SOUTH);
            matrix1BottomPanel.add(clearButton1);
            matrix1BottomPanel.add(readButton1);
            matrix1BottomPanel.add(fileName);
            matrix1BottomPanel.add(fileArea);
            matrix1BottomPanel.add(saveButton1);
            matrix1BottomPanel.add(openButton1);

            vectorF2.add(matrix2);
            vectorF2.add(matrix2BottomPanel, BorderLayout.SOUTH);
            matrix2BottomPanel.add(clearButton2);
            matrix2BottomPanel.add(readButton2);
            matrix2BottomPanel.add(fileName2);
            matrix2BottomPanel.add(fileArea2);
            matrix2BottomPanel.add(saveButton2);
            matrix2BottomPanel.add(openButton2);

            // vectorF3.add(matrixResults);

            for (int i = 0; i < matrix1X; i++) {
                for (int j = 0; j < matrix1Y; j++) {
                    array1[i][j] = new JTextField(10);

                    matrix1.add(array1[i][j]);

                }
            }

            for (int i = 0; i < matrix2X; i++) {
                for (int j = 0; j < matrix2Y; j++) {
                    array2[i][j] = new JTextField(10);

                    matrix2.add(array2[i][j]);

                }
            }

            validate();
            repaint();
            vectorF1.setBounds(500, 50, 400, 250);
            vectorF2.setBounds(1000, 50, 400, 250);
            vectorF1.setVisible(true);
            vectorF2.setVisible(true);
        }
    }

    /**
     * Resets all textfields, data, and JFrames
     * 
     */
    public void resetAll() {
        matrix1A.setText("");
        matrix1B.setText("");
        matrix2A.setText("");
        matrix2B.setText("");
        fileArea.setText("");
        fileArea2.setText("");
        vectorF1.remove(matrix1);
        vectorF2.remove(matrix2);

        data1 = null;
        data2 = null;
        dataResults = null;
        if (vectorF1 != null)
            vectorF1.dispose();
        if (vectorF2 != null)
            vectorF2.dispose();
        if (vectorF3 != null)
            vectorF3.dispose();
        System.out.println("RESET");
        remarks.setText("Feedback to the user");

    }

    /**
     * Exits Java Application
     * 
     */
    public void quitApp() {
        System.exit(1);
    }

    /**
     * Clears all textfields in the frame 
     * 
     =
     */
    public void clearAll(JTextField array[][], double[][] data) {

        data = null;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j].setText("");
                // System.out.println("cleared element");
            }
        }
        System.out.println("set fields to blank");
        remarks.setText("Cleared matrix");

    }

    /**
     * Calls calculation methods and creates panel for result
     * 
     *
     */

    public void execution(String operation) {

        switch (operation) {
            case "Add":
                if (validateRead()) {
                    dataResults = new double[data1.length][data1[0].length];
                    dataResults = m.addM(data1, data2);
                    arrayResult = new JTextField[data1.length][data1[0].length];
                    for (int i = 0; i < arrayResult.length; i++) {
                        for (int j = 0; j < arrayResult[0].length; j++) {
                            arrayResult[i][j] = new JTextField(10);
                            System.out.println(dataResults[i][j]);
                            arrayResult[i][j].setText("" + dataResults[i][j]);

                        }
                    }
                    showResult(arrayResult.length, arrayResult[0].length);
                    break;
                }
            case "Subtract":
                if (validateRead()) {
                    dataResults = new double[data1.length][data1[0].length];
                    dataResults = m.subtractM(data1, data2);
                    arrayResult = new JTextField[data1.length][data1[0].length];
                    for (int i = 0; i < arrayResult.length; i++) {
                        for (int j = 0; j < arrayResult[0].length; j++) {
                            arrayResult[i][j] = new JTextField(10);
                            System.out.println(dataResults[i][j]);
                            arrayResult[i][j].setText("" + dataResults[i][j]);

                        }
                    }
                    showResult(arrayResult.length, arrayResult[0].length);
                    break;
                }
            case "Multiply":
                if (validateRead()) {
                    dataResults = new double[data1.length][data2[0].length];
                    dataResults = m.multiplyM(data1, data2);
                    arrayResult = new JTextField[data1.length][data2[0].length];
                    for (int i = 0; i < data1.length; i++) {
                        for (int j = 0; j < data2[0].length; j++) {
                            arrayResult[i][j] = new JTextField(10);
                            System.out.println(dataResults[i][j]);
                            arrayResult[i][j].setText("" + dataResults[i][j]);

                        }
                    }
                    showResult(arrayResult.length, arrayResult[0].length);
                    break;
                }
        }

    }

    /**
     * Creates result window
     * 
     */
    public void showResult(int rows, int cols) {
        if (matrixResults != null) {
            vectorF3.remove(matrixResults);
        }
        matrixResults = new JPanel(new GridLayout(rows, cols, 4, 4));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // arrayResult[i][j] = new JTextField(10);

                matrixResults.add(arrayResult[i][j]);

            }
        }
        vectorF3.add(matrixResults);
        vectorF3.add(matrixResultsBottom, BorderLayout.SOUTH);
        matrixResultsBottom.add(fileName3);
        matrixResultsBottom.add(fileArea3);
        matrixResultsBottom.add(saveButton3);
        matrixResultsBottom.add(openButton3);
        matrixResults.setBackground(Color.GREEN);
        System.out.println("Results calculated");
        remarks.setText("Results calculated");
        vectorF3.setBounds(700, 75, 400, 250);
        vectorF3.setVisible(true);
        repaint();
        validate();
    }

    /**
     * Parses each textfield as a double
     * 
     */
    public void ReadV(int opt) {

        if (opt == 1) {
            data1 = null;
            data1 = new double[array1.length][array1[0].length];
            try {
                for (int i = 0; i < data1.length; i++) {
                    for (int j = 0; j < data1[0].length; j++) {
                        // if(!validateDouble(array1[i][j])) break;
                        data1[i][j] = Double.parseDouble(array1[i][j].getText());
                    }
                    System.out.println("Scanned matrix 1");
                    remarks.setText("Scanned Matrix 1");
                }
            } catch (NumberFormatException ex) {
                System.out.print("Number format exception");
                remarks.setText("Matrix contains non numbers or is empty");
            }
        }
        if (opt == 2) {
            data2 = null;
            data2 = new double[array2.length][array2[0].length];
            try {
                for (int i = 0; i < data2.length; i++) {
                    for (int j = 0; j < data2[0].length; j++) {
                        // if(!validateDouble(array2[i][j])) break;
                        data2[i][j] = Double.parseDouble(array2[i][j].getText());
                    }
                    System.out.println("Scanned matrix 2");
                    remarks.setText("Scanned Matrix 2");
                }
            } catch (NumberFormatException ex) {
                remarks.setText("Matrix contains non numbers or is empty");
            }
        }
        dataResults = null;
        repaint();
        validate();
    }

    /**
     * Saves file as object
     * 
     * 
     */
    public void saveFile(String file, double[][] dataStored) {

        try {
            FileOutputStream fileOutput = new FileOutputStream(file);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(dataStored);
            objectOutput.close();
            fileOutput.close();
            System.out.println("File " + file + " has been saved");
            remarks.setText("File " + file + " has been saved");
        } catch (FileNotFoundException e) {
            remarks.setText("File not found");
        } catch (IOException e) {
            remarks.setText("Error initializing stream");
        }
    }

    /**
     * Opens file
     * 
     *
     */
    public double[][] openFile(String file) {
        try {
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            double[][] data = (double[][]) objectInput.readObject();
            objectInput.close();
            fileInput.close();
            System.out.println("Opened file " + file);
            remarks.setText("Opened file: " + file);
            return data;
        } catch (ClassNotFoundException e) {
            remarks.setText("Class not found");
        } catch (FileNotFoundException e) {
            remarks.setText("File not found");
        } catch (IOException e) {
            remarks.setText("Error initializing stream");
        }
        return data1;
    }

    /**
     * Resizes JFrames and Panels to match opened file
     
     * 
     */
    public void openFileDisplay(double[][] data, JPanel matrix, JFrame vector, int num) {
        switch (num) {
            case 1:
                this.data1 = null;
                this.data1 = data;
                vector.dispose();
                vector.remove(matrix);
                // vector.remove(bottom);
                this.array1 = null;
                matrix.removeAll();
                matrix.setLayout(new GridLayout(this.data1.length, this.data1[0].length, 4, 4));
                this.array1 = new JTextField[this.data1.length][this.data1[0].length];
                // bottom.setLayout(new GridLayout(1,6));
                for (int i = 0; i < this.data1.length; i++) {
                    for (int j = 0; j < this.data1[0].length; j++) {
                        this.array1[i][j] = new JTextField(10);
                        this.array1[i][j].setText(String.valueOf(this.data1[i][j]));

                        matrix.add(this.array1[i][j]);

                    }
                }
                vector.add(matrix);
                // vector.add(bottom, BorderLayout.SOUTH);
                repaint();
                validate();
                vector.setVisible(true);
                break;
            case 2:
                vector.dispose();
                vector.remove(matrix);
                // vector.remove(bottom);
                data2 = null;
                this.data2 = data;
                this.array2 = null;
                matrix.removeAll();
                matrix.setLayout(new GridLayout(this.data2.length, this.data2[0].length, 4, 4));
                this.array2 = new JTextField[this.data2.length][this.data2[0].length];
                // bottom.setLayout(new GridLayout(1,6));
                for (int i = 0; i < this.data2.length; i++) {
                    for (int j = 0; j < this.data2[0].length; j++) {
                        this.array2[i][j] = new JTextField(10);
                        this.array2[i][j].setText(String.valueOf(this.data2[i][j]));

                        matrix.add(this.array2[i][j]);

                    }
                }
                vector.add(matrix);
                // vector.add(bottom, BorderLayout.SOUTH);
                repaint();
                validate();
                vector.setVisible(true);
                break;
            case 3:
                vector.dispose();
                vector.remove(matrix);
                // vector.remove(bottom);
                dataResults = null;
                this.dataResults = data;
                this.arrayResult = null;
                matrix.removeAll();
                matrix.setLayout(new GridLayout(dataResults.length, dataResults[0].length, 4, 4));
                this.arrayResult = new JTextField[dataResults.length][dataResults[0].length];
                // bottom.setLayout(new GridLayout(1,6));
                for (int i = 0; i < data.length; i++) {
                    for (int j = 0; j < data[0].length; j++) {
                        this.arrayResult[i][j] = new JTextField(10);
                        this.arrayResult[i][j].setText(String.valueOf(dataResults[i][j]));

                        matrix.add(this.arrayResult[i][j]);

                    }
                }
                vector.add(matrix);
                // vector.add(bottom, BorderLayout.SOUTH);
                repaint();
                validate();
                vector.setVisible(true);
        }

    }

    /*
     * Veriies that the textfield contains a double
     * /* */
    public boolean validateDouble(JTextField datum) {
        try {
            double d = Double.parseDouble(datum.getText());
            return true;
        } catch (NumberFormatException e) {
            System.out.print("Invalid number");
            return false;
        }
    }

    /**
     * Veriies that the textfield contains an integer
     * 
     *\/* */
    public boolean validateInteger(JTextField datum) {
        try {
            int d = Integer.parseInt(datum.getText());
            return true;
        } catch (NumberFormatException exeception) {
            System.out.println("Invalid Integer");
            return false;
        }
    }

    /**
     * Validates that matrices have been read
     *
     *//* */
    public boolean validateRead() {
        if (data1 == null || data2 == null) {
            remarks.setText("You need to read both matrices");
            return false;
        } else {
            return true;
        }

    }
}
