package co.danchez.valid.model.topArtists;

import co.danchez.valid.presenter.TopArtistsPresenter;
import co.danchez.valid.util.Util;
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
        Call<TopartistsResponse> callTopArtists = webService.getTopArtists(methodArtists, country, api_key, format);
        callTopArtists.enqueue(new Callback<TopartistsResponse>() {
            @Override
            public void onResponse(Call<TopartistsResponse> call, Response<TopartistsResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getTopartists() != null && response.body().getTopartists().getArtist() != null
                && response.body().getTopartists().getArtist().size() > 0) {
                    artistPresenter.showTopArtist(response.body().getTopartists().getArtist());
                } else {
                    artistPresenter.showErrorGetTopArtists();
                }
            }

            @Override
            public void onFailure(Call<TopartistsResponse> call, Throwable t) {
                artistPresenter.showErrorGetTopArtists();
            }
        });
    }
}
