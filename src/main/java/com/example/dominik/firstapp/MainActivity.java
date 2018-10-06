package com.example.dominik.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean canGenerate = false;
    private EditText textLength;
    private Button generateButton;
    private TextView generatedPassTextView;
    private TextView incorrectPassword;
    private Button copyButton;
    private Button savePasswordButton;




    private void setElements(){
        textLength = (EditText)findViewById(R.id.lengthEditText);
        generateButton = (Button)findViewById(R.id.generateButton);
        generatedPassTextView = (TextView)findViewById(R.id.generatedPasswordTextView);
        incorrectPassword = (TextView)findViewById(R.id.incorrectPasswordTextView);
        copyButton = (Button)findViewById(R.id.CopyButton);
        savePasswordButton = (Button)findViewById(R.id.saveButton);

        setIncorrectPasswordText();
        setGenerateButton();
        setCopyButton();
        setSavePasswordButton();
    }


    private void setSavePasswordButton(){
       savePasswordButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent next = new Intent(getApplicationContext(),SecondActivity.class);
               next.putExtra("PASSWORD",generatedPassTextView.getText().toString());
               startActivity(next);
           }
       });
    }
    private void setIncorrectPasswordText() {
        textLength.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty() || s.toString() == null) return;
                if (Integer.parseInt(s.toString()) > 20 ||Integer.parseInt(s.toString()) <=0) {
                    incorrectPassword.setVisibility(TextView.VISIBLE);
                    canGenerate = false;
                } else {
                    incorrectPassword.setVisibility(TextView.INVISIBLE);
                    canGenerate = true;
                }
            }
        });
    }

    private void setCopyButton(){
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", generatedPassTextView.getText().toString());
                clipboard.setPrimaryClip(clip);

                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout,"Copied your pass to cp",Snackbar.LENGTH_SHORT).show();
            }
        });
        }

    private void setGenerateButton(){
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canGenerate) {
                    String pass = Generator.generateStrongPassword(Integer.parseInt(textLength.getText().toString()));
                    generatedPassTextView.setText(pass + "");
                    generatedPassTextView.setVisibility(View.VISIBLE);
                    copyButton.setVisibility(View.VISIBLE);
                    savePasswordButton.setVisibility(View.VISIBLE);
                }
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setElements();
    }
}
