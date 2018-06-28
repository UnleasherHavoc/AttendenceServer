package com.example.application.attendenceserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
     DatabaseHandler handler;
    TextView count,slotno,date;
    String get_slot="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent slot_data_intent = getIntent();

        get_slot=slot_data_intent.getExtras().getString("Slots");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundResource(R.drawable.clg_logo);
        getSupportActionBar().setTitle("");
        count=(TextView)findViewById(R.id.count);
        slotno=(TextView)findViewById(R.id.textView9);
        date=(TextView)findViewById(R.id.textView8);

        switch(get_slot){

            case "SLOTa":{
                slotno.setText("1");
                break;
            }

            case "SLOTb":{
                slotno.setText("2");
                break;
            }
            case "SLOTc": {
                slotno.setText("3");
                break;
            }
            case "SLOTd": {
                slotno.setText("4");
                break;
            }
            case "SLOTe": {
                slotno.setText("5");
                break;
            }
            case "SLOTf": {
                slotno.setText("6");
                break;
            }}





        ListView listView = (ListView) findViewById(R.id.listview);
         handler = new DatabaseHandler(this, null, null, 1);
        CursorAdapter cursorAdapter = new custom_curso_adaptor(this,handler.getAllData1(get_slot),0);
        listView.setAdapter(cursorAdapter);
        try {
            date.setText(handler.getDATE_FROM_DATABASE(get_slot));
        } catch (Exception e) {
            e.printStackTrace();
        }
        count .setText(handler.count(get_slot));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings1) {
            ListView listView = (ListView) findViewById(R.id.listview);

               handler.onDELETE();
            CursorAdapter cursorAdapter = new custom_curso_adaptor(this,handler.getAllData1(get_slot),0);
            listView.setAdapter(cursorAdapter);
            count .setText(handler.count(get_slot));
            date.setText("DATE: ");

            Toast.makeText(getApplicationContext(),"ATTENDENCE CLEARED",Toast.LENGTH_SHORT).show();

      handler.close();

            return true;
        }
        if (id == R.id.action_settings2) {
            ListView listView = (ListView) findViewById(R.id.listview);

            handler.on_SLOT_DELETE(get_slot);
            CursorAdapter cursorAdapter = new custom_curso_adaptor(this,handler.getAllData1(get_slot),0);
            listView.setAdapter(cursorAdapter);
            count .setText(handler.count(get_slot));
            date.setText("DATE: ");
            Toast.makeText(getApplicationContext(),"ATTENDENCE CLEARED",Toast.LENGTH_SHORT).show();

            handler.close();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
