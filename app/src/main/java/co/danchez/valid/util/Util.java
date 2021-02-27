package co.danchez.valid.util;

import android.app.AlertDialog;
import android.content.Context;

import co.danchez.valid.R;

public class Util {

    public static final String methodArtists = "geo.gettopartists";
    public static final String country = "colombia";
    public static final String api_key = "829751643419a7128b7ada50de590067";
    public static final String format = "json";
    public static final String BASE_URL = "https://ws.audioscrobbler.com/";

    public static void showAlertDialogError(Context context, String title, String subtitle) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(subtitle)
                .setPositiveButton(context.getString(R.string.aceptar), (dialog, which) -> {
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
