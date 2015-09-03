package indalosoftworks.removalquoter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ActAddItem extends ActionBarActivity {

    Button boxButton;
    Button vehicleButton;
    Button furnitureButton;
    Button otherButton;
    View.OnClickListener boxLstnr;
    View.OnClickListener vehicleLstnr;
    View.OnClickListener furnitureLstnr;
    View.OnClickListener otherLstnr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_add_item);

        //get button references
        boxButton = (Button) findViewById(R.id.btn_box);
        vehicleButton = (Button) findViewById(R.id.btn_vehicle);
        furnitureButton = (Button) findViewById(R.id.btn_furniture);
        otherButton = (Button) findViewById(R.id.btn_other);

        //initialise listeners
        boxLstnr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActAddBox.class));
            }
        };
        vehicleLstnr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActVehicle.class));
            }
        };
        furnitureLstnr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActRoomList.class));
            }
        };
        otherLstnr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActAddOther.class));
            }
        };

        boxButton.setOnClickListener(boxLstnr);
        vehicleButton.setOnClickListener(vehicleLstnr);
        furnitureButton.setOnClickListener(furnitureLstnr);
        otherButton.setOnClickListener(otherLstnr);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_add_item, menu);
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
