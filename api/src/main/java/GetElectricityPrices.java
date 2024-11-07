import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;

/**
 * Retrieves electricity prices for a country zone via HTTP and parses the data
 * 
 * The class use "ElectricityPriceParser", "ElectricityPriceUrlBuilder" and "HttpHandler" to get and
 * process electricity prices for a given zone.
 * 
 * Example usage:
 * <pre>
 * GetElectricityPrices electricityPricesFetcher = new GetElectricityPrices(parser, httpHandler, urlBuilder);
 * List<ElectricityPriceData> prices = electricityPricesFetcher.fetchElectricityPrices("NO1");
 * </pre> 
 */
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
    /**
     * Retrieves electricity prices for a specified zone
     * 
     * @param zone Price zone for a given zone in the country
     * @return A List of "ElectricityPriceData" objects for a specified zone
     * @throws Exception if an error occurs during data retrieval or parsing.
     */
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
            logger.error("Det skjedde en feil under henting av strømpriser: ", e);
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
        //NO1 = Oslo / Øst-Norge
        //NO2 = Kristiansand / Sør-Norge
        //NO3 = Trondheim / Midt-Norge
        //NO4 = Tromsø / Nord-Norge
        //NO5 = Bergen / Vest-Norge
        String zone = "NO1";
        List<ElectricityPriceData> prices = electricityPricesFetcher.fetchElectricityPrices(zone);

        // Skriver ut prisene for å se om de ble hentet riktig
        for (ElectricityPriceData priceData : prices) {
            System.out.println("Pris: " + priceData.getPrice() + ", Starttid: " + priceData.getTimeStart() + ", Sluttid: " + priceData.getTimeStop());
        }
    }
}
