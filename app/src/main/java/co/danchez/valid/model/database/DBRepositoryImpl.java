package co.danchez.valid.model.database;

import android.content.Context;
import android.util.Log;

import co.danchez.valid.R;
import co.danchez.valid.presenter.TopTracksPresenter;

public class DBRepositoryImpl implements DataBaseRepository {

    private final TopTracksPresenter tracksPresenter;

    public DBRepositoryImpl(TopTracksPresenter tracksPresenter) {
        this.tracksPresenter = tracksPresenter;
    }

    @Override
    public void saveDataInDB(String data, int position, Context context) {
        SQLiteDBAdapter sqLiteDBAdapter = new SQLiteDBAdapter(context);
        if (sqLiteDBAdapter.getData(position).isEmpty()) {
            long identity = sqLiteDBAdapter.insertData(data, position);
            if (identity < 0) {
                Log.d(context.getString(R.string.error), context.getString(R.string.error_message));
            }
        } else {
            sqLiteDBAdapter.updateData(data, position);
        }
    }

    @Override
    public void getDataFromDB(Context context, int position) {
        SQLiteDBAdapter sqLiteDBAdapter = new SQLiteDBAdapter(context);
        String data = sqLiteDBAdapter.getData(position);
        if (!data.isEmpty()) {
            tracksPresenter.showDataFromDB(data, position);
        } else {
            tracksPresenter.showErrorGetTop();
        }
    }
}
