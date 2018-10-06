package com.example.dominik.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    private FloatingActionButton goGenerateButton;
    private Button goToListButton;



    private void setElements(){
        goGenerateButton = (FloatingActionButton)findViewById(R.id.goToGeneratorButton);
        goToListButton = (Button)findViewById(R.id.goToPasswordsList);

        setGoGenerateButton();
        setGoToListButton();
    }

    private void setGoToListButton(){
        goToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent next = new Intent(getApplicationContext(), PasswordView.class);
             startActivity(next);
            }
        });
    }

    private void setGoGenerateButton(){
        goGenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(next);
            }
        });
    }

    @Override
    public void onBackPressed(){
        return;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setElements();
    }

}
