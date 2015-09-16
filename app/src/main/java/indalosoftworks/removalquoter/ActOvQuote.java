package indalosoftworks.removalquoter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Activity that displays the quote items and the final quoted price for the removal.
 */
public class ActOvQuote extends ActionBarActivity {
    //declarations
    ListView removalList;
    ArrayAdapter<MoveItem> moveListAdapter;
    QuoteApp app;
    TextView showPrice;
    Button menuButton, clientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_ov_quote);

        //Get reference to the global app variable
        app = (QuoteApp) getApplicationContext();

        //get ui references to buttons
        menuButton = (Button) findViewById(R.id.btn_Ov_MainMenu);
        clientButton = (Button) findViewById(R.id.btn_Ov_ShowClient);


        //Populate the ListView
        populateRemovalList();
        //Get textView callback
        showPrice = (TextView) findViewById(R.id.textView_price);
        //format for the decimal price display
        DecimalFormat priceFormat = new DecimalFormat("#.##");
        priceFormat.setRoundingMode(RoundingMode.HALF_UP);
        //set the display string
        showPrice.setText("£" + priceFormat.format(app.getQuotePrice()));

        //Set up listeners
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        // Test button listener
        clientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(app.getClient() != null) {
                    Toast toast = Toast.makeText(getBaseContext(), app.getClient().getName() + ": " +
                            app.getClient().getMobileNumber() + " :: " + app.getClient().getEmailAddress(), Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    Toast toast = Toast.makeText(getBaseContext(), "There is no Client object", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_ov_quote, menu);
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
     * Populates the removal list by instantiating a new ArrayAdapter.
     */
    public void populateRemovalList() {
        removalList = (ListView) findViewById(R.id.listView_removal);
        moveListAdapter = new ArrayAdapter<MoveItem>(this, android.R.layout.simple_list_item_1, app.getList());
        removalList.setAdapter(moveListAdapter);

    }
}
