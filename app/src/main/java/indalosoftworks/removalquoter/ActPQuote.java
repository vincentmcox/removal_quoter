package indalosoftworks.removalquoter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.regex.Pattern;


public class ActPQuote extends ActionBarActivity {
    ListView removalList;
    Button addItemButton, allAddedButton;
    View.OnClickListener addItemListener, allAddedListener;
    ArrayAdapter<MoveItem> moveListAdapter;
    QuoteApp app;
    int listItemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_pquote);
        app = (QuoteApp) getApplicationContext();


        addItemButton = (Button) findViewById(R.id.btn_addItem);
        allAddedButton= (Button) findViewById(R.id.btn_allAdded);

        removalList = (ListView) findViewById(R.id.listView_produceQuote);
        removalList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        populateRemovalList();

        //Processing for the removal of list items dynamically.
        removalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get the ID from the list item
                listItemID = getIntFromItemString(((TextView) view).getText().toString());
                new AlertDialog.Builder(ActPQuote.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Remove item")
                        .setMessage("Do you want to remove this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Delete the item - this updates the database and the removal object
                                app.deleteItem(listItemID);
                                //display a toast showing its deletion
                                Toast toast = Toast.makeText(getBaseContext(), "Item deleted", Toast.LENGTH_SHORT);
                                toast.show();
                                //repopulate the list
                                populateRemovalList();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();


                populateRemovalList();
            }
        });
        //set up button listners
        addItemListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActAddItem.class));
            }
        };
        allAddedListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.setQuoted();
                startActivity(new Intent(getApplicationContext(), ActOvQuote.class));
            }
        };

        addItemButton.setOnClickListener(addItemListener);
        allAddedButton.setOnClickListener(allAddedListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_pquote, menu);
        return true;
    }

    @Override
    public void onResume()
    {
        populateRemovalList();
        super.onResume();
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

    public void populateRemovalList() {
        moveListAdapter = new ArrayAdapter<MoveItem>(this, android.R.layout.simple_list_item_1, app.getList());
        removalList.setAdapter(moveListAdapter);
    }

    /**
     * PArses the string displayed in the ListView and extracts the ID for it
     * @param item the toString from the MoveItem represented in the ListView
     * @return intID
     */
    private int getIntFromItemString(String item)
    {
        int intID = -1;
        String[] substrings = item.split(Pattern.quote("."));
        intID = Integer.parseInt(substrings[0]);

        return intID;
    }




}
