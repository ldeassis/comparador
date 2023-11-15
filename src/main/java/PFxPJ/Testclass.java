package PFxPJ;

import PFxPJ.taxes.IRPF;
import PFxPJ.taxes.Parameters;
import PFxPJ.util.JsonResourceReader;

public class Testclass {

    public static void main(String[] args) {
        IRPF irpf = null;
        JsonResourceReader jsonResource = null;
        try {
            jsonResource = new JsonResourceReader(Parameters.JSON_FILE_PATH_STRING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            irpf = new IRPF(1000.0, jsonResource.getJsonObject().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String IR = irpf.toString();
        System.out.println(IR);
    }
    
}
