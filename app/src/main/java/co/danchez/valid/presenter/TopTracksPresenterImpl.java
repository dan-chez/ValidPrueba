package co.danchez.valid.presenter;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.danchez.valid.model.topArtists.TopArtistsInteractor;
import co.danchez.valid.model.topArtists.TopArtistsInteractorImpl;
import co.danchez.valid.model.topArtists.models.Artist;
import co.danchez.valid.model.topArtists.models.Image;
import co.danchez.valid.model.topTracks.TopTracksInteractor;
import co.danchez.valid.model.topTracks.TopTracksInteractorImpl;
import co.danchez.valid.model.database.DataBaseInteractor;
import co.danchez.valid.model.database.DataBaseInteractorImpl;
import co.danchez.valid.model.topTracks.models.Track;
import co.danchez.valid.view.interfaces.Level2View;

public class TopTracksPresenterImpl implements TopTracksPresenter, TopArtistsPresenter {

    private final Level2View level2View;
    private final Context context;
    private final TopTracksInteractor topTracksInteractor = new TopTracksInteractorImpl(this);
    private final TopArtistsInteractor topArtistsInteractor = new TopArtistsInteractorImpl(this);
    private final DataBaseInteractor dataBaseInteractor = new DataBaseInteractorImpl(this);

    public TopTracksPresenterImpl(Level2View level2View, Context context) {
        this.level2View = level2View;
        this.context = context;
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
    public void saveDataInDB(String data, int position) {
        dataBaseInteractor.saveDataInDB(data, position, context);
    }

    @Override
    public void showDataFromDB(String data, int position) {
        try {
            if (data != null && !data.isEmpty()) {
                if (position == 0) {
                    JSONArray jsonArray = new JSONArray(data);
                    List<Artist> artists = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        Artist artist = new Gson().fromJson(jsonObject.toString(), Artist.class);
                        artists.add(artist);
                    }
                    showTopArtist(artists);
                } else if (position == 1) {
                    JSONArray jsonArray = new JSONArray(data);
                    List<Track> trackList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        Track track = new Gson().fromJson(jsonObject.toString(), Track.class);
                        trackList.add(track);
                    }
                    showTopTracks(trackList);
                } else {
                    showErrorGetTop();
                }
            } else {
                showErrorGetTop();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            showErrorGetTop();
        }
    }

    @Override
    public void getDataFromDB(int position) {
        dataBaseInteractor.getDataFromDB(context, position);
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
