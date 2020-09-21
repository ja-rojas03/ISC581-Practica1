package com.interaccion.appclase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public final static String MESSAGE = "text";

    private EditText et_name;
    private EditText et_last_name;
    private EditText et_bdate;
    private Button btn_send;
    private Button btn_clean;
    private Spinner spn_gender;
    private RadioButton rb_yes;
    private RadioButton rb_no;
    private CheckBox chbx_jav;
    private CheckBox chbx_pyt;
    private CheckBox chbx_js;
    private CheckBox chbx_go;
    private CheckBox chbx_c;
    private CheckBox chbx_cs;
    private String spn_opt;

    private String[] genders = {"Masculino", "Femenino"};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_name);
        et_last_name = findViewById(R.id.et_last_name);
        et_bdate = findViewById(R.id.et_bdate);
        btn_send = findViewById(R.id.btn_send);
        btn_clean = findViewById(R.id.btn_clean);
        spn_gender = findViewById(R.id.spn_gender);
        rb_yes = findViewById(R.id.rb_yes);
        rb_no = findViewById(R.id.rb_no);
        chbx_jav = findViewById(R.id.chbx_java);
        chbx_pyt = findViewById(R.id.chbx_python);
        chbx_js = findViewById(R.id.chbx_js);
        chbx_go = findViewById(R.id.chbx_go);
        chbx_c = findViewById(R.id.chbx_c);
        chbx_cs = findViewById(R.id.chbx_cs);

        disable_chbx();

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_gender.setAdapter(adapter);
        spn_gender.setOnItemSelectedListener(this);


        rb_yes.setOnClickListener(v -> {
            rb_no.setChecked(false);
            rb_yes.setChecked(true);

            chbx_jav.setEnabled(true);
            chbx_pyt.setEnabled(true);
            chbx_js.setEnabled(true);
            chbx_go.setEnabled(true);
            chbx_c.setEnabled(true);
            chbx_cs.setEnabled(true);
        });

        rb_no.setOnClickListener(v -> {
            rb_yes.setChecked(false);
            rb_no.setChecked(true);

            chbx_jav.setEnabled(false);
            chbx_pyt.setEnabled(false);
            chbx_js.setEnabled(false);
            chbx_go.setEnabled(false);
            chbx_c.setEnabled(false);
            chbx_cs.setEnabled(false);
        });


        btn_send.setOnClickListener(v -> {
            Toast.makeText(this, spn_opt, Toast.LENGTH_SHORT).show();
            String name = et_name.getText().toString().trim();
            String last_name = et_last_name.getText().toString().trim();
            String bdate = et_bdate.getText().toString().trim();

            if(TextUtils.isEmpty(name)) {
                et_name.setError("Este campo no puede estar vacio");
                et_name.setFocusable(true);
                return;
            }

            else if(TextUtils.isEmpty(last_name)) {
                et_last_name.setError("Este campo no puede estar vacio");
                et_last_name.setFocusable(true);
                return;
            }

            else if(TextUtils.isEmpty(bdate)) {
                et_bdate.setError("Este campo no puede estar vacio");
                et_bdate.setFocusable(true);
                return;
            }

            if(!rb_no.isChecked()){
                if(!rb_yes.isChecked()){
                    rb_yes.setError("Debe seleccionar una opcion.");
                    return;
                }
            }

            String genero = spn_opt;

            String prog = rb_yes.isChecked() ? "Me gusta programar" :  "No me gusta programar";

            Intent intent = new Intent(this, DisplayMessageActivity.class);

            if(prog == "Me gusta programar") {

                String languages = "";

                if(chbx_jav.isChecked()) {
                    languages += "Java, ";
                }
                if(chbx_pyt.isChecked()) {
                    languages += "Python, ";
                }
                if(chbx_js.isChecked()) {
                    languages += "JS, ";
                }
                if(chbx_go.isChecked()) {
                    languages += "Go Land, ";
                }
                if(chbx_c.isChecked()) {
                    languages += "C/C++, ";
                }
                if(chbx_cs.isChecked()) {
                    languages += "C#, ";
                }
                if (languages.length() > 0){
                    languages = languages.substring(0, languages.length() - 2);
                }

                intent.putExtra("name", name);
                intent.putExtra("last_name", last_name);
                intent.putExtra("bdate", bdate);
                intent.putExtra("genero", spn_opt);
                intent.putExtra("prog", prog);
                intent.putExtra("lang",languages);

            }else {

                intent.putExtra("name", name);
                intent.putExtra("last_name", last_name);
                intent.putExtra("bdate", bdate);
                intent.putExtra("genero", spn_opt);
                intent.putExtra("prog", prog);
                intent.putExtra("lang","");

            }

            startActivity(intent);
        });


        btn_clean.setOnClickListener(v -> {
           clearFields();
        });


    }

    private void disable_chbx() {

        chbx_jav.setEnabled(false);
        chbx_pyt.setEnabled(false);
        chbx_js.setEnabled(false);
        chbx_go.setEnabled(false);
        chbx_c.setEnabled(false);
        chbx_cs.setEnabled(false);

    }


    public void clearFields() {

        et_name.setText("");
        et_last_name.setText("");
        et_bdate.setText("");
        spn_gender.setSelection(0);
        rb_yes.setChecked(false);
        rb_no.setChecked(false);
        chbx_jav.setChecked(false);
        chbx_pyt.setChecked(false);
        chbx_js.setChecked(false);
        chbx_go.setChecked(false);
        chbx_c.setChecked(false);
        chbx_cs.setChecked(false);

        chbx_jav.setChecked(false);
        chbx_pyt.setChecked(false);
        chbx_js.setChecked(false);
        chbx_go.setChecked(false);
        chbx_c.setChecked(false);
        chbx_cs.setChecked(false);

    }
    

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spn_opt = genders[position];
        Toast.makeText(this, spn_opt, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}