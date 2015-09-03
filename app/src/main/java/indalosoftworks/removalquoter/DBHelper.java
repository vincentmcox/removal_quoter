package indalosoftworks.removalquoter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Helper file that sets the database schemas and allows them to be instantiated from the
 * rest of the app.
 *
 * Created by Vincent on 26/07/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    //TODO complete re-do!
    public static final String TABLE_INVENTORY = "inventory";
    public static final String KEY_ID = "_id";
    public static final String KEY_ITEM_NAME = "item_name";
    public static final String KEY_IS_FRAGILE = "is_fragile";
    public static final String KEY_CUBAGE = "cubage";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_WIDTH = "width";
    public static final String KEY_DEPTH = "depth";
    public static final String KEY_AMOUNT = "amount";


    private static final String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATION = "create table " +
            TABLE_INVENTORY + "(" + KEY_ID +
            " integer primary key, " +
            KEY_CUBAGE + " REAL, " +
            KEY_WIDTH + " REAL, " +
            KEY_HEIGHT + " REAL, " +
            KEY_DEPTH + " REAL, " +
            KEY_ITEM_NAME + " TEXT, " +
            KEY_AMOUNT + " INTEGER, " +
            KEY_IS_FRAGILE + " INTEGER" + ");";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from " + oldVersion + " to "
         + newVersion + ", which will destroy any old data.");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);
        onCreate(db);
    }

    public void reInitialiseTable(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);
        onCreate(db);
    }

}