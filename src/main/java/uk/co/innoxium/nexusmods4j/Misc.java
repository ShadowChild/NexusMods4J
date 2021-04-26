package uk.co.innoxium.nexusmods4j;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.Request;
import okhttp3.Response;
import uk.co.innoxium.nexusmods4j.response.ColourScheme;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class Misc extends RequestSender {

    public Misc(String apiKey) {
        super(apiKey);
    }

    public ArrayList<ColourScheme> colourSchemes() {

        Request request = createRequest("/colourschemes.json").build();

        try(Response response = execute(request)) {

            Type listType = new TypeToken<ArrayList<ColourScheme>>(){}.getType();

            return new Gson().fromJson(Objects.requireNonNull(response.body()).string(), listType);
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }
}
