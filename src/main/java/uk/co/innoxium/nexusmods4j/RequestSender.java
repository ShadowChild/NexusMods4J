package uk.co.innoxium.nexusmods4j;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;

@RequiredArgsConstructor
public class RequestSender {

    final String apiKey;
    private OkHttpClient client;

    /**
     * A Util method for getting a response from a request.
     * @param request - The request to get a response from
     * @return - The Response from the server
     * @throws IOException - An Exception may be thrown for timeout, invalid api call, etc. See {@link Call#execute()} for more info.
     */
    @NotNull
    Response execute(@NotNull Request request) throws IOException {

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
    Request.Builder createRequest(@NotNull String apiCall) {

        return new Request.Builder()
                .url(getNexusUrl(apiCall))
                .addHeader("apiKey", apiKey);
    }

    @SneakyThrows
    private URL getNexusUrl(@NotNull String apiCall) {

        return new URL("https://api.nexusmods.com/v1" + apiCall);
    }
}
