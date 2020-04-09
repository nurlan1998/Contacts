package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {

    EditText edFirstName;
    EditText edLastName;
    EditText edPoneNumber;
    ContactDatabase database;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        edFirstName = findViewById(R.id.edFirstName);
        edLastName = findViewById(R.id.edLastName);
        edPoneNumber = findViewById(R.id.edPhoneNumber);
        database = ContactDatabase.getInstance(this);

    }

    public void onClickSaveContact(View view) {
        String firstName = edFirstName.getText().toString().trim();
        String lastName = edLastName.getText().toString().trim();
        String phoneNumber = edPoneNumber.getText().toString().trim();

        if (isFilled(firstName, lastName)) {
            Contact contact = new Contact(firstName, lastName, phoneNumber);
            Log.d("result", firstName + lastName);
            viewModel.insertContact(contact);
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            Toast.makeText(this, "поля не заполнены", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isFilled(String fistName, String lastName) {
        return !fistName.isEmpty() && !lastName.isEmpty();
    }
}
