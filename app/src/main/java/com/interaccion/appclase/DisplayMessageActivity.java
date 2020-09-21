package com.interaccion.appclase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMessageActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        textView = findViewById(R.id.tv);

        Intent intent  = getIntent();
        String name = intent.getStringExtra("name");
        String last_name = intent.getStringExtra("last_name");
        String bdate = intent.getStringExtra("bdate");
        String genero = intent.getStringExtra("genero");
        Log.wtf("<<<<<<<<<<<<<", genero);
        String prog = intent.getStringExtra("prog");
        String lang = intent.getStringExtra("lang");

        Toast.makeText(this, genero, Toast.LENGTH_SHORT).show();
        String text;
        if (lang.isEmpty()) {
            text = "Hola! mi nombre es : " + name +" "+ last_name + "\nSoy " + genero + " y naci en fecha " + bdate + ".\n" + prog + ". ";
        }else {
            text = "Hola! mi nombre es : " + name +" "+ last_name + "\nSoy " + genero + " y naci en fecha " + bdate + ".\n" + prog + ". " + "Mis lenguajes favoritos son: "+lang;
        }

        textView.setText(text);
    }
}