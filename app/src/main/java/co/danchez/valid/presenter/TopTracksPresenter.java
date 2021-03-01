package co.danchez.valid.presenter;

import java.util.List;

import co.danchez.valid.model.topTracks.models.Track;

public interface TopTracksPresenter {

    void getTopTracks();

    void getTopArtists();

    void showTopTracks(List<Track> track);

    void showErrorGetTop();

    void saveDataInDB(String data, int position);

    void showDataFromDB(String data, int position);

    void getDataFromDB(int position);

}
