package ro.ubbcluj.cs.lab;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ro.ubbcluj.cs.lab.utils.Utils;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button send_email_btn = (Button) findViewById(R.id.send_email_btn);
        Button list_items = (Button) findViewById(R.id.list_items);

        send_email_btn.setOnClickListener(this);
        list_items.setOnClickListener(this);

        Utils utils = new Utils(MainActivity.this);
        ArrayList<CharSequence> items2 = utils.readFromFile("config.txt");
        if (items2.size() == 0) {
            ArrayList<CharSequence> items = new ArrayList<>();
            items.add("Audi");
            items.add("Volkswagen");
            items.add("Opel");
            items.add("Renault");
            items.add("Dacia");
            utils.writeToFile("config.txt", items);
        }
    }

    @Override
    public void onClick(View v) {
        Intent in;
        switch (v.getId()){
            case R.id.send_email_btn:
                in = new Intent(this, EmailScreen.class);
                startActivity(in);
                finish();
                break;
            case R.id.list_items:
                in = new Intent(this, ListScreen.class);
                startActivity(in);
                finish();
                break;
        }
    }
}
