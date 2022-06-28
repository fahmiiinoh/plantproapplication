package com.example.plantpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.FirebaseDatabase;


public class registerpage extends AppCompatActivity implements View.OnClickListener{

    private TextView registerUser;
    private EditText editTextFullName, editTextEmail, editTextPassword;

    private  FirebaseAuth Authentication;

    private String userCredentialType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);

        Authentication = FirebaseAuth.getInstance();

        registerUser = (Button) findViewById(R.id.btn_signUp);
         registerUser.setOnClickListener(this);

        userCredentialType ="2";

         editTextFullName = (EditText) findViewById(R.id.et_FullName);
         editTextEmail = (EditText) findViewById(R.id.et_email);
         editTextPassword = (EditText) findViewById(R.id.editTextpassword);


    }
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btn_signUp:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String fullname = editTextFullName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String userCredential = userCredentialType.toString().trim();


        if(fullname.isEmpty()){
            editTextFullName.setError("Full name is required!");
            editTextFullName.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email Address is required!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid Email Address!");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() <8){
            editTextPassword.setError("Minimum Password length should be 8 characters!");
            editTextPassword.requestFocus();
            return;
        }

        Authentication.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(fullname, email, userCredential);

                            FirebaseDatabase.getInstance("https://plantproapp-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){
                                                Toast.makeText(registerpage.this, "Registration Successful!", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(registerpage.this, loginpage.class));
                                            }else{
                                                Toast.makeText(registerpage.this, "Registration Failed! Try Again!", Toast.LENGTH_LONG).show();

                                            }

                                        }
                                    });
                        }else{
                            //Log.d("---->",""+task.getException());
                            Toast.makeText(registerpage.this, "Registration Failed!!!!! Try Again!", Toast.LENGTH_LONG).show();

                        }

                    }
                });

    }
}