package uk.co.innoxium.nexusmods4j;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uk.co.innoxium.nexusmods4j.response.Game;
import uk.co.innoxium.nexusmods4j.response.Games;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

@AllArgsConstructor
public class NexusAPI {

    private String apiKey;

    public ArrayList<Game> games(boolean include_unapproved) {

        Request request = createRequest("/games.json" + "?include_unapproved=" + include_unapproved)
                .build();
        try(Response response = getClient().newCall(request).execute()) {

            Type listType = new TypeToken<ArrayList<Game>>(){}.getType();

            return new Gson().fromJson(Objects.requireNonNull(response.body()).string(), listType);
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }

    private OkHttpClient getClient() {

        return new OkHttpClient();
    }

    /**
     * Provides a generic Request.Builder instance for an API call
     * @param apiCall - the api call, MUST begin in a forward slash '/'
     * @return - A Request.Builder instance of the Request, which may be further customised if required.
     */
    private Request.Builder createRequest(String apiCall) {

        return new Request.Builder()
                .url(getNexusUrl(apiCall))
                .addHeader("apiKey", apiKey);
    }

    @SneakyThrows
    private URL getNexusUrl(String apiCall) {

        return new URL("https://api.nexusmods.com/v1" + apiCall);
    }
}
