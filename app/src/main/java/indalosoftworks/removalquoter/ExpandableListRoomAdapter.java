package indalosoftworks.removalquoter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Vincent on 24/08/2015.
 */
public class ExpandableListRoomAdapter extends BaseExpandableListAdapter {

    private Context context;
    private HashMap<String, List<String>> roomMap;
    private List<String> furnitureList;


    public ExpandableListRoomAdapter(Context ctx,
                                     HashMap<String, List<String>> rooms,
                                     List<String> furniture)
    {
        context = ctx;
        roomMap = rooms;
        furnitureList = furniture;
    }


    @Override
    public int getGroupCount() {
        return furnitureList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return roomMap.get(furnitureList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return furnitureList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return roomMap.get(furnitureList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String groupTitle = (String) getGroup(groupPosition);
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_list_layout, parent, false);
        }
        TextView groupTextView = (TextView) convertView.findViewById(R.id.parent_list);
        groupTextView.setText(groupTitle);

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childTitle = (String) getChild(groupPosition, childPosition);
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_list_layout, parent, false);
        }
        TextView childTextView = (TextView) convertView.findViewById(R.id.child_list);
        childTextView.setText(childTitle);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
