package PFxPJ.util;

// Classe para representar o par (real, esperado)
public class ParDouble {
    private double value;
    private double expected;

    public ParDouble(double value, double expected) {
        this.setValue(value);
        this.setExpected(expected);
    }

    // getters e setters
    public double getValue() {
        return value;
    }

    public double getExpected() {
        return expected;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(value);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(expected);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParDouble other = (ParDouble) obj;
        if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
            return false;
        if (Double.doubleToLongBits(expected) != Double.doubleToLongBits(other.expected))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ParDouble [value=" + value + ", expected=" + expected + "]";
    }

    private void setValue(double real) {
        this.value = real;
    }

    private void setExpected(double esperado) {
        this.expected = esperado;
    }

}
