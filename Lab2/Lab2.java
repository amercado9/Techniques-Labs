package Lab2;

/**Lab 2: ARM Instructions
 * @author Alexa Mercado
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lab2 extends JFrame {
    JTextField armTextField, binaryTextField, hexTextField, remarks;
    JButton encodeButton, decodeBinaryButton, decodeHexButton;
    JLabel assemblyLabel, binaryLabel, hexLabel, remarksLabel;

    JPanel displayWindow = new JPanel(new GridLayout(7, 2, 2, 2)); // primary window
    JPanel panel1 = new JPanel(new GridLayout(1, 2, 4, 4)); // panels or small sections in primary window
    JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel panel3 = new JPanel(new GridLayout(1, 2, 4, 4));
    JPanel panel4 = new JPanel(new GridLayout(1, 2, 4, 4));
    JPanel panel5 = new JPanel(new GridLayout(1, 2, 4, 4));
    JPanel panel6 = new JPanel(new GridLayout(1, 1, 4, 4));

    // Constructor
    public Lab2() {
        // Color object
        Color lightPink = new Color(255, 182, 193);
        Color white = new Color(255, 255, 255);
        Color red = new Color(255, 0, 0);

        // Initialize components
        armTextField = new JTextField(10);
        binaryTextField = new JTextField(10);
        hexTextField = new JTextField(10);
        remarks = new JTextField(20);
        remarks.setForeground(red);

        encodeButton = new JButton("Encode");
        decodeHexButton = new JButton("Decode Hex");
        decodeBinaryButton = new JButton("Decode Binary");

        assemblyLabel = new JLabel("To Assembly language");
        binaryLabel = new JLabel("Binary Instruction");
        hexLabel = new JLabel("Hex Instruction");

        // Panel 1 adding titles
        panel1.add(armTextField);
        panel1.add(encodeButton);
        panel1.setBackground(lightPink);
        encodeButton.setForeground(lightPink);
        encodeButton.setBackground(white);

        // Panel 2 adding assembly label
        panel2.add(assemblyLabel);
        panel2.setBackground(lightPink);
        assemblyLabel.setForeground(white);

        // Panel 3 adding text fields for binary and hex
        panel3.add(binaryTextField);
        panel3.add(hexTextField);
        panel3.setBackground(lightPink);

        // Panel 4 adding labels for binary and hex
        panel4.add(binaryLabel);
        panel4.add(hexLabel);
        panel4.setBackground(lightPink);
        binaryLabel.setForeground(white);
        hexLabel.setForeground(white);

        // Panel 5 adding decode buttons
        panel5.add(decodeBinaryButton);
        panel5.add(decodeHexButton);
        panel5.setBackground(lightPink);
        decodeBinaryButton.setForeground(lightPink);
        decodeHexButton.setForeground(lightPink);
        decodeBinaryButton.setBackground(white);
        decodeHexButton.setBackground(white);

        // Panel 6 adding remarks
        panel6.add(remarks);
        panel6.setBackground(lightPink);

        // Add action listeners to buttons
        encodeButton.addActionListener(new EncodeListener());
        decodeBinaryButton.addActionListener(new DecodeBinaryListener());
        decodeHexButton.addActionListener(new DecodeHexListener());

        // Add panels to displayWindow
        displayWindow.add(panel1);
        displayWindow.add(panel2);
        displayWindow.add(panel3);
        displayWindow.add(panel4);
        displayWindow.add(panel5);
        displayWindow.add(panel6);
        displayWindow.setBackground(lightPink);

        // Add displayWindow to frame
        add(displayWindow);

        // Set frame properties
        setTitle("Encoding ARM Instructions");
        setBounds(500, 500, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Lab2();
    }

    // Encode button listener
    private class EncodeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String armInstruction = armTextField.getText(); // Get the ARM instruction from the text field
            if (armInstruction.isEmpty()) {
                remarks.setText("Enter a valid ARM instruction."); // Set the correct field for error messages
                return;
            }

            try {
                String binaryCode = armToBinary(armInstruction); // Converts ARM to binary
                String hexCode = binaryToHex(binaryCode); // Converts binary to hex

                // Set the binary and hex text fields
                binaryTextField.setText(binaryCode);
                hexTextField.setText(hexCode);

                // Display success message
                remarks.setText("Successfully encoded.");
            } catch (Exception ex) {
                // Display an error message if encoding fails
                remarks.setText("Error during encoding: " + ex.getMessage());
            }
        }
    }

    // Decode Binary button listener
    private class DecodeBinaryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String binaryCode = binaryTextField.getText();

            if (binaryCode.isEmpty()) {
                remarks.setText("Please enter a valid binary code.");
                return;
            }

            try {
                if (binaryCode.length() != 32) {
                    remarks.setText("Binary code must be 32 bits.");
                    return;
                }

                // Decode the binary string to ARM instruction
                String armInstruction = binaryToArm(binaryCode);
                // Convert binary to hexadecimal
                String hexCode = binaryToHex(binaryCode);

                // Display the results
                armTextField.setText(armInstruction);
                hexTextField.setText(hexCode);
                remarks.setText("Successfully decoded from binary.");
            } catch (Exception ex) {
                remarks.setText("Error during binary decoding: " + ex.getMessage());
            }
        }
    }

    // Decode Hex button listener
    private class DecodeHexListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String hexCode = hexTextField.getText();

            if (hexCode.isEmpty()) {
                remarks.setText("Please enter a valid hex code.");
                return;
            }

            try {
                if (hexCode.length() != 8) {
                    remarks.setText("Hex code must be 8 characters (32 bits).");
                    return;
                }

                // Convert hex to binary
                String binaryCode = hexToBinary(hexCode);
                // Decode the binary string to ARM instruction
                String armInstruction = binaryToArm(binaryCode);

                // Display the results
                binaryTextField.setText(binaryCode);
                armTextField.setText(armInstruction);
                remarks.setText("Successfully decoded from hex.");
            } catch (Exception ex) {
                remarks.setText("Error during hex decoding: " + ex.getMessage());
            }
        }
    }

    public String armToBinary(String armInstruction) {
        String opCode = "";
        String registerd = "";
        String register1 = "";
        String register2 = "";
        String condition = "1110";
        String f = "000";
        String s = "0";
        String shift = "00000000";

        // Split the instruction into its components (e.g., "ADD R0, R1, R2")
        String[] parts = armInstruction.split("[ ,]+");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid ARM instruction format.");
        }

        String instruction = parts[0].toUpperCase(); // Instruction, e.g., "ADD"
        String destReg = parts[1]; // Destination register, e.g., "R0"
        String sourceReg1 = parts[2]; // First source register, e.g., "R1"
        String sourceReg2 = parts[3]; // Second source register, e.g., "R2"

        // Convert instruction to binary
        switch (instruction.toUpperCase()) {
            case "ADD":
                opCode = "0100";
                break;
            case "SUB":
                opCode = "0010";
                break;
            case "MOV":
                opCode = "1101";
                break;
            case "AND":
                opCode = "0000";
                break;
            case "EOR":
                opCode = "0001";
                break;
            case "RSB":
                opCode = "0011";
                break;
            case "ADC":
                opCode = "0101";
                break;
            case "SBC":
                opCode = "0110";
                break;
            case "RSC":
                opCode = "0111";
                break;
            case "TST":
                opCode = "1000";
                break;
            case "TEQ":
                opCode = "1001";
                break;
            case "CMP":
                opCode = "1010";
                break;
            case "CMN":
                opCode = "1011";
                break;
            case "ORR":
                opCode = "1100";
                break;
            case "BIC":
                opCode = "1110";
                break;
            case "MVN":
                opCode = "1111";
                break;
            default:
                throw new IllegalArgumentException("Invalid ARM instruction.");
        }

        // Convert registers to binary (assuming 16 registers, so we need 4 bits for
        // each register)
        registerd += convertRegisterToBinary(destReg); // Destination register
        register1 += convertRegisterToBinary(sourceReg1); // First source register
        register2 += convertRegisterToBinary(sourceReg2); // Second source register

        // Combine instruction and register bits
        return condition + f + opCode + s + register1 + registerd + shift + register2;
    }

    // Helper method to convert a register like "R0" to its 4-bit binary
    // representation
    private String convertRegisterToBinary(String register) {
        int regNum = Integer.parseInt(register.substring(1)); // Extract the number after 'R'
        if (regNum < 0 || regNum > 15) {
            throw new IllegalArgumentException("Invalid register number.");
        }
        return String.format("%4s", Integer.toBinaryString(regNum)).replace(' ', '0'); // 4-bit binary
    }

    
    private String binaryToHex(String binaryCode) throws NumberFormatException {
        if (binaryCode.length() != 32) {
            throw new NumberFormatException("Instruction length must be 32 bits.");
        }

        // Parse binary string as a long value (base 2)
        Long newLong = Long.parseLong(binaryCode, 2);

        // Convert the long value to hexadecimal string
        String hexCode = Long.toHexString(newLong).toUpperCase();

        // Pad the result to ensure it is 8 characters (32 bits in hex is 8 characters)
        while (hexCode.length() < 8) {
            hexCode = "0" + hexCode;
        }

        return hexCode;
    }

    private String hexToBinary(String hexCode) {
        if (hexCode.length() != 8) {
            throw new IllegalArgumentException("Hex code must be 8 characters (32 bits).");
        }

        // Parse hex string as a long value (base 16)
        long value = Long.parseLong(hexCode, 16);

        // Convert the long value to a 32-bit binary string
        String binaryCode = Long.toBinaryString(value);

        // Pad the result to ensure it is 32 characters long (32 bits)
        while (binaryCode.length() < 32) {
            binaryCode = "0" + binaryCode;
        }

        return binaryCode;
        
    }


    public String binaryToArm(String binaryCode) {
    if (binaryCode.length() != 32 || !binaryCode.matches("[01]+")) {
        remarks.setText("Enter a valid 32-bit binary instruction");
        return "";
    }

    remarks.setText(null);  // Clear any previous remarks

    // Extract relevant parts of the binary code
    String opCode = getOpCode(binaryCode.substring(7, 11));
    String registerd = getRegister(binaryCode.substring(16, 20));
    String register1 = getRegister(binaryCode.substring(12, 16));
    String register2 = getRegister(binaryCode.substring(28, 32));

   
    return String.format("%s, %s, %s, %s", opCode, registerd, register1, register2);
}
    private String getOpCode(String opCode) {
    String[] opCodes = { "AND", "EOR", "SUB", "RSB", "ADD", "ADC", "SBC", "RSC", 
                         "TST", "TEQ", "CMP", "CMN", "ORR", "MOV", "BIC", "MVN" };
    int index = Integer.parseInt(opCode, 2);  // Convert binary to decimal
    return opCodes[index];  // Return corresponding opCode
}

               
             private String getRegister(String registerCode) {
                return "R" + Integer.parseInt(registerCode, 2);  // Convert binary to decimal and prepend 'R'
}
}
          

        
    
