package co.danchez.valid.model.topArtists;

import android.util.Log;

import co.danchez.valid.model.RetrofitClientInstance;
import co.danchez.valid.model.WebService;
import co.danchez.valid.model.topArtists.models.TopArtistsResponse;
import co.danchez.valid.presenter.TopArtistsPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static co.danchez.valid.util.Util.*;

public class TopArtistsRepositoryImpl implements TopArtistsRepository {

    private final TopArtistsPresenter artistPresenter;

    public TopArtistsRepositoryImpl(TopArtistsPresenter artistPresenter) {
        this.artistPresenter = artistPresenter;
    }

    @Override
    public void getTopArtist() {
        WebService webService = RetrofitClientInstance.getRetrofitInstance().create(WebService.class);
        Call<TopArtistsResponse> callTopArtists = webService.getTopArtists(methodArtists, country, api_key, format);
        callTopArtists.enqueue(new Callback<TopArtistsResponse>() {
            @Override
            public void onResponse(Call<TopArtistsResponse> call, Response<TopArtistsResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getTopartists() != null && response.body().getTopartists().getArtist() != null
                && response.body().getTopartists().getArtist().size() > 0) {
                    artistPresenter.showTopArtist(response.body().getTopartists().getArtist());
                } else {
                    artistPresenter.showErrorGetTopArtists();
                }
            }

            @Override
            public void onFailure(Call<TopArtistsResponse> call, Throwable t) {
                Log.e("TAG", "onFailure: ", t.getCause());
                artistPresenter.showErrorGetTopArtists();
            }
        });
    }
}
