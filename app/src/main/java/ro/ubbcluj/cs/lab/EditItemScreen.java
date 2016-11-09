package ro.ubbcluj.cs.lab;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ro.ubbcluj.cs.lab.utils.Utils;

/**
 * Created by Pavlik on 2016-11-09.
 */

public class EditItemScreen extends Activity {
    EditText item_et;
    ArrayList<CharSequence> items;
    int position;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item_screen);
        Intent in = getIntent();
        utils = new Utils(this);
        items = in.getCharSequenceArrayListExtra("list");
        position = in.getIntExtra("position", 0);

        item_et = (EditText) findViewById(R.id.item_et);
        item_et.setText(items.get(position));
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.set(position, item_et.getText().toString());
                utils.writeToFile("config.txt", items);
                new AlertDialog.Builder(EditItemScreen.this)
                        .setTitle("Success:")
                        .setMessage("Data saved!")
                        .create().show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(this, ListScreen.class);
        startActivity(in);
        finish();
    }
}
