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
            "{\"min\": 0,\"max\": 1320.00,\"rate\": 0.075}," +
            "{\"min\": 1320.01,\"max\": 2571.29,\"rate\": 0.09}," +
            "{\"min\": 2571.30,\"max\": 3856.94,\"rate\": 0.12}," +
            "{\"min\": 3856.95,\"max\": 7507.49,\"rate\": 0.14}]," +
            "\"Teto INSS\": 876.95}";
    /**
     * String default para a localização da tabela embutida do INSS
     * Quando funcionar, o json String acima será apagado do código
     */
    public static final String INSS_JSON_FILE_PATH_STRING = "/inss.json";

}
