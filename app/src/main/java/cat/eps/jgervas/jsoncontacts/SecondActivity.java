package cat.jgervas.jsoncontacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SecondActivity extends AppCompatActivity {

    // URL to get contacts JSON
    final static String urlContacts = "https://api.androidhive.info/contacts/";

    private String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Thread thread = new Thread() {
            @Override
            public void run() {


                BufferedReader reader=null;
                HttpURLConnection conn=null;
                InputStream inputStream = null;

                try {

                    URL url = new URL(urlContacts);

                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    //BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));!

                    inputStream = conn.getInputStream();

                    //if (inputStream == null) {
                        // Nothing to do.
                        //return null;
                    //}
                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder buffer = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                        // But it does make debugging a *lot* easier if you print out the completed
                        // buffer for debugging.
                        buffer.append(line + "\n");
                    }

                    //if (buffer.length() == 0) {
                        // Stream was empty.  No point in parsing.
                        //return null;
                    //}

                    String jsonStr = buffer.toString();
                    Log.e(TAG, "Response from url: " + jsonStr);



                } catch (MalformedURLException e) {
                    Log.e(TAG, "MalformedURLException: " + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (final IOException e) {
                            Log.e(TAG, "Error closing stream", e);
                        }
                    }

                }

                }//thread

        };

        thread.start();
    }
}
