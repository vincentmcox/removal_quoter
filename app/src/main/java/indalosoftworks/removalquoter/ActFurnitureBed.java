package indalosoftworks.removalquoter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ActFurnitureBed extends ActionBarActivity {
    //UI declarations
    Button singleBedButton;
    Button doubleBedButton;
    Button queenBedButton;
    Button kingBedButton;
    View.OnClickListener singleListener;
    View.OnClickListener doubleListener;
    View.OnClickListener queenListener;
    View.OnClickListener kingListener;
    QuoteApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_furniture_bed);

        //Get reference to the global app variable
        app = (QuoteApp) getApplicationContext();

        //get ui references
        singleBedButton =  (Button) findViewById(R.id.btn_bed_single);
        doubleBedButton = (Button) findViewById(R.id.btn_bed_double);
        queenBedButton = (Button) findViewById(R.id.btn_bed_queen);
        kingBedButton = (Button) findViewById(R.id.btn_bed_king);

        //initialise listeners and define inputs based on standard bed sizes
        singleListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("Single bed", 90, 50, 190, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };
        doubleListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("Double bed", 135, 50, 190, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };
        queenListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("Queen size bed", 150, 50, 200, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };
        kingListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("King size bed", 180, 50, 200, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };

        //attach listeners to buttons
        singleBedButton.setOnClickListener(singleListener);
        doubleBedButton.setOnClickListener(doubleListener);
        queenBedButton.setOnClickListener(queenListener);
        kingBedButton.setOnClickListener(kingListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_furniture_bed, menu);
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
