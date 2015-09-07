package indalosoftworks.removalquoter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class ActNQuote extends ActionBarActivity {
    //declarations
    Button gotoButton, testButton;
    EditText name, fromAddress, toAddress, mobilePhone, email;
    Spinner fromCountrySpinner, toCountrySpinner, fromRegionSpinner, toRegionSpinner;
    View.OnClickListener goToListener;
    String fromCountry, toCountry, fromRegion, toRegion, nameString, fromAddressString,
            toAddressString, mobilePhoneString, emailString;
    QuoteApp app;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_nquote);

        app = (QuoteApp) getApplicationContext();

        //If Client is not null, go to ActPQuote
        if(app.getClient() != null)
        {
            startActivity(new Intent(getApplicationContext(), ActPQuote.class));
        }


        //Get ui references
        gotoButton      = (Button) findViewById(R.id.btn_goToPquote);
        testButton      = (Button) findViewById(R.id.btn_TESTCLIENT1);
        name            = (EditText)    findViewById(R.id.editText_name);
        fromAddress     = (EditText)    findViewById(R.id.editText_fromAddress);
        toAddress       = (EditText)    findViewById(R.id.editText_toAddress);
        mobilePhone     = (EditText)    findViewById(R.id.editText_phone);
        email           = (EditText)    findViewById(R.id.editText_email);
        fromCountrySpinner = (Spinner)     findViewById(R.id.spnr_fromcountry);
        toCountrySpinner = (Spinner)     findViewById(R.id.spnr_tocountry);
        fromRegionSpinner = (Spinner)     findViewById(R.id.spnr_fromRegion);
        toRegionSpinner = (Spinner)     findViewById(R.id.spnr_toRegion);

        //instantiate strings
        fromCountry = "";
        toCountry   = "";
        fromRegion  = "";
        toRegion    = "";

        //Set up listeners
        goToListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get values from the EditText elements and spinners
                nameString = name.getText().toString();
                fromAddressString = fromAddress.getText().toString();
                toAddressString = toAddress.getText().toString();
                mobilePhoneString = mobilePhone.getText().toString();
                emailString = email.getText().toString();
                //These not needed because of the spinner listeners.
                //fromCountry = fromCountrySpinner.getSelectedItem().toString();
                //toCountry = toCountrySpinner.getSelectedItem().toString();
                //fromRegion = fromRegionSpinner.getSelectedItem().toString();
                //toRegion = toRegionSpinner.getSelectedItem().toString();

                //Ensure that the fields are filled in
                if(name.equals("") || fromAddress.equals("") || toAddress.equals("")  ||
                        mobilePhone.equals("") || email.equals("") ||
                        (fromCountry.equals("UK") && toCountry.equals("UK")) ||
                        (toCountry.equals("Spain") && fromCountry.equals("Spain"))    )
                {
                    //Don't allow flow - in a full implementation a focus would be used
                    Toast toast = Toast.makeText(getApplicationContext(), "details not filled in",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {

                    //Put client details into database.
                    app.setClientDetails(nameString, emailString, fromAddressString, toAddressString, fromRegion,
                                            fromCountry, toRegion, toCountry, mobilePhoneString);
                    //app.saveClientToDatabase(app.getClient());

                    //go to produce quote activity
                    startActivity(new Intent(getApplicationContext(), ActPQuote.class));
                }
            }
        };
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(app.getClient() != null) {
                    Toast toast = Toast.makeText(getBaseContext(), app.getClient().getName() + ": " +
                            app.getClient().getMobileNumber() + " :: " + app.getClient().getEmailAddress(), Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    Toast toast = Toast.makeText(getBaseContext(), "There is no Client object", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        //listeners for the spinners that assign the selected item string to the respective
        // String variable in the class.
        fromCountrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                fromCountry = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        toCountrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                toCountry = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        fromRegionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                fromRegion = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        toRegionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                toRegion = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        gotoButton.setOnClickListener(goToListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_nquote, menu);
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
