package co.danchez.valid.model.topArtists;

import co.danchez.valid.presenter.TopArtistsPresenter;

public class TopArtistsInteractorImpl implements TopArtistsInteractor {

    private final TopArtistsRepository apiRepository;

    public TopArtistsInteractorImpl(TopArtistsPresenter artistPresenter) {
        this.apiRepository = new TopArtistsRepositoryImpl(artistPresenter);
    }

    @Override
    public void getTopArtists() {
        apiRepository.getTopArtist();
    }
}
