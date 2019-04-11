package com.example.nothirsthere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class dealermain extends AppCompatActivity {
    TextView b1,b2,b3,v2;
    EditText v1;
    Button b4;
    String url="https://annalprojects.000webhostapp.com/addcans.php";
    String url2 ="https://annalprojects.000webhostapp.com/avaicans.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealermain);
        b1=(TextView)findViewById(R.id.b1);
        b2=(TextView)findViewById(R.id.b2);
        b3=(TextView)findViewById(R.id.b3);
        v1=(EditText)findViewById(R.id.v1);
        v2=(TextView) findViewById(R.id.v2);
        b4=(Button)findViewById(R.id.b4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      v2.setVisibility(View.VISIBLE);

                                      StringRequest stringRequest = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                                          @Override
                                          public void onResponse(String response) {

                                             // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                                              String res = "No of cans available now is "+response;
                                              v2.setText(res);
                                          }
                                      }, new Response.ErrorListener() {
                                          @Override
                                          public void onErrorResponse(VolleyError error) {
                                              //
                                              //errorview.setText(error.toString());
                                              //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                                          }
                                      }) {

                                      };
                                      RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                      requestQueue.add(stringRequest);
                                      stringRequest.setShouldCache(false);

                                  }
                              });








                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), orderdetails.class);
                        startActivity(i);
                    }
                });
                b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String can = v1.getText().toString();
                        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Toast.makeText(getApplicationContext(), "You are not a valid user!!", Toast.LENGTH_LONG).show()
                                finish();


                            }

                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("can", can);
                                return params;

                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);
                        stringRequest.setShouldCache(false);


                        Toast.makeText(getApplicationContext(), "added...", Toast.LENGTH_LONG).show();
                    }
                });
            }

        }