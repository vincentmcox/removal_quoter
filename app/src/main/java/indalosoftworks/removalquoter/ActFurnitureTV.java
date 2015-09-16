package indalosoftworks.removalquoter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity to add a TV to the removal. These are not input as fragile items but
 * based on a set price for TV sizes.
 */
public class ActFurnitureTV extends ActionBarActivity {
    //Declarations for ui elements
    Button smallButton, medButton, largeButton, vLargeButton;
    View.OnClickListener smallListener, medListener, largeListener, vLargeListener;
    QuoteApp app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_furniture_tv);

        //Get reference to the global app variable
        app = (QuoteApp) getApplicationContext();

        //get ui references
        smallButton = (Button) findViewById(R.id.btn_smallTV);
        medButton = (Button) findViewById(R.id.btn_medTV);
        largeButton = (Button) findViewById(R.id.btn_largeTV);
        vLargeButton = (Button) findViewById(R.id.btn_vLargeTV);


        //set up listeners and specify item input details
        smallListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("Small TV", 50000, 2, 1, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };
        medListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("MEdium TV", 1000, 250, 1, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));

            }
        };
        largeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("Large TV", 1000, 500, 1, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };
        vLargeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("Very Large TV", 1000, 800,1, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };

        //Link listeners with buttons
        smallButton.setOnClickListener(smallListener);
        medButton.setOnClickListener(medListener);
        largeButton.setOnClickListener(largeListener);
        vLargeButton.setOnClickListener(vLargeListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_furniture_tv, menu);
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
}
