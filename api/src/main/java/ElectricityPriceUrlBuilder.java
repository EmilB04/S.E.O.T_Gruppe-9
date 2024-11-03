import java.time.LocalDate;

public class ElectricityPriceUrlBuilder {

    private static final String URL = "https://www.hvakosterstrommen.no/api/v1/prices/";

    public String buildUrl(String zone, LocalDate date) {
        String year = String.valueOf(date.getYear());
        String month = String.format("%02d", date.getMonthValue());
        String day = String.format("%02d", date.getDayOfMonth());

        return URL + year + "/" + month + "-" + day + "_" + zone + ".json";
    }
}
