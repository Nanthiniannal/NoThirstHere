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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ordercan extends AppCompatActivity {
    EditText e1;
    FirebaseAuth firebaseAuth;
    Button b1,b2;
    TextView errorview;
    String url ="https://annalprojects.000webhostapp.com/canorderpush.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordercan);
        e1=(EditText)findViewById(R.id.e1);
        firebaseAuth = FirebaseAuth.getInstance();
        errorview=(TextView)findViewById(R.id.textview);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar. getInstance();
                Date date=cal. getTime();
                DateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");
                final String d=dateFormat. format(date);
                final String can=e1.getText().toString();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                final String email = user.getEmail();
                AddDb addDb = new AddDb(email,d,can);
                //databaseReference.child(formattedDate).child(formatterTime).setValue(addDb);
                // Toast.makeText(getApplicationContext(),"Attendance completed",Toast.LENGTH_LONG).show();


                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        errorview.setText(response);
                        if(response.contains("NO STOCK NOW...TRY LATER...")){
                            Toast.makeText(getApplicationContext(), "NO STOCK NOW...TRY LATER...", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Your order has been placed...It will be delivered soon...",Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //
                        errorview.setText(error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", email);
                        params.put("d", d);
                        params.put("can", can);

                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
                stringRequest.setShouldCache(false);






            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                Toast.makeText(getApplicationContext(),"Logged out...",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),userlogin.class);
                startActivity(i);
            }
        });
    }
}
