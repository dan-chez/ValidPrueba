package co.danchez.valid.presenter;

import java.util.List;

import co.danchez.valid.model.topArtists.TopArtistsInteractor;
import co.danchez.valid.model.topArtists.TopArtistsInteractorImpl;
import co.danchez.valid.model.topArtists.models.Artist;
import co.danchez.valid.model.topTracks.TopTracksInteractor;
import co.danchez.valid.model.topTracks.TopTracksInteractorImpl;
import co.danchez.valid.model.topTracks.models.Track;
import co.danchez.valid.view.interfaces.Level2View;

public class TopTracksPresenterImpl implements TopTracksPresenter, TopArtistsPresenter {

    private final Level2View level2View;
    private final TopTracksInteractor topTracksInteractor = new TopTracksInteractorImpl(this);
    private final TopArtistsInteractor topArtistsInteractor = new TopArtistsInteractorImpl(this);

    public TopTracksPresenterImpl(Level2View level2View) {
        this.level2View = level2View;
    }

    @Override
    public void getTopTracks() {
        topTracksInteractor.getTopTracks();
    }

    @Override
    public void getTopArtists() {
        topArtistsInteractor.getTopArtists();
    }

    @Override
    public void showTopTracks(List<Track> track) {
        level2View.showTopTracks(track);
    }

    @Override
    public void showErrorGetTop() {
        level2View.showErrorGetTop();
    }

    @Override
    public void getTopArtist() {

    }

    @Override
    public void showTopArtist(List<Artist> artist) {
        level2View.showTopArtists(artist);
    }

    @Override
    public void showErrorGetTopArtists() {
        level2View.showErrorGetTop();
    }
}
