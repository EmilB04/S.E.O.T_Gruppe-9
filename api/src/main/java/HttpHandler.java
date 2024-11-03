import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpHandler {

    private static final Logger logger = LoggerFactory.getLogger(HttpHandler.class);

    // Metode som henter JSON-data fra en URL
    public String getJSONDataFromUrl (String urlInput){
        // Lager en stringbuilder for å samle alle linjene fra API
        StringBuilder data = new StringBuilder(); 

        try {
            URL url = new URL(urlInput); 
            // Åpner tilkoblingen til serveren via API
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
            connection.setRequestMethod("GET");

            // Logger hvilken url som har blitt brukt 
            logger.info("Koblet til: {}", urlInput);

            // Leser API-responsen
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String infoLine;

             // Hvis infoLine ikke er tom appender data infoLine
             while ((infoLine = in.readLine()) != null) {
                data.append(infoLine);
            }

            in.close();

            // Kobler fra serveren 
            connection.disconnect();

            logger.info("Hentet data fra siden"); 
    } catch (Exception e) {
        logger.error("Det skjedde en feil under henting av data fra siden ", e);
        e.printStackTrace();
    }
    return data.toString();
        }
}
