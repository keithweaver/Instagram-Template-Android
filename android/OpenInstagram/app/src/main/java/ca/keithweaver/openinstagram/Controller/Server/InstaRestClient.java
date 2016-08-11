package ca.keithweaver.openinstagram.Controller.Server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
/**
 * Created by keithweaver on 16-08-11.
 */
public class InstaRestClient {
    private static InstaAPI sSIClient;

    public static InstaAPI get(){
        if (sSIClient == null){
            setupClient();
        }
        return sSIClient;
    }

    private static void setupClient(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Object.class, new NaturalDeserializer())
                .create();

        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(InstaAPI.ENDPOINT);
        builder.setClient(new OkClient(getHttpClient()));
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        builder.setConverter(new GsonConverter(gson));

        RestAdapter restAdapter = builder.build();
        sSIClient = restAdapter.create(InstaAPI.class);
    }

    private static OkHttpClient getHttpClient(){
        OkHttpClient client = new OkHttpClient();
        return client;
    }
}
