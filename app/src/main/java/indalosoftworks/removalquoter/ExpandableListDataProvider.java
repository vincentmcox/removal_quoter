package indalosoftworks.removalquoter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Vincent on 24/08/2015.
 */
public class ExpandableListDataProvider {

    public static HashMap<String, List<String>> getData()
    {
        HashMap<String, List<String>> map = new HashMap<>();
        List<String> kitchen        =   new ArrayList<>();
        kitchen.add("fridge");
        kitchen.add("oven");
        kitchen.add("dishwasher");
        kitchen.add("dining table");
        kitchen.add("chair");
        kitchen.add("washing machine");
        kitchen.add("tumble dryer");
        List<String> diningRoom     =   new ArrayList<>();
        diningRoom.add("unit");
        diningRoom.add("table");
        diningRoom.add("chair");

        List<String> livingRoom     =   new ArrayList<>();
        livingRoom.add("sofa");
        livingRoom.add("table");
        livingRoom.add("tv");
        livingRoom.add("bookcase");
        livingRoom.add("lamp");
        livingRoom.add("desk");
        livingRoom.add("monitor");

        List<String> bedroom        =   new ArrayList<>();
        bedroom.add("bed");
        bedroom.add("desk");
        bedroom.add("dresser");
        bedroom.add("wardrobe");
        bedroom.add("table");
        bedroom.add("chest of drawers");
        bedroom.add("mirror");

        List<String> garden         =   new ArrayList<>();
        garden.add("bench");
        garden.add("table");
        garden.add("bbq");

        List<String> bathroom =      new ArrayList<>();
        bathroom.add("mirror");
        bathroom.add("washing machine");
        bathroom.add("tumble dryer");

        map.put("kitchen", kitchen);
        map.put("dining", diningRoom);
        map.put("living", livingRoom);
        map.put("bedroom", bedroom);
        map.put("garden", garden);
        map.put("bathroom", bathroom);

        return map;
    }
}
