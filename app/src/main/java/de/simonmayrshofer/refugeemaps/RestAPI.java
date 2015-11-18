package de.simonmayrshofer.refugeemaps;

import java.util.List;

import de.simonmayrshofer.refugeemaps.pojos.Hotspot;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface RestAPI {

    String API_HOTSPOTS = "/_api/hotspots/{location}.json";

    @GET(API_HOTSPOTS)
    Observable<List<Hotspot>> getHotspots(@Path("location") String location);

}