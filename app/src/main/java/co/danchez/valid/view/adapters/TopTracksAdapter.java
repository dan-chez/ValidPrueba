package co.danchez.valid.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.danchez.valid.R;
import co.danchez.valid.model.topArtists.models.Artist;
import co.danchez.valid.model.topTracks.models.Track;
import co.danchez.valid.util.Util;

public class TopTracksAdapter extends RecyclerView.Adapter<TopTracksAdapter.CustomViewHolder>{

    private List<Track> tracksList;
    Context context;

    public TopTracksAdapter(Context context, List<Track> trackList) {
        this.tracksList = trackList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Track trackDto = tracksList.get(position);
        holder.tv_name.setText(trackDto.getName());
        holder.tv_listeners.setText(String.format("%s %s", context.getString(R.string.listeners), trackDto.getListeners()));
        holder.tv_duration.setText(String.format("%s %s", context.getString(R.string.duration), Util.durationTime(trackDto.getDuration())));
        holder.tv_mbid.setText(trackDto.getMbid());
        Picasso
                .get()
                .load(trackDto.getImage().get(3).getText())
                .error(R.drawable.ic_logo)
                .into(holder.iv_icon);
        holder.cv_container.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(trackDto.getUrl()));
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return tracksList.size();
    }

    @NonNull
    @Override
    public TopTracksAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.layout_tracks_adapter, null);
        return new TopTracksAdapter.CustomViewHolder(view);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_icon)
        ImageView iv_icon;
        @BindView(R.id.cv_container)
        CardView cv_container;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_listeners)
        TextView tv_listeners;
        @BindView(R.id.tv_mbid)
        TextView tv_mbid;
        @BindView(R.id.tv_duration)
        TextView tv_duration;

        CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void filterList(List<Track> trackList) {
        this.tracksList = trackList;
        notifyDataSetChanged();
    }

}
