import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;

public class GetElectricityPrices {

    private static final Logger logger = LoggerFactory.getLogger(GetElectricityPrices.class);
    private final ElectricityPriceParser parser;
    private final HttpHandler httpHandler;
    private final ElectricityPriceUrlBuilder urlBuilder; 

    public GetElectricityPrices(ElectricityPriceParser parser, HttpHandler httpHandler, ElectricityPriceUrlBuilder urlBuilder) {
        this.parser = parser;
        this.httpHandler = httpHandler; 
        this.urlBuilder = urlBuilder; 
    }

    public List<ElectricityPriceData> fetchElectricityPrices(String zone) {
        List<ElectricityPriceData> electricityPrices = new ArrayList<>();

        try {
            LocalDate today = LocalDate.now();
            String electricityUrl = urlBuilder.buildUrl(zone, today);
            logger.info("Henter data fra: {}", electricityUrl);

            String fetchedData = httpHandler.getJSONDataFromUrl(electricityUrl);
            electricityPrices = parser.parse(fetchedData);
            logger.info("Hentet: {} objekter", electricityPrices.size());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Det skjedd en feil under henting av strømpriser: ", e);
        }

        return electricityPrices; 
    }

     // Main-metode for testing
     public static void main(String[] args) {
        // Initialiserer avhengigheter
        ElectricityPriceParser parser = new ElectricityPriceParser();
        HttpHandler httpHandler = new HttpHandler();
        ElectricityPriceUrlBuilder urlBuilder = new ElectricityPriceUrlBuilder();

        // Oppretter GetElectricityPrices med nødvendige avhengigheter
        GetElectricityPrices electricityPricesFetcher = new GetElectricityPrices(parser, httpHandler, urlBuilder);

        // Velger hvilken prissone som skal testes
        String zone = "NO1";
        List<ElectricityPriceData> prices = electricityPricesFetcher.fetchElectricityPrices(zone);

        // Skriver ut prisene for å se om de ble hentet riktig
        for (ElectricityPriceData priceData : prices) {
            System.out.println("Pris: " + priceData.getPrice() + ", Starttid: " + priceData.getTimeStart() + ", Sluttid: " + priceData.getTimeStop());
        }
    }
}
