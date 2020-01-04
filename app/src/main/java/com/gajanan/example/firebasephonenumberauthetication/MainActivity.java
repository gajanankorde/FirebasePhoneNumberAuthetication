package com.gajanan.example.firebasephonenumberauthetication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText edtMobile,edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        edtName=findViewById(R.id.edt_name);
        edtMobile = findViewById(R.id.edt_mobile);

        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = edtMobile.getText().toString().trim();
                String name = edtName.getText().toString().trim();

                if (name.isEmpty()){
                    edtName.setError("Enter your name");
                    edtName.requestFocus();
                    return;
                }
                if(mobile.isEmpty() || mobile.length() < 10){
                    edtMobile.setError("Enter a valid mobile");
                    edtMobile.requestFocus();
                    return;
                }

                Intent intent = new Intent(MainActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("mobile", mobile);
                startActivity(intent);

                //Toast.makeText(MainActivity.this, mobile+"\n"+name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser()!=null){

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }

    }
}
