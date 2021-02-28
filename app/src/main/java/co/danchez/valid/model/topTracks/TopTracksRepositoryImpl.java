package co.danchez.valid.model.topTracks;

import co.danchez.valid.model.RetrofitClientInstance;
import co.danchez.valid.model.WebService;
import co.danchez.valid.model.topTracks.models.TopTracksResponse;
import co.danchez.valid.presenter.TopTracksPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static co.danchez.valid.util.Util.api_key;
import static co.danchez.valid.util.Util.country;
import static co.danchez.valid.util.Util.format;
import static co.danchez.valid.util.Util.methodTracks;

public class TopTracksRepositoryImpl implements TopTracksRepository {

    private final TopTracksPresenter topTracksPresenter;

    public TopTracksRepositoryImpl(TopTracksPresenter topTracksPresenter) {
        this.topTracksPresenter = topTracksPresenter;
    }

    @Override
    public void getTopTracks() {
        WebService webService = RetrofitClientInstance.getRetrofitInstance().create(WebService.class);
        Call<TopTracksResponse> callTopArtists = webService.getTopTracks(methodTracks, country, api_key, format);
        callTopArtists.enqueue(new Callback<TopTracksResponse>() {
            @Override
            public void onResponse(Call<TopTracksResponse> call, Response<TopTracksResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getTracks() != null && response.body().getTracks().getTrack() != null
                && response.body().getTracks().getTrack().size() > 0) {
                    topTracksPresenter.showTopTracks(response.body().getTracks().getTrack());
                } else {
                    topTracksPresenter.showErrorGetTop();
                }
            }

            @Override
            public void onFailure(Call<TopTracksResponse> call, Throwable t) {
                topTracksPresenter.showErrorGetTop();
            }
        });

    }
}
