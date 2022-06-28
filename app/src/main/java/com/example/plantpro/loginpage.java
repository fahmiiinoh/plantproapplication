package com.example.plantpro;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginpage extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail, editTextPassword;
    private Button signIn;

    private FirebaseAuth Authentication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        TextView textviewRegister = findViewById(R.id.tv_registerRedirect);

        textviewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginpage.this, registerpage.class);
                startActivity(intent);
            }
        });
        Button signIn = findViewById(R.id.btn_signIn);
        signIn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.et_emailSignIn);
        editTextPassword = (EditText)  findViewById(R.id.et_passwordSignIn);

        Authentication =FirebaseAuth.getInstance();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btn_signIn:
                usersignIn();
                break;
        }

    }

    private void usersignIn() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter valid email address!");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 8){
            editTextPassword.setError("Minimum password length is 8 characters!");
            editTextPassword.requestFocus();
            return;
        }


        Authentication.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String uid;

                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    uid = user.getUid();

                    if (user.isEmailVerified()){

                        FirebaseDatabase.getInstance("https://plantproapp-default-rtdb.asia-southeast1.firebasedatabase.app/")
                                .getReference().child("Users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        snapshot.getChildren();{
                                            String ut = String.valueOf(snapshot.child("userCredential").getValue());
                                            Log.d(TAG,ut);
                                           if(ut.equals("1")){
                                              startActivity(new Intent(loginpage.this, HomeScreen.class));
                                          }else{
                                              startActivity(new Intent(loginpage.this, adminpage.class));
                                           }
                                        }


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(loginpage.this, "Check your email to verify account.", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(loginpage.this, "FAILED TO LOGIN, CHECK YOUR CREDENTIALS", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}