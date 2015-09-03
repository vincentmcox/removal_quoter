package indalosoftworks.removalquoter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;


public class MainActivity extends ActionBarActivity {

    Button produceQuoteButton;
    Button seeQuoteButton;
    View.OnClickListener produceQuoteButtonListener;
    View.OnClickListener seeQuoteButtonListener;
    FileInputStream fis;
    QuoteApp app;
    private static Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appContext = getApplicationContext();

        // Test to see whether the user is logged in or not, if not then the user is taken to
        // the login/register activity. Information attained from QuoteApp application context.
        //QuoteApp thisAppRun = ((QuoteApp)getApplicationContext());
        //boolean state = thisAppRun.isLoggedIn();



        // Test to see whether the user has entered an email address which has been stored by the
        // system.
//        boolean state = true;
//        try{
//            fis = openFileInput("email_store");
//        }
//        catch(FileNotFoundException e)
//        {
//            state = false;
//        }
//        if(!state)
//        {
//            //Go to ActRegisterLogin
//            startActivity(new Intent(getApplicationContext(), ActRegisterLogin.class));
//        }



        //set up the singleton.
        app = new QuoteApp();

        //check if there is a client
        if(app.getClient() == null)
        {
            //attempt to get the client from preferences
            app.getClientFromPreferences(getApplicationContext());

        }

            // Set up references to the on-screen buttons
            produceQuoteButton = (Button) findViewById(R.id.btn_ProduceQuote);
            seeQuoteButton = (Button) findViewById(R.id.btn_SeeQuote);

            // Create listeners for the buttons
            produceQuoteButtonListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //check if preferences populated client with default value.
                    Toast toast = Toast.makeText(getBaseContext(), "The button is working", Toast.LENGTH_SHORT);
                    toast.show();
                    if(app.getClient().getEmailAddress().equals(""))
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


            // Assign the listeners to the buttons.
            produceQuoteButton.setOnClickListener(produceQuoteButtonListener);
            seeQuoteButton.setOnClickListener(seeQuoteButtonListener);
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
