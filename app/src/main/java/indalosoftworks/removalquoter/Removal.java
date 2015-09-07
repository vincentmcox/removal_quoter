package indalosoftworks.removalquoter;

import android.content.ClipData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class that stores data and the logic for processing it
 * to produce a quote.
 *
 */
public class Removal
{
    private List<MoveItem> inventory;
    private double quoteAmount;
    private HashMap<String, Double> priceList;
    private int countryTo;
    private int regionTo;
    private int countryFrom;
    private int regionFrom;

    public Removal()
    {
        super();
        inventory = new ArrayList<>();

    }
    public Removal(HashMap<String, Double> pricings)
    {
        super();
        inventory = new ArrayList<>();
        setPriceList(pricings);
    }

    public void addItem(MoveItem item)
    {
        inventory.add(item);
    }


    /**
     * Method that contains the logic for costing the removal
     */
    private void makeQuote()
    {

        double totalCubage = 0.0;
        double fragileCubage = 0.0;
        double normalCubage = 0.0;
        boolean needPorter = false;
        //iterate over inventory, getting details as you go.
        for(MoveItem item : inventory)
        {
            totalCubage += item.getCube();
            if(item.isFragile())
                fragileCubage += item.getCube() * item.getAmount();
            else
                normalCubage += item.getCube() * item.getAmount();

            if(item.getCube() >= 0.4)
            {
                needPorter = true;
            }

        }
        double price = 0.0;

        //Get the actual price for the meterage
        price += fragileCubage * getPriceList().get("price_per_fragile_meter");
        price += normalCubage * getPriceList().get("price_per_meter");

        //Check to see whether the removal is an overnight's stay away (in north of UK)
        // This will likely need a more complex solution in a commercial application
        // for more accuracy.
        if(getCountryFrom() == 0 && getRegionFrom() == 0
                || getCountryTo() == 0 && getRegionTo() == 0)
        {
            price += getPriceList().get("price_per_overnight_stay");
        }
        if(getCountryTo() == 0 || getCountryTo() == 0) //If in the UK
        {
            if( getRegionTo() == 0 || getRegionFrom() == 0) //if in North
                 price += (279 * getPriceList().get("price_per_mile"));
            else if( getRegionTo() == 1 || getRegionFrom() == 1) //if in West
                price += (107 *getPriceList().get("price_per_mile"));
            else if( getRegionTo() == 3 || getRegionFrom() == 3) //if in East
                price += (68 * getPriceList().get("price_per_mile"));
        }
        if(getCountryTo() == 1 || getCountryTo() == 1) //If in Spain
        {
            if( getRegionTo() == 0 || getRegionFrom() == 0) //if in North
                price += (558 * getPriceList().get("price_per_mile"));
            else if( getRegionTo() == 1 || getRegionFrom() == 1) //if in West
                price += (335 * getPriceList().get("price_per_mile"));
            else if( getRegionTo() == 3 || getRegionFrom() == 3) //if in East
                price += (335 * getPriceList().get("price_per_mile"));
        }


        if(needPorter || totalCubage >= 10.00) {
            price += getPriceList().get("price_per_porter_minimum");
            if (totalCubage >= 14 && totalCubage < 18)
                price += getPriceList().get("price_per_porter_hourly");
            else if(totalCubage >= 18 && totalCubage < 22)
                price += getPriceList().get("price_per_porter_hourly")*2;
            else if(totalCubage >= 22 && totalCubage < 26)
                price += getPriceList().get("price_per_porter_hourly")*3;
            else if(totalCubage >= 26 && totalCubage < 30)
                price += getPriceList().get("price_per_porter_hourly")*4;
            else if(totalCubage >= 30)
                price += getPriceList().get("price_per_porter_hourly")*5;
        }


        quoteAmount = price;
    }

    //setters and getters

    public void setPriceList(HashMap<String, Double> priceList) {
        this.priceList = priceList;
    }
    /**
     * returns the last input item into inventory
     * @return last item in inventory
     */
    public MoveItem getItem()
    {
        int index = 0;
        if(inventory.isEmpty())
            return null;
        else{
            index = inventory.size();
            return inventory.get(index);
        }
    }

    /**
     * returns the item at index
     * @param index index
     * @return MoveItem at index
     */
    public MoveItem getItem(int index)
    {
        return inventory.get(index);
    }

    public void removeItem(int index)
    {
        inventory.remove(index);
    }

    HashMap<String, Double> getPriceList()
    {
        return priceList;
    }

    public int getCountryFrom() {
        return countryFrom;
    }

    public void setCountryFrom(int countryFrom) {
        this.countryFrom = countryFrom;
    }

    public int getRegionTo() {
        return regionTo;
    }

    public void setRegionTo(int regionTo) {
        this.regionTo = regionTo;
    }

    public int getCountryTo() {
        return countryTo;
    }

    public void setCountryTo(int countryTo) {
        this.countryTo = countryTo;
    }

    public int getRegionFrom() {
        return regionFrom;
    }

    public void setRegionFrom(int regionFrom) {
        this.regionFrom = regionFrom;
    }

    public double getQuoteAmount() {
        makeQuote();
        return quoteAmount;
    }

    public List<MoveItem> getInventory()
    {
        return inventory;
    }

    public void setInventory(List<MoveItem> inventoryList)
    {
        inventory = inventoryList;
    }
}
