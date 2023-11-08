package PFxPJ.taxes;

public final class Parameters {
    private Parameters() {
    }

    // Parametros do INSS

    /**
     * json default para a tabela do INSS
     * Estará aqui até o json embutido funcionar corretamente
     */
    public static final String JSON_EMBEMBEDED_STRING = "{\"INSS\":[" +
            "{\"min\": 0,\"max\": 1320.00,\"aliquota\": 0.075}," +
            "{\"min\": 1320.01,\"max\": 2571.29,\"aliquota\": 0.09}," +
            "{\"min\": 2571.30,\"max\": 3856.94,\"aliquota\": 0.12}," +
            "{\"min\": 3856.95,\"max\": 999999999.99,\"aliquota\": 0.14}]," +
            "\"Teto INSS\": 876.95}";
    /**
     * String default para a localização da tabela embutida do INSS
     * Quando funcionar, o json String acima será apagado do código
     */
    public static final String JSON_EMBEMBEDED_FILE_PATH_STRING = "/inss.json";

}
