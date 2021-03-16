package controller;

import model.Monomial;
import model.Polynomial;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    private final View view;
    private Polynomial polynomial1 = new Polynomial();
    private Polynomial polynomial2 = new Polynomial();
    private Polynomial result = new Polynomial();

    public Controller(View view) {

        this.view = view;

        this.view.addAddListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polynomial1 = test(polynomial1, view.getFirst());
                polynomial2 = test(polynomial1, view.getSecond());
                result = polynomial1.add(polynomial2);
                view.setResult(result.toString());
            }
        });

        this.view.addSubtractListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polynomial1 = test(polynomial1, view.getFirst());
                polynomial2 = test(polynomial1, view.getSecond());
                result = polynomial1.subtract(polynomial2);
                view.setResult(result.toString());
            }
        });
        this.view.addMultiplyListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polynomial1 = test(polynomial1, view.getFirst());
                polynomial2 = test(polynomial1, view.getSecond());
                result = polynomial1.multiply(polynomial2);
                view.setResult(result.toString());
            }
        });
        this.view.addDivideListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polynomial1 = test(polynomial1, view.getFirst());
                ArrayList<Polynomial> resultDivide;
                try {
                    polynomial2 = test(polynomial1, view.getSecond());
                    if (polynomial2.getMonomialList() == null || polynomial2.getMonomialList().get(0).equals(new Monomial(0, 0))) {
                        throw new Exception();
                    }
                    resultDivide = polynomial1.divide(polynomial2);
                    view.setResult(resultDivide.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Bad input!Divide by 0 error!");
                }

            }
        });

        this.view.addDerivationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polynomial1 = test(polynomial1, view.getFirst());
                result = polynomial1.differentiate();
                view.setResult(result.toString());
            }
        });
        this.view.addIntegrationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polynomial1 = test(polynomial1, view.getFirst());
                result = polynomial1.integrate();
                view.setResult(result.toString());
            }
        });
    }

    public Polynomial test(Polynomial polynomial, String input) {
        try {
            polynomial = Polynomial.extractMonomial(input);
            for (int i = 0; i < input.length(); i++) {
                char charInput = input.charAt(i);
                boolean flag = Character.isDigit(charInput);
                if (charInput != 'x' && charInput != '+' && charInput != '-' && charInput != '^' && !flag) {
                    throw new Exception();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Bad input!");
        }
        return polynomial;
    }
}
