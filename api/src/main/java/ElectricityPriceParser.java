import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Parses JSON data to retrieve electricity prices.
 * 
 * This class interprets JSON data and converts it into objects, where each object contains information
 * about electricity prices, such as price per kWh, start time, and end time.
 * 
 * Example usage:
 * <pre>
 * ElectricityPriceParser parser = new ElectricityPriceParser();
 * List<ElectricityPriceData> prices = parser.parse(jsonData);
 * </pre>
 */

public class ElectricityPriceParser {

   /**
     * Parses JSON data and returns a list of electricity price data.
     * 
     * @param jsonData JSON string containing electricity price data.
     * @return A list of `ElectricityPriceData` objects.
     * @throws JSONException if the data is invalid or required fields are missing.
     */
    public List<ElectricityPriceData> parse(String jsonData) {
        List<ElectricityPriceData> prices = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonData);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject electricityPriceObject = jsonArray.getJSONObject(i);
            double price = electricityPriceObject.getDouble("NOK_per_kWh");
            String timeStart = electricityPriceObject.getString("time_start");
            String timeStop = electricityPriceObject.getString("time_end");

            ElectricityPriceData data = new ElectricityPriceData(price, timeStart, timeStop);

            prices.add(data);
        }
        return prices; 

    }
}
