package co.danchez.valid.model.database;

import android.content.Context;

public interface DataBaseRepository {

    void saveDataInDB(String data, int position, Context context);

    void getDataFromDB(Context context, int position);

}
