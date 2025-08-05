package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String language, String address) {

        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setName(name);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("http://google.com");
        // Setting types as a list of strings
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);
        // Setting location with latitude and longitude
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;
    }

    public String deletePlacePayload(String place_id) {
        return "{\n" +
                "\"place_id\":\"" + place_id + "\"\n" +
                "}";
    }
}

