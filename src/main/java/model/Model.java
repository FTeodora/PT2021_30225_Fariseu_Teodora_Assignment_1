package model;

public class Model {

    public String sum(Polynomial p1,Polynomial p2) {
        return p1.addPolynomials(p2).toString();
    }

    public String dif(Polynomial p1,Polynomial p2) {
        return p1.subtractPolynomials(p2).toString();
    }

    public String multiply(Polynomial p1,Polynomial p2) {
        return p1.multiplyPolynomials(p2).toString();
    }

    public String divide(Polynomial p1,Polynomial p2) {
        try{
            Polynomial[] rez=p1.dividePolynomials(p2);
        return "Cat: "+rez[0]+"\nRest: "+rez[1];
    }catch(ArithmeticException e){
        return e.getMessage();
    }
    }

    public String derive(Polynomial p1) {
        return p1.derivePolynomials().toString();
    }
    public String integrate(Polynomial p1) { String rez=p1.integratePolynomials().toString();
    if(!rez.equals("0"))
        return rez+" +C";
    else
        return rez;
    }
}
