package com.example.nothirsthere;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    TextView b1,b2,b3;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    FirebaseAuth.AuthStateListener mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(TextView)findViewById(R.id.b1);
        b2=(TextView)findViewById(R.id.b2);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        mAuth=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    finish();

                    startActivity(new Intent(getApplicationContext(),ordercan.class));
                }

            }
        };
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i =new Intent(getApplicationContext(),userlogin.class);
                startActivity(i);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i =new Intent(getApplicationContext(),dealerlogin.class);
                startActivity(i);
            }
        });
    }
}
