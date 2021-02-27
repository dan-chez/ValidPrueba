package co.danchez.valid.model.topArtists;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {

    @GET("/2.0/")
    Call<TopartistsResponse> getTopArtists(@Query("method") String method, @Query("country") String country, @Query("api_key") String api_key, @Query("format") String format);

}
