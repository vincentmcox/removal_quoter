package indalosoftworks.removalquoter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ActVehicle extends ActionBarActivity {
    //Declarations
    Button quadBikeButton, bicycleButton, scooterButton, motorbikeButton;
    View.OnClickListener quadBikeListener, bicycleListener, scooterListener, motorbikeListener;
    QuoteApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_vehicle);
        app = (QuoteApp) getApplicationContext();

        //Get ui refs
        quadBikeButton = (Button) findViewById(R.id.btn_quadBike);
        bicycleButton = (Button) findViewById(R.id.btn_bicycle);
        scooterButton = (Button) findViewById(R.id.btn_mobilityScooter);
        motorbikeButton = (Button) findViewById(R.id.btn_motorbike);

        //Set up listeners
        quadBikeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("Quad Bike", 92, 101, 167, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));

            }
        };
        bicycleListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("Bicycle", 35, 100, 180, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };
        scooterListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("Mobility Scooter", 75, 150, 130, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };
        motorbikeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("Motorbike", 81, 100, 208, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };

        //Attach listeners to views
        quadBikeButton.setOnClickListener(quadBikeListener);
        bicycleButton.setOnClickListener(bicycleListener);
        motorbikeButton.setOnClickListener(motorbikeListener);
        scooterButton.setOnClickListener(scooterListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_vehicle, menu);
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
