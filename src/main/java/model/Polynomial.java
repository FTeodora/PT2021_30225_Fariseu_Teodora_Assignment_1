
package model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial implements Cloneable{
    private List<Monomial> terms;
    public Polynomial(){
        terms =new ArrayList<>();
    }
    public void addElement(Monomial el){
        if(el==null)
            return;
        if(el.getPower()>=0)
            terms.add(el);
    }
    public Polynomial addPolynomials(Polynomial p){
        Polynomial rez=new Polynomial();
        try{
            rez=(Polynomial) this.clone();
        }catch(CloneNotSupportedException e1){
            e1.printStackTrace();
        }
        for(Monomial i:p.terms)
            rez.addElement(i);
        rez.arrangeTerms();
        return rez;
    }
    public Polynomial subtractPolynomials(Polynomial p){
        Polynomial rez=new Polynomial();
        try{
            rez=(Polynomial) this.clone();
        }catch(CloneNotSupportedException e1){
            e1.printStackTrace();
        }
        for(Monomial i:p.terms)
            rez.addElement(i.getNegatedCoefficient());
        rez.arrangeTerms();
        return rez;
    }
    public Polynomial multiplyPolynomials(Polynomial p){
        Polynomial rez=new Polynomial();
        for(Monomial i:this.terms)
            for(Monomial j:p.terms)
                rez.addElement(i.multiplyMonomials(j));
            rez.arrangeTerms();
        return rez;
    }

    public Polynomial[] dividePolynomials(Polynomial p) throws ArithmeticException{
        if(p.terms==null||p.terms.size()==0)
            throw new ArithmeticException("Division by 0");
        this.arrangeTerms();
        p.arrangeTerms();
        Polynomial[] rez=new Polynomial[2];
        rez[0]=new Polynomial();
        try {
            rez[1] = (Polynomial) this.clone();
        }catch(CloneNotSupportedException e1){
            e1.printStackTrace();
        }
        Monomial partialResult;
        Polynomial partialRemainder=new Polynomial();
        while(rez[1].terms.size()>0) {
            partialResult=rez[1].terms.get(0).divideMonomials(p.terms.get(0));
            if(partialResult.getPower()<0)
            break;
            rez[0].addElement(partialResult);
            partialRemainder.addElement(partialResult);
            rez[1] = rez[1].subtractPolynomials(p.multiplyPolynomials(partialRemainder));
            partialRemainder.terms.remove(partialResult);
        }
        return rez;
    }
    public Polynomial derivePolynomials(){
        Polynomial rez=new Polynomial();
        for(Monomial i:this.terms)
            rez.addElement(i.deriveMonomial());
        return rez;
    }
    public Polynomial integratePolynomials(){
        Polynomial rez=new Polynomial();
        for(Monomial i:this.terms)
            rez.addElement(i.integrateMonomial());
        return rez;
    }
    public static Polynomial parsePolynomial(String source){
        Pattern pattern = Pattern.compile("(([+-])?([0-9])*((x)((\\^)([0-9])+)?)?)");
        Matcher matcher = pattern.matcher(source);

        Polynomial rez=new Polynomial();
        while(matcher.find()) {
            rez.addElement(Monomial.parseMonomial(matcher.group()));
        }
        rez.arrangeTerms();
        return rez;
    }
    private void arrangeTerms(){
        Collections.sort(this.terms);
        Polynomial copy=new Polynomial();
        for(Monomial i:this.terms){
            if(!i.getCoefficient().equalsValue(0)){
                if(copy.terms.size()==0 ||
                        copy.terms.get(copy.terms.size()-1).getPower()!=i.getPower()){
                            copy.terms.add(i);
                }
                else{ copy.terms.set(copy.terms.size()-1,copy.terms.get(copy.terms.size()-1).sumMonomials(i));
                if(copy.terms.get(copy.terms.size()-1).getCoefficient().equalsValue(0))
                    copy.terms.remove(copy.terms.size()-1);
                }
            }
        }
        this.terms=copy.terms;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        Polynomial clone=new Polynomial();
        for(Monomial i:this.terms)
            clone.addElement(new Monomial(i.getCoefficient(),i.getPower()));
        return clone;
    }
    @Override
    public String toString(){
        StringBuilder resultString= new StringBuilder();
        this.arrangeTerms();
        if(this.terms !=null)
        {
            if(this.terms.size()==0)
                return "0";
            for(Monomial i: this.terms)
                resultString.append(i.toString()+" ");
            if(resultString.charAt(0)=='+')
                resultString = new StringBuilder(resultString.substring(1));
        }
        return resultString.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Polynomial))
            return false;
        Polynomial o = (Polynomial) obj;
        this.arrangeTerms();
        o.arrangeTerms();
        Iterator<Monomial> p1 = this.terms.iterator();
        Iterator<Monomial> p2 = o.terms.iterator();
        while (p1.hasNext() && p2.hasNext()) {
            if (!p1.next().equals(p2.next()))
                return false;
        }
        return p1.hasNext() == p2.hasNext();
    }
}
