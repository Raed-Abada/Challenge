package tn.challenge;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class insertActivity extends AppCompatActivity {
    private static final String GET_REQUEST_URL = "https://erotic-ally.000webhostapp.com/getCities.php";
    private Context context;
    static List<String> cities = new ArrayList<>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText Name = (EditText) findViewById(R.id.CityNameText);
        final EditText PopulationNumber = (EditText) findViewById(R.id.CityPopulationText);
        Button Submit = (Button) findViewById(R.id.submit);
        Button getCities = (Button) findViewById(R.id.getCities);
        final ListView CitiesList = (ListView) findViewById(R.id.CitiesList);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = Name.getText().toString();
                final int number = Integer.parseInt(PopulationNumber.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(insertActivity.this, insertActivity.class);
                                insertActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(insertActivity.this);
                                builder.setMessage("Insertion Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                insertRequest insertRequest = new insertRequest(name, number, responseListener);
                RequestQueue queue = Volley.newRequestQueue(insertActivity.this);
                queue.add(insertRequest);
            }
        });


        getCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cities.clear();

                StringRequest stringRequest = new StringRequest(Request.Method.GET, GET_REQUEST_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                context = getApplicationContext();


                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONArray array = jsonObject.getJSONArray("cities");

                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject o = array.getJSONObject(i);
                                        String name = o.getString("Name");
                                        int number = o.getInt("Population");
                                        City city = new City(name, number);
                                        cities.add(city.toString());

                                        adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, cities);
                                        CitiesList.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();



                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();


                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);


            }


        });
    }
}


