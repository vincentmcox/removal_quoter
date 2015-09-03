package indalosoftworks.removalquoter;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Class to extend Application in order to maintain session storage of
 * data that needs not to persist between app runs.
 *
 *
 * Created by Vincent on 26/07/2015.
 */
public class QuoteApp extends Application
{


    private InventoryDataSource dataSource;
    private Client client = null;
    private Removal removal;
    private boolean isQuoted;
    private HashMap<String, Double> priceList;



    public QuoteApp()
    {
        //database = new DBHelper(this, this);
        removal = new Removal();

        priceList = new HashMap<>();
        priceList.put("price_per_meter", 130.0); //PRice per cubic meter of normal items
        priceList.put("price_per_porter_minimum", 60.0); //Base cost of getting a porter
        priceList.put("price_per_porter_hourly", 12.0); // Hourly cost of getting a porter
        priceList.put("price_per_fragile_meter", 195.0); // Price per cubic meter of fragile items
        priceList.put("price_per_overnight_stay", 75.0); // Price levied for an overnight stay
        priceList.put("price_per_mile", 0.5); //Price per mile from a depot for over 50
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        dataSource = new InventoryDataSource(this);
        try {
            dataSource.open();
        }
        catch (SQLException e)
        {
            Log.w(e.toString() + ":", "Exception thrown at Application onCreate.");
        }

    }



//    public void setPriceDetails()
//    {
//        database.addPricingDetails("price_per_meter", 130); //PRice per cubic meter of normal items
//        database.addPricingDetails("price_per_porter_minimum", 60); //Base cost of getting a porter
//        database.addPricingDetails("price_per_porter_hourly", 12); // Hourly cost of getting a porter
//        database.addPricingDetails("price_per_fragile_meter", 195); // Price per cubic meter of fragile items
//        database.addPricingDetails("price_per_overnight_stay", 75); // Price levied for an overnight stay
//        database.addPricingDetails("price_per_mile", 0.5); //Price per mile from a depot for over 50
//
//        app.setPriceList(database.getPricingListFromDatabase());
//    }

    //May need to pass a context into here
    public void saveClientToPreferences(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences("clientStored", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", client.getName());
        editor.putString("add1", client.getAddress1());
        editor.putString("add2", client.getAddress2());
        editor.putInt("fromRegionCode", client.getFromRegionCode());
        editor.putInt("fromCountryCode", client.getFromCountryCode());
        editor.putInt("toRegionCode", client.getToRegionCode());
        editor.putInt("toCountryCode", client.getToCountryCode());
        editor.putString("mobileNumber", client.getMobileNumber());
        editor.putString("emailAddress", client.getEmailAddress());
        editor.commit();
    }

    //May need to pass a context again
    public void getClientFromPreferences(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences("clientStored", Context.MODE_PRIVATE);
        client = new Client();

        client.setName(prefs.getString("name", ""));
        client.setAddress1(prefs.getString("add1", ""));
        client.setAddress2(prefs.getString("add2", ""));
        client.setFromRegionCode((prefs.getInt("fromRegionCode", 0)));
        client.setFromCountryCode((prefs.getInt("fromCountryCode", 0)));
        client.setToRegionCode((prefs.getInt("ToRegionCode", 0)));
        client.setToCountryCode((prefs.getInt("ToCountryCode", 0)));
        client.setMobileNumber(prefs.getString("mobileNumber", ""));
        client.setEmailAddress(prefs.getString("emailAddress)", ""));
    }


    private boolean loggedIn;           // is the user logged in or not.

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public void setLoggedIn(boolean bool){
        loggedIn = bool;
    }


    /**
     * Sends the client details to the Client held in this singleton
     * @param name client name
     * @param email email address
     * @param add1 address line one
     * @param add2 address line 2
     * @param fromRegion region: north, south, west, east
     * @param fromCountry uk or spain
     * @param toRegion region: north, south, west east
     * @param toCountry UK or SPAIN
     * @param mobNumber mobile number
     */
    public void setClientDetails(String name, String email,
                                 String add1, String add2,
                                 String fromRegion, String fromCountry,
                                 String toRegion, String toCountry,
                                 String mobNumber){
        int fromCountryCode = getCountryCode(fromCountry);
        int fromRegionCode = getRegionCode(fromRegion);
        int toCountryCode = getCountryCode(toCountry);
        int toRegionCode = getRegionCode(toRegion);
        this.client = new Client(name, add1, add2, fromRegionCode, fromCountryCode,
                toRegionCode, toCountryCode, mobNumber, email);

    }

    /**
     * Method that works out the cubage of an item and inputs the data into the
     * database.
     * @param itemName name of the item
     * @param width width of the item
     * @param height height of the item
     * @param depth depth of the item
     * @param isFragileInt 0 if not fragile, 1 if is fragile
     * @param number amount of the item
     */
    public void addItemDetails(String itemName, double width, double height, double depth,
                                  int isFragileInt, int number)
    {
        boolean isFragile;
        double cube;
        cube = (width*height*depth)/1000000;
        if(isFragileInt == 0)
            isFragile = false;
        else
            isFragile = true;

            MoveItem item = new MoveItem(cube, width, height, depth, itemName, number, isFragile);

        removal.addItem(item);
        dataSource.insertItem(item);
    }

    /**
     * Converts a string representation of a region into an int that can determine the
     * cost more easily
     * @param country string country from input
     * @return int countrycode
     */
    private int getCountryCode(String country)
    {
        int result = -1;
        country = country.toUpperCase();
        if(country.equals("UK"))
            result = 0;
        else if (country.equals("SPAIN"))
            result = 1;
        return result;
    }

    /**
     * Converts a string representation of a region into an int that can determine the
     * cost more easily.
     * @param region string of the region
     * @return int regionCode
     */
    private int getRegionCode(String region)
    {
        int result = -1;
        region = region.toUpperCase();
        if(region.equals("NORTH"))
            result = 0;
        else if (region.equals("WEST"))
            result = 1;
        else if (region.equals("SOUTH"))
            result = 2;
        else if (region.equals("EAST"))
            result = 3;
        return result;
    }

    public void setQuoted()
    {
        List<MoveItem> emptyTester = removal.getInventory();

        if(emptyTester.isEmpty())
                    isQuoted = false;
        else
                    isQuoted = true;
    }

    boolean getQuoted()
    {
        return isQuoted;
    }

    public double getQuotePrice()
    {
        return removal.getQuoteAmount();
    }

    public void deleteItem(int index)
    {
        removal.removeItem(index);
        dataSource.deleteItem(index);
    }

    public List<MoveItem> getList()
    {
        return removal.getInventory();
    }
    public List<MoveItem> getListFromDatabase()
    {
        return dataSource.getInventory();
    }

    public Client getClient()
    {
        return client;
    }

}
