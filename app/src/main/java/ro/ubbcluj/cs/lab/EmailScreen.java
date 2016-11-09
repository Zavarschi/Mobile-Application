package ro.ubbcluj.cs.lab;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Pavlik on 2016-11-09.
 */

public class EmailScreen extends Activity {

    EditText email_et, subject_et, text_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_screen);

        email_et = (EditText) findViewById(R.id.email_et);
        subject_et = (EditText) findViewById(R.id.subject_et);
        text_et = (EditText) findViewById(R.id.text_et);


        Button send_email_btn = (Button) findViewById(R.id.send_email_btn);
        send_email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!email_et.getText().toString().isEmpty() && !subject_et.getText().toString().isEmpty() && !text_et.getText().toString().isEmpty()){
                    Intent emailIn = new Intent(Intent.ACTION_SEND);
                    emailIn.setData(Uri.parse("mailto:"));
                    String[] to = {email_et.getText().toString()};
                    emailIn.putExtra(Intent.EXTRA_EMAIL, to);
                    emailIn.putExtra(Intent.EXTRA_SUBJECT, subject_et.getText().toString());
                    emailIn.putExtra(Intent.EXTRA_TEXT, text_et.getText().toString());
                    emailIn.setType("message/rfc822");
                    emailIn = Intent.createChooser(emailIn, "Send Email");
                    startActivity(emailIn);
                }else{
                    new AlertDialog.Builder(EmailScreen.this)
                            .setTitle("Error:")
                            .setMessage("All fields required!")
                            .create().show();
                }
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
