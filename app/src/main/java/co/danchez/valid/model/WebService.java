package co.danchez.valid.model;

import co.danchez.valid.model.topArtists.models.TopArtistsResponse;
import co.danchez.valid.model.topTracks.models.TopTracksResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {

    @GET("/2.0/")
    Call<TopArtistsResponse> getTopArtists(@Query("method") String method, @Query("country") String country, @Query("api_key") String api_key, @Query("format") String format);

    @GET("/2.0/")
    Call<TopTracksResponse> getTopTracks(@Query("method") String method, @Query("country") String country, @Query("api_key") String api_key, @Query("format") String format);

}
