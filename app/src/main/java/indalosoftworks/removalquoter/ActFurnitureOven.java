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
 * Activity to input an oven type item into the removal
 */
public class ActFurnitureOven extends ActionBarActivity {

    //Declarations for ui elements
    Button enterButton;
    View.OnClickListener enterButtonListener;
    EditText heightEntry;
    EditText widthEntry;
    EditText depthEntry;
    int height;
    int depth;
    int width;
    QuoteApp app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_furniture_oven);

        //Get reference to the global app variable
        app = (QuoteApp) getApplicationContext();

        height = -1;
        depth = -1;
        width = -1;

        //get ui references
        enterButton = (Button) findViewById(R.id.btn_enterOven);
        heightEntry = (EditText) findViewById(R.id.height_entryOven);
        widthEntry = (EditText) findViewById(R.id.width_entryOven);
        depthEntry = (EditText) findViewById(R.id.depth_entryOven);

        //set up listener
        enterButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check for empty strings
                if (heightEntry.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(getBaseContext(), "Enter a height", Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    height = Integer.parseInt(heightEntry.getText().toString());
                }
                if (widthEntry.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(getBaseContext(), "Enter a width", Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    width = Integer.parseInt(widthEntry.getText().toString());
                }
                if (depthEntry.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(getBaseContext(), "Enter a depth", Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    depth = Integer.parseInt(depthEntry.getText().toString());
                }

                //Check item dimensions are fit for transport, then input them into the app object.
                if(app.isItemFitForTransport(height, width, depth, getApplicationContext()))
                {
                    app.addItemDetails("Oven", (double)width, (double)height, (double)depth, 0, 1);
                    startActivity(new Intent(getApplicationContext(), ActPQuote.class));
                }
            }
        };
        enterButton.setOnClickListener(enterButtonListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_furniture_oven, menu);
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
