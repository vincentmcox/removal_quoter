package indalosoftworks.removalquoter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.math.RoundingMode;
import java.text.DecimalFormat;


public class ActOvQuote extends ActionBarActivity {
    //declarations
    ListView removalList;
    ArrayAdapter<MoveItem> moveListAdapter;
    QuoteApp app;
    TextView showPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_ov_quote);
        app = (QuoteApp) getApplicationContext();


        //Populate the ListView
        populateRemovalList();
        //Get textView callback
        showPrice = (TextView) findViewById(R.id.textView_price);
        //format for the decimals
        DecimalFormat priceFormat = new DecimalFormat("#.##");
        priceFormat.setRoundingMode(RoundingMode.HALF_UP);
        //set the display string
        showPrice.setText("£" + priceFormat.format(app.getQuotePrice()));
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

    public void populateRemovalList() {
        removalList = (ListView) findViewById(R.id.listView_removal);
        moveListAdapter = new ArrayAdapter<MoveItem>(this, android.R.layout.simple_list_item_1, app.getList());
        removalList.setAdapter(moveListAdapter);

    }
}
