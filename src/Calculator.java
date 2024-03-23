package calculator;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {

    // Swing variables
    JFrame jFrame;
    JTextField jTextField;
    JPanel jPanel;

    Font fieldFont;
    Font buttonFont;

    JButton[] numButtons = new JButton[10];
    JButton[] funcButtons = new JButton[12];

    JButton addButton;
    JButton subButton;
    JButton mulButton;
    JButton divButton;
    JButton squareButton;
    JButton sqrtButton;

    JButton dotButton;
    JButton eqButton;
    JButton negButton;
    JButton clearButton;
    JButton clearEntryButton;
    JButton delButton;

    // Logic variables
    double accumulator = 0.0;
    String selectedOperation = "";

    public Calculator() {
        // Main Frame
        jFrame = new JFrame("Simple Calculator");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(460, 460);
        jFrame.setLayout(null);
        jFrame.setResizable(false);

        // Interface font
        fieldFont = new Font("Cambria Math", Font.BOLD, 40);
        buttonFont = new Font("Cambria Math", Font.PLAIN, 25);

        // Main text field
        jTextField = new JTextField();
        jTextField.setBounds(40,40,370,60);
        jTextField.setFont(fieldFont);
        jTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        jTextField.setEditable(false);
        jTextField.setBorder(new LineBorder(new Color(157, 159, 170), 2));
        jTextField.setText("");
        jFrame.add(jTextField);

        // Buttons instantiation
        for (int i = 0; i < numButtons.length; i++) {
            String buttonName = String.valueOf(i);
            JButton newJButton = new JButton(buttonName);
            newJButton.setFont(buttonFont);
            newJButton.addActionListener(this);
            newJButton.setFocusable(false);

            numButtons[i] = newJButton;
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        squareButton = new JButton("x^2");
        sqrtButton = new JButton("sqrt");

        dotButton = new JButton(".");
        eqButton = new JButton("=");
        negButton = new JButton("+/-");
        clearButton = new JButton("C");
        clearEntryButton = new JButton("CE");
        delButton = new JButton("del");

        funcButtons[0] = addButton;
        funcButtons[1] = subButton;
        funcButtons[2] = mulButton;
        funcButtons[3] = divButton;
        funcButtons[4] = squareButton;
        funcButtons[5] = sqrtButton;

        funcButtons[6] = dotButton;
        funcButtons[7] = eqButton;
        funcButtons[8] = negButton;
        funcButtons[9] = clearButton;
        funcButtons[10] = clearEntryButton;
        funcButtons[11] = delButton;

        for(int i = 0; i < funcButtons.length; i++){
            funcButtons[i].setFont(buttonFont);
            funcButtons[i].addActionListener(this);
            funcButtons[i].setFocusable(false);
        }

        // Buttons placement
        jPanel = new JPanel();
        jPanel.setBounds(40, 120, 370, 210);
        jPanel.setLayout(new GridLayout(5, 4, 10, 10));

        jPanel.add(clearEntryButton);
        jPanel.add(clearButton);
        jPanel.add(delButton);
        jPanel.add(sqrtButton);

        jPanel.add(numButtons[7]);
        jPanel.add(numButtons[8]);
        jPanel.add(numButtons[9]);
        jPanel.add(squareButton);
        jPanel.add(numButtons[4]);
        jPanel.add(numButtons[5]);
        jPanel.add(numButtons[6]);
        jPanel.add(divButton);
        jPanel.add(numButtons[1]);
        jPanel.add(numButtons[2]);
        jPanel.add(numButtons[3]);
        jPanel.add(mulButton);
        jPanel.add(negButton);
        jPanel.add(numButtons[0]);
        jPanel.add(dotButton);
        jPanel.add(subButton);

        eqButton.setBounds(40, 340, 275, 50);
        jFrame.add(eqButton);

        addButton.setBounds(325, 340, 85, 50);
        jFrame.add(addButton);

        jFrame.add(jPanel);

        // Show frame
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        // Numeric buttons
        for(int i = 0; i < numButtons.length; i++) {
            if (e.getSource() == numButtons[i]){
                String number = source.getText();

                jTextField.setText(jTextField.getText()+number);
            }
        }

        // Dot button
        if(source == dotButton && !jTextField.getText().contains(".")){
            jTextField.setText(jTextField.getText()+".");
        }

        // 4 operations (+, -, *, /)
        if(source == addButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectedOperation = "+";
            jTextField.setText("");
        }
        if(source == subButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectedOperation = "-";
            jTextField.setText("");
        }
        if(source == mulButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectedOperation = "*";
            jTextField.setText("");
        }
        if(source == divButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectedOperation = "/";
            jTextField.setText("");
        }
        if(source == sqrtButton && !jTextField.getText().equals("")){
            double value = Double.parseDouble(jTextField.getText());
            double newValue = Math.sqrt(value);

            jTextField.setText(String.valueOf(newValue));
        }
        if(source == squareButton && !jTextField.getText().equals("")){
            double value = Double.parseDouble(jTextField.getText());
            double newValue = value * value;

            jTextField.setText(String.valueOf(newValue));
        }

        // Negate value button
        if(source == negButton && !jTextField.getText().equals("")){
            double newValue = -Double.parseDouble(jTextField.getText());
            String newText = String.valueOf(newValue);
            jTextField.setText(newText);
        }

        // Clear calculator state button
        if(source == clearButton){
            jTextField.setText("");
            accumulator = 0.0;
            selectedOperation = "";
        }
        // Clear entry button
        if(source == clearEntryButton){
            jTextField.setText("");
        }
        // Del button
        if(source == delButton && !jTextField.getText().equals("")){
            String currentValue = jTextField.getText();

            String newValue = currentValue.substring(0, currentValue.length() - 1);
            jTextField.setText(newValue);
        }

        // Evaluate value button
        if(source == eqButton &&
                !selectedOperation.equals("") && !jTextField.getText().equals("")){

            double textFieldValue = Double.parseDouble(jTextField.getText());

            double newValue = 0.0;
            switch (selectedOperation){
                case "+": newValue = accumulator + textFieldValue;break;
                case "-": newValue = accumulator - textFieldValue;break;
                case "*": newValue = accumulator * textFieldValue;break;
                case "/": newValue = accumulator / textFieldValue;break;
            }

            accumulator = 0.0;
            selectedOperation = "";
            jTextField.setText(String.valueOf(newValue));
        }

    }
}
