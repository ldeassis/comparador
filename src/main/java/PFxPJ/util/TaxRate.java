package PFxPJ.util;

/**
 * The TaxRate class represents a tax rate with a minimum and maximum income range and a corresponding tax rate.
 */
public class TaxRate {
    private double min;
    private double max;
    private double rate;

    /**
     * Constructor for the TaxRate class.
     * @param min minimum value for the tax rate
     * @param max maximum value for the tax rate
     * @param rate the tax rate
     */
    public TaxRate(double min, double max, double rate) {
        this.setMin(min <= max ? min: max);
        this.setMax(max >= min ? max: min);
        this.setRate(rate >=0 && rate <= 1 ? rate : 0);
    }

    private void setMax(double max) {
        this.max = max > 0 ? max : 0;
    }

    private void setMin(double min) {
        this.min = min > 0 ? min : 0;
    }

    private void setRate(double rate) {
        this.rate = rate >= 0 && rate <= 1 ? rate : 0;
    }

    /**
     * Returns the minimum value that the tax rate have to be applied.
     *
     * @return the minimum value for the tax rate
     */
    public double getMin() {
        return this.min;
    }

    /**
     * Returns the maximum value that the tax rate have to be applied.
     *
     * @return the maximum tax rate
     */
    public double getMax() {
        return this.max;
    }

   /**
     * Returns the tax rate.
     *
     * @return the tax rate.
     */
    public double getRate() {
        return this.rate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(min);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(max);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rate);
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
        TaxRate other = (TaxRate) obj;
        if (Double.doubleToLongBits(min) != Double.doubleToLongBits(other.min))
            return false;
        if (Double.doubleToLongBits(max) != Double.doubleToLongBits(other.max))

            return false;
        if (Double.doubleToLongBits(rate) != Double.doubleToLongBits(other.rate))
            return false;
        return true;
    }
    
    /**
     * Checks if this tax rate overlaps with another tax rate.
     * @param taxRate the tax rate to compare with
     * @return true if the tax rates overlap, false otherwise
     */
    public boolean overlap(TaxRate taxRate) {
        return
        (this.getMax() >= taxRate.getMin() && this.getMax() <= taxRate.getMax()) || // 1st case - Range 1 Max inside range 2
        (this.getMin() >= taxRate.getMin() && this.getMin() <= taxRate.getMax()) || // 2nd case - Range 1 Min inside  Range 2
        (this.getMin() <= taxRate.getMin() && this.getMax() >= taxRate.getMax()) || // 3rd case - Range 1 inside range 2
        (this.getMin() >= taxRate.getMin() && this.getMax() <= taxRate.getMax()); // 4th case - Range 2 inside range 1

    }

    @Override
    public String toString() {
        return "TaxRate [min=" + min + ", max=" + max + ", rate=" + rate + "]";
    }

}
