package ro.ubbcluj.cs.lab.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import ro.ubbcluj.cs.lab.EmailScreen;
import ro.ubbcluj.cs.lab.ListScreen;
import ro.ubbcluj.cs.lab.MainActivity;
import ro.ubbcluj.cs.lab.R;

/**
 * Created by Pavlik on 2017-01-19.
 */

public class Dashboard  extends Activity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button send_email_btn = (Button) findViewById(R.id.send_email_btn);
        Button list_items = (Button) findViewById(R.id.list_items);
        Button log_out = (Button) findViewById(R.id.log_out);
        log_out.setOnClickListener(this);

        send_email_btn.setOnClickListener(this);
        list_items.setOnClickListener(this);

        Utils utils = new Utils(Dashboard.this);
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
            case R.id.log_out:
               mAuth.signOut();
                break;
        }
    }
}
