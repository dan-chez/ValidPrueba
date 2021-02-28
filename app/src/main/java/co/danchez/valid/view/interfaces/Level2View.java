package co.danchez.valid.view.interfaces;

import java.util.List;

import co.danchez.valid.model.topArtists.models.Artist;
import co.danchez.valid.model.topTracks.models.Track;

public interface Level2View {

    void getTopArtists();

    void showTopArtists(List<Artist> artist);

    void getTopTracks();

    void showTopTracks(List<Track> track);

    void showErrorGetTop();

}
