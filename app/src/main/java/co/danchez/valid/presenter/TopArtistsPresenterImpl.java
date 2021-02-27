package co.danchez.valid.presenter;

import java.util.List;

import co.danchez.valid.model.topArtists.Artist;
import co.danchez.valid.model.topArtists.TopArtistsInteractor;
import co.danchez.valid.model.topArtists.TopArtistsInteractorImpl;
import co.danchez.valid.view.interfaces.TraineeView;

public class TopArtistsPresenterImpl implements TopArtistsPresenter {

    private final TraineeView traineeView;
    private final TopArtistsInteractor topArtistsInteractor = new TopArtistsInteractorImpl(this);

    public TopArtistsPresenterImpl(TraineeView traineeView) {
        this.traineeView = traineeView;
    }

    @Override
    public void getTopArtist() {
        topArtistsInteractor.getTopArtists();
    }

    @Override
    public void showTopArtist(List<Artist> artist) {
        traineeView.showTopArtist(artist);
    }

    @Override
    public void showErrorGetTopArtists() {
        traineeView.showErrorGetTopArtists();
    }
}
