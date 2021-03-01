package co.danchez.valid.presenter;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.danchez.valid.model.database.DataBaseInteractor;
import co.danchez.valid.model.database.DataBaseInteractorImpl;
import co.danchez.valid.model.topArtists.models.Artist;
import co.danchez.valid.model.topArtists.TopArtistsInteractor;
import co.danchez.valid.model.topArtists.TopArtistsInteractorImpl;
import co.danchez.valid.model.topTracks.models.Track;
import co.danchez.valid.view.interfaces.TraineeView;

public class TopArtistsPresenterImpl implements TopArtistsPresenter, TopTracksPresenter {

    private final TraineeView traineeView;
    private final TopArtistsInteractor topArtistsInteractor = new TopArtistsInteractorImpl(this);
    private final DataBaseInteractor dataBaseInteractor = new DataBaseInteractorImpl(this);
    private final Context context;

    public TopArtistsPresenterImpl(TraineeView traineeView, Context context) {
        this.traineeView = traineeView;
        this.context = context;
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

    @Override
    public void getTopTracks() {

    }

    @Override
    public void getTopArtists() {

    }

    @Override
    public void showTopTracks(List<Track> track) {

    }

    @Override
    public void showErrorGetTop() {

    }

    @Override
    public void saveDataInDB(String data, int position) {
    }

    @Override
    public void showDataFromDB(String data, int position) {
        try {
            JSONArray jsonArray = new JSONArray(data);
        List<Artist> artists = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            Artist artist = new Gson().fromJson(jsonObject.toString(), Artist.class);
            artists.add(artist);
        }
        traineeView.showTopArtist(artists);
        } catch (JSONException e) {
            e.printStackTrace();
            traineeView.showErrorGetTopArtists();
        }
    }

    @Override
    public void getDataFromDB(int position) {
        dataBaseInteractor.getDataFromDB(context, position);
    }
}
