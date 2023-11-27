package Converter;

import java.util.Map;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class CurrencyConverter {

    private WebClient webClient = WebClient.create("https://api.exchangerate-api.com/v4/latest/");

    public Mono<Double> convert(String from, String to, double amount) {
        return webClient.get()
                .uri(from)
                .retrieve()
                .bodyToMono(ConversionRate.class)
                .map(conversionRate -> conversionRate.getRates().get(to) * amount);
    }
}

class ConversionRate {
    private String base;
    public String getBase() {
        return base;
    }
    public void setBase(String base) {
        this.base = base;
    }
    private Map<String, Double> rates;
    
    public Map<String, Double> getRates() {
        return rates;
    }
    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    // getters and setters
}
