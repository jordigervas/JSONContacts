package cat.jgervas.jsoncontacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FirstActivity extends AppCompatActivity {

    private String jsonString = "";

    private String TAG = MainActivity.class.getSimpleName();

    ArrayList<HashMap<String, String>> contactList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        jsonString = "{\n" +
                "    \"contacts\": [\n" +
                "        {\n" +
                "                \"id\": \"c200\",\n" +
                "                \"name\": \"Jack Nicholson\",\n" +
                "                \"email\": \"jack_nicholson@gmail.com\",\n" +
                "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                "                \"gender\" : \"male\",\n" +
                "                \"phone\": {\n" +
                "                    \"mobile\": \"+91 0000000000\",\n" +
                "                    \"home\": \"00 000000\",\n" +
                "                    \"office\": \"00 000000\"\n" +
                "                }\n" +
                "        },\n" +
                "        {\n" +
                "                \"id\": \"c201\",\n" +
                "                \"name\": \"Johnny Depp\",\n" +
                "                \"email\": \"johnny_depp@gmail.com\",\n" +
                "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                "                \"gender\" : \"male\",\n" +
                "                \"phone\": {\n" +
                "                    \"mobile\": \"+91 0000000000\",\n" +
                "                    \"home\": \"00 000000\",\n" +
                "                    \"office\": \"00 000000\"\n" +
                "                }\n" +
                "        },\n" +
                "        {\n" +
                "                \"id\": \"c202\",\n" +
                "                \"name\": \"Leonardo Dicaprio\",\n" +
                "                \"email\": \"leonardo_dicaprio@gmail.com\",\n" +
                "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                "                \"gender\" : \"male\",\n" +
                "                \"phone\": {\n" +
                "                    \"mobile\": \"+91 0000000000\",\n" +
                "                    \"home\": \"00 000000\",\n" +
                "                    \"office\": \"00 000000\"\n" +
                "                }\n" +
                "        }\n" +
                "]\n" +
                "}";



        String jsonStr = getJsonData();

        Log.e(TAG, "Response from url: " + jsonStr);

        contactList = new ArrayList<>();

        if (jsonStr != null)
        {
            try {
                JSONObject jsonObj =  new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("contacts");

                // looping through All Contacts
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);

                    String id = c.getString("id");
                    String name = c.getString("name");
                    String email = c.getString("email");

                    // Phone node is JSON Object
                    JSONObject phone = c.getJSONObject("phone");
                    String mobile = phone.getString("mobile");

                    // tmp hash map for single contact
                    HashMap<String, String> contact = new HashMap<>();

                    // adding each child node to HashMap key => value
                    contact.put("id", id);
                    contact.put("name", name);
                    contact.put("email", email);
                    contact.put("mobile", mobile);

                    // adding contact to contact list
                    contactList.add(contact);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        else
            Log.e(TAG, "Couldn't get json from server.");


        TextView tv = (TextView) findViewById(R.id.helloText);
        tv.setText((contactList.get(1)).get("name"));
        tv.append(((contactList.get(0)).get("name")));

    }


    String getJsonData()
    {
        return jsonString;
    }

}
