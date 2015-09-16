package indalosoftworks.removalquoter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Activity that defines a way to set all variables in MoveItem from the
 * user interface.
 */
public class ActAddOther extends ActionBarActivity {
    EditText itemNameView, itemWidthView, itemHeightView, itemDepthView, itemAmount;
    Switch switchView;
    Button enterButton;
    View.OnClickListener enterLstnr;
    CompoundButton.OnCheckedChangeListener switchListener;
    QuoteApp app;
    int isFragile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_add_other);

        //Get the global QuoteApp reference
        app = (QuoteApp) getApplicationContext();

        isFragile = 0;


        //Get callbacks to views
        itemNameView = (EditText) findViewById(R.id.editText_itemName);
        itemWidthView = (EditText) findViewById(R.id.editText_other_enterWidth);
        itemHeightView = (EditText) findViewById(R.id.editText_other_enterHeight);
        itemDepthView = (EditText) findViewById(R.id.editText_other_enterDepth);
        itemAmount = (EditText) findViewById(R.id.editText_other_itemAmount);
        switchView = (Switch) findViewById(R.id.switch_isFragile);
        enterButton = (Button) findViewById(R.id.btn_other_enterButton);

        enterLstnr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = itemNameView.getText().toString();
                //Set the dimensions to 0, to avoid reference errors later.
                double width = 0.0;
                double height = 0.0;
                double depth = 0.0;
                int amount = 0;
                //Tests for empty string inputs and controls
                if (itemWidthView.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(getBaseContext(), "Enter something for width!", Toast.LENGTH_LONG);
                    toast.show();
                }
                    else {
                    width = Integer.parseInt(itemWidthView.getText().toString());
                    }

                if (itemHeightView.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(getBaseContext(), "Enter something for height!", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    height = Integer.parseInt(itemHeightView.getText().toString());
                    }

                if (itemDepthView.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(getBaseContext(), "Enter something for depth!", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    depth = Integer.parseInt(itemDepthView.getText().toString());
                }
                if (itemAmount.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getBaseContext(), "Enter something for amount!", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    amount = Integer.parseInt(itemAmount.getText().toString());
                }

                //Check item dimensions are fit for transport, then input them into the app object.
                if(isItemFit((int)height,(int)width,(int)depth,amount,itemName))
                {
                    app.addItemDetails(itemName,(double)width,(double)height,(double)depth,isFragile,amount);
                    startActivity(new Intent(getApplicationContext(), ActPQuote.class));
                }
            }
        };
        switchListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    isFragile = 1;
                else
                    isFragile = 0;
            }
        };

        enterButton.setOnClickListener(enterLstnr);
        switchView.setOnCheckedChangeListener(switchListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_add_other, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Tests to see if the item is fit for transport. If item is unfit, returns false and
     * displays an appropriate Toast message.
     * @param height int height of item in cm
     * @param width int width of item in cm
     * @param depth int depth of item in cm
     * @param amount int amount of the item
     * @param name String name of the item
     * @return true if item is fit for transport, false if item is unfit
     */
    private boolean isItemFit(int height, int width, int depth, int amount, String name)
    {
        boolean result = true;

        if(height <= 0)
        {
            Toast toast = Toast.makeText(getBaseContext(), "Please enter a height", Toast.LENGTH_LONG);
            toast.show();
            result = false;
        }
        else if(width <= 0)
        {
            Toast toast = Toast.makeText(getBaseContext(), "Please enter a width", Toast.LENGTH_LONG);
            toast.show();
            result = false;
        }
        else if(depth <= 0)
        {
            Toast toast = Toast.makeText(getBaseContext(), "Please enter a depth", Toast.LENGTH_LONG);
            toast.show();
            result = false;
        }
        else if(height >= 320 || width >= 320 || depth >= 320)
        {
            Toast toast = Toast.makeText(getBaseContext(), "Item is too big for transport!", Toast.LENGTH_LONG);
            toast.show();
            result = false;
        }
        else if (amount <= 0)
        {
            Toast toast = Toast.makeText(getBaseContext(), "You must enter a positive amount!", Toast.LENGTH_LONG);
            toast.show();
            result = false;
        }
        else if (name.equals(""))
        {
            Toast toast = Toast.makeText(getBaseContext(), "Please enter a name for th item.", Toast.LENGTH_LONG);
            toast.show();
            result = false;
        }

        return result;

    }
}
