package ro.ubbcluj.cs.lab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ro.ubbcluj.cs.lab.utils.Dashboard;

import static android.content.ContentValues.TAG;

/**
 * Created by Pavlik on 2016-11-09.
 */

public class LoginScreen extends Activity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText emailEt, passwordEt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        emailEt  = (EditText) findViewById(R.id.email);
        passwordEt = (EditText) findViewById(R.id.password);
        Button loginbutton = (Button) findViewById(R.id.login_button);
        Button singup = (Button) findViewById(R.id.singup);
        singup.setOnClickListener(this);
        loginbutton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(LoginScreen.this, Dashboard.class));
                    finish();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }





    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                String email = emailEt.getText().toString();
                String password = passwordEt.getText().toString();
                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginScreen.this, "Please enter email and password",
                            Toast.LENGTH_SHORT).show();
                }else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Log.w(TAG, "signInWithEmail:failed", task.getException());
                                        Toast.makeText(LoginScreen.this, "Login Failer",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        startActivity(new Intent(LoginScreen.this, Dashboard.class));
                                        finish();
                                    }
                                }
                            });
                }
                break;
            case R.id.singup:
                String email2 = emailEt.getText().toString();
                String password2 = passwordEt.getText().toString();
                if(email2.isEmpty() || password2.isEmpty()){
                    Toast.makeText(LoginScreen.this, "Please enter email and password",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Log.i(TAG, "onClick: "+email2 + " "+password2);
                    mAuth.createUserWithEmailAndPassword(email2, password2)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(LoginScreen.this, "Register failed",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

                break;
        }
    }
}
