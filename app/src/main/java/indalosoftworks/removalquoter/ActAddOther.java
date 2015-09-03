package indalosoftworks.removalquoter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;


public class ActAddOther extends ActionBarActivity {
    EditText itemNameView, itemWidthView, itemHeightView, itemDepthView, itemAmount;
    Switch switchView;
    Button enterButton;
    View.OnClickListener enterLstnr;
    CompoundButton.OnCheckedChangeListener switchListener;
    QuoteApp app;
    int isFragile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_add_other);

        app = (QuoteApp) getApplicationContext();
        isFragile = 0;

        //Get callbacks to views
        itemNameView = (EditText) findViewById(R.id.editText_itemName);
        itemWidthView = (EditText) findViewById(R.id.editText_other_enterWidth);
        itemHeightView = (EditText) findViewById(R.id.editText_other_enterHeight);
        itemDepthView = (EditText) findViewById(R.id.editText_other_enterDepth);
        itemAmount = (EditText) findViewById(R.id.editText_other_itemAmount);
        switchView = (Switch) findViewById(R.id.switch_isFragile);
        enterButton = (Button) findViewById(R.id.btn_other_enterButton);

        enterLstnr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = itemNameView.getText().toString();
                double width = Integer.parseInt(itemWidthView.getText().toString());
                double height = Integer.parseInt(itemHeightView.getText().toString());
                double depth = Integer.parseInt(itemDepthView.getText().toString());
                int amount = Integer.parseInt(itemAmount.getText().toString());

                app.addItemDetails(itemName,(double)width,(double)height,(double)depth,isFragile,amount);

                startActivity(new Intent(getApplicationContext(), ActPQuote.class));
            }
        };
        switchListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    isFragile = 1;
                else
                    isFragile = 0;
            }
        };

        enterButton.setOnClickListener(enterLstnr);
        switchView.setOnCheckedChangeListener(switchListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_add_other, menu);
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
