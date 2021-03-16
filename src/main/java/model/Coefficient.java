package model;

import java.text.DecimalFormat;

@SuppressWarnings("rawtypes")
public class Coefficient implements Comparable{
    private Number value;
    public Coefficient(Number value){
        this.value=value;
    }
    public Coefficient add(Coefficient src){
        if(this.value instanceof Integer)
        {
            if(src.value instanceof Integer)
                return new Coefficient(((Integer)this.value+(Integer)src.value));
            else
                return new Coefficient(((Integer)this.value+(Double)src.value));
        }
        else{
            if(src.value instanceof Integer)
                return new Coefficient(((Double)this.value+(Integer)src.value));
            else
                return new Coefficient(((Double)this.value+(Double)src.value));
        }
    }
    public Coefficient dif(Coefficient src){
        if(this.value instanceof Integer)
        {
            if(src.value instanceof Integer)
                return new Coefficient(((Integer)this.value-(Integer)src.value));
            else
                return new Coefficient(((Integer)this.value-(Double)src.value));
        }
        else{
            if(src.value instanceof Integer)
                return new Coefficient(((Double)this.value-(Integer)src.value));
            else
                return new Coefficient(((Double)this.value-(Double)src.value));
        }
    }
    public Coefficient multiply(Coefficient src){
        if(this.value instanceof Integer)
        {
            if(src.value instanceof Integer)
                return new Coefficient(((Integer)this.value*(Integer)src.value));
            else
                return new Coefficient(((Integer)this.value*(Double)src.value));
        }
        else{
            if(src.value instanceof Integer)
                return new Coefficient(((Double)this.value*(Integer)src.value));
            else
                return new Coefficient(((Double)this.value*(Double)src.value));
        }
    }
    public Coefficient divide(Coefficient src) throws ArithmeticException{
        if(this.value instanceof Integer)
        {
            if(src.value instanceof Integer)
                return new Coefficient((((Integer)this.value*1.0)/(Integer)src.value));
            else
                return new Coefficient(((Integer)this.value/(Double)src.value));
        }
        else{
            if(src.value instanceof Integer)
                return new Coefficient(((Double)this.value/(Integer)src.value));
            else
                return new Coefficient(((Double)this.value/(Double)src.value));
        }
    }
    public boolean equalsValue(Number value){
        if((this.value instanceof Integer)&&(value instanceof Integer))
            return  this.value.equals(value);
        Coefficient dif=this.dif(new Coefficient(value));
        return Math.abs((Double)dif.value)<0.00000001;

    }
    public Number getValue(){
        return this.value;
    }
    public void setValue(Number value){
        this.value=value;
    }
    @Override
    public int compareTo(Object o) {
        if(this.equalsValue(((Coefficient)o).value))
            return 0;
        Coefficient dif=this.dif((Coefficient)o);
        if(dif.value instanceof Integer)
            return (Integer)dif.value/Math.abs((Integer)dif.value);
        double val=(Double)dif.value/Math.abs((Double)dif.value);
        if(val<0)
            return -1;
        return 1;
    }

    @Override
    public String toString() {
        if(value instanceof Integer)
            return value.toString();
        DecimalFormat format=new DecimalFormat("0.00");
        return format.format(value);
    }
}
