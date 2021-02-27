package co.danchez.valid.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
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

public class TopArtistsAdapter extends RecyclerView.Adapter<TopArtistsAdapter.CustomViewHolder> {

    private List<Artist> artistDtos;
    Context context;

    public TopArtistsAdapter(Context context, List<Artist> artistList) {
        this.artistDtos = artistList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.layout_artists_adapter, null);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Artist artist = artistDtos.get(position);
        holder.tv_name.setText(artist.getName());
        holder.tv_listeners.setText(String.format("%s %s", context.getString(R.string.listeners), artist.getListeners()));
        SpannableString spannableString = new SpannableString(artist.getUrl());
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
        holder.tv_url.setText(spannableString);
        Picasso
                .get()
                .load(artist.getImage().get(3).getText())
                .error(R.drawable.ic_logo)
                .into(holder.iv_icon);
        holder.cv_container.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(artist.getUrl()));
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return artistDtos.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.iv_icon)
        ImageView iv_icon;
        @BindView(R.id.cv_container)
        CardView cv_container;
        @BindView(R.id.tv_listeners)
        TextView tv_listeners;
        @BindView(R.id.tv_mbid)
        TextView tv_url;

        CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void filterList(Artist artist) {
        //this.artistDtos = mainDto.getTopartists().getArtist();
        notifyDataSetChanged();
    }

}
