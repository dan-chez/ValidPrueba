package co.danchez.valid.view.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.danchez.valid.R;
import co.danchez.valid.model.topArtists.models.Artist;
import co.danchez.valid.model.topTracks.models.Track;

public class ViewPagerAdapter extends PagerAdapter {

    @BindView(R.id.tv_title_fm)
    TextView tv_title;
    @BindView(R.id.rv_fm)
    RecyclerView rv_fm;
    @BindView(R.id.et_search)
    EditText et_search;

    private List<Track> tracks;
    private List<Artist> artists;
    private Context context;

    private TopArtistsAdapter topArtistsAdapter;
    private TopTracksAdapter topTracksAdapter;

    public ViewPagerAdapter(Context context, List<Track> track, List<Artist> artist) {
        this.tracks = track;
        this.artists = artist;
        this.context = context;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_slider, container, false);
        ButterKnife.bind(this, view);

        switch (position) {
            case 0: {
                tv_title.setText(context.getString(R.string.title_top_artists));
                break;
            }
            case 1: {
                tv_title.setText(context.getString(R.string.title_top_tracks));
                break;
            }
        }
        generateList(position);
        container.addView(view);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString(), position);
            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    private void generateList(int position) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rv_fm.setLayoutManager(layoutManager);
        if (position == 0) {
            topArtistsAdapter = new TopArtistsAdapter(context, artists);
            rv_fm.setAdapter(topArtistsAdapter);
        } else {
            topTracksAdapter = new TopTracksAdapter(context, tracks);
            rv_fm.setAdapter(topTracksAdapter);
        }
    }

    private void filter(String text, int position) {
        switch (position) {
            case 0: {
                List<Artist> artist = new ArrayList<>();
                for (int i = 0; i < artists.size(); i++) {
                    Artist arti = artists.get(i);
                    if (arti.getName().toLowerCase().contains(text)) {
                        artist.add(arti);
                    }
                }
                topArtistsAdapter.filterList(artist);
                break;
            }
            case 1: {
                List<Track> track = new ArrayList<>();
                for (int i = 0; i < tracks.size(); i++) {
                    Track trac = tracks.get(i);
                    if (trac.getName().toLowerCase().contains(text)) {
                        track.add(trac);
                    }
                }
                topTracksAdapter.filterList(track);
                break;
            }
        }
    }

}
