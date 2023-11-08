package PFxPJ.taxes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class INSSTest {
    @Test
    public void testGetSalario() {
        INSS inss = null;
        try {
            inss = new INSS(1000.0);
        } catch (Exception e) {
            e.printStackTrace();
        } // create an instance of INSS with a salary of 1000.0

        double expected = 1000.0;
        double actual = inss.getSalario();
        try {
            assertEquals(expected, actual, 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        } // check that the actual salary matches the expected salary
    }

    @Test
    public void testGetTetoInss() {
        final INSS inss = new INSS(
                1000.0,
                Parameters.JSON_EMBEMBEDED_STRING);

        try {
            assertEquals(876.95, inss.getTetoInss());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}