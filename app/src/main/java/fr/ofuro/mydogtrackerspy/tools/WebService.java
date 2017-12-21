package fr.ofuro.mydogtrackerspy.tools;
import com.google.gson.Gson;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


//TODO: webservice à continuer
public class WebService {

    private final String URL = "http://testwebapi.lalimace.fr/api/Point";

    Gson gson;

    public WebService() {

        gson = new Gson();
    }

    private InputStream sendRequest(URL url) throws Exception {
        try {

            // Ouverture de la connexion
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Connexion à l’URL
            urlConnection.connect();

            // Si le serveur nous répond avec un code OK
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return urlConnection.getInputStream();
            }
        } catch (Exception e) {
            throw new Exception("");
        }
        return null;
    }
}
