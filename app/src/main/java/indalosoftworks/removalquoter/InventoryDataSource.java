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
    private String[] allInventoryColumns = {DBHelper.KEY_ID,
            DBHelper.KEY_CUBAGE, DBHelper.KEY_WIDTH,
            DBHelper.KEY_HEIGHT, DBHelper.KEY_DEPTH,
            DBHelper.KEY_ITEM_NAME, DBHelper.KEY_AMOUNT,
            DBHelper.KEY_IS_FRAGILE};
    private String[] allClientColumns = {DBHelper.KEY_CLIENT_NAME,
            DBHelper.KEY_ADDRESS_FROM, DBHelper.KEY_ADDRESS_TO,
            DBHelper.KEY_REGION_CODE_FROM, DBHelper.KEY_COUNTRY_CODE_FROM,
            DBHelper.KEY_REGION_CODE_TO, DBHelper.KEY_COUNTRY_CODE_TO,
            DBHelper.KEY_MOBILE_NUMBER, DBHelper.KEY_EMAIL_ADDRESS};

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

    public void insertClient(Client client)
    {
        //database.execSQL(DBHelper.CLIENT_CREATION);
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_CLIENT_NAME, client.getName());
        values.put(DBHelper.KEY_ADDRESS_FROM, client.getAddress1());
        values.put(DBHelper.KEY_ADDRESS_TO, client.getAddress2());
        values.put(DBHelper.KEY_REGION_CODE_FROM, client.getFromCountryCode());
        values.put(DBHelper.KEY_COUNTRY_CODE_FROM, client.getFromCountryCode());
        values.put(DBHelper.KEY_REGION_CODE_TO, client.getToRegionCode());
        values.put(DBHelper.KEY_COUNTRY_CODE_TO, client.getToCountryCode());
        values.put(DBHelper.KEY_MOBILE_NUMBER, client.getMobileNumber());
        values.put(DBHelper.KEY_EMAIL_ADDRESS, client.getEmailAddress());
        database.insert(DBHelper.TABLE_CLIENT, null, values);
    }

    public void reInitClientTable()
    {
        dbHelper.dropClientTable(database);
    }

    /**
     * Wiped the inventory table on the database and repopulates it with the supplied
     * MoveItem list.
     * @param list The new inventory
     */
    public void insertNewInventoryList(List<MoveItem> list)
    {
        reInitInventoryTable();
        for(MoveItem i : list)
        {
            insertItem(i);
        }
    }
    private void reInitInventoryTable()
    {
        dbHelper.reInitialiseInventoryTable(database);
    }


    public List<MoveItem> getInventory() {


    List<MoveItem> inventory = new ArrayList<>();

    Cursor cursor = database.query(DBHelper.TABLE_INVENTORY, allInventoryColumns,
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

    public int getClientCount()
    {
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_CLIENT, null);
        int result = cursor.getCount();
        cursor.close();
        return result;
    }

    public void deleteItem(int _id)
    {
        database.delete(DBHelper.TABLE_INVENTORY, DBHelper.KEY_ID + " = " + _id, null);
    }

    public Client getClient()
    {
        Cursor cursor = database.query(DBHelper.TABLE_CLIENT, allClientColumns,
                null, null, null, null, null);
        cursor.moveToFirst();
        Client client = new Client();
        client.setName(cursor.getString(1));
        client.setAddress1(cursor.getString(2));
        client.setAddress2(cursor.getString(3));
        client.setFromRegionCode(cursor.getInt(4));
        client.setFromCountryCode(cursor.getInt(5));
        client.setToRegionCode(cursor.getInt(6));
        client.setToCountryCode(cursor.getInt(7));
        client.setMobileNumber(cursor.getString(8));
        client.setEmailAddress(cursor.getString(9));

        return client;
    }

}
