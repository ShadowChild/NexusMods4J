package uk.co.innoxium.nexusmods4j;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import uk.co.innoxium.nexusmods4j.response.Game;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class Games extends RequestSender {

    public Games(String apiKey) {
        super(apiKey);
    }

    /**
     * This method returns a list of games, with info, that are available on the Nexus Mods Website.
     * @param include_unapproved - Select whether or not to include unapproved games in the result, may increase response times.
     * @return - An ArrayList of {@link Game} Game objects if the API responded in time, else null
     */
    public ArrayList<Game> all(boolean include_unapproved) {

        Request request = createRequest("/games.json" + "?include_unapproved=" + include_unapproved)
                .build();
        try(Response response = execute(request)) {

            Type listType = new TypeToken<ArrayList<Game>>(){}.getType();

            return new Gson().fromJson(Objects.requireNonNull(response.body()).string(), listType);
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get a list of all approved games on the Nexus Mods Website, along with all info.
     * @return - An ArrayList of {@link Game} objects if the API responded in time, else null
     */
    public ArrayList<Game> all() {

        return all(false);
    }

    /**
     * Returns the game information for the provided game 'slug'
     * @param game_domain_name - The name, as shown in the nexus mods url, of the game to get the info from.
     * @return - A {@link Game} object of the game 'slug' if exists, else null;
     */
    public Game single(@NotNull String game_domain_name) {

        Request request = createRequest("/games/" + game_domain_name + ".json")
                .build();
        try(Response response = execute(request)) {

            return new Gson().fromJson(Objects.requireNonNull(response.body()).string(), Game.class);
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }
}
