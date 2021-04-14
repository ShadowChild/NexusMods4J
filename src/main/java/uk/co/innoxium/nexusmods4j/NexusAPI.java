package uk.co.innoxium.nexusmods4j;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import uk.co.innoxium.nexusmods4j.response.GameInfo;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

@RequiredArgsConstructor
public class NexusAPI {

    private final String apiKey;
    private OkHttpClient client;


    /**
     * This method returns a list of games, with info, that are available on the Nexus Mods Website.
     * @param include_unapproved - Select whether or not to include unapproved games in the result, may increase response times.
     * @return - An ArrayList of {@link GameInfo} Game objects if the API responded in time, else null
     */
    public ArrayList<GameInfo> getAllGamesInfo(boolean include_unapproved) {

        Request request = createRequest("/games.json" + "?include_unapproved=" + include_unapproved)
                .build();
        try(Response response = execute(request)) {

            Type listType = new TypeToken<ArrayList<GameInfo>>(){}.getType();

            return new Gson().fromJson(Objects.requireNonNull(response.body()).string(), listType);
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get a list of all approved games on the Nexus Mods Website, along with all info.
     * @return - An ArrayList of {@link GameInfo} objects if the API responded in time, else null
     */
    public ArrayList<GameInfo> getAllGamesInfo() {

        return getAllGamesInfo(false);
    }

    /**
     * Returns the game information for the provided game 'slug'
     * @param game_domain_name - The name, as shown in the nexus mods url, of the game to get the info from.
     * @return - A {@link GameInfo} object of the game 'slug' if exists, else null;
     */
    public GameInfo getGameInfo(@NotNull String game_domain_name) {

        Request request = createRequest("/games/" + game_domain_name + ".json")
                .build();
        try(Response response = execute(request)) {

            return new Gson().fromJson(Objects.requireNonNull(response.body()).string(), GameInfo.class);
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }

    /**
     * A Util method for getting a response from a request.
     * @param request - The request to get a response from
     * @return - The Response from the server
     * @throws IOException - An Exception may be thrown for timeout, invalid api call, etc. See {@link Call#execute()} for more info.
     */
    @NotNull
    private Response execute(@NotNull Request request) throws IOException {

        return getClient().newCall(request).execute();
    }

    /**
     * @return - A new HttpClient instance.
     */
    @NotNull
    private OkHttpClient getClient() {

        if(client == null) client = new OkHttpClient();

        return client;
    }

    /**
     * Provides a generic Request.Builder instance for an API call
     * @param apiCall - the api call, MUST begin in a forward slash '/'
     * @return - A Request.Builder instance of the Request, which may be further customised if required.
     */
    private Request.Builder createRequest(@NotNull String apiCall) {

        return new Request.Builder()
                .url(getNexusUrl(apiCall))
                .addHeader("apiKey", apiKey);
    }

    @SneakyThrows
    private URL getNexusUrl(@NotNull String apiCall) {

        return new URL("https://api.nexusmods.com/v1" + apiCall);
    }
}
