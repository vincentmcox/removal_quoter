package indalosoftworks.removalquoter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class InventoryDataSource {

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = {DBHelper.KEY_ID,
            DBHelper.KEY_CUBAGE, DBHelper.KEY_WIDTH,
            DBHelper.KEY_HEIGHT, DBHelper.KEY_DEPTH,
            DBHelper.KEY_ITEM_NAME, DBHelper.KEY_AMOUNT,
            DBHelper.KEY_IS_FRAGILE};

    public InventoryDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertItem(MoveItem item) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_CUBAGE, item.getCube());
        values.put(DBHelper.KEY_WIDTH, item.getWidth());
        values.put(DBHelper.KEY_HEIGHT, item.getHeight());
        values.put(DBHelper.KEY_DEPTH, item.getDepth());
        values.put(DBHelper.KEY_ITEM_NAME, item.getItemName());
        values.put(DBHelper.KEY_AMOUNT, item.getAmount());
        values.put(DBHelper.KEY_IS_FRAGILE, item.getIntFragile());
        database.insert(DBHelper.TABLE_INVENTORY, null, values);
    }

    /**
     * Qiped the inventory table on the database and repopulates it with the supplied
     * MoveItem list.
     * @param list The new inventory
     */
    public void insertNewInventoryList(List<MoveItem> list)
    {
        reInitTable();
        for(MoveItem i : list)
        {
            insertItem(i);
        }
    }
    private void reInitTable()
    {
        dbHelper.reInitialiseTable(database);
    }

    public List<MoveItem> getInventory() {


    List<MoveItem> inventory = new ArrayList<>();

    Cursor cursor = database.query(DBHelper.TABLE_INVENTORY, allColumns,
            null, null, null, null, null);

    cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            MoveItem item = itemFromCursor(cursor);
            inventory.add(item);
            cursor.moveToNext();
        }

        cursor.close();

        return inventory;
}

    private MoveItem itemFromCursor(Cursor cursor)
    {
        MoveItem item = new MoveItem();
        item.set_id(cursor.getInt(0));
        item.setCube(cursor.getDouble(1));
        item.setWidth(cursor.getDouble(2));
        item.setHeight(cursor.getDouble(3));
        item.setDepth(cursor.getDouble(4));
        item.setItemName(cursor.getString(5));
        item.setAmount(cursor.getInt(6));
        item.setFragileFromInt(cursor.getInt(7));

        return item;
    }

    public void deleteItem(int _id)
    {
        database.delete(DBHelper.TABLE_INVENTORY, DBHelper.KEY_ID + " = " + _id, null);
    }

}
