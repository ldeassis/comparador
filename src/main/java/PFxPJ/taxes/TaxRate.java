package PFxPJ.taxes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Scanner;

import org.json.JSONObject;

/**
 * The TaxRate class represents a tax rate for a given salary range.
 * It contains the minimum and maximum salary values for the range and the tax rate
 * for that range.
 * The class provides methods to set the minimum and maximum salary values and the
 * tax rate.
 */
public abstract class TaxRate {
    private double salary; // The salary of an employee used to calculate INSS tax.
    private JSONObject jsonObject; // A class representing a JSON object.this object contains the INSS taxes
    

    // parameters
     /**
     * Constructor for the TaxRate class
     * 
     * @param salary     the salary of the employee
     * @param jsonString the JSON string to set the JSONObject from
     */
    public TaxRate(double salary, String jsonString) {
        this.setJsonObjectFromString(jsonString);
        this.setSalary(salary);

    }

    /**
     * This class represents the TaxRate tax calculation for a given salary.
     * It takes in a salary and a JSON input stream to calculate the TaxRate tax.
     */
    public TaxRate(double salary, InputStream jsonStream) {
        this.setJsonObjectFromString(jsonStream.toString());
        this.setSalary(salary);
    }

    /**
     * Returns the value of the salary attribute.
     * 
     * @return the value of the salary attribute.
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
     * Returns the value of TaxValue Tax Value (contribution).
     *
     * @return the value of TaxValue tax value calculated (contribution), calculated using the current formula
     *         logic
     */
    public double getTaxValue() {
        return this.calcContribution();
    }


    public abstract double calcContribution();

    protected JSONObject getJsonObject() {
        return this.jsonObject;
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
        if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.getSalary()))
            return false;
        if (jsonObject == null) {
            if (other.getJsonObject() != null)
                return false;
        } else if (!this.jsonObject.equals(other.getJsonObject()))
            return false;
        return true;
    }
}
