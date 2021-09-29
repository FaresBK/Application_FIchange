package com.fares.fi_echanger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class enrigstrer1 extends AppCompatActivity {
    TextView achateurdoll,dinar,cart,user,ccp,telephon,kimaa,kimaa1;
    String achat1,dinar1,cart1,nom1,prenom1,ccp1,tele,operation,opertion1;
    Double som;
    Button confermer,Retour;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrigstrer1);
        dialog=new Dialog(enrigstrer1.this);
        dialog.setContentView(R.layout.dialog);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dilogee));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations=R.style.animation;
        dialog.getWindow().setLayout(800,800);
        Button sortie=dialog.findViewById(R.id.buttonsortier);
        achateurdoll=(TextView)findViewById(R.id.achat);
        dinar=(TextView)findViewById(R.id.dinar);
        cart=(TextView) findViewById(R.id.cart);
        user=(TextView)findViewById(R.id.user);
        ccp=(TextView)findViewById(R.id.ccp);
        telephon=(TextView)findViewById(R.id.tele);
        kimaa=(TextView)findViewById(R.id.kima);
        kimaa1=(TextView)findViewById(R.id.kima1);
        achat1=getIntent().getStringExtra("achatt");
        dinar1=getIntent().getStringExtra("euro");
        cart1=getIntent().getStringExtra("email");
        nom1=getIntent().getStringExtra("nom");
        prenom1=getIntent().getStringExtra("prenom");
        ccp1=getIntent().getStringExtra("ccp");
        tele=getIntent().getStringExtra("telephone");
       operation=getIntent().getStringExtra("operation");
       opertion1=getIntent().getStringExtra("operation1");


        som=getIntent().getDoubleExtra("x",0);
        achateurdoll.setText(achat1);
        dinar.setText(dinar1);
        cart.setText(cart1);
        ccp.setText(ccp1);
        user.setText(nom1 +" "+prenom1);
        kimaa.setText(getIntent().getStringExtra("kima"));
        kimaa1.setText(getIntent().getStringExtra("kima1"));
        telephon.setText(tele);
      confermer=(Button)findViewById(R.id.conf);
      Retour=(Button)findViewById(R.id.Retour);
      confermer.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                       DatabaseReference mf = firebaseDatabase.getReference(operation);
                                       mf.setValue(String.valueOf(som));
                                       String Date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                                       String heur = sdf.format(new Date());
                                       FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance();
                                       DatabaseReference fr = firebaseDatabase1.getReference(opertion1);
                                       fr.push().setValue(opertion1 +" "+ achat1 +" CCP : "+ ccp1 +" Reçu Dinar "+ dinar1 + "  Date : "+ Date + " Time : "+heur +" Emial-pay : "+cart1 +" Nom : "+nom1+ " Preno : "+ prenom1 +" Telaphone : "+ tele);
                dialog.show();


          }
      });

     Retour.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             enrigstrer1.super.onBackPressed();
             finish();
         }
     });
     sortie.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent=new Intent(enrigstrer1.this,change.class);
             startActivity(intent);
             finish();
         }
     });


    }
}
