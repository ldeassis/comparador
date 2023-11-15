import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import PFxPJ.taxes.IRPF;
import PFxPJ.taxes.Parameters;
import PFxPJ.util.JsonResourceReader;
import PFxPJ.util.ParDouble;

public class IRPFTest {

    @Test
    public void testCalcContribution() {
        IRPF irpf = null;
        JsonResourceReader jsonResource = null;

        List<ParDouble> list = new ArrayList<ParDouble>();
        try {
            jsonResource = new JsonResourceReader(Parameters.JSON_FILE_PATH_STRING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        list.add(new ParDouble(1000.0, 0.0));
//        list.add(new ParDouble(2000.0, 0.0));
//        list.add(new ParDouble(3000.0, 59.58));
//        list.add(new ParDouble(5000.0, 339.68));
//        list.add(new ParDouble(10000.0, 1639.48));
//        list.add(new ParDouble(20000.0, 4389.48));
        for (ParDouble par : list) {
            try {
                irpf = new IRPF(par.getValue(), jsonResource.getJsonObject().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(irpf.toString());
            final double expected = par.getExpected();
            final double actual = irpf.getTaxValue();
            try {
                assertEquals(expected, actual, 0.01);
            } catch (Exception e) {
                e.printStackTrace();
            } // check that the actual contribution matches the expected contribution

        }
    }
}