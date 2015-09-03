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


public class ActFurnitureUnit extends ActionBarActivity {

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
        setContentView(R.layout.activity_act_furniture_unit);
        app = (QuoteApp) getApplicationContext();

        height = -1;
        depth = -1;
        width = -1;

        //get ui references
        enterButton = (Button) findViewById(R.id.btn_enterUnit);
        heightEntry = (EditText) findViewById(R.id.height_entryUnit);
        widthEntry = (EditText) findViewById(R.id.width_entryUnit);
        depthEntry = (EditText) findViewById(R.id.depth_entryUnit);

        //set up listener
        enterButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height = Integer.parseInt(heightEntry.getText().toString());
                width = Integer.parseInt(widthEntry.getText().toString());
                depth = Integer.parseInt(depthEntry.getText().toString());
                if(app.isItemFitForTransport(height, width, depth, getApplicationContext()))
                {
                    app.addItemDetails("Unit", (double)width, (double)height, (double)depth, 0, 1);
                    startActivity(new Intent(getApplicationContext(), ActPQuote.class));
                }
            }
        };
        enterButton.setOnClickListener(enterButtonListener);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_furniture_unit, menu);
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
