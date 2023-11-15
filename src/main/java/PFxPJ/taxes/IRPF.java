package PFxPJ.taxes;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author leonard Assis
 * @version 1.0
 * @license MIT
 *
 *          The INSS class represents the INSS tax calculation for a given
 *          salary.
 *          It takes the salary as a parameter and calculates the INSS tax based
 *          on it.
 *          The class provides methods to set the salary, get the calculated
 *          INSS
 *          tax, and read a JSON file containing the INSS tax rates.
 */
public class IRPF extends TaxRate implements Tax {

    /**
     * This class represents the INSS tax calculation for a given salary.
     * It extends the abstract class Tax and implements its abstract methods.
     */
    public IRPF(double salary, String jsonString) {
        super(salary, jsonString);
    }

    /**
     * A JSONArray is an ordered sequence of values. Its external text form is a
     * string wrapped in square brackets with commas separating the values. The
     * internal form is an object having get() and opt() methods for accessing the
     * values by index, and put() methods for adding or replacing values. The values
     * can be any of these types: Boolean, JSONArray, JSONObject, Number, String, or
     * the JSONObject.NULL object.
     */
    private JSONArray getIRPFArray() {
        JSONObject jsonObject = this.getJsonObject();
        if (jsonObject.has("IRPF")) {
            return jsonObject.getJSONArray("IRPF");
        } else {
            throw new IllegalArgumentException("O JSON não contém a chave IRPF");
        }
    }

    /**
     * Calculates the IRPF contribution based on the salary and the IRPF rates.
     * These aliquotas are defined yearly and have to be updated in the JSON file.
     */
    public double calcContribution() {
        final double fatorEscala = Math.pow(10, 5);
        INSS inss = new INSS(this.getSalary(), this.getJsonObject().toString());
        final double inssTax = Math.round(inss.calcContribution() * fatorEscala) / fatorEscala;
        final double salaryMinusINSS = Math.round((this.getSalary() - inssTax) * fatorEscala) / fatorEscala;
        double value = 0.0;
        JSONArray taxRates = this.getIRPFArray();
        int i = 0;
        for (i = 0; i < taxRates.length(); i++) {
            JSONObject bracket = taxRates.getJSONObject(i);
            final double min = bracket.has("min") ? bracket.getDouble("min") : 0.0;
            final double max = bracket.has("max") ? bracket.getDouble("max") : Double.MAX_VALUE;
            final boolean inRange = salaryMinusINSS >= min && salaryMinusINSS <= max;
             
            if (inRange) {
                final double deduct = bracket.has("deduct") ? bracket.getDouble("deduct") : 0.0;
                final double rate = bracket.has("rate") ? bracket.getDouble("rate") : 0.0;

                value = Math.round((salaryMinusINSS * rate) * fatorEscala) / fatorEscala - deduct;
                break;
            }
        }
        return value;

    }

    @Override
    public String toString() {
        final double salary = this.getSalary();
        final double inssTax = new INSS(this.getSalary(), this.getJsonObject().toString()).calcContribution();
        final double salaryCalc = salary - inssTax;
        final double irpfTax = this.calcContribution();

        return "IRPF {" +
                "salary = " + salary +
                ", INSS Tax = " + inssTax +
                ", Salary for IRPF Tax = " + salaryCalc +
                ", IRPF Tax = " + irpfTax +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final IRPF other = (IRPF) obj;
        if (Double.doubleToLongBits(this.getSalary()) != Double.doubleToLongBits(other.getSalary()))
            return false;
        if (Double.doubleToLongBits(this.getTaxValue()) != Double.doubleToLongBits(other.getTaxValue()))
            return false;
        return true;
    }

}
