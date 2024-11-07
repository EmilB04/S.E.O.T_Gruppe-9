import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles HTTP requests to get JSON data from a URL
 * 
 * The class sends GET requests to a URLs, retrieving JSON-data and returns the data as a String.
 * 
 * Example usage:
 * <pre>
 * HttpHandler httpHandler = HttpHandler();
 * String jsonData = httpHandler.getJSONDataFromUrl("exampleURL");
 * </pre>
 */
public class HttpHandler {

    private static final Logger logger = LoggerFactory.getLogger(HttpHandler.class);

    /**
     * Retrieves JSON-data from a URL
     * 
     * @param urlInput the URL for the API
     * @return JSON data as a String
     * @throws IOException if a network error occurs
     */
    public String getJSONDataFromUrl (String urlInput){

        StringBuilder data = new StringBuilder(); 

        try {
            URL url = new URL(urlInput); 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
            connection.setRequestMethod("GET");

            logger.info("Koblet til: {}", urlInput);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String infoLine;

             while ((infoLine = in.readLine()) != null) {
                data.append(infoLine);
            }

            in.close();
 
            connection.disconnect();

            logger.info("Hentet data fra siden"); 
    } catch (Exception e) {
        logger.error("Det skjedde en feil under henting av data fra siden ", e);
        e.printStackTrace();
    }
    return data.toString();
        }
}
