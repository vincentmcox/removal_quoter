package indalosoftworks.removalquoter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;

/**
 * First activity started when the app is started out. Fucntions as a navigation menu and
 * performs some setup and logic for the app.
 */
public class MainActivity extends ActionBarActivity {

    Button produceQuoteButton;
    Button seeQuoteButton;
    Button clearClientButton;
    View.OnClickListener produceQuoteButtonListener;
    View.OnClickListener seeQuoteButtonListener;
    View.OnClickListener clientListener;
    QuoteApp app;
    private static Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get application context
        appContext = getApplicationContext();

        //set up the singleton.
        app = new QuoteApp();
        app = (QuoteApp) getApplicationContext();

        // check if there is a client || This code is crashing the app for some reason I cannot work out.
        // Apparently the database object is not instantiated before the .getWriteableDatabase() method
        // is called on the reference, but database instantiation happens as part of the QuoteApp onCreate()
        // method. When that database instantiation method is passed to the constructor for QuoteApp, the
        // same issue occurs.
//        if(app.getClient() == null)
//        {
//            //attempt to get the client from tha database
//            app.getClientFromDatabase();
//
//        }

            // Set up references to the on-screen buttons
            produceQuoteButton = (Button) findViewById(R.id.btn_ProduceQuote);
            seeQuoteButton = (Button) findViewById(R.id.btn_SeeQuote);
            clearClientButton = (Button) findViewById(R.id.btn_clearClient);

            // Create listeners for the buttons
            produceQuoteButtonListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //check if preferences populated client with default value.
                    if(app.getClient() == null) //TODO test this logic
                    {
                        //go to the client details entry Activity, ActNQuote
                        startActivity(new Intent(getApplicationContext(), ActNQuote.class));
                    }
                    else{
                        startActivity(new Intent(getApplicationContext(), ActPQuote.class));
                    }

                }
            };
            seeQuoteButtonListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (app.getQuoted())
                        startActivity(new Intent(getApplicationContext(), ActOvQuote.class));
                    else
                    {
                        Toast toast = Toast.makeText(getBaseContext(), "No quoted removal yet!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            };
        clientListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.removeClientFromApp();
                Toast toast = Toast.makeText(getBaseContext(), "Client details removed.", Toast.LENGTH_LONG);
                toast.show();
            }
        };




            // Assign the listeners to the buttons.
            produceQuoteButton.setOnClickListener(produceQuoteButtonListener);
            seeQuoteButton.setOnClickListener(seeQuoteButtonListener);
        clearClientButton.setOnClickListener(clientListener);
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public static Context getAppContext()
    {
        return appContext;
    }

}
