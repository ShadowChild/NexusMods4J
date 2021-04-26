package uk.co.innoxium.nexusmods4j;

import com.google.gson.Gson;
import okhttp3.Request;
import okhttp3.Response;
import uk.co.innoxium.nexusmods4j.response.User;

import java.io.IOException;
import java.util.Objects;

public class Users extends RequestSender {

    public Users(String apiKey) {
        super(apiKey);
    }

    public User validate() {

        Request request = createRequest("/users/validate.json").build();

        try(Response response = execute(request)) {

            return new Gson().fromJson(Objects.requireNonNull(response.body()).string(), User.class);
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }
}
