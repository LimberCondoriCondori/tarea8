package com.example.practica8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<String> list_data;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_data = new ArrayList<>();
        loadData();
        loadComponents();


    }

    private void loadComponents() {

        Button hogar = findViewById(R.id.btnadd);

        hogar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnadd: {
                Intent agregar = new Intent(MainActivity.this, Enviar.class);
                startActivity(agregar);
                break;
            }
        }

    }

    private void loadData() {
        ListView lista = findViewById(R.id.listuser);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list_data);
        lista.setAdapter(adapter);


        AsyncHttpClient client = new AsyncHttpClient();
        list_data.clear();
        client.get(Utils.GET_USER, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        list_data.add(obj.getString("nombre"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
