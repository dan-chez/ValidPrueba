package co.danchez.valid.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.danchez.valid.R;
import co.danchez.valid.model.topArtists.models.Artist;
import co.danchez.valid.presenter.TopArtistsPresenter;
import co.danchez.valid.presenter.TopArtistsPresenterImpl;
import co.danchez.valid.util.Util;
import co.danchez.valid.view.adapters.TopArtistsAdapter;
import co.danchez.valid.view.interfaces.TraineeView;

public class TraineeActivity extends AppCompatActivity implements TraineeView {

    private TopArtistsPresenter topArtistsPresenter;
    @BindView(R.id.rv_artists)
    RecyclerView rv_artists;
    @BindView(R.id.rl_loading)
    RelativeLayout rl_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee);
        ButterKnife.bind(this);

        topArtistsPresenter = new TopArtistsPresenterImpl(this);

        getTopArtist();

    }

    @Override
    public void getTopArtist() {
        rl_loading.setVisibility(View.VISIBLE);
        topArtistsPresenter.getTopArtist();
    }

    @Override
    public void showTopArtist(List<Artist> artist) {
        TopArtistsAdapter topArtistsAdapter = new TopArtistsAdapter(TraineeActivity.this, artist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TraineeActivity.this);
        rv_artists.setLayoutManager(layoutManager);
        rv_artists.setAdapter(topArtistsAdapter);
        rl_loading.setVisibility(View.GONE);
    }

    @Override
    public void showErrorGetTopArtists() {
        rl_loading.setVisibility(View.GONE);
        Util.showAlertDialogError(TraineeActivity.this, getString(R.string.requestErrorTitle), getString(R.string.requestErrorSubtitle));
    }
}