
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import PFxPJ.taxes.INSS;
import PFxPJ.taxes.Parameters;
import PFxPJ.util.JsonResourceReader;
import PFxPJ.util.ParDouble;

public class INSSTest {
    @Test
    public void testGetSalary() {
        INSS inss = null;
        JsonResourceReader jsonResource = null;
        try {
            try{
                jsonResource = new JsonResourceReader(Parameters.INSS_JSON_FILE_PATH_STRING);
            } catch (Exception e) {
                e.printStackTrace();
            }
            inss = new INSS(1000.0, jsonResource.getJsonObject().toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // do nothing
        }

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
        JsonResourceReader jsonResource = null;
        try {
            try{
                jsonResource = new JsonResourceReader(Parameters.INSS_JSON_FILE_PATH_STRING);
            } catch (Exception e) {
                e.printStackTrace();
            }
            inss = new INSS(1000.0, jsonResource.getJsonObject().toString());
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
    public void testGetContribution() {
        INSS inss = null;
        JsonResourceReader jsonResource = null;

        List<ParDouble> list = new ArrayList<ParDouble>();
            try{
                jsonResource = new JsonResourceReader(Parameters.INSS_JSON_FILE_PATH_STRING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        list.add(new ParDouble(1000.0, 75.0));
        list.add(new ParDouble(2000.0, 160.2));
        list.add(new ParDouble(3000.0, 301.64));
        list.add(new ParDouble(5000.0, 663.09));
        list.add(new ParDouble(10000.0, 876.95));

        for(ParDouble par : list) {
            try {
                inss = new INSS(par.getValue(), jsonResource.getJsonObject().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            final double expected = par.getExpected();
            final double actual = inss.getContribution();
            try {
                assertEquals(expected, actual, 0.01);
            } catch (Exception e) {
                e.printStackTrace();
            } // check that the actual contribution matches the expected contribution

        }
     }
}