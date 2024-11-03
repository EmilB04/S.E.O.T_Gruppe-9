import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;

public class GetElectricityPrices {

    private static final Logger logger = LoggerFactory.getLogger(GetElectricityPrices.class);
    private final ElectricityPriceParser parser = new ElectricityPriceParser(); 
    private final HttpHandler httpHandler = new HttpHandler();

    
    // Henter strømprisdata fra Api
    public List<Double> fetchElectricityPrices(String zone) {
        List<Double> electricityPrices = new ArrayList<>();

        // Henter strømprisen for dagens dato
        LocalDate today = LocalDate.now();
        String year = String.valueOf(today.getYear());
        String month = String.format("%02d", today.getMonthValue());
        String day = String.format("%02d", today.getDayOfMonth());

        // Plasserer hovedkoden i en try/except block
        try {
            // Lager URL og bruker API hentet fra  https://www.hvakosterstrommen.no/strompris-api
            String electricityUrl = "https://www.hvakosterstrommen.no/api/v1/prices/" + year + "/" + month + "-" + day + "_" + zone + ".json";
            // Logger hvilken URL som har blitt brukt
            logger.info("Henter data fra: {}", electricityUrl);

            // Bruker HttpHandler for å hente JSON data
            String fetchedData = httpHandler.getJSONDataFromUrl(electricityUrl);

            // Bruker parser for å hente strømpriser fra JSON data
            electricityPrices = parser.parse(fetchedData);
            // Logger antall objekter som har blitt hentet
            logger.info("Hentet: {} objekter", electricityPrices.size());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Det skjedd en feil under henting av strømpriser: ", e);
        }

        // Returnerer strømpriser i listeform 
        return electricityPrices; 
    }

    // Main metode for teste koden
    public static void main(String[] args) {
        GetElectricityPrices electricityPricesFetcher = new GetElectricityPrices();
        // Kan endre prissone her:
        List<Double> prices = electricityPricesFetcher.fetchElectricityPrices("NO1");

        // Skriv ut prisene for å se om de er hentet
        System.out.println(prices);
    }
}
