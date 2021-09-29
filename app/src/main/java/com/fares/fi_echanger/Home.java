package com.fares.fi_echanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
   EditText email,password,confpass;
   String mail,passworde,confpasswrd;
   TextView textView;
   CardView sing;
   FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        email=(EditText) findViewById(R.id.user);
        password=(EditText)findViewById(R.id.password);
        confpass=(EditText)findViewById(R.id.confpass);
        mFirebaseAuth=FirebaseAuth.getInstance();
        textView=(TextView)findViewById(R.id.login);

        sing=(CardView)findViewById(R.id.sign);

        sing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              mail=email.getText().toString();
              passworde=password.getText().toString();
              confpasswrd=confpass.getText().toString();
              if (mail.isEmpty()){
                  email.setError("Please enter E-mail");
                  email.requestFocus();
              }else if(passworde.isEmpty()){
                  password.setError("Please enter Password ");
                  password.requestFocus();
                }else if(confpasswrd.isEmpty()){
                  confpass.setError("Please enter Confirmation Password");
              } else if(mail.isEmpty() && passworde.isEmpty()&& confpasswrd.isEmpty()){
                  Toast.makeText(Home.this, "Enter E-mail and Password", Toast.LENGTH_SHORT).show();
              }else if(!(mail.isEmpty() && passworde.isEmpty()&& confpasswrd.isEmpty())){
                  if(!(passworde.equals(confpasswrd))){
                      Toast.makeText(Home.this, "Password False", Toast.LENGTH_SHORT).show();
                  }else if (passworde.equals(confpasswrd)){
                              enrigister();
                  }else{
                      Toast.makeText(Home.this, "Error", Toast.LENGTH_SHORT).show();
                  }
              }

            }
        });

      textView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(Home.this,Login.class);
              startActivity(intent);
          }
      });
    }
   public void enrigister(){
       mFirebaseAuth.createUserWithEmailAndPassword(mail,passworde).addOnCompleteListener(Home.this, new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if (!task.isSuccessful()){
                   Toast.makeText(Home.this, "SignUP UnSuccessful", Toast.LENGTH_SHORT).show();
               }else{
                verifayEmail();
               }
           }
       });
   }
   public void verifayEmail(){
        mFirebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Intent intent=new Intent(Home.this,Login.class);
                        startActivity(intent);
                        Toast.makeText(Home.this, "validate your Account", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Home.this, "Your Email not existe", Toast.LENGTH_SHORT).show();
                    }
            }
        });
   }
}
