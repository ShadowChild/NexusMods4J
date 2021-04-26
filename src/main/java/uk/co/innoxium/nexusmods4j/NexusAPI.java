package uk.co.innoxium.nexusmods4j;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;

@RequiredArgsConstructor
public class NexusAPI {

    private final String apiKey;
    private OkHttpClient client;


    public Get get() {

        return new Get(apiKey);
    }

    public Post post() {

        return new Post(apiKey);
    }
}
