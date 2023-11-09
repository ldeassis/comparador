
package PFxPJ.taxes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Scanner;

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
public class INSS {
 
    private double salary; // The salary of an employee used to calculate INSS tax.
    private JSONObject jsonObject; // A class representing a JSON object.this object contains the INSS taxes parameters

    /**
     * Constructor for the INSS class
     * @param salary    the salary of the employee
     * @param jsonString the JSON string to set the JSONObject from
     */
    public INSS(double salary, String jsonString) {
        this.setJsonObjectFromString(jsonString);
        this.setSalary(salary);

    }

    /**
     * Returns the value of the salario attribute.
     * 
     * @return the value of the salario attribute.
     */
    public double getSalary() {
        return this.salary;
    }

    /**
     * Reads a JSON file from the given file path and sets the class's jsonObject
     * field to the parsed JSONObject.
     * 
     * @param jsonFilePath the file path of the JSON file to be read
     */
    public void setJsonObjectFromFile(String jsonFilePath) {
        String jsonString = "";
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setJsonObjectFromString(jsonString);
    }

    /**
     * Sets the JSONObject from a given JSON string.
     * 
     * @param jsonString the JSON string to set the JSONObject from
     */
    public void setJsonObjectFromString(String jsonString) {
        this.jsonObject = new JSONObject(jsonString);
    }

    /**
     * Reads a JSON file from a given input stream and sets the jsonObject field to
     * its contents.
     * 
     * @param jsonStream the input stream containing the JSON file
     */
    public void setJsonObjectFromStream(String jsonStream) {
        InputStream inputStream = this.getClass().getResourceAsStream(jsonStream);
        String json;

        try (Scanner scanner = new Scanner(inputStream)) {
            json = scanner.useDelimiter("\\A").next();
        }

        this.jsonObject = new JSONObject(json);
    }

    /**
     * Sets the salary of the employee and calculates the INSS tax.
     * 
     * @param salary the salary of the employee
     * @throws IllegalArgumentException if the salary is less than or equal to 0
     */
    public final void setSalary(double salary) {
        if (salary > 0) {
            this.salary = salary;
            this.calcContribution();
        } else {
            throw new IllegalArgumentException("Salário deve ser maior que 0");
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
        if (jsonObject.has("INSS")) {
            return jsonObject.getJSONArray("INSS");
        } else {
            throw new IllegalArgumentException("O JSON não contém a chave INSS");
        }
    }

    /**
     * Returns the maximum value for INSS tax calculation.
     * 
     * @return the maximum value for INSS tax calculation.
     * @throws IllegalArgumentException if the JSON object does not contain the key
     *                                  "Teto INSS".
     */
    public double getTetoInss() {
        if (jsonObject.has("Teto INSS")) {
            return jsonObject.getDouble("Teto INSS");
        } else {
            throw new IllegalArgumentException("O JSON não contém a chave Teto INSS");
        }
    }

    /**
     * Returns the value of INSS contribution.
     *
     * @return the value of INSS contribution, calculated using the current formula logic
     */
    public double getContribution() {
        return calcContribution();
    }

    /**
     * Calculates the INSS contribution based on the salary and the INSS aliquotas.
     * These aliquotas are defined yearly and have to be updated in the JSON file.
     */
    private double calcContribution() {

        double valor = 0;
        double old_value = 0;
        JSONArray aliquotas = this.getInssArray();
        int i = 0;
        for (i = 0; i < aliquotas.length(); i++) {
            JSONObject obj = aliquotas.getJSONObject(i);
            double aliquota = 0.0;
            double min = obj.getDouble("min");
            double max = obj.getDouble("max");
            aliquota = obj.getDouble("aliquota");
            old_value = valor;
            valor += (salary >= max ? max : (salary < min ? 0 : max - salary)) * aliquota;
            if (old_value == valor) {
                break;
            }
        }
        return valor > this.getTetoInss() ? this.getTetoInss() : valor;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(salary);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((jsonObject == null) ? 0 : jsonObject.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        INSS other = (INSS) obj;
        if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
            return false;
        if (jsonObject == null) {
            if (other.jsonObject != null)
                return false;
        } else if (!jsonObject.equals(other.jsonObject))
            return false;
        return true;
    }

    /**
     * Returns a string representation of the INSS object.
     * 
     * @return a string representation of the INSS object
     */
    @Override
    public String toString() {
            final String stringINSS = "INSS{" +
            "salary=" + this.getSalary() +
            ", contribution=" + this.getContribution() +
            '}';

        return stringINSS;
    }

}
