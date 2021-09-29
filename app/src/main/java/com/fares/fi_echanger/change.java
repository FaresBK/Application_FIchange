package com.fares.fi_echanger;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;



public class change extends AppCompatActivity {
    CardView logout,postpaysera,baridipaysera,postpaypal,baridipaypal,khamsatpost,contect;
    Dialog dialog;
    Button exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        logout=findViewById(R.id.logout);
        postpaysera=findViewById(R.id.postpayesera);
        baridipaysera=findViewById(R.id.baridipaysera);
        postpaypal=findViewById(R.id.postpaypal);
        baridipaypal=findViewById(R.id.baridipaypal);
        khamsatpost=findViewById(R.id.khamstatpost);
        contect=findViewById(R.id.contectus);
        dialog=new Dialog(change.this);
        dialog.setContentView(R.layout.dilagee);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dilogee));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations=R.style.animation;
        dialog.getWindow().setLayout(800,800);
        ImageButton gmail=dialog.findViewById(R.id.gmaill);
        ImageButton facebook=dialog.findViewById(R.id.Fb);
        exit=dialog.findViewById(R.id.exit);

        gmail.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "faresoz122@gmail.com" + "?Subject=" + "رسالة من مستخدم"));
                startActivity(intent);
                dialog.dismiss();
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100013283434092"));
                    startActivity(intent);
                    dialog.dismiss();
                } catch(Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100013283434092")));
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        postpaysera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                            Intent intent=new Intent(change.this,payeserapost.class);
                            startActivity(intent);
                            finish();
            }

        });
        baridipaysera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                               Intent intent=new Intent(change.this,baridiipaysera.class);
                               startActivity(intent);
                               finish();
            }

        });
        postpaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                              Intent intent = new Intent(change.this, postpaypall.class);
                              startActivity(intent);
                              finish();
            }
        });
        baridipaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                            Intent intent=new Intent(change.this,paypalbaridii.class);
                            startActivity(intent);
                            finish();
            }
        });
        khamsatpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                            Intent intent=new Intent(change.this,khamsatapostt.class);
                            startActivity(intent);
                            finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                SharedPreferences dhar=getSharedPreferences("users", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=dhar.edit();
                editor.remove("users");
                Intent intent=new  Intent(change.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

        contect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //moveTaskToBack(true);
        finishAffinity();
    }
}
