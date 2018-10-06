package com.example.dominik.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DataSelector extends AppCompatActivity {
    private TextView websiteTextView;
    private TextView passwordTextView;
    private Button deleteButton;



    private void setDeleteButton(){
     deleteButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             DataPackage.deleteRecord(websiteTextView.getText().toString(),getApplicationContext());

             Intent next = new Intent(getApplicationContext(),PasswordView.class);
             startActivity(next);
         }
     });
    }

    private void setElements(){
        websiteTextView = findViewById(R.id.WebsiteTextView);
        passwordTextView = findViewById(R.id.PasswordTextView);
        deleteButton = findViewById(R.id.DeleteButton);

        setDeleteButton();

        String website = getIntent().getStringExtra("WEBSITE");
        String password = getIntent().getStringExtra("PASSWORD");

        websiteTextView.setText(website);
        passwordTextView.setText(password);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_selector);

        setElements();
    }
}
