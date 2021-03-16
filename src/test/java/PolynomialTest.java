import model.Monomial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Polynomial;

import static org.junit.jupiter.api.Assertions.*;
public class PolynomialTest {
    private static Polynomial polynomial1,polynomial2;
    @BeforeEach
    public void initializePolynomials(){
        polynomial1=new Polynomial();
        polynomial2=new Polynomial();
    }
    @Test
    public void parsingTest(){
        polynomial1=Polynomial.parsePolynomial("x^4-8x^3+51x^3-x^2+1+419+x^21+x-x");
        polynomial2.addElement(new Monomial(1,4));
        polynomial2.addElement(new Monomial(-8,3));
        polynomial2.addElement(new Monomial(51,3));
        polynomial2.addElement(new Monomial(-1,2));
        polynomial2.addElement(new Monomial(1,0));
        polynomial2.addElement(new Monomial(419,0));
        polynomial2.addElement(new Monomial(1,21));
        polynomial2.addElement(new Monomial(1,1));
        polynomial2.addElement(new Monomial(-1,1));
        assertEquals(polynomial2, polynomial1);
    }
    @Test
    public void addTest(){
        polynomial1=Polynomial.parsePolynomial("2x^2+1+x^3+x");
        polynomial2=Polynomial.parsePolynomial("x^2-1-2x+x^5");
        Polynomial result=Polynomial.parsePolynomial("x^5+x^3+3x^2-x");
        assertEquals(result, polynomial1.addPolynomials(polynomial2));

    }
    @Test
    public void subtractTest(){
        polynomial1=Polynomial.parsePolynomial("2x^2+1+x^3+x");
        polynomial2=Polynomial.parsePolynomial("2x^2-1-2x+x^5");
        Polynomial result=Polynomial.parsePolynomial("-x^5+x^3+3x+2");
        assertEquals(result, polynomial1.subtractPolynomials(polynomial2));
    }
    @Test
    public void multiplyTest(){
        polynomial1=Polynomial.parsePolynomial("x^2+1");
        polynomial2=Polynomial.parsePolynomial("0");
        Polynomial result=Polynomial.parsePolynomial("0");
        assertEquals(result, polynomial1.multiplyPolynomials(polynomial2));
    }
    @Test
    public void multiplyWithZeroTest(){
        polynomial1=Polynomial.parsePolynomial("x^2+1");
        polynomial2=Polynomial.parsePolynomial("x^2-1");
        Polynomial result=Polynomial.parsePolynomial("x^4-1");
        assertEquals(result, polynomial1.multiplyPolynomials(polynomial2));
    }
    @Test
    public void divisionTest(){
        polynomial1=Polynomial.parsePolynomial("x^3-2x^2-4");
        polynomial2=Polynomial.parsePolynomial("x-3");
        Polynomial quotient=Polynomial.parsePolynomial("x^2+x+3");
        Polynomial remainder=Polynomial.parsePolynomial("5");
        Polynomial[] trueResult=polynomial1.dividePolynomials(polynomial2);
        assertTrue(quotient.equals(trueResult[0])&&remainder.equals(trueResult[1]));
    }
    @Test
    public void divisionByZeroTest(){
        boolean success=false;
        try{
        polynomial1=Polynomial.parsePolynomial("x^3-2x^2-4");
        Polynomial[] trueResult=polynomial1.dividePolynomials(polynomial2);}
        catch(ArithmeticException e){
            success=true;
        }
        assertTrue(success);
    }
    @Test
    public void deriveTest(){
        polynomial1=Polynomial.parsePolynomial("x^170-x^5+1");
        polynomial2=Polynomial.parsePolynomial("170x^169-5x^4");
        assertEquals(polynomial1.derivePolynomials(),polynomial2);

    }
    @Test
    public void deriveConstantTest(){
        polynomial1=Polynomial.parsePolynomial("3");
        polynomial2=Polynomial.parsePolynomial("0");
        assertEquals(polynomial1.derivePolynomials(),polynomial2);

    }
    @Test
    public void integrateTest(){
        polynomial1=Polynomial.parsePolynomial("x^3-3x^2+x+3");
        polynomial2.addElement(new Monomial(0.25,4));
        polynomial2.addElement(new Monomial(-1,3));
        polynomial2.addElement(new Monomial(0.5,2));
        polynomial2.addElement(new Monomial(3,1));
        assertEquals(polynomial1.integratePolynomials(),polynomial2);
    }
}
