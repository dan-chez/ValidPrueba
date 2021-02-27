package co.danchez.valid.view.interfaces;

import java.util.List;

import co.danchez.valid.model.topArtists.models.Artist;

public interface TraineeView {

    void getTopArtist();

    void showTopArtist(List<Artist> artist);

    void showErrorGetTopArtists();

}
