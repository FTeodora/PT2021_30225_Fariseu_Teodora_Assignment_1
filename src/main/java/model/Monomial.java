package model;

import java.lang.Math;
import java.text.DecimalFormat;

@SuppressWarnings("rawtypes")
public class Monomial implements Comparable{
    private final Coefficient coefficient;
    private int power;

    public Monomial(Coefficient coefficient,int power){
        this.coefficient=coefficient;
        this.power=power;
    }
    public Monomial(Number coefficient,int power){
        this.coefficient=new Coefficient(coefficient);
        this.power=power;
    }
    public Coefficient getCoefficient(){
        return this.coefficient;
    }
    public int getPower(){
        return this.power;
    }
    public Monomial sumMonomials(Monomial m){
        return new Monomial(this.coefficient.add(m.getCoefficient()),this.power);
    }
    public Monomial getNegatedCoefficient(){
        return new Monomial(coefficient.multiply(new Coefficient(-1)),this.power);
    }
    public Monomial multiplyMonomials(Monomial m){
        return new Monomial(this.coefficient.multiply(m.getCoefficient()),this.power+m.power);
    }
    public Monomial divideMonomials(Monomial m) throws ArithmeticException{
        if(m.getCoefficient().equalsValue(0))
            throw new ArithmeticException("Division by zero");
        return new Monomial(this.coefficient.divide(m.getCoefficient()),this.power-m.power);
    }
    public Monomial deriveMonomial(){
        return new Monomial(this.coefficient.multiply(new Coefficient(this.power)),this.power-1);
    }
    public Monomial integrateMonomial(){
        return new Monomial(this.coefficient.divide(new Coefficient(this.power+1)),this.power+1);
    }
    public static Monomial parseMonomial(String source){
        Monomial rez=new Monomial(0,0);
        int sgn=1;
        if(source.length()==0)
            return rez;
        if(source.startsWith("-"))
        {
            source=source.substring(1);
            sgn=-1;
        }else
            if(source.startsWith("+"))
                source=source.substring(1);
        if(source.startsWith("x"))
        {
            rez.power=1;
            rez.coefficient.setValue(sgn);
            if(source.length()>1)
                 rez.power=Integer.parseInt(source.substring(2));
        }
        else{
            if(source.endsWith("x"))
                rez.power=1;
            String[] numbers=source.split("x");
            rez.coefficient.setValue(Integer.parseInt(numbers[0])*sgn);
            if(numbers.length>1) {
                rez.power = Integer.parseInt(numbers[1].substring(1));
            }
        }
        return rez;
    }
    @Override
    public String toString(){
        String resultString;
        if(this.coefficient.compareTo(new Coefficient(0))<0) {
            resultString="-";
        } else
            resultString="+";
        if(this.power==0   ||
                ((this.coefficient.getValue() instanceof Double) && !this.coefficient.equalsValue(1)&&!this.coefficient.equalsValue(-1)) ||
                     ((this.coefficient.getValue() instanceof Integer)   &&
                        (Math.abs((Integer)this.coefficient.getValue())!=1))  )
        {
            if(this.coefficient.getValue() instanceof Integer)
                 resultString+=Math.abs((Integer)this.coefficient.getValue());
            else
            {
                DecimalFormat format=new DecimalFormat("0.00");
                resultString+=format.format(Math.abs((Double)this.coefficient.getValue()));
            }
        }
        if(power!=0)
        {
            resultString+="x";
            if(power!=1)
                resultString+="^"+power;
        }
        return resultString;
    }
    @Override
    public int compareTo(Object o) {
        Monomial obj=(Monomial)o;
        return obj.power - this.power;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Monomial))
            return false;
        Monomial o=(Monomial) obj;
        return this.power==o.power&&this.coefficient.equalsValue(o.coefficient.getValue());
    }
}
