package indalosoftworks.removalquoter;

/**
 * Class that models an item that can be transported in the real world.
 */
public class MoveItem {
    private double cube;
    private double width;
    private double height;
    private double depth;
    private String itemName;
    private int amount;
    private boolean isFragile;
    private int _id;


    public MoveItem()
    {
        super();
    }
    public MoveItem(double aCube, double aWidth, double aHeight, double aDepth,
                    String name, int anAmount, boolean isFragile)
    {
        super();
        cube = aCube;
        width = aWidth;
        height = aHeight;
        depth = aDepth;
        itemName = name;
        amount = anAmount;
        this.isFragile = isFragile;
    }

    public double getCube()
    {
        return cube;
    }

    public String getItemName()
    {
        return itemName;
    }

    public boolean isFragile()
    {
        return isFragile;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setIsFragile(boolean isFragile) {
        this.isFragile = isFragile;
    }
    public void setItemName(String newName) {
        this.itemName = newName;
    }
    public void setCube(double cubage)
    {
        this.cube = cubage;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    /**
     * Gets an integer value from a boolean. If true = 1, if false = 0
     * @return 1 if true, 2 if false
     */
    public int getIntFragile()
    {
        int result = 0;
        if(isFragile())
            result =1;
        return result;
    }
    public void setFragileFromInt(int binary)
    {
        if(binary == 1)
            setIsFragile(true);
        else
            setIsFragile(false);
    }

    @Override
    public String toString()
    {
        return String.valueOf(get_id()) + ". " + getItemName();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDepth() {
        return depth;
    }

    public int getAmount() {
        return amount;
    }
}
