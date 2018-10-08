package com.example.dominik.firstapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
/*
ekran z zapisanymi haslami
 */
public class PasswordView extends AppCompatActivity {

    private ListView myList;
    private ArrayList<DataPackage> packageList;
    //hasla wczytane z listy pakietow uzyte do ListView
    private String[] website;
    //flaga mowiaca o tym czy mamy plik z haslami
    private boolean gotPackages = false;

    private void setClickList(){
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent next = new Intent(getApplicationContext(),DataSelector.class);
                next.putExtra("WEBSITE",packageList.get(position).getWebsite());
                next.putExtra("PASSWORD",packageList.get(position).getPassword());
                startActivity(next);
            }
        });
    }
    //sprawdzamy tutaj czy mamy plik z haslami i go wczytujemy
    private void getPackages(){
     packageList = DataPackage.getPackages(getApplicationContext());
     if(packageList != null){
         gotPackages = true;
     }else return;
     DataPackage[] temp = packageList.toArray(new DataPackage[packageList.size()]);
     website = DataPackage.getWebsiteArray(temp);
    }
    //ustawienie elementow zalezne od tego czy istenieje plik z haslami
    private void setElements(){
        getPackages();
        if(gotPackages) {
            myList = (ListView) findViewById(R.id.myList);
            setClickList();
            ArrayAdapter<String> array = new ArrayAdapter<String>(this,
                    R.layout.activity_view_for_list,
                    R.id.textView
                    , website);
            myList.setAdapter(array);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        setElements();
    }

    //jesli nacisniemy do tylu to idziemy do ekranu poczatkowego "main activity"
    @Override
    public void onBackPressed(){
        Intent next = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(next);
    }
}
