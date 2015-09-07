package indalosoftworks.removalquoter;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;


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
    private Client client;
    private Removal removal;
    private boolean isQuoted;
    private HashMap<String, Double> priceList;



    public QuoteApp()
    {
        // Declare and set up the price list for the app - in a full implementation this would
        // be taken from a server database.
        priceList = new HashMap<>();
        priceList.put("price_per_meter", 130.0); //PRice per cubic meter of normal items
        priceList.put("price_per_porter_minimum", 60.0); //Base cost of getting a porter
        priceList.put("price_per_porter_hourly", 12.0); // Hourly cost of getting a porter
        priceList.put("price_per_fragile_meter", 195.0); // Price per cubic meter of fragile items
        priceList.put("price_per_overnight_stay", 75.0); // Price levied for an overnight stay
        priceList.put("price_per_mile", 0.5); //Price per mile from a depot for over 50
        // Must come after price list initialisation as it takes priceList as an arg.
        removal = new Removal(priceList);



        super.onCreate();
    }

    @Override
    public void onCreate()
    {

        dataSource = new InventoryDataSource(this);
        try {
            dataSource.open();
        }
        catch (SQLException e)
        {
            Log.w(e.toString() + ":", "Exception thrown at Application.");
        }
        if(setQuoted())
        {
            removal.setInventory(dataSource.getInventory());
        }

        super.onCreate();

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
    public void saveClientToDatabase(Client aClient)
    {

    }

    //May need to pass a context again
    public void getClientFromDatabase()
    {
        if(dataSource.getClientCount() >= 1)
        {
            this.client = dataSource.getClient();
        }
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

        removal.setCountryFrom(fromCountryCode);
        removal.setRegionFrom(fromRegionCode);
        removal.setCountryTo(toCountryCode);
        removal.setRegionTo(toRegionCode);

        //dataSource.insertClient(client);

    }
    public void removeClientFromApp()
    {
        this.client = null;
        //dataSource.reInitClientTable();
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

        dataSource.insertItem(item);
        removal.setInventory(dataSource.getInventory());

    }

    /**
     * This is an inefficient and unelegant solution to keeping both the Removal and the Database
     * in sync. In reality a better thought-out refactor would be more effective at solving this
     * problem.
     * @param index index of item to be deleted
     */
    public void deleteItem(int index)
    {
        //Some strange logic here designed to prevent IndexOutOfBounds errors, and keep both the
        // database and the Removal object's _id attributes in line with each other.

        // Remove item from Removal first. Since the _id produced by the database starts at 1 and
        // the index of the List begins at 0, subtract one from the index
        removal.removeItem(index-1);
        // Drop the inventory table from the database and repopulate it with the removal inventory
        dataSource.insertNewInventoryList(removal.getInventory());
        // Get the removal inventory back from the database.
        removal.setInventory(dataSource.getInventory());

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

    /**
     * Method used for checking whether there is already a removal quoted, allowing access to the
     * ActOvQuote activity.
     * @return result true if there is an inventory table in the database.
     */
    public boolean setQuoted()
    {
        boolean result = false;
        List<MoveItem> emptyTester = dataSource.getInventory();

        if(emptyTester.size() == 0 || emptyTester == null)
        {
            isQuoted = false;
        }
        else
        {
            isQuoted = true;
            result = true;
        }
        return result;
    }

    boolean getQuoted()
    {
        return isQuoted;
    }

    public double getQuotePrice()
    {
        isQuoted = true;
        return removal.getQuoteAmount();
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
        return this.client;
    }

    /**
     * Checks to see whether the item is fit for transport. For those items whose dimensions are
     * specified by the user.
     * @param height int height of the item
     * @param width int width of the item
     * @param depth int depth of the item
     * @param context application context
     * @return boolean result
     */
    public boolean isItemFitForTransport(int height, int width, int depth, Context context)
    {
        boolean result = true;
        if(height <= 0)
        {
            Toast toast = Toast.makeText(context, "Please enter a height", Toast.LENGTH_LONG);
            toast.show();
            result = false;
        }
        else if(width <= 0)
        {
            Toast toast = Toast.makeText(context, "Please enter a width", Toast.LENGTH_LONG);
            toast.show();
            result = false;
        }
        else if(depth <= 0)
        {
            Toast toast = Toast.makeText(context, "Please enter a depth", Toast.LENGTH_LONG);
            toast.show();
            result = false;
        }
        else if(height >= 320 || width >= 320 || depth >= 320)
        {
            Toast toast = Toast.makeText(context, "Item is too big for transport!", Toast.LENGTH_LONG);
            toast.show();
            result = false;
        }

        return result;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
