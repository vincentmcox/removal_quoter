package indalosoftworks.removalquoter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class ActFurnitureChair extends ActionBarActivity {
    //Declarations
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
        setContentView(R.layout.activity_act_furniture_chair);
        app = (QuoteApp) getApplicationContext();
        nameString = "";

        //get ui references
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupChair);
        amountEntry = (EditText) findViewById(R.id.amount_entry_chair);
        enterButton = (Button) findViewById(R.id.btn_enter_chair);

        enterButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(amountEntry.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(getBaseContext(), "Must enter an amount!", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    amountEntered = Integer.parseInt(amountEntry.getText().toString());
                    if(ensureAmountCorrect()) {
                        switch (radioGroup.getCheckedRadioButtonId()) {
                            case R.id.radio_armchair:
                                width = 89;
                                depth = 89;
                                height = 110;
                                nameString = "Armchair";
                                break;
                            case R.id.radio_desk_chair:
                                width = 46;
                                depth = 46;
                                height = 110;
                                nameString = "Desk Chair";
                                break;
                            case R.id.radio_dining_chair:
                                width = 46;
                                depth = 46;
                                height = 100;
                                nameString = "Dining Chair";
                                break;
                        }
                        app.addItemDetails(nameString, width, height, depth, 0, amountEntered);
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
        getMenuInflater().inflate(R.menu.menu_act_furniture_chair, menu);
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

    private boolean ensureAmountCorrect()
    {
        boolean result = true;

        if(radioGroup.getCheckedRadioButtonId() != R.id.radio_desk_chair
                && radioGroup.getCheckedRadioButtonId() != R.id.radio_dining_chair
                && radioGroup.getCheckedRadioButtonId() != R.id.radio_armchair)
        {
            Toast toast = Toast.makeText(getBaseContext(), "Please select a chair type", Toast.LENGTH_LONG);
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
