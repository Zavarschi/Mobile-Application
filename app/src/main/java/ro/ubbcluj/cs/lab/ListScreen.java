package ro.ubbcluj.cs.lab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ro.ubbcluj.cs.lab.utils.Utils;


/**
 * Created by Pavlik on 2016-11-09.
 */

public class ListScreen extends Activity {

    ListView list;
    ArrayList<CharSequence> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_screen);
        Utils utils = new Utils(this);
        list = (ListView) findViewById(R.id.list);
        items = utils.readFromFile("config.txt");
        list.setAdapter(new ArrayAdapter<CharSequence>(ListScreen.this,
                android.R.layout.simple_list_item_1, items));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(ListScreen.this, EditItemScreen.class);
                in.putCharSequenceArrayListExtra("list", items);
                in.putExtra("position", position);
                startActivity(in);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
        finish();
    }
}
