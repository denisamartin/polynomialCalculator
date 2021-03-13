package controller;

import model.Polynomial;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    private View view;
    private Polynomial a = new Polynomial();
    private Polynomial b = new Polynomial();
    private Polynomial result = new Polynomial();

    public Controller(View view) {

        this.view = view;

        this.view.addAddListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Polynomial.extractMonomial(view.getFirst());
                b = Polynomial.extractMonomial(view.getSecond());
                result = a.addOrSubtractPolynomial(b, "add");
                view.setResult(result.toString());
            }
        });

        this.view.addSubtractListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Polynomial.extractMonomial(view.getFirst());
                b = Polynomial.extractMonomial(view.getSecond());
                result = a.addOrSubtractPolynomial(b, "subtract");
                view.setResult(result.toString());
            }
        });
        this.view.addMultiplyListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Polynomial.extractMonomial(view.getFirst());
                b = Polynomial.extractMonomial(view.getSecond());
                result = a.multiply(b);
                view.setResult(result.toString());
            }
        });
        this.view.addDivideListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Polynomial.extractMonomial(view.getFirst());
                b = Polynomial.extractMonomial(view.getSecond());
                ArrayList<Polynomial> resultDivide;
                resultDivide = a.dividePolynomials(b);
                view.setResult(resultDivide.toString());
            }
        });

        this.view.addDerivationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Polynomial.extractMonomial(view.getFirst());
                result = a.derivatePolynomial();
                view.setResult(result.toString());
            }
        });
        this.view.addIntegrationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Polynomial.extractMonomial(view.getFirst());
                result = a.integratePolynomial();
                view.setResult(result.toString());
            }
        });
    }
}
