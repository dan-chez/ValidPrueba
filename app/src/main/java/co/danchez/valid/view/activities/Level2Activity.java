package co.danchez.valid.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.danchez.valid.R;
import co.danchez.valid.model.topArtists.models.Artist;
import co.danchez.valid.model.topTracks.models.Track;
import co.danchez.valid.presenter.TopTracksPresenter;
import co.danchez.valid.presenter.TopTracksPresenterImpl;
import co.danchez.valid.util.Util;
import co.danchez.valid.view.adapters.ViewPagerAdapter;
import co.danchez.valid.view.interfaces.Level2View;

public class Level2Activity extends AppCompatActivity implements Level2View {

    @BindView(R.id.layout_dots)
    LinearLayout layout_dots;
    @BindView(R.id.rl_loading)
    RelativeLayout rl_loading;
    @BindView(R.id.vp_fm)
    ViewPager vp_fm;

    private TopTracksPresenter topTracksPresenter;
    private ViewPagerAdapter viewPagerAdapter;
    private List<Track> tracks;
    private List<Artist> artists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);
        ButterKnife.bind(this);

        Util.addBottomDots(layout_dots, 0, Level2Activity.this);

        topTracksPresenter = new TopTracksPresenterImpl(this, Level2Activity.this);

        vp_fm.addOnPageChangeListener(viewPagerPageChangeListener);

        getTopArtists();

    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            Util.addBottomDots(layout_dots, position, Level2Activity.this);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };

    @Override
    public void getTopArtists() {
        rl_loading.setVisibility(View.VISIBLE);
        topTracksPresenter.getTopArtists();
    }

    @Override
    public void showTopArtists(List<Artist> artist) {
        this.artists = artist;
        getTopTracks();
    }

    @Override
    public void getTopTracks() {
        rl_loading.setVisibility(View.VISIBLE);
        topTracksPresenter.getTopTracks();
    }

    @Override
    public void showTopTracks(List<Track> track) {
        this.tracks = track;
        viewPagerAdapter = new ViewPagerAdapter(Level2Activity.this, tracks, artists);
        vp_fm.setAdapter(viewPagerAdapter);
        rl_loading.setVisibility(View.GONE);
    }

    @Override
    public void showErrorGetTop() {
        rl_loading.setVisibility(View.GONE);
        Util.showAlertDialogError(Level2Activity.this, getString(R.string.requestErrorTitle), getString(R.string.requestErrorSubtitle));
    }
}