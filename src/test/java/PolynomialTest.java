import model.Polynomial;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PolynomialTest {
    Polynomial m=new Polynomial();
    Polynomial p=new Polynomial();
    Polynomial result= new Polynomial();

 @Test
    public void addAddTest(){
       m=m.extractMonomial("2x^2+1");
       p= p.extractMonomial("2x^2");
       result=result.extractMonomial("4x^2+1");
     assertTrue(m.addOrSubtractPolynomial(p, "add").equals(result));
 }
    @Test
    public void addSubtractTest(){
        m=m.extractMonomial("2x^2+1");
        p= p.extractMonomial("1x^2");
        result=result.extractMonomial("1x^2+1");
        assertTrue(m.addOrSubtractPolynomial(p, "subtract").equals(result));

    }
    @Test
    public void addMultiplyTest(){
        m=m.extractMonomial("2x^2+1");
        p= p.extractMonomial("1x^2");
        result=result.extractMonomial("2x^4+1x^2");
        assertTrue(m.multiply(p).equals(result));

    }
    @Test

    public void addDivideTest(){
        ArrayList<Polynomial> rez=new ArrayList<>();
        int i=0;
        m=Polynomial.extractMonomial("2x^4");
        p= Polynomial.extractMonomial("1x^2");
        rez.add(Polynomial.extractMonomial("2x^2"));
        rez.add(new Polynomial());
        assertTrue(m.dividePolynomials(p).get(0).equals(rez.get(0))&& m.dividePolynomials(p).get(1).equals(rez.get(1)));

    }

    @Test
    public void addDerivativeTest(){

        m=Polynomial.extractMonomial("1x^3−2x^2+6x^1−5");
        result=Polynomial.extractMonomial("3x^2−4x^1+6");
        assertTrue(m.derivatePolynomial().equals(result));

    }
    @Test
    public void addIntegrateTest(){

        m=Polynomial.extractMonomial("6x^2+1");
        result=Polynomial.extractMonomial("2x^3+1x^1");
        assertTrue(m.integratePolynomial().equals(result));

    }

}
