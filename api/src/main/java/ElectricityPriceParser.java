import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ElectricityPriceParser {

    // Parser JSON data og returnerer strømpriser
    public List<Double> parse(String jsonData) {
        List<Double> prices = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonData);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject electricityPriceObject = jsonArray.getJSONObject(i);
            double price = electricityPriceObject.getDouble("NOK_per_kWh");
            prices.add(price);
        }
        return prices; 

    }
}
