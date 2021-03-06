package indalosoftworks.removalquoter;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Activity that provides a way to add a lamp item to the removal
 */
public class ActFurnitureLamp extends ActionBarActivity {
    RadioGroup radioGroup;
    EditText amountEntry;
    Button enterButton;
    double width, height, depth;
    String nameString;
    View.OnClickListener enterButtonListener;
    QuoteApp app;
    int amountEntered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_furniture_lamp);

        //Get reference to the global app variable
        app = (QuoteApp) getApplicationContext();

        //get ui references
        radioGroup = (RadioGroup)findViewById(R.id.radioGroupLamp);
        amountEntry = (EditText)findViewById(R.id.amount_entry_lamp);
        enterButton = (Button)findViewById(R.id.btn_enter_lamp);

        enterButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountEntered = 0;

                //Check for empty string
                if(amountEntry.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getBaseContext(), "Must enter an amount", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    //get amount
                    amountEntered = Integer.parseInt(amountEntry.getText().toString());
                    //Check that the radio buttons are checked and the amount is correct
                    if (ensureAmountCorrect()) {
                        //Get the radiogroup button checked
                        if (radioGroup.getCheckedRadioButtonId() == R.id.radio_tall_lamp) {
                            width = 35;
                            depth = 35;
                            height = 170;
                            nameString = "Tall lamp";
                        } else if (radioGroup.getCheckedRadioButtonId() == R.id.radio_desk_lamp) {
                            width = 35;
                            depth = 35;
                            height = 50;
                            nameString = "desk lamp";
                        }
                        //Add details to the app object removal
                        app.addItemDetails(nameString, width, height, depth, 1, amountEntered);
                        startActivity(new Intent(getApplicationContext(), ActPQuote.class));

                    }
                }
            }
        };


        //Assign listener
        enterButton.setOnClickListener(enterButtonListener);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_furniture_lamp, menu);
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

    /**
     * Checks that a radio button is checked and that the amount is appropriate
     * for a removal. If false, displays a Toast message with information as to
     * what went wrong.
     * @return true if input is ok, false otherwise
     */
    private boolean ensureAmountCorrect()
    {
        boolean result = true;

        if(radioGroup.getCheckedRadioButtonId() != R.id.radio_tall_lamp && radioGroup.getCheckedRadioButtonId() != R.id.radio_desk_lamp)
        {
            Toast toast = Toast.makeText(getBaseContext(), "Please select a lamp type", Toast.LENGTH_LONG);
            toast.show();
            result = false;
        }
        if (amountEntered <= 0) {
            Toast toast = Toast.makeText(getBaseContext(), "Must enter a valid amount", Toast.LENGTH_SHORT);
            toast.show();
            result = false;
        } else if (amountEntered >= 15) {
            Toast toast = Toast.makeText(getBaseContext(), "Too many lamps! please phone us!", Toast.LENGTH_LONG);
            toast.show();
            result = false;
    }
        return result;
    }
}
