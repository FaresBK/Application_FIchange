package com.fares.fi_echanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    TextView register;
    EditText email,password;
    String mail,passworde;
    FirebaseAuth mFirebaseAuth;
    private  FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register=(TextView) findViewById(R.id.register);
        email=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);
        if(!isConnected(this)){
            showCustomDialog();
        }
        mFirebaseAuth=FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Home.class);
                startActivity(intent);
                finish();
            }
        });


    }


    public void login(){
    mFirebaseAuth.signInWithEmailAndPassword(mail,passworde).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){
               verifyEmailaddress();
            }else{
                Toast.makeText(Login.this, "Login erreur", Toast.LENGTH_SHORT).show();
            }
        }
    });
}
public void verifyEmailaddress(){
        if(mFirebaseAuth.getCurrentUser().isEmailVerified()){
            Intent intent =new Intent(Login.this,change.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "S'il vous plaît verify your account  ...", Toast.LENGTH_SHORT).show();
        }

}
public void shar(String email){
    SharedPreferences shar= getSharedPreferences("users", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor=shar.edit();
    editor.putString("user",email);
    editor.apply();
}

    public void onclicklogin(View view) {


        mail=email.getText().toString();
        passworde=password.getText().toString();
        if (mail.isEmpty()){
            email.setError("S'il vous plaît enter E-mail");
            email.requestFocus();
        }else if(passworde.isEmpty()){
            password.setError("S'il vous plaît enter Password ");
            password.requestFocus();
        } else if(mail.isEmpty() && passworde.isEmpty()){
            Toast.makeText(Login.this, "Enter E-mail and Password", Toast.LENGTH_SHORT).show();
        }else if(!(mail.isEmpty() && passworde.isEmpty())){
            login();
            shar(mail);

        }else{
            Toast.makeText(Login.this, "Error eccurred !", Toast.LENGTH_SHORT).show();
        }

    }

    private void showCustomDialog() {

        final AlertDialog.Builder builder=new AlertDialog.Builder(Login.this);
        builder.setMessage("Activez votre internet").setCancelable(false)
        .setPositiveButton("Connecter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                  startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

            }
        })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      dialogInterface.dismiss();
                    }
                })
                .show();

    }

    private boolean isConnected(Login login) {
        ConnectivityManager connectivityManager= (ConnectivityManager) login.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi!=null && wifi.isConnected())|| (mobile!=null && mobile.isConnected())){
               return  true;
        }else {
            return false;
        }

    }
}
