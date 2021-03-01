package co.danchez.valid.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBRepository  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "geoTop";
    public static final String TABLE_NAME = "json";
    public static final String ID = "id";
    public static final String JSON = "json";
    public static final int DATABASE_VERSION = 1;
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY, " + JSON + " TEXT NOT NULL);";

    public DBRepository(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
