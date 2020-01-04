package com.gajanan.example.firebasephonenumberauthetication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    //Button btnLogout;

    TextView txtName,txtMobile;

    private PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtName=findViewById(R.id.txt_name);
        txtMobile=findViewById(R.id.txt_mobile);

        pref = new PrefManager(getApplicationContext());

        txtName.setText("Name : "+pref.getName());
        txtMobile.setText("Mobile : "+pref.getMobileNumber());

        //Toast.makeText(this, name+"\n"+mobile, Toast.LENGTH_LONG).show();

        findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pref.clearSession();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
