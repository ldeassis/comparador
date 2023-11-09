
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import PFxPJ.taxes.INSS;
import PFxPJ.taxes.Parameters;

public class INSSTest {
    @Test
    public void testGetSalary() {
        INSS inss = null;
        try {
            inss = new INSS(1000.0, Parameters.JSON_EMBEMBEDED_STRING);
        } catch (Exception e) {
            e.printStackTrace();
        } // create an instance of INSS with a salary of 1000.0

        final double expected = 1000.0;
        final double actual = inss.getSalary();
        try {
            assertEquals(expected, actual, 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        } // check that the actual salary matches the expected salary
    }

    @Test
    public void testGetTetoInss() {
        INSS inss = null;
        try {
            inss = new INSS(1000.0, Parameters.JSON_EMBEMBEDED_STRING);
        } catch (Exception e) {
            e.printStackTrace();
        } // create an instance of INSS with a salary of 1000.0

        final double expected = 876.95;
        final double actual = inss.getTetoInss();
        try {
            assertEquals(expected, actual, 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetContributionCase_1k() {
        INSS inss = null;
        try {
            inss = new INSS(1000.0, Parameters.JSON_EMBEMBEDED_STRING);
        } catch (Exception e) {
            e.printStackTrace();
        } // create an instance of INSS with a salary of 1000.0

        final double expected = 75.0;
        final double actual = inss.getContribution();
        try {
            assertEquals(expected, actual, 0.01);
        } catch (Exception e) {
            e.printStackTrace();
        } // check that the actual contribution matches the expected contribution
    }

    @Test
    public void testGetContributionCase_2k() {
        INSS inss = null;
        try {
            inss = new INSS(2000.0, Parameters.JSON_EMBEMBEDED_STRING);
        } catch (Exception e) {
            e.printStackTrace();
        } // create an instance of INSS with a salary of 1000.0

        final double expected = 160.2;
        final double actual = inss.getContribution();
        try {
            assertEquals(expected, actual, 0.01);
        } catch (Exception e) {
            e.printStackTrace();
        } // check that the actual contribution matches the expected contribution
    }

    @Test
    public void testGetContributionCase_3k() {
        INSS inss = null;
        try {
            inss = new INSS(3000.0, Parameters.JSON_EMBEMBEDED_STRING);
        } catch (Exception e) {
            e.printStackTrace();
        } // create an instance of INSS with a salary of 1000.0

        final double expected = 301.64;
        final double actual = inss.getContribution();
        try {
            assertEquals(expected, actual, 0.01);
        } catch (Exception e) {
            e.printStackTrace();
        } // check that the actual contribution matches the expected contribution
    }

    @Test
    public void testGetContributionCase_5k() {
        INSS inss = null;
        try {
            inss = new INSS(5000.0, Parameters.JSON_EMBEMBEDED_STRING);
        } catch (Exception e) {
            e.printStackTrace();
        } // create an instance of INSS with a salary of 1000.0

        final double expected = 663.09;
        final double actual = inss.getContribution();
        try {
            assertEquals(expected, actual, 0.01);
        } catch (Exception e) {
            e.printStackTrace();
        } // check that the actual contribution matches the expected contribution
    }

    @Test
    public void testGetContributionCase_10k() {
        INSS inss = null;
        try {
            inss = new INSS(10000.0, Parameters.JSON_EMBEMBEDED_STRING);
        } catch (Exception e) {
            e.printStackTrace();
        } // create an instance of INSS with a salary of 1000.0

        final double expected = 876.95;
        final double actual = inss.getContribution();
        try {
            assertEquals(expected, actual, 0.01);
        } catch (Exception e) {
            e.printStackTrace();
        } // check that the actual contribution matches the expected contribution
    }

}