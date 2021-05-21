package com.KyleRuiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class MyFrame extends JFrame implements ActionListener {

    JComboBox cipherTypeComboBox;
    JComboBox typeComboBox;
    JTextField shiftKeyTextField;
    JLabel shiftKeyLabel;
    JLabel topLabel;
    JLabel bottomLabel;
    JTextArea topTextArea;
    JTextArea bottomTextArea;
    JButton typeButton;
    JButton saveButton;
    JButton loadButton;

    MyFrame() {
        // Frame with icon
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Cipher Program");
        this.setLayout(null);
        this.setSize(695, 500);
        this.setResizable(false);
        this.setVisible(true);

        // Cipher Type Label and Combo box
        String[] cipherType = {"Caesar Cipher", "Vigenère Cipher", "Atbash"};
        cipherTypeComboBox = new JComboBox(cipherType);
        cipherTypeComboBox.setBounds(110, 10, 150, 25);
        cipherTypeComboBox.addActionListener(this);
        cipherTypeComboBox.setSelectedIndex(0);

        JLabel cipherSelectLabel = new JLabel();
        cipherSelectLabel.setText("Cipher Type: ");
        cipherSelectLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        cipherSelectLabel.setBounds(10, 10, 100, 20);


        // Type Label and Combo Box
        String[] type = {"Encrypt", "Decrypt"};
        typeComboBox = new JComboBox(type);
        typeComboBox.setBounds(340 , 10, 150, 25);
        typeComboBox.setSelectedIndex(0);
        typeComboBox.addActionListener(this);

        JLabel typeLabel = new JLabel();
        typeLabel.setText("Type: ");
        typeLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        typeLabel.setBounds(290, 10, 100, 20);


        // Shift Label and shift Text field
        shiftKeyLabel = new JLabel();
        shiftKeyLabel.setText("Shift: ");
        shiftKeyLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        shiftKeyLabel.setBounds(520, 10, 100, 20);

        shiftKeyTextField = new JTextField();
        shiftKeyTextField.setBounds(568, 10, 100, 23);
        shiftKeyTextField.addActionListener(this);


        // Top Text area, Label, and Scroll panel.
        topLabel = new JLabel();
        topLabel.setText("Plain Text: ");
        topLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        topLabel.setBounds(10, 50, 100, 20);

        topTextArea = new JTextArea();
        topTextArea.setEditable(true);
        topTextArea.setLineWrap(true);
        topTextArea.setWrapStyleWord(true);

        JScrollPane topScrollPane = new JScrollPane(topTextArea);
        topScrollPane.setBounds(10, 75, 660, 150);


        // Bottom Text area, Label, and Scroll panel.
        bottomLabel = new JLabel();
        bottomLabel.setText("Cipher Text: ");
        bottomLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        bottomLabel.setBounds(10, 235, 100, 20);

        bottomTextArea = new JTextArea();
        bottomTextArea.setEditable(true);
        bottomTextArea.setLineWrap(true);
        bottomTextArea.setWrapStyleWord(true);

        JScrollPane bottomScrollPane = new JScrollPane(bottomTextArea);
        bottomScrollPane.setBounds(10, 260, 660, 150);


        // Bottom row Buttons
        typeButton = new JButton();
        typeButton.setText("Encrypt");
        typeButton.addActionListener(this);
        typeButton.setBounds(580, 435, 90, 23);

        saveButton = new JButton();
        saveButton.setText("Save");
        saveButton.addActionListener(this);
        saveButton.setBounds(480, 435, 90, 23);

        loadButton = new JButton();
        loadButton.setText("Load");
        loadButton.addActionListener(this);
        loadButton.setBounds(380, 435, 90, 23);


        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 800, 500);
        panel.setLayout(null);


        this.add(panel);
        panel.add(cipherSelectLabel);
        panel.add(typeLabel);
        panel.add(topScrollPane);
        panel.add(bottomScrollPane);
        panel.add(cipherTypeComboBox);
        panel.add(typeComboBox);
        panel.add(shiftKeyLabel);
        panel.add(shiftKeyTextField);
        panel.add(topLabel);
        panel.add(bottomLabel);
        panel.add(typeButton);
        panel.add(saveButton);
        panel.add(loadButton);

        // Fixed missing components when window first loaded
        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cipherTypeComboBox) {
            if (cipherTypeComboBox.getSelectedItem() == "Caesar Cipher") {
                if (shiftKeyLabel != null) {
                    shiftKeyLabel.setVisible(true);
                    shiftKeyTextField.setVisible(true);
                    shiftKeyLabel.setBounds(520, 10, 100, 20);
                    shiftKeyLabel.setText("Shift: ");
                }
            } else if (cipherTypeComboBox.getSelectedItem() == "Vigenère Cipher") {
                if (shiftKeyLabel != null) {
                    shiftKeyLabel.setVisible(true);
                    shiftKeyTextField.setVisible(true);
                    shiftKeyLabel.setBounds(525, 10, 100, 20);
                    shiftKeyLabel.setText("Key: ");
                }
            } else if (cipherTypeComboBox.getSelectedItem() == "Atbash") {
                if (shiftKeyLabel != null) {
                    shiftKeyLabel.setVisible(false);
                    shiftKeyTextField.setVisible(false);
                }
            }
            if (bottomTextArea != null) bottomTextArea.setText("");
        }

        if (e.getSource() == typeComboBox) {
            if (typeComboBox.getSelectedItem() == "Encrypt") {
                topLabel.setText("Plain Text: ");
                bottomLabel.setText("Cipher Text: ");
                typeButton.setText("Encrypt");
            } else if (typeComboBox.getSelectedItem() == "Decrypt") {
                bottomLabel.setText("Plain Text: ");
                topLabel.setText("Cipher Text: ");
                typeButton.setText("Decrypt");
            }
            if (bottomTextArea != null) bottomTextArea.setText("");
        }

        if (e.getSource() == typeButton) {
            if (cipherTypeComboBox.getSelectedItem() == "Caesar Cipher") {
                bottomTextArea.setText(caesar(topTextArea.getText(), Integer.valueOf(shiftKeyTextField.getText()), typeComboBox.getSelectedItem().toString()));
            } else if (cipherTypeComboBox.getSelectedItem() == "Vigenère Cipher") {
                bottomTextArea.setText(vigenere(topTextArea.getText(), shiftKeyTextField.getText(), typeComboBox.getSelectedItem().toString()));
            } else if (cipherTypeComboBox.getSelectedItem() == "Atbash") {
                bottomTextArea.setText(atbash(topTextArea.getText()));
            }
        }

        if (e.getSource() == loadButton) {
            String topText = "";
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Load");
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(String.valueOf(fileChooser.getSelectedFile().getAbsoluteFile()));
                try {
                    Scanner scan = new Scanner(file);
                    while (scan.hasNextLine()) {
                        topText += scan.nextLine();
                        topText += "\n";
                    }
                    scan.close();
                    topTextArea.setText(topText);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                if (bottomTextArea != null) bottomTextArea.setText("");
            }
        }

        if (e.getSource() == saveButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Load");
            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File fileSave = fileChooser.getSelectedFile();
                try {
                    FileWriter fileWriter = new FileWriter(fileSave);
                    fileWriter.write(bottomTextArea.getText());
                    fileWriter.close();
                } catch (IOException f) {
                    f.printStackTrace();
                }
            }
        }
    }

    public static String vigenere (String text, String key, String type) {
        text = text.toUpperCase();
        key = key.toUpperCase();

        String newText = "";
        int[] ascii = new int[text.length()];
        int[] keyAscii = new int[text.length()];
        int counter = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            ascii[i] = c;

            if (counter == key.length()) {
                counter = 0;
            }
            if (ascii[i] < 65 || ascii[i] > 90){
                keyAscii[i] = keyAscii[i];
            } else {
                char k = key.charAt(counter);
                keyAscii[i] = k;
                counter++;
            }
        }

        if (type.equals("Encrypt")) {
            for (int i = 0; i < text.length(); i++) {
                if ((ascii[i]-65)+keyAscii[i] > 90) {
                    ascii[i] = ((((ascii[i]-65)+keyAscii[i])-90)+64);
                } else if (ascii[i] >= 65 && ascii[i] <= 90) {
                    ascii[i] = (ascii[i] - 65) + keyAscii[i];
                } else {
                    ascii[i] = ascii[i];
                }
            }
        } else if (type.equals("Decrypt")) {
            for (int i = 0; i < text.length(); i++) {
                if ((ascii[i] + 65) - keyAscii[i] < 65) {
                    ascii[i] = (91 - Math.abs(keyAscii[i] - ascii[i]));
                } else if (ascii[i] >= 65 && ascii[i] <= 90){
                    ascii[i] = (ascii[i] - keyAscii[i]) + 65;
                } else {
                    ascii[i] = ascii[i];
                }
            }
        }

        for (int i = 0; i < text.length(); i++) {
            newText += Character.toString((char)ascii[i]);
        }
        return newText;
    }

    public static String atbash (String text) {
        String newText = "";
        int[] ascii = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            ascii[i] = c;
        }
        for (int i = 0; i < text.length(); i++) {
            if (ascii[i] < 122 && ascii[i] > 97) {
                ascii[i] = 122 - (ascii[i]-97 );
            } else if (ascii[i] < 90 && ascii[i] > 65) {
                ascii[i] = 90 - (ascii[i]-65);
            } else {
                ascii[i] = ascii[i];
            }
            newText += Character.toString((char)ascii[i]);
        }
        return newText;
    }

    public static String caesar (String text, int shift, String type) {
        String newText = "";
        int[] ascii = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            ascii[i] = c;
        }

        if (type.equals("Decrypt")) {
            for (int i = 0; i < ascii.length; i++) {
                if ((ascii[i] - shift < 65) && (ascii[i] <= 90 && ascii[i] >= 65)) {
                    ascii[i] = (ascii[i] - shift) + 26;
                } else if ((ascii[i] - shift < 97) && (ascii[i] <= 122 && ascii[i] >= 97)) {
                    ascii[i] = (ascii[i] - shift) + 26;
                } else if ((ascii[i] >= 65 && ascii[i] <= 90) || (ascii[i] >= 97 && ascii[i] <= 122)) {
                    ascii[i] = ascii[i] - shift;
                } else {
                    ascii[i] = ascii[i];
                }
                newText += Character.toString((char)ascii[i]);
            }
        } else if (type.equals("Encrypt")) {
            for (int i = 0; i < ascii.length; i++) {
                if ((ascii[i] + shift > 90) && (ascii[i] <= 90 && ascii[i] >= 65)) {
                    ascii[i] = ((ascii[i] + shift) - 90) + 64;
                } else if ((ascii[i] + shift > 122) && (ascii[i] <= 122 && ascii[i] >= 97)) {
                    ascii[i] = ((ascii[i] + shift) - 122) + 96;
                } else if ((ascii[i] >= 65 && ascii[i] <= 90) || (ascii[i] >= 97 && ascii[i] <= 122)) {
                    ascii[i] = ascii[i] + shift;
                } else {
                    ascii[i] = ascii[i];
                }
                newText += Character.toString((char)ascii[i]);
            }
        }
        return newText;
    }
}
