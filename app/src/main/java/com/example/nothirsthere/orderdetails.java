package com.example.nothirsthere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class orderdetails extends AppCompatActivity {
    //TextView textView, count;
    private static final String url = "https://annalprojects.000webhostapp.com/retriveorders.php";

    //a list to store all the products
    List<Product> productList;


    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int i;
                try {
                    //converting the string to json array object
                    JSONArray array = new JSONArray(response);
                    // textView.setText(response);
                    //traversing through all the object
                    for (i = 0; i < array.length(); i++) {

                        //getting product object from json array
                        JSONObject product = array.getJSONObject(i);

                        //adding the product to product list
                        productList.add(new Product(

                                product.getString("email"),
                                product.getString("dates"),
                                product.getString("cans")


                        ));
                    }

                    ProductAdapter adapter = new ProductAdapter(getApplicationContext(), productList);
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        ;
        Volley.newRequestQueue(this).add(stringRequest);
    }


}



