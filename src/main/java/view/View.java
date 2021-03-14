package view;

import controller.Controller;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;

public class View extends JPanel {
    private Image background;
    private JTextField firstText;
    private JTextField secondText;
    private JTextField resultText;
    private JLabel secondLabel;
    private JLabel resultLabel;
    private JLabel title;
    private JLabel firstLabel;
    private JButton btnIntegration;
    private JButton btnMultiply;
    private JButton btnDivide;
    private JButton btnSubtract;
    private JButton btnAdd;
    private JButton btnDerivation;


    public View(String filename) throws IOException {
        background = ImageIO.read(new File(filename));
        setBounds(0, 0, 458, 536);
        this.setBackground(new Color(204, 204, 255));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(null);

        firstText = new JTextField();
        firstText.setBackground(new Color(254, 199, 254));
        firstText.setFont(new Font("Tahoma", Font.PLAIN, 20));
        firstText.setBounds(139, 78, 255, 46);
        this.add(firstText);
        firstText.setColumns(10);

        secondText = new JTextField();
        secondText.setFont(new Font("Tahoma", Font.PLAIN, 20));
        secondText.setBackground(new Color(254, 199, 254));
        secondText.setColumns(10);
        secondText.setBounds(139, 142, 255, 46);
        this.add(secondText);

        resultText = new JTextField();
        resultText.setFont(new Font("Tahoma", Font.PLAIN, 15));
        resultText.setBackground(new Color(254, 199, 254));
        resultText.setColumns(10);
        resultText.setBounds(139, 225, 255, 46);
        this.add(resultText);

        title = new JLabel("Polynomial Calculator");
        title.setForeground(new Color(254, 199, 254));
        title.setFont(new Font("Tahoma", Font.BOLD, 13));
        title.setBounds(131, 15, 193, 22);
        this.add(title);

        firstLabel = new JLabel("First Polynomial");
        firstLabel.setForeground(new Color(254, 199, 254));
        firstLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        firstLabel.setBounds(20, 82, 114, 38);
        this.add(firstLabel);

        secondLabel = new JLabel("   Second Polynomial");
        secondLabel.setForeground(new Color(254, 199, 254));
        secondLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        secondLabel.setBounds(8, 145, 126, 38);
        this.add(secondLabel);

        resultLabel = new JLabel("Result=");
        resultLabel.setForeground(new Color(254, 199, 254));
        resultLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        resultLabel.setBounds(40, 225, 93, 38);
        this.add(resultLabel);

        btnMultiply = new JButton("Multiply");
        btnMultiply.setBackground(new Color(99, 2, 99));
        btnMultiply.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnMultiply.setBounds(29, 393, 170, 38);
        btnMultiply.setForeground(new Color(255, 255, 255));
        this.add(btnMultiply);

        btnSubtract = new JButton("Subtract");
        btnSubtract.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSubtract.setBackground(new Color(99, 2, 99));
        btnSubtract.setBounds(228, 346, 170, 38);
        btnSubtract.setForeground(new Color(255, 255, 255));
        this.add(btnSubtract);

        btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAdd.setBackground(new Color(99, 2, 99));
        btnAdd.setBounds(29, 345, 170, 38);
        btnAdd.setForeground(new Color(255, 255, 255));
        this.add(btnAdd);

        btnDivide = new JButton("Divide");
        btnDivide.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnDivide.setBackground(new Color(99, 2, 99));
        btnDivide.setBounds(228, 393, 170, 38);
        btnDivide.setForeground(new Color(255, 255, 255));
        this.add(btnDivide);

        btnDerivation = new JButton("Differentiate");
        btnDerivation.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnDerivation.setBackground(new Color(99, 2, 99));
        btnDerivation.setBounds(29, 441, 170, 38);
        btnDerivation.setForeground(new Color(255, 255, 255));
        this.add(btnDerivation);

        btnIntegration = new JButton("Integrate");
        btnIntegration.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnIntegration.setBackground(new Color(99, 2, 99));
        btnIntegration.setBounds(228, 441, 170, 38);
        btnIntegration.setForeground(new Color(255, 255, 255));
        this.add(btnIntegration);
        setVisible(true);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, this);
        }
    }

    public void addAddListener(ActionListener action) {
        this.btnAdd.addActionListener(action);
    }

    public void addSubtractListener(ActionListener action) {
        this.btnSubtract.addActionListener(action);
    }

    public void addMultiplyListener(ActionListener action) {
        this.btnMultiply.addActionListener(action);
    }

    public void addDivideListener(ActionListener action) {
        this.btnDivide.addActionListener(action);
    }

    public void addDerivationListener(ActionListener action) {
        this.btnDerivation.addActionListener(action);
    }

    public void addIntegrationListener(ActionListener action) {
        this.btnIntegration.addActionListener(action);
    }

    public String getFirst() {
        return this.firstText.getText();
    }

    public String getSecond() {
        return this.secondText.getText();
    }

    public void setResult(String text) {
        this.resultText.setText(text);
    }

}