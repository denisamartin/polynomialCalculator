import model.Polynomial;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PolynomialTest {
    Polynomial polymonial1 =new Polynomial();
    Polynomial polynomial2 =new Polynomial();
    Polynomial result= new Polynomial();

 @Test
    public void addAddTest(){
       polymonial1 = Polynomial.extractMonomial("2x^2+1");
       polynomial2 = Polynomial.extractMonomial("2x^2");
       result= Polynomial.extractMonomial("4x^2+1");
     assertTrue(polymonial1.add(polynomial2).equals(result));
 }
    @Test
    public void addSubtractTest(){
        polymonial1 = Polynomial.extractMonomial("2x^2+1");
        polynomial2 = Polynomial.extractMonomial("1x^2");
        result= Polynomial.extractMonomial("1x^2+1");
        assertTrue(polymonial1.subtract(polynomial2).equals(result));

    }
    @Test
    public void addMultiplyTest(){
        polymonial1 = Polynomial.extractMonomial("2x^2+1");
        polynomial2 = Polynomial.extractMonomial("1x^2");
        result= Polynomial.extractMonomial("2x^4+1x^2");
        assertTrue(polymonial1.multiply(polynomial2).equals(result));

    }
    @Test

    public void addDivideTest(){
        ArrayList<Polynomial> rez=new ArrayList<>();
        polymonial1 =Polynomial.extractMonomial("2x^4");
        polynomial2 = Polynomial.extractMonomial("1x^2");
        rez.add(Polynomial.extractMonomial("2x^2"));
        rez.add(new Polynomial());
        assertTrue(polymonial1.divide(polynomial2).get(0).equals(rez.get(0))&& polymonial1.divide(polynomial2).get(1).equals(rez.get(1)));

    }

    @Test
    public void addDerivativeTest(){

        polymonial1 =Polynomial.extractMonomial("1x^3−2x^2+6x^1−5");
        result=Polynomial.extractMonomial("3x^2−4x^1+6");
        assertTrue(polymonial1.differentiate().equals(result));

    }
    @Test
    public void addIntegrateTest(){

        polymonial1 =Polynomial.extractMonomial("6x^2+1");
        result=Polynomial.extractMonomial("2x^3+1x^1");
        assertTrue(polymonial1.integrate().equals(result));

    }

}
