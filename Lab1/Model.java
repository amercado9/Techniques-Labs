package Lab1;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

/**
 * Model class for Lab 1
 * 
 * @author Alexa Mercado
 * 
 */

public class Model implements WindowListener {
    Control c;

    public Model(Control fromC) {
        c = fromC;
        c.addWindowListener(this);
    }

    /**
     * Adds matrix 1 to matrix 2
     * 
     * @param xarray the data of matrix 1
     * @param yarray the data of matrix 2
     * @return data of the result
     */
    public double[][] addM(double[][] xarray, double[][] yarray) {
        int rows = xarray.length;
        int cols = xarray[0].length;
        double[][] result = new double[rows][cols];
        try {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    result[i][j] = xarray[i][j] + yarray[i][j];
                }
            }
            return result;
        } catch (IndexOutOfBoundsException e) {
            c.remarks.setText("Invalid matrix dimenions");
            return null;
        }
    }

    /**
     * Subtracts matrix 1 from matrix 2
     * 
     * 
     */
    public double[][] subtractM(double[][] xarray, double[][] yarray) {
        int rows = xarray.length;
        int cols = xarray[0].length;
        double[][] result = new double[rows][cols];
        try {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    result[i][j] = xarray[i][j] - yarray[i][j];
                }
            }
            return result;
        } catch (IndexOutOfBoundsException e) {
            c.remarks.setText("Invalid matrix dimensions");
            return null;
        }
    }

   

    /**
     * Multiplies matrix 1 and matrix 2
     * 
     *
     */
    public double[][] multiplyM(double[][] xray, double[][] yray) {

        int rows1 = xray.length;
        int cols1 = xray[0].length;
        int rows2 = yray.length;
        int cols2 = yray[0].length;
        double[][] result = new double[rows1][cols2];
        try {

            for (int i = 0; i < rows1; i++) {

                for (int j = 0; j < cols2; j++) {
                    result[i][j] = 0;
                    for (int k = 0; k < cols1; k++) {
                        result[i][j] += (xray[i][k] * yray[k][j]);

                    }
                }
            }
            return result;
        } catch (IndexOutOfBoundsException e) {
            c.remarks.setText("Invalid matrix dimensions");
            System.out.print(e);
            return null;
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosing(WindowEvent e) {

        if (c.vectorF1 != null)
            c.vectorF1.dispose();
        if (c.vectorF2 != null)
            c.vectorF2.dispose();
        System.exit(1);

    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }
}
