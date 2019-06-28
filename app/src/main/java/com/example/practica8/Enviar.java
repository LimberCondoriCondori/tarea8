package com.example.practica8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Enviar extends AppCompatActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar);

        loadComponents();

    }

    private void loadComponents(){
        Button btnsend = findViewById(R.id.btnsend);

        btnsend.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsend: {
                Toast.makeText(Enviar.this, "Enviando", Toast.LENGTH_SHORT).show();
                sendLogin();
                break;
            }

        }

    }


    private void sendLogin() {
        EditText name = findViewById(R.id.editname);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.add("nombre",name.getText().toString());

        client.post(Utils.REGISTER_USER,params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (response.has("id")){
                    Toast.makeText(Enviar.this, "Usuario registrado con exito", Toast.LENGTH_LONG).show();
                    Intent login = new Intent(Enviar.this, MainActivity.class);

                    startActivity(login);
                }
            }

        });
    }
}
