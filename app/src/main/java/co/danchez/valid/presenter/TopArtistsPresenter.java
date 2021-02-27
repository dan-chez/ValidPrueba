package co.danchez.valid.presenter;

import java.util.List;

import co.danchez.valid.model.topArtists.models.Artist;

public interface TopArtistsPresenter {

    void getTopArtist();

    void showTopArtist(List<Artist> artist);

    void showErrorGetTopArtists();

}
