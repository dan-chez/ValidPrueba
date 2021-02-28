package co.danchez.valid.model.topTracks;

import co.danchez.valid.presenter.TopTracksPresenter;

public class TopTracksInteractorImpl implements TopTracksInteractor{

    private final TopTracksRepository apiRepository;

    public TopTracksInteractorImpl(TopTracksPresenter topTracksPresenter) {
        this.apiRepository = new TopTracksRepositoryImpl(topTracksPresenter);
    }

    @Override
    public void getTopTracks() {
        apiRepository.getTopTracks();
    }
}
