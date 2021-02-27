package co.danchez.valid.model.topArtists.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopartistsResponse {

    @SerializedName("topartists")
    @Expose
    private Topartists topartists;

    public Topartists getTopartists() {
        return topartists;
    }

    public void setTopartists(Topartists topartists) {
        this.topartists = topartists;
    }

}
