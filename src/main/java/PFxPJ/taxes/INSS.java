
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
public class INSS extends TaxRate implements Tax {

    /**
     * This class represents the INSS tax calculation for a given salary.
     * It extends the abstract class Tax and implements its abstract methods.
     */
    public INSS(double salary, String jsonString) {
        super(salary, jsonString);
    }

    /**
     * Returns the maximum value for INSS tax calculation.
     * 
     * @return the maximum value for INSS tax calculation.
     * @throws IllegalArgumentException if the JSON object does not contain the key
     *                                  "Teto INSS".
     */
    public double getTetoInss() {
        JSONObject jsonObject = this.getJsonObject();
        if (jsonObject.has("Teto INSS")) {
            return jsonObject.getDouble("Teto INSS");
        } else {
            throw new IllegalArgumentException("O JSON não contém a chave Teto INSS");
        }
    }

    /**
     * A JSONArray is an ordered sequence of values. Its external text form is a
     * string wrapped in square brackets with commas separating the values. The
     * internal form is an object having get() and opt() methods for accessing the
     * values by index, and put() methods for adding or replacing values. The values
     * can be any of these types: Boolean, JSONArray, JSONObject, Number, String, or
     * the JSONObject.NULL object.
     */
    private JSONArray getInssArray() {
        JSONObject jsonObject = this.getJsonObject();
        if (jsonObject.has("INSS")) {
            return jsonObject.getJSONArray("INSS");
        } else {
            throw new IllegalArgumentException("O JSON não contém a chave INSS");
        }
    }

    /**
     * Calculates the INSS contribution based on the salary and the INSS aliquotas.
     * These aliquotas are defined yearly and have to be updated in the JSON file.
     */
    public double calcContribution() {
        final double fatorEscala = Math.pow(10, 5);
        double value = 0.0;
        double old_value = 0;
        JSONArray taxRates = this.getInssArray();
        int i = 0;
        for (i = 0; i < taxRates.length(); i++) {
            JSONObject obj = taxRates.getJSONObject(i);
            final double min = obj.has("min") ? obj.getDouble("min") : 0.0;
            final double max = obj.has("max") ? obj.getDouble("max") : Double.MAX_VALUE;
            final double salaryMinusMin = this.getSalary() - min;
            final double maxMinusMin = max - min;
            final double increment = salaryMinusMin > 0.0 && salaryMinusMin > max ? maxMinusMin
                    : (salaryMinusMin > 0.0 ? salaryMinusMin : 0.0);
            final double rate = obj.getDouble("rate");

            old_value = value;
            value += Math.round((increment * rate) * fatorEscala) / fatorEscala;;
            if (old_value == value) {
                break;
            }
        }
        return value > this.getTetoInss() ? this.getTetoInss() : value;

    }

    /**
     * Returns a string representation of the INSS object.
     * 
     * @return a string representation of the INSS object
     */
    @Override
    public String toString() {
        final String stringINSS = "INSS {" +
                "salary =" + this.getSalary() +
                ", INSS contribution = " + this.getTaxValue() +
                '}';

        return stringINSS;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final INSS other = (INSS) obj;
        if (Double.doubleToLongBits(this.getSalary()) != Double.doubleToLongBits(other.getSalary()))
            return false;
        if (Double.doubleToLongBits(this.getTaxValue()) != Double.doubleToLongBits(other.getTaxValue()))
            return false;
        return true;
    }


}
