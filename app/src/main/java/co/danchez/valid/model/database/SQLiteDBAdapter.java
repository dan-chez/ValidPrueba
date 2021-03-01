package co.danchez.valid.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import co.danchez.valid.model.database.DBRepository;

public class SQLiteDBAdapter {

    private DBRepository helper;

    public SQLiteDBAdapter(Context context) {
        helper = new DBRepository(context);
    }

    public long insertData(String json, int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBRepository.ID, id);
        contentValues.put(DBRepository.JSON, json);
        return db.insert(DBRepository.TABLE_NAME, null, contentValues);
    }

    public String getData(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {DBRepository.ID, DBRepository.JSON};
        Cursor cursor = db.query(DBRepository.TABLE_NAME, columns, DBRepository.ID + "=" + id, null, null, null, null);
        String json = "";
        while (cursor.moveToNext()) {
            json = cursor.getString(cursor.getColumnIndex(DBRepository.JSON));
        }
        return json;
    }

    public void updateData(String json, int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBRepository.JSON, json);
        db.update(DBRepository.TABLE_NAME, contentValues, DBRepository.ID + "=" + id, null);
    }

}
