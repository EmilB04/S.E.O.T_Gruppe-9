import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ElectricityPriceParser {

    // Parser JSON data og returnerer strømpriser
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
