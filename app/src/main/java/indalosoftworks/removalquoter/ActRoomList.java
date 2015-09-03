package indalosoftworks.removalquoter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ActRoomList extends ActionBarActivity {

    //declarations
    HashMap<String, List<String>> rooms;
    List<String> furniture;
    ExpandableListView overList;
    ExpandableListRoomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_room_list);

        overList = (ExpandableListView) findViewById(R.id.expandableListView);
        rooms = ExpandableListDataProvider.getData();
        furniture = new ArrayList<String>(rooms.keySet());

        adapter = new ExpandableListRoomAdapter(this, rooms, furniture);
        overList.setAdapter(adapter);


        //Set up listener for child elements, and process the events
        overList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                boolean result = false;
                String thisChild = rooms.get(groupPosition).get(childPosition);
                // Control based on the selected child. Noted that long lines of if statements are
                // inefficient. This should be rectified at some juncture. This is hard coded to
                // pick up every current item that has been put into the list. If something goes
                // wrong, a toast is shown.
                if(thisChild.equals("bbq"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureBBQ.class));
                    result = true;
                }
                else if(thisChild.equals("fridge"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureFridge.class));
                    result = true;
                }
                else if(thisChild.equals("oven"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureOven.class));
                    result = true;
                }
                else if(thisChild.equals("dishwasher"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureMachine.class));
                    result = true;
                }
                else if(thisChild.equals("dining table"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureTable.class));
                    result = true;
                }
                else if(thisChild.equals("chair"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureChair.class));
                    result = true;
                }
                else if(thisChild.equals("washing machine"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureMachine.class));
                    result = true;
                }
                else if(thisChild.equals("tumble dryer"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureMachine.class));
                    result = true;
                }
                else if(thisChild.equals("unit"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureUnit.class));
                    result = true;
                }
                else if(thisChild.equals("table"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureTable.class));
                    result = true;
                }
                else if(thisChild.equals("sofa"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureSofa.class));
                    result = true;
                }
                else if(thisChild.equals("tv"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureTV.class));
                    result = true;
                }
                else if(thisChild.equals("bookcase"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureBookcase.class));
                    result = true;
                }
                else if(thisChild.equals("lamp"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureLamp.class));
                    result = true;
                }
                else if(thisChild.equals("desk"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureTable.class));
                    result = true;
                }
                else if(thisChild.equals("monitor"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureTV.class));
                    result = true;
                }
                else if(thisChild.equals("bed"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureBed.class));
                    result = true;
                }
                else if(thisChild.equals("dresser"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureDresser.class));
                    result = true;
                }
                else if(thisChild.equals("wardrobe"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureWardrobe.class));
                    result = true;
                }
                else if(thisChild.equals("chest of drawers"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureDrawers.class));
                    result = true;
                }
                else if(thisChild.equals("mirror"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureMirror.class));
                    result = true;
                }
                else if(thisChild.equals("bench"))
                {
                    startActivity(new Intent(getApplicationContext(), ActFurnitureBench.class));
                    result = true;
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            thisChild + " didn't get picked up.",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }


                    return result;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_room_list, menu);
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
