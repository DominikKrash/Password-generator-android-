package com.example.dominik.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView passwordText;
    private EditText websiteEditText;
    private Button saveButton;
    private CheckBox addPrefixBox;

    private void setElements(){
        passwordText = (TextView)findViewById(R.id.passwordTextView);
        websiteEditText = (EditText)findViewById(R.id.websiteEditText);
        saveButton = (Button)findViewById(R.id.SaveButton);
        addPrefixBox = findViewById(R.id.ExtendCheckBox);

        setPasswordText();
        setSaveButton();
    }
    private void setSaveButton(){
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(websiteEditText.getText().toString().isEmpty()){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout,"Enter your website !",Snackbar.LENGTH_SHORT).show();
                }
                else{
                    String website = DataPackage.fixSpaces(websiteEditText.getText().toString());
                    if(addPrefixBox.isChecked()==true){
                        website = "www."+website;
                    }
                    String password = passwordText.getText().toString();
                    DataPackage newPackage = new DataPackage(website,password);
                    DataPackage.addPackage(newPackage,getApplicationContext());

                    Intent next = new Intent(getApplicationContext(),ThirdActivity.class);
                    startActivity(next);
                }
            }
        });
    }
    private void setPasswordText(){
        if(getIntent().hasExtra("PASSWORD")) {
            String text = getIntent().getStringExtra("PASSWORD");
            passwordText.setText(text +"");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setElements();


    }
}
