
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
    /**
     * The salary of an employee used to calculate INSS tax.
     */
    private double salario;
    /**
     * The value of the INSS tax.
     */
    private double valor_inss;
    /**
     * A class representing a JSON object.this object contains the INSS taxes
     * parameters
     */
    private JSONObject jsonObject;

    /**
     * This class represents the INSS tax calculation for a given salary.
     * It takes the salary as a parameter and calculates the INSS tax
     * based on it.
     * 
     * @param salario the salary of the employee
     */
    public INSS(double salario) {
        this.setSalario(salario);
    }

    /**
     * This class represents the INSS tax calculation for a given salary.
     * It takes the salary as a parameter and calculates the INSS tax
     * based on it.
     * 
     * @param salario    the salary of the employee
     * @param jsonString the JSON string to set the JSONObject from
     */
    public INSS(double salario, String jsonString) {
        this.setSalario(salario);
        this.setJsonObjectFromString(jsonString);
    }

    /**
     * Returns the value of the salario attribute.
     * 
     * @return the value of the salario attribute.
     */
    public double getSalario() {
        return salario;
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
     * @param salario the salary of the employee
     * @throws IllegalArgumentException if the salary is less than or equal to 0
     */
    public final void setSalario(double salario) {
        if (salario > 0) {
            this.setSalario(salario);
            this.calculaInss();
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
     * Returns the value of INSS.
     *
     * @return the value of INSS
     */
    public double getInss() {
        return this.valor_inss;
    }

    /**
     * Calculates the INSS tax based on the salary and the INSS aliquot table.
     */
    private void calculaInss() {

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
            valor += (salario >= max ? max : (salario < min ? 0 : max - salario)) * aliquota;
            if (old_value == valor) {
                break;
            }
        }
        this.valor_inss = valor > this.getTetoInss() ? this.getTetoInss() : valor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(salario);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(valor_inss);
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
        if (getClass() != obj.getClass())
            return false;
        INSS other = (INSS) obj;
        if (Double.doubleToLongBits(salario) != Double.doubleToLongBits(other.salario))
            return false;
        if (Double.doubleToLongBits(valor_inss) != Double.doubleToLongBits(other.valor_inss))
            return false;
        if (jsonObject == null) {
            if (other.jsonObject != null)
                return false;
        } else if (!jsonObject.equals(other.jsonObject))
            return false;
        return true;
    }

}
