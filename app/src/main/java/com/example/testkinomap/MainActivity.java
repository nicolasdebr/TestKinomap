package com.example.testkinomap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private ImageView icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView)findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mQueue = Volley.newRequestQueue(this);
        final List<VehicleDetail> listVehicle = new ArrayList<VehicleDetail>();



        String url = Url.fetchdata;
        //Try request on api
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response != null) {
                                //Get result of request
                                JSONArray result = response.getJSONObject("vehicleList").getJSONArray("response");
                                //Create a table of the size of result length
                                VehicleDetail[] vehicule = new VehicleDetail[result.length()];
                                for (int i = 0; i < result.length(); i++) {
                                    JSONObject vehicleList = result.getJSONObject(i);
                                    JSONObject iconDetail =  vehicleList.getJSONObject("icon").getJSONObject("url");
                                    VehicleDetail newVehicule = new VehicleDetail(vehicleList.getInt("id"), vehicleList.getString("name"), iconDetail.getString("right"));
                                    listVehicle.add(newVehicule);
                                }
                                listView.setAdapter(new CustomListAdapter(listVehicle, MainActivity.this));
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
        //launch request
        mQueue.add(request);

        //Handling Click event in listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        "ID : "+ listVehicle.get(position).getId(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
