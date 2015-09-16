package indalosoftworks.removalquoter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Activity that specifies a way to add a fridge type object to the removal.
 */
public class ActFurnitureFridge extends ActionBarActivity {
    //declarations
    Button twoDoorButton;
    Button fridgeFreezerButton;
    Button underCounterButton;
    View.OnClickListener twoDoorListener;
    View.OnClickListener fridgeFreezerListener;
    View.OnClickListener underCounterListener;
    QuoteApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_furniture_fridge);

        //get reference to the global app variable
        app = (QuoteApp) getApplicationContext();

        //get ui references
        twoDoorButton = (Button) findViewById(R.id.btn_two_door_fridge);
        fridgeFreezerButton = (Button) findViewById(R.id.btn_fridge_freezer);
        underCounterButton = (Button) findViewById(R.id.btn_under_counter_fridge);

        //Initialise listeners and speficy inputs for each item
        twoDoorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("Two door fridge freezer", 90, 180, 70, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };
        fridgeFreezerListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("Fridge freezer", 54, 180, 60, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };
        underCounterListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("Under counter fridge", 55, 85, 57, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };

        //Link listeners to buttons
        twoDoorButton.setOnClickListener(twoDoorListener);
        fridgeFreezerButton.setOnClickListener(fridgeFreezerListener);
        underCounterButton.setOnClickListener(underCounterListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_furniture_fridge, menu);
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
