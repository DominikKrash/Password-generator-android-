package com.example.dominik.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*
aktywnosc ktora pokazuje zawartosc nszego pakietu
uzyskujemy ja po wybraniu rekordu z listy
 */
public class DataSelector extends AppCompatActivity {
    private TextView websiteTextView;
    private TextView passwordTextView;
    private Button deleteButton;
    private Button copyButton;



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
    private void setCopyButton() {
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", passwordTextView.getText().toString());
                clipboard.setPrimaryClip(clip);
                //powiadomienie o wczytaniu do schowka
                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout,"Copied your pass to cp",Snackbar.LENGTH_SHORT).show();
            }
        });
    }
    private void setElements(){
        websiteTextView = (TextView)findViewById(R.id.WebsiteTextView);
        passwordTextView = (TextView)findViewById(R.id.PasswordTextView);
        deleteButton = (Button)findViewById(R.id.DeleteButton);
        copyButton = (Button)findViewById(R.id.copyButton);

        setCopyButton();
        setDeleteButton();
        //dane wczytane z listy
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
