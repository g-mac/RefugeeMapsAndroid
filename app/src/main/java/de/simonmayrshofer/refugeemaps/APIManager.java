package de.simonmayrshofer.refugeemaps;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.List;

import de.simonmayrshofer.refugeemaps.pojos.Hotspot;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

public class APIManager {

    String endpoint = "http://refugeemaps.eu";

    RestAPI restAPI;

    public APIManager() {

        OkHttpClient client = new OkHttpClient();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        client.interceptors().add(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(endpoint)
                .client(client)
                .build();

        restAPI = retrofit.create(RestAPI.class);
    }

    public Observable<List<Hotspot>> getHotspots(String location) {
        return restAPI.getHotspots(location);
    }

}
