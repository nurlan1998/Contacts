package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailContactActivity extends AppCompatActivity {

    TextView tvFirstName;
    TextView tvPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);

        tvFirstName = findViewById(R.id.tvName);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);

        Intent intent = getIntent();
        tvFirstName.setText(intent.getStringExtra("firstName"));
        tvPhoneNumber.setText(intent.getStringExtra("phoneNumber"));


    }
}
