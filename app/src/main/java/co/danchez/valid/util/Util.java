package co.danchez.valid.util;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import co.danchez.valid.R;

public class Util {

    public static final String methodArtists = "geo.gettopartists";
    public static final String methodTracks = "geo.gettoptracks";
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

    public static TextView[] addBottomDots(LinearLayout layout_dots, int currentPage, Context context) {
        TextView[] textViews = new TextView[2];
        layout_dots.removeAllViews();
        for (int i = 0; i < textViews.length; i++) {
            textViews[i] = new TextView(context);
            textViews[i].setText(Html.fromHtml("&#8226;"));
            textViews[i].setTextSize(35);
            textViews[i].setTextColor(Color.TRANSPARENT);
            textViews[i].setBackground((i != currentPage) ?
                    context.getResources().getDrawable(R.drawable.inactive_dot) : context.getResources().getDrawable(R.drawable.active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(30, 0, 30, 0);
            textViews[i].setLayoutParams(params);
            layout_dots.addView(textViews[i]);
        }
        return textViews;
    }

    public static String durationTime(String duration) {
        int time = Integer.parseInt(duration);
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%d:%d", minutes, seconds);
    }

}
