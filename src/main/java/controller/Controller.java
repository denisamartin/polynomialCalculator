package controller;

import model.Polynomial;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    private View view;
    private Polynomial polynomial1 = new Polynomial();
    private Polynomial polynomial2 = new Polynomial();
    private Polynomial result = new Polynomial();

    public Controller(View view) {

        this.view = view;

        this.view.addAddListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polynomial1 = Polynomial.extractMonomial(view.getFirst());
                polynomial2 = Polynomial.extractMonomial(view.getSecond());
                result = polynomial1.add(polynomial2);
                view.setResult(result.toString());
            }
        });

        this.view.addSubtractListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polynomial1 = Polynomial.extractMonomial(view.getFirst());
                polynomial2 = Polynomial.extractMonomial(view.getSecond());
                result= polynomial1.subtract(polynomial2);
                view.setResult(result.toString());
            }
        });
        this.view.addMultiplyListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polynomial1 = Polynomial.extractMonomial(view.getFirst());
                polynomial2 = Polynomial.extractMonomial(view.getSecond());
                result = polynomial1.multiply(polynomial2);
                view.setResult(result.toString());
            }
        });
        this.view.addDivideListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polynomial1 = Polynomial.extractMonomial(view.getFirst());
                polynomial2 = Polynomial.extractMonomial(view.getSecond());
                ArrayList<Polynomial> resultDivide;
                resultDivide = polynomial1.divide(polynomial2);
                view.setResult(resultDivide.toString());
            }
        });

        this.view.addDerivationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polynomial1 = Polynomial.extractMonomial(view.getFirst());
                result = polynomial1.differentiate();
                view.setResult(result.toString());
            }
        });
        this.view.addIntegrationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polynomial1 = Polynomial.extractMonomial(view.getFirst());
                result = polynomial1.integrate();
                view.setResult(result.toString());
            }
        });
    }
}
