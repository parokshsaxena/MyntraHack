package com.example.parokshsaxena.myntrahack;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.parokshsaxena.myntrahack.model.Item;
import com.example.parokshsaxena.myntrahack.utils.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResultDisplayPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_display_page);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        final ListView listview = (ListView) findViewById(R.id.listview);
        ArrayAdapter<Item> adapter = new CustomAdapter(this,parseJson(message));
        listview.setAdapter(adapter);

    }

    protected List<Item> parseJson(String input){
        try {
            List<Item> items = new ArrayList<>();
            JSONArray jr = new JSONArray(input);
            int len = jr.length();
            for(int i = 0;i<len;i++){
                JSONObject obj = jr.getJSONObject(i);
                Item eachItem = new Item(obj.getString("name"),obj.getString("phoneNum"));
                items.add(eachItem);
            }
            return items;
        }catch(Exception e){
            System.out.println(e);
            return new ArrayList<>();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result_display_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
