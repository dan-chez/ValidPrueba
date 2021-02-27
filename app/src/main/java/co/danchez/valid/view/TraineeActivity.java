package co.danchez.valid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import co.danchez.valid.R;
import co.danchez.valid.model.topArtists.Artist;
import co.danchez.valid.presenter.TopArtistsPresenter;
import co.danchez.valid.presenter.TopArtistsPresenterImpl;
import co.danchez.valid.view.interfaces.TraineeView;

public class TraineeActivity extends AppCompatActivity implements TraineeView {

    private TopArtistsPresenter topArtistsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee);

        topArtistsPresenter = new TopArtistsPresenterImpl(this);

        getTopArtist();

    }

    @Override
    public void getTopArtist() {
        topArtistsPresenter.getTopArtist();
    }

    @Override
    public void showTopArtist(List<Artist> artist) {

    }

    @Override
    public void showErrorGetTopArtists() {

    }
}