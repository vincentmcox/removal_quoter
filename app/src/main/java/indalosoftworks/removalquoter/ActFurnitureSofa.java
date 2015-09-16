package indalosoftworks.removalquoter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Activity that provides input for a sofa item into the removal
 */
public class ActFurnitureSofa extends ActionBarActivity {
    //Declarations
    Button twoSofaButton;
    Button threeSofaButton;
    View.OnClickListener twoSofaListener;
    View.OnClickListener threeSofaListener;
    QuoteApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_furniture_sofa);

        //Get reference to the global app variable
        app = (QuoteApp) getApplicationContext();

        //Get button ui references
        twoSofaButton = (Button) findViewById(R.id.btn_sofa_two);
        threeSofaButton = (Button) findViewById(R.id.btn_sofa_three);

        //Set up listeners and specity item inputs
        twoSofaListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("Two seater sofa", 152, 110, 89, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };
        threeSofaListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.addItemDetails("Three seater sofa", 213, 110, 89, 0, 1);
                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };

        //attach listeners to buttons
        twoSofaButton.setOnClickListener(twoSofaListener);
        threeSofaButton.setOnClickListener(threeSofaListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_furniture_sofa, menu);
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
